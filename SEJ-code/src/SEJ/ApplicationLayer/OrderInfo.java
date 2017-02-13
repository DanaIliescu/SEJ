// OrderInfo Class - created by Marius

package SEJ.ApplicationLayer;
import SEJ.ApplicationLayer.DataTypes.Customer;
import SEJ.ApplicationLayer.DataTypes.Order;
import SEJ.ApplicationLayer.DataTypes.OrderContent;
import SEJ.ApplicationLayer.DataTypes.Ticket;
import SEJ.DataAccessLayer.OrderSQL;
import java.util.ArrayList;
import java.util.List;

public class OrderInfo {
    private static List<Order> orders;
    static Order order;

    // gets all orders from the Data Access Layer
    public static List<Order> selectAllOrders() throws Exception
    {
        orders = OrderSQL.loadAllOrders();
        return orders;
    }

    // sends order to the Data Access Layer
    // adds it to the array
    public static void createOrder(int employeeID, int customerID) throws Exception
    {
        Order order = new Order(employeeID, customerID);
        OrderSQL.createOrder(order);
        orders.add(new Order(orders.size()+1, employeeID, customerID));
    }

    // add a ticket to an order - used for orders_tickets table
    public static void addTicketToOrder(Ticket ticket) throws Exception
    {
        order.addTicketToOrder(ticket);
    }

    // sends information about the orders_tickets table to the Data Access Layer
    public static void createOrders_Tickets(List<Ticket> tickets) throws Exception
    {
        Order order = new Order(selectAllOrders().size()+1, tickets);
        OrderSQL.updateOrders_Tickets(order);
    }

    // creates an array with OrderContent objects - created by Lei
    // adds all orders that have the same order id as the one in the parameter
    // returns array
    public static List<OrderContent> createSearchedOrder(int orderID) throws Exception
    {
        List<OrderContent> allOrders = OrderSQL.createOrderContent();
        List<OrderContent> searchedOrder = new ArrayList<>();

        for(int i = 0; i < allOrders.size(); i++)
        {
            if(orderID == allOrders.get(i).getOrderID())
            {
                searchedOrder.add(allOrders.get(i));
            }
        }
        return searchedOrder;
    }

    // finds the order id based on the customer's first and last name - created by Dana
    public static int findOrderId(String firstName, String lastName) throws Exception
    {
        List<Customer> allCustomers = CustomerInfo.selectAllCustomers();
        List<Order> allOrders = OrderInfo.selectAllOrders();
        int customerID = 0;
        int orderID = 0;

        // first, it finds the customer id the has the specified first and last name
        for(int i = 0; i < allCustomers.size(); i++){
            if(firstName.equalsIgnoreCase(allCustomers.get(i).getFirstName()) && lastName.equalsIgnoreCase(allCustomers.get(i).getLastName()))
                customerID = allCustomers.get(i).getCustomerID();
        }

        // finds the order id that has the specified customer id
        for(int i = 0; i < allOrders.size(); i++){
            if(customerID == allOrders.get(i).getCustomerID())
                orderID = allOrders.get(i).getOrderID();
        }

        return orderID;
    }

}