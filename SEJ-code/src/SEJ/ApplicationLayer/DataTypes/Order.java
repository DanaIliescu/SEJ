// Order Data Type - Created by Dana

package SEJ.ApplicationLayer.DataTypes;
import java.util.*;

public class Order
{
    private int orderID;
    private int employeeID;
    private int customerID;
    static List<Ticket> tickets = new ArrayList<>();

    // used when creating an order for the orders table
    public Order(int orderID, int employeeID, int customerID) {
        this.orderID = orderID;
        this.employeeID = employeeID;
        this.customerID = customerID;
    }

    // constructor with employee id and customer id
    public Order(int employeeID, int customerID) {
        this.employeeID = employeeID;
        this.customerID = customerID;
    }

    // unused constructor
    public Order()
    {
    }

    // constructor used for the orders_tickets table
    public Order(int orderID, List<Ticket> tickets)
    {
        this.orderID = orderID;
        this.tickets = tickets;
    }

    public static void addTicketToOrder(Ticket ticket){
        tickets.add(ticket);
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }
}
