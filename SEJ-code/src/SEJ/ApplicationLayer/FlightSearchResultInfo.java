// FlightSearchResultInfo Class - Created by Lei

package SEJ.ApplicationLayer;
import SEJ.ApplicationLayer.DataTypes.FlightSearchResult;
import SEJ.ApplicationLayer.DataTypes.Leg;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.List;

public class FlightSearchResultInfo
{
    static Leg selectedLeg;

    // gets all legs that satisfy the search condition
    private static List<Leg> getAllSearchedLegs(String departureCity, String arrivalCity, String departureDate) throws Exception
    {
        List<Leg> allLSearchedLegs = LegInfo.createSearchedLegs(departureCity, arrivalCity, departureDate);
        return allLSearchedLegs;
    }

    // creates Observable List with FlightSearchResult objects
    // takes information from each leg and creates a FlightSearchResult object (that has StringProperties as fields)
    // returns the allFlightResults Observable List that will be used in the TableView
    public static ObservableList<FlightSearchResult> createFlightSearchResultsProperty(String departureCity,
                                                                                       String arrivalCity,
                                                                                       String departureDate) throws Exception
    {
        List<Leg> allLSearchedLegs = getAllSearchedLegs(departureCity, arrivalCity, departureDate);

        ObservableList<FlightSearchResult> allFlightResults = FXCollections.observableArrayList();

        for(Leg leg: allLSearchedLegs)
        {
            String dep_city = leg.getDepartureAirport();
            String arr_city = leg.getArrivalAirport();
            String dep_Date = leg.getDepartureDate().toString();
            String dep_Time = leg.getDepartureTime().toString();
            String arr_date = leg.getArrivalDate().toString();
            String arr_Time = leg.getArrivalTime().toString();
            FlightSearchResult flightSearchResult = new FlightSearchResult(dep_city, arr_city, dep_Date, dep_Time,
                    arr_date, arr_Time);
            allFlightResults.add(flightSearchResult);
        }
        return allFlightResults;
    }

    // finds the selected leg when the user clicks on one row in the TableView
    public static Leg findSelectedLeg(FlightSearchResult selectedFlight) throws Exception
    {
        List<Leg> allLegs = LegInfo.selectAllLegs();
        for (int i = 0; i < allLegs.size(); i++){
            if(selectedFlight.getDepartureCity().equals(allLegs.get(i).getDepartureAirport())
                    && selectedFlight.getDepartureDate().equals(allLegs.get(i).getDepartureDate())
                    && selectedFlight.getDepartureTime().equals(allLegs.get(i).getDepartureTime()))
                selectedLeg = allLegs.get(i);
        }
        return selectedLeg;
    }

}
