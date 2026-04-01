import java.util.*;

public class ParkingLot {

    HashMap<String, Integer> parking = new HashMap<>();
    int nextSpot = 1;

    public void parkVehicle(String plate) {

        parking.put(plate, nextSpot);

        System.out.println(plate + " parked at spot " + nextSpot);

        nextSpot++;
    }

    public void exitVehicle(String plate) {

        if (parking.containsKey(plate)) {

            System.out.println(plate + " exited from spot " +
                    parking.get(plate));

            parking.remove(plate);
        } else {
            System.out.println("Vehicle not found");
        }
    }

    public static void main(String[] args) {

        ParkingLot lot = new ParkingLot();

        lot.parkVehicle("ABC123");
        lot.parkVehicle("XYZ999");

        lot.exitVehicle("ABC123");
    }
}