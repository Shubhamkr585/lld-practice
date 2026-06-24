import java.time.Duration;
import java.time.*;
import java.util.*;

enum SpotType{
    COMPACT,
    LARGE,
    BIKE
 }

 enum VehicleType{
     CAR,
     BIKE,
     TRUCK
 }

 class VehicleFactory{
    public static Vehicle createVehicle(VehicleType type,String number){
        switch (type){
            case BIKE:
                return new Bike(number);

            case CAR:
                return new Car(number);

            case TRUCK:
                return new Truck(number);

            default:
                throw new IllegalArgumentException("Invalid Vehicle Type");
        }
    }
 }

 // create spots

 class SpotFactory{
    public static ParkingSpot createSpot(SpotType type,int id){
        switch (type){
            case LARGE:
                return new LargeSpot(id);
            case COMPACT:
                return new CompactSpot(id);
            case BIKE:
                return new BikeSpot(id);
            default:
                throw new IllegalArgumentException("Unknown spot type");
        }
    }
 }

 abstract class Vehicle{
    protected String number;
    protected VehicleType type;

    public  Vehicle(String number,VehicleType type){
        this.number = number;
        this.type = type;
    }

    public VehicleType getType() {
        return type;
    }

    public String getNumber() {
        return number;
    }



 }

 class Car extends Vehicle{
    public Car(String number){
        super(number,VehicleType.CAR);
    }
 }

 class Bike extends Vehicle{
    public Bike(String number){
        super(number,VehicleType.BIKE);
    }
 }

 class Truck extends Vehicle{
    public Truck(String number){
        super(number,VehicleType.TRUCK);
    }
 }

 abstract class ParkingSpot{

    protected int id;
    protected SpotType type;
    protected boolean occupied;
    protected Vehicle parkedVehicle;

    public ParkingSpot(int id,SpotType type){
        this.id = id;
        this.type = type;
        this.occupied = false;
    }

    public boolean isOccupied(){
        return occupied;
    }

    public void parkVehicle(Vehicle vehicle){
        this.parkedVehicle = vehicle;
        this.occupied = true;
    }

    public void removeVehicle(){
        this.parkedVehicle = null;
        this.occupied = false;
    }

    public int getId(){
        return id;
    }

    public abstract boolean canFitVehicle(Vehicle vehicle);

 }

 class BikeSpot extends ParkingSpot{
    public BikeSpot(int id){
        super(id,SpotType.BIKE);
    }
     @Override
    public boolean canFitVehicle(Vehicle vehicle){
        if(vehicle.getType() == VehicleType.BIKE){
            return true;
        }
        return false;
    }
 }

 class LargeSpot extends ParkingSpot{

    public LargeSpot(int id){
        super(id,SpotType.LARGE);
    }
      @Override
    public boolean canFitVehicle(Vehicle vehicle){
        return true;
    }
 }

 class CompactSpot extends ParkingSpot{
    public CompactSpot(int id){
        super(id,SpotType.COMPACT);
    }

    @Override
    public boolean canFitVehicle(Vehicle vehicle){
        if(vehicle.getType() == VehicleType.CAR){
            return true;
        }
        return false;
    }
 }


 class Floor{
    int id;
    List<ParkingSpot> spots;

    public Floor(int id){
        this.id = id;
        this.spots = new ArrayList<>();
    }

    public void addSpot(ParkingSpot spot){
        spots.add(spot);
    }

    public List<ParkingSpot> getSpots(){
        return spots;
    }
 }


 class Ticket {

    private int id;
    private Vehicle vehicle;
    private ParkingSpot spot;
    private LocalDateTime entryTime;

    public Ticket(
            int id,
            Vehicle vehicle,
            ParkingSpot spot
    ) {

        this.id = id;
        this.vehicle = vehicle;
        this.spot = spot;

        this.entryTime = LocalDateTime.now();
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public ParkingSpot getSpot() {
        return spot;
    }
}

 interface PricingStrategy{
     double calculateFee(Ticket ticket);
 }

 class HourlyPricingStrategy implements PricingStrategy{
     @Override
     public double calculateFee(Ticket ticket){

         LocalDateTime entryTime = ticket.getEntryTime();
         LocalDateTime exitTime= LocalDateTime.now();

         long hours= Duration.between(entryTime,exitTime).toHours();

         if(hours==0){
             hours=1;
         }

         double fee = hours*20;
         return fee;
     }
 }


interface ParkingStrategy{
    ParkingSpot findSpot(Vehicle vehicle,List<Floor> floors);
}

class NearestParkingStrategy implements ParkingStrategy{
    @Override

    public ParkingSpot findSpot(Vehicle vehicle,List<Floor> floors){
        // iterate through each floor
        for(Floor floor:floors){
            // iterate through each available spots on that floor
            for(ParkingSpot spot:floor.getSpots()){
                if(!spot.occupied && spot.canFitVehicle(vehicle)){
                    return spot;
                }
            }
        }
        return null;
    }
}

class Receipt{
   private int receiptId;
   private double amount;

   public Receipt(int receiptId,double amount){
       this.receiptId = receiptId;
       this.amount = amount;
   }

   @Override
   public String toString(){
       return "Amount: "+amount;
   }
}

class EntryGate {

    private ParkingLot parkingLot;

    public EntryGate() {
        parkingLot = ParkingLot.getInstance();
    }

    public Ticket generateTicket(VehicleType type, String number) {

        return parkingLot.parkVehicle(type, number);
    }
}

class ExitGate {

    private ParkingLot parkingLot;

    public ExitGate() {
        parkingLot = ParkingLot.getInstance();
    }

    public Receipt processExit(Ticket ticket) {

        return parkingLot.unparkVehicle(ticket);
    }
}



 class ParkingLot {

    private static ParkingLot instance;

    private List<Floor> floors;

    private ParkingStrategy parkingStrategy;

    private PricingStrategy pricingStrategy;

    private ParkingLot() {

        floors = new ArrayList<>();
    }

    public static ParkingLot getInstance() {

        if(instance == null) {
            instance = new ParkingLot();
        }

        return instance;
    }

    public void setParkingStrategy(
            ParkingStrategy parkingStrategy
    ) {
        this.parkingStrategy = parkingStrategy;
    }

    public void setPricingStrategy(
            PricingStrategy pricingStrategy
    ) {
        this.pricingStrategy = pricingStrategy;
    }

    public void addFloor(Floor floor) {
        floors.add(floor);
    }

    public Ticket parkVehicle(
            VehicleType type,
            String number
    ) {

        Vehicle vehicle =
                VehicleFactory.createVehicle(type, number);

        ParkingSpot spot =
                parkingStrategy.findSpot(vehicle, floors);

        if(spot == null) {

            System.out.println("No spot available");

            return null;
        }

        spot.parkVehicle(vehicle);

        Ticket ticket =
                new Ticket(1, vehicle, spot);

        System.out.println(
                "Vehicle parked at spot: " + spot.getId()
        );

        return ticket;
    }

    public Receipt unparkVehicle(Ticket ticket) {

        double fee =
                pricingStrategy.calculateFee(ticket);

        ticket.getSpot().removeVehicle();

        return new Receipt(1, fee);
    }
}

public class ParkingLotSystem {

    public static void main(String[] args) {

        ParkingLot parkingLot =
                ParkingLot.getInstance();

        parkingLot.setParkingStrategy(
                new NearestParkingStrategy()
        );

        parkingLot.setPricingStrategy(
                new HourlyPricingStrategy()
        );

        Floor floor1 = new Floor(1);

        floor1.addSpot(
                SpotFactory.createSpot(
                        SpotType.COMPACT,
                        1
                )
        );

        floor1.addSpot(
                SpotFactory.createSpot(
                        SpotType.LARGE,
                        2
                )
        );

        floor1.addSpot(
                SpotFactory.createSpot(
                        SpotType.BIKE,
                        3
                )
        );

        parkingLot.addFloor(floor1);

        EntryGate entryGate =
                new EntryGate();

        Ticket ticket =
                entryGate.generateTicket(
                        VehicleType.CAR,
                        "JH01AB1234"
                );

        ExitGate exitGate =
                new ExitGate();

        Receipt receipt =
                exitGate.processExit(ticket);

        System.out.println(receipt);
    }
}