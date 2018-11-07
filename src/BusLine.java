import java.util.ArrayDeque;

class BusLine {

    // Variable
    private final String destination;
    private final int maxSeat;
    private int seat;
    private ArrayDeque<Bus> bus = new ArrayDeque<>();

    BusLine(String name, int max) {
        destination = name;
        maxSeat = max;
    }

    // Methods
    synchronized public void allocateBus(String nameOfTourGroup, int numberOfPassenger, int transactionCount) {
        seat = maxSeat - numberOfPassenger;
        Bus.createBus(bus, nameOfTourGroup, destination, numberOfPassenger, seat, transactionCount);
    }

}

class Bus {

    private ArrayDeque<Group> TourGroupAL = new ArrayDeque<>();
    private String busNumber;

    private Bus(String tourGroup, int passenger) {
        TourGroupAL.add(new Group(tourGroup, passenger));
    }

    private void addGroup(String tourGroup, int passenger) {
        TourGroupAL.add(new Group(tourGroup, passenger));
    }

    private void print(int transactionCount) {
        System.out.printf("%s >> Transaction %2d : %-20s (%2s seats) bus %s\n",
                Thread.currentThread().getName(), transactionCount,
                TourGroupAL.peek().getNameOfTourGroup(), TourGroupAL.peek().getNumberOfPassenger(), busNumber);
    }

    private void setBusNumber(String destination ,ArrayDeque<Bus> BAD) { busNumber = destination + (BAD.size() - 1); }

    public static void createBus(ArrayDeque<Bus> bus, String nameOfTourGroup, String destination, int numberOfPassenger, int leftSeat, int transactionCount) {

        if (!bus.isEmpty() && leftSeat <= 0) {
            bus.peek().addGroup(nameOfTourGroup, numberOfPassenger + leftSeat);
            bus.peek().print(transactionCount);
            bus.add(new Bus(nameOfTourGroup, leftSeat));
        }
        else bus.add(new Bus(nameOfTourGroup, numberOfPassenger));

        bus.peek().setBusNumber(destination, bus);
        bus.peek().print(transactionCount);

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

}