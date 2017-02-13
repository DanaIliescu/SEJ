// LegInfo Class - created by Felix

package SEJ.ApplicationLayer;
import SEJ.ApplicationLayer.DataTypes.Leg;
import SEJ.DataAccessLayer.LegSQL;
import java.util.ArrayList;
import java.util.List;

public class LegInfo {
    private static List<Leg> legs;
    static Leg leg;

    // gets all legs from the Data Access Layer
    public static List<Leg> selectAllLegs() throws Exception
    {
        legs = LegSQL.loadAllLegs();
        return legs;
    }

    // sends leg to the Data Access Layer
    // adds it to the array
    public static void createLeg(String departureAirport, String arrivalAirport, String departureDate,
                                  String departureTime, String arrivalDate, String arrivalTime, int price) throws Exception {
        leg = new Leg(departureAirport, arrivalAirport, departureDate, departureTime, arrivalDate, arrivalTime, price);
        LegSQL.createLeg(leg);
        legs.add(new Leg(legs.size(), departureAirport, arrivalAirport, departureDate, departureTime, arrivalDate, arrivalTime, price));
    }

    //returns an array of legs that satisfy the search requirements
    public static List<Leg> createSearchedLegs(String departureCity, String arrivalCity, String departureDate) throws Exception
    {
        List<Leg> searchedLegs = new ArrayList<>();
        List<Leg> localLegs = selectAllLegs();

        for(int i = 0; i < localLegs.size(); i++)
        {
            //legs that fits search condition is added to a list
            String leg_departureCity = localLegs.get(i).getDepartureAirport().substring(0,3);
            String leg_arrivalCity = localLegs.get(i).getArrivalAirport().substring(0,3);
            String leg_departureDate = localLegs.get(i).getDepartureDate().toString();
            if(departureCity.equalsIgnoreCase(leg_departureCity) && arrivalCity.equalsIgnoreCase(leg_arrivalCity)
                    && departureDate.equalsIgnoreCase(leg_departureDate))
            {
                searchedLegs.add(localLegs.get(i));
            }
        }
        return searchedLegs;
    }

    // sends necessary information (leg id, class type) to the Data Access Layer
    // this information will be used for updating the legs table after a booking is done
    public static void updateSeat(int legId, int classType) throws Exception
    {
        LegSQL.updateSeatBookedState(legId, classType);
    }

    // returns ticket price for different classes - created by Lei
    public static int getPrice(Leg leg, String ticketClass)
    {
        int price = 0;
        if(ticketClass.equalsIgnoreCase("Business Class"))
        {
            price = (leg.getPrice() * 85) / 100;
        }
        if(ticketClass.equalsIgnoreCase("Economy Class"))
        {
            price = (leg.getPrice()* 70) / 100;
        }
        if(ticketClass.equalsIgnoreCase("first class"))
        {
            price = leg.getPrice();
        }
        return price;
    }

}
