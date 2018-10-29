import java.util.ArrayList;

class TicketCounter extends Thread {

    // Variable
    private String infile;
    private BusLine airport_bound;
    private BusLine city_bound;

    // Constructor
    public TicketCounter(String name, String input, BusLine airport, BusLine city) {
        super(name);
        infile = input;
        airport_bound = airport;
        city_bound = city;
    }

    // Method
    @Override
    public void run() {

    }

}