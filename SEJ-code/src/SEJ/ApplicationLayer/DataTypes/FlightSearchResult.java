// FlightSearchResult Data Type - Created by Lei
// Used in searching a flight

package SEJ.ApplicationLayer.DataTypes;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class FlightSearchResult
{
    private StringProperty departureCity;
    private StringProperty arrivalCity;
    private StringProperty departureDate;
    private StringProperty departureTime;
    private StringProperty arrivalDate;
    private StringProperty arrivalTime;
    private IntegerProperty firstClassBooked;
    private IntegerProperty businessClassBooked;
    private IntegerProperty coachClassBooked;

    public FlightSearchResult(String departureCity, String arrivalCity, String departureDate, String departureTime,
                              String arrivalDate, String arrivalTime)
    {
        this.departureCity = new SimpleStringProperty(departureCity);
        this.arrivalCity = new SimpleStringProperty(arrivalCity);
        this.departureDate = new SimpleStringProperty(departureDate);
        this.departureTime = new SimpleStringProperty(departureTime);
        this.arrivalDate = new SimpleStringProperty(arrivalDate);
        this.arrivalTime = new SimpleStringProperty(arrivalTime);
        this.firstClassBooked = new SimpleIntegerProperty(0);
        this.businessClassBooked = new SimpleIntegerProperty(0);
        this.coachClassBooked = new SimpleIntegerProperty(0);

    }

    public String getDepartureCity()
    {
        return departureCity.get();
    }

    public StringProperty departureCityProperty()
    {
        return departureCity;
    }

    public void setDepartureCity(String departureCity)
    {
        this.departureCity.set(departureCity);
    }

    public String getArrivalCity()
    {
        return arrivalCity.get();
    }

    public StringProperty arrivalCityProperty()
    {
        return arrivalCity;
    }

    public void setArrivalCity(String arrivalCity)
    {
        this.arrivalCity.set(arrivalCity);
    }

    public String getDepartureTime()
    {
        return departureTime.get();
    }

    public StringProperty departureTimeProperty()
    {
        return departureTime;
    }

    public void setDepartureTime(String departureTime)
    {
        this.departureTime.set(departureTime);
    }

    public String getArrivalTime()
    {
        return arrivalTime.get();
    }

    public StringProperty arrivalTimeProperty()
    {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime)
    {
        this.arrivalTime.set(arrivalTime);
    }

    public String getDepartureDate()
    {
        return departureDate.get();
    }

    public StringProperty departureDateProperty()
    {
        return departureDate;
    }

    public void setDepartureDate(String departureDate)
    {
        this.departureDate.set(departureDate);
    }

    public String getArrivalDate()
    {
        return arrivalDate.get();
    }

    public StringProperty arrivalDateProperty()
    {
        return arrivalDate;
    }

    public void setArrivalDate(String arrivalDate)
    {
        this.arrivalDate.set(arrivalDate);
    }

    public int getFirstClassBooked()
    {
        return firstClassBooked.get();
    }

    public IntegerProperty firstClassBookedProperty()
    {
        return firstClassBooked;
    }

    public void setFirstClassBooked(int firstClassBooked)
    {
        this.firstClassBooked.set(firstClassBooked);
    }

    public int getBusinessClassBooked()
    {
        return businessClassBooked.get();
    }

    public IntegerProperty businessClassBookedProperty()
    {
        return businessClassBooked;
    }

    public void setBusinessClassBooked(int businessClassBooked)
    {
        this.businessClassBooked.set(businessClassBooked);
    }

    public int getCoachClassBooked()
    {
        return coachClassBooked.get();
    }

    public IntegerProperty coachClassBookedProperty()
    {
        return coachClassBooked;
    }

    public void setCoachClassBooked(int coachClassBooked)
    {
        this.coachClassBooked.set(coachClassBooked);
    }

    @Override
    public String toString() {
        return "FlightSearchResult{" +
                "departureCity=" + departureCity +
                ", arrivalCity=" + arrivalCity +
                ", departureDate=" + departureDate +
                ", departureTime=" + departureTime +
                ", arrivalDate=" + arrivalDate +
                ", arrivalTime=" + arrivalTime +
                ", firstClassBooked=" + firstClassBooked +
                ", businessClassBooked=" + businessClassBooked +
                ", coachClassBooked=" + coachClassBooked +
                '}';
    }
}
