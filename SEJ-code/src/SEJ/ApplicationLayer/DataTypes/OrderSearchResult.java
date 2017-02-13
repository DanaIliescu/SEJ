// OrderSearchResult Data Type - Created by Lei

package SEJ.ApplicationLayer.DataTypes;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class OrderSearchResult
{
    private IntegerProperty orderID;
    private IntegerProperty ticketID;
    private StringProperty customerFirstName;
    private StringProperty customerLastName;
    private IntegerProperty ticketClass;
    private IntegerProperty ticketPrice;
    private IntegerProperty ticketSeatNumber;
    private StringProperty ticketState;

    public OrderSearchResult(int orderID, int ticketID, String customerFirstName, String customerLastName, int ticketClass,
                             int ticketPrice, int ticketSeatNumber, String ticketState)
    {
        this.orderID = new SimpleIntegerProperty(orderID);
        this.ticketID = new SimpleIntegerProperty(ticketID);
        this.customerFirstName = new SimpleStringProperty(customerFirstName);
        this.customerLastName = new SimpleStringProperty(customerLastName);
        this.ticketClass = new SimpleIntegerProperty(ticketClass);
        this.ticketPrice = new SimpleIntegerProperty(ticketPrice);
        this.ticketSeatNumber = new SimpleIntegerProperty(ticketSeatNumber);
        this.ticketState = new SimpleStringProperty(ticketState);
    }

    public int getOrderID()
    {
        return orderID.get();
    }

    public IntegerProperty orderIDProperty()
    {
        return orderID;
    }

    public void setOrderID(int orderID)
    {
        this.orderID.set(orderID);
    }

    public int getTicketID() {
        return ticketID.get();
    }

    public IntegerProperty ticketIDProperty() {
        return ticketID;
    }

    public void setTicketID(int ticketID) {
        this.ticketID.set(ticketID);
    }

    public StringProperty customerLastNameProperty() {
        return customerLastName;
    }

    public String getCustomerFirstName()
    {
        return customerFirstName.get();
    }

    public StringProperty customerFirstNameProperty()
    {
        return customerFirstName;
    }

    public void setCustomerFirstName(String customerFirstName)
    {
        this.customerFirstName.set(customerFirstName);
    }

    public String getCustomerLastName()
    {
        return customerLastName.get();
    }

    public StringProperty customerLasttNameProperty()
    {
        return customerLastName;
    }

    public void setCustomerLastName(String customerLasttName)
    {
        this.customerLastName.set(customerLasttName);
    }

    public int getTicketClass()
    {
        return ticketClass.get();
    }

    public IntegerProperty ticketClassProperty()
    {
        return ticketClass;
    }

    public void setTicketClass(int ticketClass)
    {
        this.ticketClass.set(ticketClass);
    }

    public int getTicketPrice()
    {
        return ticketPrice.get();
    }

    public IntegerProperty ticketPriceProperty()
    {
        return ticketPrice;
    }

    public void setTicketPrice(int ticketPrice)
    {
        this.ticketPrice.set(ticketPrice);
    }

    public int getTicketSeatNumber()
    {
        return ticketSeatNumber.get();
    }

    public IntegerProperty ticketSeatNumberProperty()
    {
        return ticketSeatNumber;
    }

    public void setTicketSeatNumber(int ticketSeatNumber)
    {
        this.ticketSeatNumber.set(ticketSeatNumber);
    }

    public String getTicketState()
    {
        return ticketState.get();
    }

    public StringProperty ticketStateProperty()
    {
        return ticketState;
    }

    public void setTicketState(String ticketState)
    {
        this.ticketState.set(ticketState);
    }

    @Override
    public String toString() {
        return "OrderSearchResult{" +
                "orderID=" + orderID +
                ", customerFirstName=" + customerFirstName +
                ", customerLastName=" + customerLastName +
                ", ticketClass=" + ticketClass +
                ", ticketPrice=" + ticketPrice +
                ", ticketSeatNumber=" + ticketSeatNumber +
                ", ticketState=" + ticketState +
                '}';
    }
}
