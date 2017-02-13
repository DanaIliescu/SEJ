// Leg Data Type - Created by Lei

package SEJ.ApplicationLayer.DataTypes;

public class Leg
{
    private int legID;
    private String departureAirport;
    private String arrivalAirport;
    private String departureDate;
    private String departureTime;
    private String arrivalDate;
    private String arrivalTime;
    private byte firstSeatBooked;
    private byte businessSeatBooked;
    private short coachSeatBooked;
    private int price;

    // constructor with leg id, without specifying how many seats are booked for each leg
    // used when creating a new schedule
    public Leg(int legID, String departureAirport, String arrivalAirport, String departureDate,
               String departureTime, String arrivalDate, String arrivalTime, int price)
    {
        this.legID = legID;
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.arrivalDate = arrivalDate;
        this.arrivalTime = arrivalTime;
        this.price = price;
    }

    // constructor with all fields
    // used when reading from the database
    public Leg(int legID, String departureAirport, String arrivalAirport, String departureDate,
               String departureTime, String arrivalDate, String arrivalTime, byte firstSeatBooked,
               byte businessSeatBooked, short coachSeatBooked, int price)
    {
        this.legID = legID;
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.arrivalDate = arrivalDate;
        this.arrivalTime = arrivalTime;
        this.firstSeatBooked = firstSeatBooked;
        this.businessSeatBooked = businessSeatBooked;
        this.coachSeatBooked = coachSeatBooked;
        this.price = price;
    }

    // constructor without leg id
    // used when creating a new schedule
    public Leg(String departureAirport, String arrivalAirport, String departureDate,
               String departureTime, String arrivalDate, String arrivalTime, int price)
    {
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.arrivalDate = arrivalDate;
        this.arrivalTime = arrivalTime;
        this.price = price;
    }

    public int getLegID()
    {
        return legID;
    }

    public void setLegID(int legID)
    {
        this.legID = legID;
    }

    public String getDepartureAirport()
    {
        return departureAirport;
    }

    public void setDepartureAirport(String departureAirport)
    {
        this.departureAirport = departureAirport;
    }

    public String getArrivalAirport()
    {
        return arrivalAirport;
    }

    public void setArrivalAirport(String arrivalAirport)
    {
        this.arrivalAirport = arrivalAirport;
    }

    public String getDepartureDate()
    {
        return departureDate;
    }

    public void setDepartureDate(String departureDate)
    {
        this.departureDate = departureDate;
    }

    public String getDepartureTime()
    {
        return departureTime;
    }

    public void setDepartureTime(String departureTime)
    {
        this.departureTime = departureTime;
    }

    public String getArrivalDate()
    {
        return arrivalDate;
    }

    public void setArrivalDate(String arrivalDate)
    {
        this.arrivalDate = arrivalDate;
    }

    public String getArrivalTime()
    {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime)
    {
        this.arrivalTime = arrivalTime;
    }

    public byte getFirstSeatBooked()
    {
        return firstSeatBooked;
    }

    public void setFirstSeatBooked(byte firstSeatBooked)
    {
        this.firstSeatBooked = firstSeatBooked;
    }

    public byte getBusinessSeatBooked()
    {
        return businessSeatBooked;
    }

    public void setBusinessSeatBooked(byte businessSeatBooked)
    {
        this.businessSeatBooked = businessSeatBooked;
    }

    public short getCoachSeatBooked()
    {
        return coachSeatBooked;
    }

    public void setCoachSeatBooked(short coachSeatBooked)
    {
        this.coachSeatBooked = coachSeatBooked;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Leg{" +
                "legID=" + legID +
                ", departureAirport='" + departureAirport + '\'' +
                ", arrivalAirport='" + arrivalAirport + '\'' +
                ", departureDate='" + departureDate + '\'' +
                ", departureTime='" + departureTime + '\'' +
                ", arrivalDate='" + arrivalDate + '\'' +
                ", arrivalTime='" + arrivalTime + '\'' +
                ", firstSeatBooked=" + firstSeatBooked +
                ", businessSeatBooked=" + businessSeatBooked +
                ", coachSeatBooked=" + coachSeatBooked +
                ", price=" + price +
                '}';
    }
}
