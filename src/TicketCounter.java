import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.lang.String;
import java.util.concurrent.CyclicBarrier;

class TicketCounter extends Thread {
    protected CyclicBarrier f;
     public void setBarrier(CyclicBarrier e){
       f=e;
     }
    // Variable
    private File infile;
    private ArrayList<BusLine> airport_bound = new ArrayList<>();
    private ArrayList<BusLine> city_bound = new ArrayList<>();
    private int checkpoint;

    // Constructor
    public TicketCounter(String name, File input, BusLine airport, BusLine city,int check) {
        super(name);
        infile = input;
        airport_bound.add(airport);
        city_bound.add(city);
        checkpoint=check;

    }


    // Method

    @Override
    public void run() {
        try {
            Scanner scan = new Scanner(infile);
            while (scan.hasNext()) {
                int i=1;
                String s = scan.nextLine();
                String[] buf = s.split(",");
                String ac = buf[3].trim();
                String m  = buf[2].trim();

                if ((ac.equals("A"))) {
                    
                    System.out.printf("%s>> Transaction %d %s(%s) bus %s\n",Thread.currentThread().getName(),i,buf[1].trim(),m,ac);
                    //System.out.printf("%s>> Transaction %d %s(%s) bus %s\n", Thread.currentThread().getName(), i, buf[1], m, buf[4]);
                    i++;
                } else {

                   // System.out.printf("%s>> Transaction %d %s(%s) bus %s\n", Thread.currentThread().getName(), i, buf[1], m, buf[4]);
                    i++;
                }

                if(i==checkpoint)
                {
                    f.await();
                }
                String name = buf[1];
                //System.out.printf(name);
                //System.out.printf("%s>> Transaction %d %s(%s) bus %s", Thread.currentThread().getName(), i, buf[1].trim(), m, buf[3].trim());
                scan.close();
                //


            }
        } catch (Exception e) {

        }
    }

}