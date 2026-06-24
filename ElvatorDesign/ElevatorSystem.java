import java.util.*;
import java.util.concurrent.*;

// 1. Core Enums
enum Direction { UP, DOWN, IDLE }
enum State { MOVING, STOPPED }

// 2. Request Object (Command Pattern)
class Request {
    int floor;
    Direction direction; // Needed for external calls

    public Request(int floor, Direction direction) {
        this.floor = floor;
        this.direction = direction;
    }
}

// 3. The Elevator Car (The State & Data)
class ElevatorCar {
    private final int id;
    private int currentFloor = 0;
    private Direction direction = Direction.IDLE;

    // Thread-safe sets for SCAN algorithm
    // Using TreeSets to keep floors sorted
    private final TreeSet<Integer> upRequests = new TreeSet<>();
    private final TreeSet<Integer> downRequests = new TreeSet<>(Collections.reverseOrder());

    public ElevatorCar(int id) { this.id = id; }

    // SYNCHRONIZED: Ensuring thread safety when adding requests
    public synchronized void addRequest(Request req) {
        if (req.floor > currentFloor) {
            upRequests.add(req.floor);
        } else if (req.floor < currentFloor) {
            downRequests.add(req.floor);
        } else {
            System.out.println("Elevator " + id + " already at floor " + req.floor);
            return;
        }

        if (direction == Direction.IDLE) {
            direction = (req.floor > currentFloor) ? Direction.UP : Direction.DOWN;
        }
    }

    public synchronized void process() {
        while (!upRequests.isEmpty() || !downRequests.isEmpty()) {
            if (direction == Direction.UP) {
                processRequests(upRequests);
                direction = downRequests.isEmpty() ? Direction.IDLE : Direction.DOWN;
            } else if (direction == Direction.DOWN) {
                processRequests(downRequests);
                direction = upRequests.isEmpty() ? Direction.IDLE : Direction.UP;
            }
        }
    }

    private void processRequests(TreeSet<Integer> requests) {
        Iterator<Integer> iterator = requests.iterator();
        while (iterator.hasNext()) {
            int targetFloor = iterator.next();
            System.out.println("Elevator " + id + " moving from " + currentFloor + " to " + targetFloor);
            currentFloor = targetFloor;
            System.out.println("Elevator " + id + " stopped at floor " + currentFloor + ". Opening Doors...");
            iterator.remove();
            // Simulate time taken to stop/open doors
            try { Thread.sleep(500); } catch (InterruptedException e) {}
        }
    }

    public int getCurrentFloor() { return currentFloor; }
    public Direction getDirection() { return direction; }
    public int getId() { return id; }
}

// 4. Dispatcher (Strategy Pattern)
class Dispatcher {
    private final List<ElevatorCar> elevators;

    public Dispatcher(List<ElevatorCar> elevators) { this.elevators = elevators; }

    // Strategy: Assign to the nearest elevator moving in the same direction or an IDLE one
    public void dispatch(Request req) {
        ElevatorCar bestCar = null;
        int minDistance = Integer.MAX_VALUE;

        for (ElevatorCar car : elevators) {
            int distance = Math.abs(car.getCurrentFloor() - req.floor);
            if (isEligible(car, req) && distance < minDistance) {
                minDistance = distance;
                bestCar = car;
            }
        }

        if (bestCar == null) bestCar = elevators.get(0); // Default fallback
        System.out.println("Assigning floor " + req.floor + " to Elevator " + bestCar.getId());
        bestCar.addRequest(req);
    }

    private boolean isEligible(ElevatorCar car, Request req) {
        if (car.getDirection() == Direction.IDLE) return true;
        if (car.getDirection() == Direction.UP && req.floor >= car.getCurrentFloor() && req.direction == Direction.UP) return true;
        if (car.getDirection() == Direction.DOWN && req.floor <= car.getCurrentFloor() && req.direction == Direction.DOWN) return true;
        return false;
    }
}

// 5. Elevator System (Facade & Singleton)
public class ElevatorSystem {
    private static ElevatorSystem instance;
    private final Dispatcher dispatcher;
    private final List<ElevatorCar> elevators;

    private ElevatorSystem(int numCars) {
        elevators = new ArrayList<>();
        for (int i = 0; i < numCars; i++) elevators.add(new ElevatorCar(i));
        dispatcher = new Dispatcher(elevators);
    }

    public static synchronized ElevatorSystem getInstance(int numCars) {
        if (instance == null) instance = new ElevatorSystem(numCars);
        return instance;
    }

    public void externalRequest(int floor, Direction direction) {
        dispatcher.dispatch(new Request(floor, direction));
    }

    public void run() {
        for (ElevatorCar car : elevators) {
            new Thread(car::process).start();
        }
    }

    public static void main(String[] args) {
        ElevatorSystem system = ElevatorSystem.getInstance(2);
        system.run();

        // Simulate people pressing buttons
        system.externalRequest(5, Direction.UP);
        system.externalRequest(2, Direction.DOWN);
        system.externalRequest(9, Direction.UP);
    }
}