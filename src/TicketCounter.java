import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.lang.String;
import java.util.concurrent.CyclicBarrier;

class TicketCounter extends Thread {
    // Variable
    private int checkpoint;
    private int transactionCount = 0;
    private File infile;
    private ArrayList<BusLine> BusLineArrayList;
    private CyclicBarrier barrier;

    // Constructor
    public TicketCounter(String name, File input, int cp, ArrayList<BusLine> busLines, CyclicBarrier cb) {
        super(name);
        infile = input;
        checkpoint = cp;
        BusLineArrayList = busLines;
        barrier = cb;
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

                if (transactionCount == checkpoint) {
                    try { barrier.await(); }
                    catch (Exception e) {  }
                }

                if ((destination.equals("A")))
                     BusLineArrayList.get(0).allocateBus(nameOfTourGroup, numberOfPassenger, ++transactionCount);
                else BusLineArrayList.get(1).allocateBus(nameOfTourGroup, numberOfPassenger, ++transactionCount);

            }
        } catch (FileNotFoundException e) { e.printStackTrace(); }
    }

}