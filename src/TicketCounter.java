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

    @Override
    public void run() {
        try (Scanner scan = new Scanner(infile)) {

            while (scan.hasNext()) {
                    String input = scan.nextLine();
                    String[] buf = input.split(",");
                    String nameOfTourGroup = buf[1].trim();
                    int numberOfPassenger = Integer.parseInt(buf[2].trim());
                    String destination = buf[3].trim();

                    if ((destination.equals("A")))
                         airport_bound.allocateBus(nameOfTourGroup, numberOfPassenger, ++transactionCount);
                    else city_bound.allocateBus(nameOfTourGroup, numberOfPassenger, ++transactionCount);



            }
        } catch (FileNotFoundException e) { e.printStackTrace(); }
    }

}