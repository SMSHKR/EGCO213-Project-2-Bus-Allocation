import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.lang.String;

class TicketCounter extends Thread {
    // Variable
    private File infile;
    private int checkpoint;
    private int maxSeat;
    private int transactionCount = 0;

    private ArrayList<BusLine> airport_bound = new ArrayList<>();
    private ArrayList<BusLine> city_bound = new ArrayList<>();

    // Constructor
    public TicketCounter(String name, File input, int check, int max) {
        super(name);
        infile = input;
        maxSeat = max;
        checkpoint = check;
    }

    // Method
    private void printAndCount(String nameOfTourGroup, String seat) {
        System.out.printf("%s >> Transaction %d : %10s(%2s) bus \n",
                Thread.currentThread().getName(), ++transactionCount, nameOfTourGroup, seat);
    }
    @Override
    public void run() {
        try (Scanner scan = new Scanner(infile)) {
            while (scan.hasNext()) {

                String input           = scan.nextLine();
                String [] buf          = input.split(",");
                String nameOfTourGroup = buf[1].trim();
                String seat            = buf[2].trim();
                String destination     = buf[3].trim();

                if ((destination.equals("A"))) {
                    printAndCount(nameOfTourGroup, seat);
                }
                else {
                    printAndCount(nameOfTourGroup, seat);
                }

            }
        } catch (FileNotFoundException e) { e.printStackTrace(); }
    }

}