import java.util.ArrayDeque;
import java.util.ArrayList;

class BusLine {

    // Variable
    private final int maxSeat;
    private int Seat;
    private ArrayDeque<Bus> bus = new ArrayDeque<>();

    BusLine(int max) {
        maxSeat = max;
    }


    // Methods

    synchronized private void printAndCount(String nameOfTourGroup, int numberOfPassenger, String busNumber) {
        System.out.printf("%s >> Transaction %2d : %-20s (%2s seats) bus %s\n",
                Thread.currentThread().getName(), ++transactionCount, nameOfTourGroup, numberOfPassenger, busNumber);
    }

    synchronized public void allocateBus(String nameOfTourGroup, int numberOfPassenger, String inputDestination, int transactioncount) {
        Seat = maxSeat;

        do {
            Seat -= numberOfPassenger;
            if (Seat > 0) {
                Bus temp = Bus.createBus(bus, nameOfTourGroup, numberOfPassenger, maxSeat);
                if (temp != null)
                    bus.add(temp);
            } else if (Seat < 0) {
                Bus temp = Bus.createBus(bus, nameOfTourGroup, -Seat, maxSeat);
                if (temp != null)
                    bus.add(temp);
            }

            //printAndCount();
        } while (Seat > 0);
    }

}

class Bus {

    private ArrayList<String> nameOfTourGroup = new ArrayList<>();
    private int numberOfPassenger = 0;

    private void addPassenger(int passenger) {
        numberOfPassenger += passenger;
    }

    private Bus(String tourGroup, int passenger) {
        nameOfTourGroup.add(tourGroup);
        numberOfPassenger = passenger;
    }

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

class Group {
    private ArrayList<Integer> numberOfTourGroup = new ArrayList<>();


}