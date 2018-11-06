import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.lang.String;

class TicketCounter extends Thread {
    // Variable
    private File infile;
    private int checkpoint;
    private int maxSeat;
    private int transactionCount = 0;

    private BusLine airport_bound;
    private BusLine city_bound;

    // Constructor
    public TicketCounter(String name, File input, int check, int max, BusLine airport, BusLine city) {
        super(name);
        infile = input;
        maxSeat = max;
        checkpoint = check;
        airport_bound = airport;
        city_bound = city;
    }

    // Method
    synchronized private void printAndCount(String nameOfTourGroup, int numberOfPassenger, String busNumber) {
        System.out.printf("%s >> Transaction %2d : %-20s (%2s seats) bus %s\n",
                Thread.currentThread().getName(), ++transactionCount, nameOfTourGroup, numberOfPassenger, busNumber);
    }
    @Override
    public void run() {
        try (Scanner scan = new Scanner(infile)) {
            while (scan.hasNext()) {

                String input             = scan.nextLine();
                String [] buf            = input.split(",");
                String nameOfTourGroup   = buf[1].trim();
                int    numberOfPassenger = Integer.parseInt(buf[2].trim());
                String destination       = buf[3].trim();

                String busNumber;
                if ((destination.equals("A"))) busNumber = airport_bound.allocateBus(nameOfTourGroup, numberOfPassenger, destination);
                else busNumber = city_bound.allocateBus(nameOfTourGroup, numberOfPassenger, destination);
                printAndCount(nameOfTourGroup, numberOfPassenger, busNumber);

            }
        } catch (FileNotFoundException e) { e.printStackTrace(); }
    }

}