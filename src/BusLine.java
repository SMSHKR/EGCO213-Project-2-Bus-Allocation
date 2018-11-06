import java.util.ArrayList;
import java.util.Scanner;

class BusLine {

    // Variable
    private String destination;
    private int maxSeat;
    private int emtrySeat,fullSeat;
    private int Seat;
    BusLine(int max)
    {
        this.maxSeat=max;
    }



    // Methods
    public String allocateBus(String seat, String des) {

        int numOfBus=0;
        destination = des;
        if(numOfBus!=0&&numOfBus!=maxSeat){//bus has empty
            fullSeat=maxSeat-Seat;
            bus.add(seat);
        }
        if(temp==maxSeat){//full bus
            bus.add(seat);
            n++;
        }
        if (0==maxSeat-st) {     //full bus

            bus.add(seat);
            destination = d + n;
            n++;
        }

        if (0>maxSeat-st) {//bus has empty

            if(0==full-st){//bus full

                bus.add(seat);
                n++;
                full=0;
            }
            if(0>full-st){  //empty
                full=full-st;
                bus.add(seat);//add seat
            }
            if(0<full-st){//over

                bus.add(seat);
                temp=st-full;
                n++;
            }
            // String e = Integer.toString(maxSeat);
            //seat[3] = e;
            bus.add(seat);
            full=maxSeat-st;
            temp=st-full;

        } if (0<maxSeat-st) {//over

            bus.add(seat);
            temp=st-full;
            n++;
        }
        return seat;


    }

}