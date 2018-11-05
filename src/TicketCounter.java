import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.lang.String;
import java.util.concurrent.CyclicBarrier;

class TicketCounter extends Thread {
    /*protected CyclicBarrier f;
     public void setBarrier(CyclicBarrier e){
       f=e;
     }*/
    // Variable
    private File infile;
    private BusLine airport_bound;
    private BusLine city_bound;
    private int i;

    // Constructor
    public TicketCounter(String name, File input, BusLine airport, BusLine city) {
        super(name);
        infile = input;
        airport_bound = airport;
        city_bound = city;
    }


    // Method

    @Override
    public void run() {
        try {
            Scanner scan = new Scanner(new File(String.valueOf(infile)));
            String s = scan.nextLine();

            for (i = 0; i < 10; i++) {


                String[] buf = s.split(",");
                //
                String ac = buf[3].trim();
                String m  = buf[2].trim();

                if ((ac.equals("A"))) {
                    System.out.printf("Here%s", Thread.currentThread().getName());
                    buf = airport_bound.allocateBus(buf, "A");
                    System.out.printf("%s>> Transaction %d %s(%s) bus %s", Thread.currentThread().getName(), i, buf[1].trim(), m, buf[4]);
                } else {
                    buf = city_bound.allocateBus(buf, "C");
                    System.out.printf("Here%s\n", Thread.currentThread().getName());
                    System.out.printf("%s>> Transaction %d %s(%s) bus %s", Thread.currentThread().getName(), i, buf[1].trim(), m, buf[4]);
                }
//f.await();
                String name = buf[1];
                System.out.printf(name);
                System.out.printf("%s>> Transaction %d %s(%s) bus %s", Thread.currentThread().getName(), i, buf[1].trim(), m, buf[4].trim());
                //


            }
        } catch (Exception e) {

        }
    }

}