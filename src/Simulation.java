import java.util.Scanner;

class Simulation {

    public static void main(String [] args) {

        Scanner scan = new Scanner(System.in);

        System.out.print(Thread.currentThread().getName() + " >> Enter max seats  = ");
        int maxSeat = scan.nextInt();

        System.out.print(Thread.currentThread().getName() + " >> Enter checkpoint = ");
        int checkpoint = scan.nextInt();

        System.out.println();



    }

}