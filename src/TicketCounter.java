import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.lang.String;

class TicketCounter extends Thread {
    // Variable
    private File infile;
    private int checkpoint, maxSeat;

    private ArrayList<BusLine> airport_bound = new ArrayList<>();
    private ArrayList<BusLine> city_bound = new ArrayList<>();

    // Constructor
    public TicketCounter(String name, File input,int check,int max) {
        super(name);
        infile = input;
        maxSeat = max;
        checkpoint = check;
    }

    // Method
    @Override
    public void run() {
        try (Scanner scan = new Scanner(infile)) {
            while (scan.hasNext()) {
                int i = 1;
                String desNum;
                String s = scan.nextLine();
                String[] buf = s.split(",");
                String des = buf[3].trim();
                String seat  = buf[2].trim();

                if ((des.equals("A"))) {
                    System.out.printf("%s >> Transaction %d : %10s(%2s) bus \n",Thread.currentThread().getName(),i,buf[1].trim(),seat);
                    i++;
                } else {
                    desNum=city_bound.get(i-1).allocateBus(seat,des);
                    System.out.printf("%s >> Transaction %d %10s(%2s) bus \n", Thread.currentThread().getName(), i, buf[1].trim(),seat);
                    i++;
                }

                // if (i == checkpoint) f.await();
                String name = buf[1];
                //System.out.printf(name);
                //System.out.printf("%s>> Transaction %d %s(%s) bus %s", Thread.currentThread().getName(), i, buf[1].trim(), m, buf[3].trim());
            }
        } catch (Exception e) {

        }
    }

}