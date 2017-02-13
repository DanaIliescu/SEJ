// OrderContent Data Type - Created by Lei
// Used for searching an order

package SEJ.ApplicationLayer.DataTypes;

public class OrderContent
{
    private int orderID;
    private int ticketID;
    private String customerFirstName;
    private String customerLastName;
    private int ticketClass;
    private int ticketPrice;
    private int ticketSeatNumber;
    private String ticketState;

    public OrderContent(int orderID, int ticketID, String customerFirstName, String customerLastName, int ticketClass,
                        int ticketPrice, int ticketSeatNumber, String ticketState)
    {
        this.orderID = orderID;
        this.ticketID = ticketID;
        this.customerFirstName = customerFirstName;
        this.customerLastName = customerLastName;
        this.ticketClass = ticketClass;
        this.ticketPrice = ticketPrice;
        this.ticketSeatNumber = ticketSeatNumber;
        this.ticketState = ticketState;
    }

    public int getOrderID()
    {
        return orderID;
    }

    public void setOrderID(int orderID)
    {
        this.orderID = orderID;
    }

    public int getTicketID() {
        return ticketID;
    }

    public void setTicketID(int ticketID) {
        this.ticketID = ticketID;
    }

    public String getCustomerFirstName()
    {
        return customerFirstName;
    }

    public void setCustomerFirstName(String customerFirstName)
    {
        this.customerFirstName = customerFirstName;
    }

    public String getCustomerLastName()
    {
        return customerLastName;
    }

    public void setCustomerLastName(String customerLastName)
    {
        this.customerLastName = customerLastName;
    }

    public int getTicketClass()
    {
        return ticketClass;
    }

    public void setTicketClass(int ticketClass)
    {
        this.ticketClass = ticketClass;
    }

    public int getTicketPrice()
    {
        return ticketPrice;
    }

    public void setTicketPrice(int ticketPrice)
    {
        this.ticketPrice = ticketPrice;
    }

    public int getTicketSeatNumber()
    {
        return ticketSeatNumber;
    }

    public void setTicketSeatNumber(int ticketSeatNumber)
    {
        this.ticketSeatNumber = ticketSeatNumber;
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
        return "OrderContent{" +
                "orderID=" + orderID +
                ", customerFirstName='" + customerFirstName + '\'' +
                ", customerLastName='" + customerLastName + '\'' +
                ", ticketClass=" + ticketClass +
                ", ticketPrice=" + ticketPrice +
                ", ticketSeatNumber=" + ticketSeatNumber +
                ", ticketState='" + ticketState + '\'' +
                '}';
    }
}
