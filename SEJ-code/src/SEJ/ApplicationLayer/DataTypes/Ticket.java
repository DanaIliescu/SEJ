// Ticket Data Type - Created by Lei

package SEJ.ApplicationLayer.DataTypes;

public class Ticket
{
    private int ticketID;
    private int legID;
    private int customerID;
    private int ticketClass;
    private int ticketSeatNumber;
    private int ticketPrice;
    private String ticketState;

    public Ticket(int ticketID, int legID, int customerID, int ticketClass,
                  int ticketSeatNumber, int ticketPrice, String ticketState)
    {
        this.ticketID = ticketID;
        this.legID = legID;
        this.customerID = customerID;
        this.ticketClass = ticketClass;
        this.ticketSeatNumber = ticketSeatNumber;
        this.ticketPrice = ticketPrice;
        this.ticketState = ticketState;
    }

    public Ticket(){}

    public int getTicketID()
    {
        return ticketID;
    }

    public void setTicketID(int ticketID)
    {
        this.ticketID = ticketID;
    }

    public int getLegID()
    {
        return legID;
    }

    public void setLegID(int legID)
    {
        this.legID = legID;
    }

    public int getCustomerID()
    {
        return customerID;
    }

    public void setCustomerID(int customerID)
    {
        this.customerID = customerID;
    }

    public int getTicketClass()
    {
        return ticketClass;
    }

    public void setTicketClass(int ticketClass)
    {
        this.ticketClass = ticketClass;
    }

    public int getTicketSeatNumber()
    {
        return ticketSeatNumber;
    }

    public void setTicketSeatNumber(int ticketSeatNumber)
    {
        this.ticketSeatNumber = ticketSeatNumber;
    }

    public int getTicketPrice()
    {
        return ticketPrice;
    }

    public void setTicketPrice(int ticketPrice)
    {
        this.ticketPrice = ticketPrice;
    }

    public String getTicketState()
    {
        return ticketState;
    }

    public void setTicketState(String ticketState)
    {
        this.ticketState = ticketState;
    }

    @Override
    public String toString()
    {
        return "Ticket{" +
                "ticketID=" + ticketID +
                ", legID=" + legID +
                ", customerID=" + customerID +
                ", ticketClass=" + ticketClass +
                ", ticketSeatNumber=" + ticketSeatNumber +
                ", ticketPrice=" + ticketPrice +
                ", ticketState='" + ticketState + '\'' +
                '}';
    }
}