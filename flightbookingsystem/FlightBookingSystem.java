package flightbookingsystem;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Predicate;
import java.util.stream.Collectors;

enum SeatStatus{
    AVAILABLE,
    BOOKED
}
enum BookingStatus{
    CONFIRMED,
    CANCELLED
}

// passenger ka system se koi interaction nahi, bas booking ke saath data jaata h

class Passenger{
    final String id,name;
    Passenger(String id,String name){
        this.id = id;
        this.name=name;
    }
    @Override
    public String toString(){
        return "Passenger "+name;
    }
}

// seat hi concurrency ki unit h, lock isi pe lagta h
class Seat{
    private final String id;
    private volatile SeatStatus status=SeatStatus.AVAILABLE;
    private final ReentrantLock lock=new ReentrantLock();
    Seat(String id){
        this.id = id;
    }

    public String getId() {return id;}
    boolean isAvailable(){return status.equals(SeatStatus.AVAILABLE);}

    void lock(){
        lock.lock();
    }
    void unlock(){
        lock.unlock();
    }
    // status badalne se pehle lock hona zaruri h, warna exception
    void markBooked(){
        requireLock();status=SeatStatus.BOOKED;
    }
    void release(){
        requireLock();status=SeatStatus.AVAILABLE;
    }
    private void requireLock(){
        if(!lock.isHeldByCurrentThread()) throw new IllegalMonitorStateException();
    }
}

// ek flight = ek date, isliye har date ki seats alag hain
class Flight{
    private final String id,source,destination;
    private final LocalDate date;
    private final Map<String,Seat> seats=new LinkedHashMap<>();

    Flight(String id,String source,String destination,LocalDate date,int rows,int cols){
        this.id = id;
        this.source = source;
        this.destination = destination;
        this.date = date;
        for (int r = 1; r <= rows; r++)
            for (int c = 0; c < cols; c++) { String sid = r + "" + (char) ('A' + c); seats.put(sid, new Seat(sid)); }
    }
    String getId() { return id; }
    String getSource() { return source; }
    String getDestination() { return destination; }
    LocalDate getDate() { return date; }
    Collection<Seat> getSeats() { return seats.values(); }
    Seat getSeat(String sid) {
        Seat s = seats.get(sid);
        if (s == null) throw new NoSuchElementException("No such seat " + sid);
        return s;
    }
    public String toString(){
        return id + " " + source + "-->" + destination + " " + date.toString();
    }
}

class Ticket {
    private final Seat seat;
    private final Passenger passenger;
    Ticket(Seat seat, Passenger passenger) { this.seat = seat; this.passenger = passenger; }
    Seat getSeat() { return seat; }
    public String toString() { return seat.getId() + "=" + passenger; }
}

class Booking {
    private final String id;
    private final List<Ticket> tickets;
    private volatile BookingStatus status = BookingStatus.CONFIRMED;
    Booking(String id, List<Ticket> tickets) { this.id = id; this.tickets = tickets; }
    String getId() { return id; }
    List<Ticket> getTickets() { return tickets; }
    boolean isCancelled() { return status == BookingStatus.CANCELLED; }
    void markCancelled() { status = BookingStatus.CANCELLED; }
    public String toString() { return id + " " + status + " " + tickets; }
}

class SearchCriteria {
    private final String source, destination; private final LocalDate date;
    SearchCriteria(String source, String destination, LocalDate date) {
        this.source = source; this.destination = destination; this.date = date;
    }
    String getSource() { return source; }
    String getDestination() { return destination; }
    LocalDate getDate() { return date; }
}

// search: har criteria ka alag predicate, sab pe allMatch (AND)
class FlightSearchService {
    private final Collection<Flight> flights;
    FlightSearchService(Collection<Flight> flights) { this.flights = flights; }

    List<Flight> search(SearchCriteria c) {
        List<Predicate<Flight>> filters = new ArrayList<>();
        filters.add(f -> f.getSource().equals(c.getSource()));
        filters.add(f -> f.getDestination().equals(c.getDestination()));
        filters.add(f -> f.getDate().equals(c.getDate()));
        // naya filter (airline, price...) = bas ek line aur yahan
        return flights.stream()
                .filter(f -> filters.stream().allMatch(p -> p.test(f)))
                .collect(Collectors.toList());
    }
}

