import java.util.ArrayDeque;
import java.util.ArrayList;

class BusLine {

    // Variable
    private int maxSeat;
    private ArrayDeque<Bus> bus = new ArrayDeque<>();

    BusLine(int max) { maxSeat = max; }

    // Methods
    synchronized public String allocateBus(String nameOfTourGroup, int numberOfPassenger, String inputDestination) {

        String busNumber;

        Bus temp = Bus.createBus(bus, nameOfTourGroup, numberOfPassenger, maxSeat);
        if (temp != null) bus.add(temp);
        busNumber = inputDestination + (bus.size() - 1);

        return busNumber;

    }

}

class Bus {

    private ArrayList<String> nameOfTourGroup = new ArrayList<>();
    private int numberOfPassenger = 0;

    private Bus(String tourGroup, int passenger) {
        nameOfTourGroup.add(tourGroup);
        numberOfPassenger = passenger;
    }

    private void addPassenger(int passenger) { numberOfPassenger += passenger; }

    synchronized public static Bus createBus(ArrayDeque<Bus> bus, String tourGroup, int passenger, int maxSeat) {

        Bus newBus = null;

        if (bus.isEmpty() || bus.peek().numberOfPassenger + passenger > maxSeat)
            newBus = new Bus(tourGroup, passenger);
        else {
            bus.peek().addPassenger(passenger);
        }

        return newBus;

    }

}