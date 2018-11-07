import java.util.ArrayDeque;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

class BusLine {
    private CyclicBarrier barrier;

    // Variable
    private final String destination;
    private final int maxSeat;
    private int checkpoint;
    private int seat;



    private ArrayDeque<Bus> bus = new ArrayDeque<>();

    BusLine(String name, int max,int check,CyclicBarrier e) {
        destination = name;
        maxSeat = max;
        seat = maxSeat;
        checkpoint=check;
        barrier=e;
    }

    // Methods
    synchronized public void allocateBus(String nameOfTourGroup, int numberOfPassenger, int transactionCount) {
        seat -= numberOfPassenger;
        seat = Bus.createBus(bus, nameOfTourGroup, destination, numberOfPassenger, seat, transactionCount, maxSeat);
        if (seat < 0) seat = maxSeat;



    }

}

class Bus {

    private ArrayDeque<Group> TourGroupAD = new ArrayDeque<>();
    private String busNumber;

    public void printTourGroupList() {
        System.out.print(Thread.currentThread().getName() + " >> " + busNumber + " : ");
        for (Group tourGroup : TourGroupAD) {
            tourGroup.print();
            System.out.print(", ");
        }
    }

    private Bus(String tourGroup, int passenger, String destination, ArrayDeque<Bus> BAD) {
        TourGroupAD.push(new Group(tourGroup, passenger));
        busNumber = destination + (BAD.size());
    }

    private void addGroup(String tourGroup, int passenger, ArrayDeque<Bus> BAD, int transactionCount) {
        TourGroupAD.push(new Group(tourGroup, passenger));
        BAD.peek().print(transactionCount);
    }

    private void print(int transactionCount) {
        System.out.printf("%s >> Transaction %2d : %-20s (%2s seats) bus %s\n",
                Thread.currentThread().getName(), transactionCount,
                TourGroupAD.peek().getNameOfTourGroup(), TourGroupAD.peek().getNumberOfPassenger(), busNumber);
    }

    public static int createBus(ArrayDeque<Bus> bus, String nameOfTourGroup, String destination, int numberOfPassenger, int leftSeat, int transactionCount, int maxSeat) {

        if (!bus.isEmpty()) {
            if (leftSeat < 0) {
                if (numberOfPassenger + leftSeat > 0) bus.peek().addGroup(nameOfTourGroup, numberOfPassenger + leftSeat, bus, transactionCount);
                bus.push(new Bus(nameOfTourGroup, -leftSeat, destination, bus));
                bus.peek().print(transactionCount);
                if (numberOfPassenger + leftSeat == 0) return maxSeat + leftSeat;
            }
            else bus.peek().addGroup(nameOfTourGroup, numberOfPassenger, bus, transactionCount);
        }
        else {
            bus.push(new Bus(nameOfTourGroup, numberOfPassenger, destination, bus));
            bus.peek().print(transactionCount);
        }

        return leftSeat;

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