// booking karna h
class BookingService{
    private final Map<String,Flight> flights=new ConcurrentHashMap<>();
    private final Map<String,Booking> bookings=new ConcurrentHashMap<>();

    private final AtomicLong seq=new AtomicLong(1);

    BookingService(Collection<Flight>all){
        for(Flight f:all){
            flights.put(f.getId(), f);
        }
    }

    // bina lock ke, bas dikhane ke liye. asli faisla book() mein hota h
    List<String> getAvailableSeats(String flightId){
        return flights.get(flightId).getSeats().stream()
                .filter(Seat::isAvailable).map(Seat::getId)
                .collect(Collectors.toList());
    }

    Booking book(String flightId,Map<String,Passenger>seatToPassenger){
        Flight f=flights.get(flightId);

        // S: seat ids sort karke lo, sabka lock order same rahega => deadlock nahi
        List<Seat>seats=new ArrayList<>();
        for(String sid : new TreeSet<>(seatToPassenger.keySet())){
            seats.add(f.getSeat(sid));
        }

        List<Seat>locked=new ArrayList<>();
        try {
            // L: saari seats lock karo
            for (Seat seat : seats) {
                seat.lock();
                locked.add(seat);
            }
            // V: pehle sab check karo, ek bhi booked h to poori booking fail
            for (Seat s : seats) {
                if (!s.isAvailable())
                    throw new IllegalStateException("Seat not available: " + s.getId());
            }

            // M: ab mutate karo, har passenger ka ticket banao
            List<Ticket> tickets=new ArrayList<>();
            for (Seat seat : seats) {
                seat.markBooked();
                tickets.add(new Ticket(seat,seatToPassenger.get(seat.getId())));
            }
            Booking b=new Booking("BK--" + seq.getAndIncrement(),tickets);
            bookings.put(b.getId(), b);
            return b;
        }
        finally {
            // U: unlock hamesha finally mein
            for(Seat seat : locked){
                seat.unlock();
            }
        }
    }

    // booking cancel
    void cancel(String bookingId) {
        Booking b = bookings.get(bookingId);
        List<Seat> seats = b.getTickets().stream().map(Ticket::getSeat)
                .sorted(Comparator.comparing(Seat::getId)).collect(Collectors.toList());   // lock order book() jaisa hi
        List<Seat> locked = new ArrayList<>();
        try {
            for (Seat s : seats) { s.lock(); locked.add(s); }
            if (b.isCancelled()) return;     // dobara cancel safe h, lock ke andar check
            seats.forEach(Seat::release);
            b.markCancelled();
        } finally {
            for (Seat s : locked) s.unlock();
        }
    }
}


public class FlightBookingSystem{
    public static void main(String[] args) throws Exception {
        LocalDate d = LocalDate.of(2026, 10, 12);
        List<Flight> flights = Arrays.asList(new Flight("F1", "DEL", "BOM", d, 5, 4),
                new Flight("F2", "DEL", "BOM", d, 5, 4),
                new Flight("F3", "BOM", "DEL", d, 5, 4));
        BookingService service = new BookingService(flights);

        // 50 threads ek hi seat ke liye ladenge
        ExecutorService pool = Executors.newFixedThreadPool(50);
        CountDownLatch go = new CountDownLatch(1);     // starting gun, sab ek saath chhutenge
        AtomicInteger ok = new AtomicInteger(), fail = new AtomicInteger();
        List<Future<?>> fs = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            final int n = i;
            fs.add(pool.submit(() -> {
                try {
                    go.await();
                    service.book("F2", Collections.singletonMap("1A", new Passenger("U" + n, "User" + n)));
                    ok.incrementAndGet();
                } catch (IllegalStateException e) { fail.incrementAndGet(); }
                catch (InterruptedException ignored) { }
            }));
        }
        go.countDown();
        for (Future<?> f : fs) f.get();
        pool.shutdown();
        System.out.println("50 threads, same seat -> success=" + ok + ", rejected=" + fail);

    }
}