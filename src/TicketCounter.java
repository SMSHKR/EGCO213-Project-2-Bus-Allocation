import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.lang.String;

class TicketCounter extends Thread {
    // Variable
    private final int maxSeat;
    private int checkpoint;
    private int transactionCount = 0;
    private File infile;
    private ArrayList<BusLine> BLAL;

    // Constructor
    public TicketCounter(String name, File input, int cp, int max, ArrayList<BusLine> busLines) {
        super(name);
        infile = input;
        maxSeat = max;
        checkpoint = cp;
        BLAL = busLines;
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
                     BLAL.get(0).allocateBus(nameOfTourGroup, numberOfPassenger, ++transactionCount);
                else BLAL.get(1).allocateBus(nameOfTourGroup, numberOfPassenger, ++transactionCount);
            }
        } catch (FileNotFoundException e) { e.printStackTrace(); }
    }

}