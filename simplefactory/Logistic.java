//package simplefactory;
//
//interface Vehicle {
//    void deliver();
//}
//
//class Truck implements Vehicle {
//    public void deliver() { System.out.println("Delivering by land in a Truck."); }
//}
//
//class Ship implements Vehicle {
//    public void deliver() { System.out.println("Delivering by sea in a Ship."); }
//}
//
//class Bike implements Vehicle {
//    public void deliver() { System.out.println("Delivering by road on a Bike."); }
//}
//
//class VehicleFactory {
//    // The creation logic is encapsulated here
//    public static Vehicle createVehicle(String type) {
//        if (type == null) return null;
//
//        return switch (type.toUpperCase()) {
//            case "TRUCK" -> new Truck();
//            case "SHIP"  -> new Ship();
//            case "BIKE"  -> new Bike();
//            default -> throw new IllegalArgumentException("Unknown vehicle type");
//        };
//    }
//}
//
//public class Logistic {
//    public static void main(String[] args) {
//        // We take input (e.g., from a config file or user)
//        String transportNeeded = "SHIP";
//
//        // No 'new Ship()' here!
//        Vehicle myVehicle = VehicleFactory.createVehicle(transportNeeded);
//
//        myVehicle.deliver();
//    }
//}