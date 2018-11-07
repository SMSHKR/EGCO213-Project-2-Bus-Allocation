import java.io.File;
import java.util.Scanner;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

class Simulation {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        System.out.print(Thread.currentThread().getName() + " >> Enter max seats  = ");
        int maxSeat = scan.nextInt();
        int transactionCount=0;
        System.out.print(Thread.currentThread().getName() + " >> Enter checkpoint = ");
        int checkpoint = scan.nextInt();
        CyclicBarrier barrier = new CyclicBarrier(3);
        BusLine airport_bound = new BusLine("A", maxSeat,checkpoint,barrier);
        BusLine city_bound    = new BusLine("C", maxSeat,checkpoint,barrier);



        TicketCounter T1 = new TicketCounter("T1", new File("in/T1.txt"), maxSeat, checkpoint, airport_bound, city_bound);
        TicketCounter T2 = new TicketCounter("T2", new File("in/T2.txt"), maxSeat, checkpoint, airport_bound, city_bound);
        TicketCounter T3 = new TicketCounter("T3", new File("in/T3.txt"), maxSeat, checkpoint, airport_bound, city_bound);

        System.out.println();

        T1.start();
        T2.start();
        T3.start();
        if( transactionCount==checkpoint){
            try{
                barrier.await();
                if(Thread.currentThread().getName()=="T3"){
                    System.out.printf("%s>> %d airport-bound buses have been allocated",Thread.currentThread().getName(),bus.size());
                    System.out.printf("%s>> %d city-bound buses have been allocated",Thread.currentThread().getName(),bus.size());
                }
            }catch (InterruptedException e){
                System.out.println("inter");
            }
            catch (BrokenBarrierException e){
                System.out.println("broke");
            }
        }
    }


}