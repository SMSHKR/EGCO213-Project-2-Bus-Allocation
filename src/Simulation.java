import java.io.File;
import java.util.Scanner;
import java.util.concurrent.CyclicBarrier;

class Simulation {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        System.out.print(Thread.currentThread().getName() + " >> Enter max seats  = ");
        int maxSeat = scan.nextInt();

        System.out.print(Thread.currentThread().getName() + " >> Enter checkpoint = ");
        int checkpoint = scan.nextInt();
        CyclicBarrier r = new CyclicBarrier(checkpoint);
        System.out.println();
        TicketCounter T1 = new TicketCounter("T1", new File("in/T1.txt"),maxSeat,checkpoint);
        TicketCounter T2 = new TicketCounter("T2", new File("in/T2.txt"),maxSeat,checkpoint);
        TicketCounter T3 = new TicketCounter("T3", new File("in/T3.txt"),maxSeat,checkpoint);
        T1.start();
        T2.start();
        T3.start();

        /*try {
            T1.join();
            T2.join();
            T3.join();
        } catch (InterruptedException e) {

        }*/

    }


}