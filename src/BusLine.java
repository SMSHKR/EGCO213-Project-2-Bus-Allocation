import java.util.ArrayDeque;
import java.util.Iterator;

class BusLine {

    // Variable
    private final String destination;
    private final int maxSeat;
    private ArrayDeque<Bus> BAD = new ArrayDeque<>();

    BusLine(String name, int max) {
        destination = name;
        maxSeat = max;
    }

    // Methods
    public int getAllocated() { return BAD.size(); }
    public void printSummary() {
        Iterator<Bus> walker = BAD.descendingIterator();
        while (walker.hasNext()) {
            walker.next().printTourGroupList();
            System.out.println();
        }
    }
    synchronized public void allocateBus(String nameOfTourGroup, int numberOfPassenger, int transactionCount) {
        Bus.createBus(BAD, nameOfTourGroup, destination, numberOfPassenger, maxSeat, transactionCount);
    }

}

class Bus {

    private ArrayDeque<Group> TourGroupAD = new ArrayDeque<>();
    private String busNumber;
    private int remainingSeat;

    private Bus(String destination, ArrayDeque<Bus> BAD, int maxSeat) {
        remainingSeat = maxSeat;
        busNumber = destination + (BAD.size());
    }

    private void addGroup(String tourGroup, int passenger, ArrayDeque<Bus> BAD, int transactionCount) {
        if (passenger <= 0) return;
        TourGroupAD.push(new Group(tourGroup, passenger));
        remainingSeat -= passenger;
        BAD.peek().print(transactionCount);
    }

    public void printTourGroupList() {
        System.out.printf("%s >> %-3s : ", Thread.currentThread().getName(), busNumber);
        int size = TourGroupAD.size();
        int count = 0;
        Iterator<Group> walker = TourGroupAD.descendingIterator();
        while (walker.hasNext()) {
            walker.next().print();
            if (++count < size) System.out.print(", ");
        }
    }

    private void print(int transactionCount) {
        System.out.printf("%s >> Transaction %2d : %-20s (%2s seats) bus %s\n",
                Thread.currentThread().getName(), transactionCount,
                TourGroupAD.peek().getNameOfTourGroup(), TourGroupAD.peek().getNumberOfPassenger(), busNumber);
    }

    public static void createBus(ArrayDeque<Bus> bus, String nameOfTourGroup, String destination, int numberOfPassenger, int maxSeat, int transactionCount) {

        if (bus.isEmpty()) bus.push(new Bus(destination, bus, maxSeat));

        if (bus.peek().remainingSeat > numberOfPassenger) bus.peek().addGroup(nameOfTourGroup, numberOfPassenger, bus, transactionCount);
        else {

            int remainingPassenger = numberOfPassenger;
            if (remainingPassenger > bus.peek().remainingSeat) {
                remainingPassenger -= bus.peek().remainingSeat;
                bus.peek().addGroup(nameOfTourGroup, bus.peek().remainingSeat, bus, transactionCount);
                bus.push(new Bus(destination, bus, maxSeat));
                while (remainingPassenger > maxSeat) {
                    remainingPassenger -= maxSeat;
                    bus.peek().addGroup(nameOfTourGroup, maxSeat, bus, transactionCount);
                    bus.push(new Bus(destination, bus, maxSeat));
                }
            }
            bus.peek().addGroup(nameOfTourGroup, remainingPassenger, bus, transactionCount);

        }

        // Obsolete brute-forcing attempt
        /*
        if (!bus.isEmpty()) {
            if (leftSeat < 0) {
                if (numberOfPassenger + leftSeat > 0) bus.peek().addGroup(nameOfTourGroup, numberOfPassenger + leftSeat, bus, transactionCount);
                bus.push(new Bus(nameOfTourGroup, -leftSeat, destination, bus));
                bus.peek().print(transactionCount);
                return maxSeat + leftSeat;
            }
            else bus.peek().addGroup(nameOfTourGroup, numberOfPassenger, bus, transactionCount);
        }
        else {
            if (leftSeat < 0) {
                bus.push(new Bus(nameOfTourGroup, maxSeat, destination, bus));
                bus.peek().print(transactionCount);
                bus.push(new Bus(nameOfTourGroup, numberOfPassenger - maxSeat, destination, bus));
                bus.peek().print(transactionCount);
                return (2 * maxSeat) - numberOfPassenger;
            }
            else {
                bus.push(new Bus(nameOfTourGroup, numberOfPassenger, destination, bus));
                bus.peek().print(transactionCount);
            }
        }

        return leftSeat;
        */
    }

}

class Group {

    private String nameOfTourGroup;
    private int numberOfPassenger;

    public Group(String groupName, int passengerNumber) {
        nameOfTourGroup = groupName;
        numberOfPassenger = passengerNumber;
    }

    public String getNameOfTourGroup() { return nameOfTourGroup; }
    public int getNumberOfPassenger() { return numberOfPassenger; }

    public void print() { System.out.printf("%-20s (%2s seats)", nameOfTourGroup, numberOfPassenger); }

}