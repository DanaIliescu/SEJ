// SeatInfo Class - created by Dana
// used to handle seats

package SEJ.ApplicationLayer;
import SEJ.ApplicationLayer.DataTypes.Leg;
import SEJ.ApplicationLayer.DataTypes.Plane;
import SEJ.ApplicationLayer.DataTypes.Schedule;
import SEJ.DataAccessLayer.ScheduleSQL;

import java.util.ArrayList;
import java.util.List;

public class SeatInfo {
    static int[] seats;
    static Plane searchedPlane = new Plane();

    public static int method(Leg leg, int ticketClass) throws Exception{
        List<Plane> planes = PlaneInfo.selectAllPlanes();

        // loops through the planes array, finds the plane corresponding to that leg
        for(int i = 0; i < planes.size(); i++)
            if(planes.get(i).getPlaneID() == ScheduleSQL.getSearchedPlane(leg.getLegID())) // the plane is found by executing an SQL query
                searchedPlane = planes.get(i);                                             // in the Data Access Layer
                                                                                           // check ScheduleSQL class for details
        int maximumSeats = searchedPlane.getFirstClassSeatTotal() + searchedPlane.getBusinessSeatTotal() + searchedPlane.getCoachSeatTotal();

        seats = new int[maximumSeats];   // the length of the array is set to how many seats the plane has in total

        for (int i = 0; i < seats.length; i++)
            seats[i] = 0;                // each slot of the array is instantiated with 0, meaning all seats are available

        return handleSeats(leg, ticketClass);
    }

    // calls the corresponding method to the ticket class
    // returns the seat number
    public static int handleSeats(Leg leg, int ticketClass){
        int seatNumber = 0;
        if (ticketClass == 1) {
            seatNumber = bookFirstClass(leg);
        }
        if (ticketClass == 2) {
            seatNumber = bookBusinessClass(leg);
        }
        if (ticketClass == 3) {
            seatNumber = bookEconomyClass(leg);
        }
        return seatNumber;
    }

    // if it is a first class
    // the counting is between how many first class tickets are already booked and the maximum amount of first class seats
    // when an available seat is found, it is set to 1, meaning it becomes booked
    // it returns index + 1 as seat number (taking into consideration that counting in an array starts from 0)
    // if there are no available seats, returns -1
    private static int bookFirstClass(Leg leg) {
        for (int i = leg.getFirstSeatBooked(); i < searchedPlane.getFirstClassSeatTotal(); i++) {
            if (seats[i] == 0) {
                seats[i] = 1;
                return i + 1;
            }
        }
        return -1;
    }

    // if it is a business class
    // the counting is between total first class + how many business seats are already booked
    //                                            and the first class seats + business class in total
    // it returns index + 1 as seat number (taking into consideration that counting in an array starts from 0)
    // if there are no available seats, returns -1
    private static int bookBusinessClass(Leg leg) {
        for (int i = searchedPlane.getFirstClassSeatTotal() + leg.getBusinessSeatBooked();
                    i < searchedPlane.getFirstClassSeatTotal() + searchedPlane.getBusinessSeatTotal(); i++) {
            if (seats[i] == 0) {
                seats[i] = 1;
                return i + 1;
            }
        }
        return -1;
    }

    // if it is an economy class
    // the counting is between total first class + total business class + how many economy seats are already booked
    //                                                  and the total number of seats in the plane
    // it returns index + 1 as seat number (taking into consideration that counting in an array starts from 0)
    // if there are no available seats, returns -1
    private static int bookEconomyClass(Leg leg) {
        for (int i = searchedPlane.getFirstClassSeatTotal() + searchedPlane.getBusinessSeatTotal() + leg.getCoachSeatBooked();
             i < seats.length; i++)
        {
            if (seats[i] == 0) {
                seats[i] = 1;
                return i + 1;
            }
        }
        return -1;
    }


}
