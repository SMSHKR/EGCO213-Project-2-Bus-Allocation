import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.CyclicBarrier;

class Simulation {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        System.out.print(Thread.currentThread().getName() + " >> Enter max seats  = ");
        int maxSeat = scan.nextInt();

        System.out.print(Thread.currentThread().getName() + " >> Enter checkpoint = ");
        int checkpoint = scan.nextInt();

        ArrayList<BusLine> BusLineArrayList = new ArrayList<>();
        BusLineArrayList.add(new BusLine("A", maxSeat));
        BusLineArrayList.add(new BusLine("C", maxSeat));

        CyclicBarrier barrier = new CyclicBarrier(3 + 1);

        TicketCounter T1 = new TicketCounter("T1", new File("in/T1.txt"), checkpoint, BusLineArrayList,barrier);
        TicketCounter T2 = new TicketCounter("T2", new File("in/T2.txt"), checkpoint, BusLineArrayList,barrier);
        TicketCounter T3 = new TicketCounter("T3", new File("in/T3.txt"), checkpoint, BusLineArrayList,barrier);

        System.out.println();

        T1.start();
        T2.start();
        T3.start();

        while (barrier.getNumberWaiting() < barrier.getParties() - 1)
            try { Thread.sleep(100); }
            catch (InterruptedException e) {  }

        System.out.println();
        System.out.printf("%s >> %d airport-bound buses have been allocated\n", Thread.currentThread().getName(), BusLineArrayList.get(0).getAllocated());
        System.out.printf("%s >> %d city-bound buses have been allocated\n", Thread.currentThread().getName(), BusLineArrayList.get(1).getAllocated());
        System.out.println();

        try { barrier.await(); }
        catch (Exception e) {  }

        try { T1.join(); T2.join(); T3.join(); }
        catch (InterruptedException e) {  }

        System.out.println();
        System.out.println(Thread.currentThread().getName() + " >> ===== Airport Bound =====");
        BusLineArrayList.get(0).printSummary();

        System.out.println();
        System.out.println(Thread.currentThread().getName() + " >> ===== City Bound =====");
        BusLineArrayList.get(1).printSummary();

    }

}