import java.util.ArrayList;
import java.util.Scanner;

class BusLine {

    // Variable
    private String destination;
    private int maxSeat;
    private int full;
    private String[] seats;
    BusLine(int max)
    {
        this.maxSeat=max;
    }
    public void add(String[] seat)
    {
        seats = seat;
    }


    // Methods
    public String[] allocateBus(String[] seat, String d,BusLine bus) {

        int st = Integer.parseInt(seat[3].trim());
        int n = 0;
        destination = d;
        seat[4] = d + n;

        st += full;
        if (maxSeat == st) {

            bus.add(seat);
            destination = d + n;
            n++;
        }
        if (maxSeat < st) {
            String e = Integer.toString(maxSeat);
            seat[3] = e;
            bus.add(seat);
            full = st - maxSeat;
            destination = d + n;
            n++;
        } else {

            bus.add(seat);
        }
        return seat;


    }

}