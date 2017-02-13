// Class to write from and to the 'orders' table in the database - created by Dana

package SEJ.DataAccessLayer;
import SEJ.ApplicationLayer.DataTypes.Order;
import SEJ.ApplicationLayer.DataTypes.OrderContent;
import SEJ.ApplicationLayer.DataTypes.Ticket;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OrderSQL {

    // receive data from database, put in array
    public static List<Order> loadAllOrders() throws Exception {
        Connection con = MySqlConnection.getConnection();
        Statement st = con.createStatement();
        String sql = ("SELECT * FROM orders");
        ResultSet rs = st.executeQuery(sql);

        ArrayList<Order> orders = new ArrayList<Order>();

        while (rs.next()) {
            int order_id = rs.getInt("order_id");
            int employee_id = rs.getInt("employee_id");
            int customer_id = rs.getInt("customer_id");
            orders.add(new Order(order_id, employee_id, customer_id));
        }

        con.close();
        rs.close();
        st.close();

        return orders;
    }

    //create new order and add it to orders table
    public static void createOrder(Order order) throws Exception{
        Connection con = MySqlConnection.getConnection();
        Statement st = con.createStatement();
        String sql = ("INSERT INTO orders VALUES (DEFAULT, "
                + order.getEmployeeID() + ", "
                + order.getCustomerID() + ")");
        st.executeUpdate(sql);

        con.close();
        st.close();
    }

    // method used for searching an order - created by Lei
    public static List<OrderContent> createOrderContent() throws  Exception
    {
        List<OrderContent> allOrderContent = new ArrayList<>();
        Connection con = MySqlConnection.getConnection();
        Statement st = con.createStatement();

        String sql = "SELECT order_id, t.ticket_id, customer_first_name, customer_last_name, t.ticket_class, t.ticket_price, t.ticket_seat_number, t.ticket_state\n" +
                "FROM orders_tickets ot \n" +
                "JOIN tickets t\n" +
                    "ON ot.ticket_id = t.ticket_id\n" +
                "JOIN customers c \n" +
                    "ON t.customer_id = c.customer_id;";

        ResultSet rs = st.executeQuery(sql);

        while(rs.next())
        {
            int orderID = rs.getInt("order_id");
            int ticketID = rs.getInt("ticket_id");
            String customerFirstName = rs.getString("customer_first_name");
            String customerLastName = rs.getString("customer_last_name");
            int ticketClass = rs.getInt("ticket_class");
            int ticketPrice = rs.getInt("ticket_price");
            int ticketSeatNumber = rs.getInt("ticket_seat_number");
            String ticketState = rs.getString("ticket_state");

            OrderContent orderContent = new OrderContent(orderID, ticketID, customerFirstName, customerLastName,
                    ticketClass, ticketPrice, ticketSeatNumber, ticketState);
            allOrderContent.add(orderContent);
        }
        return allOrderContent;
    }

    // updating the orders_tickets table
    public static void updateOrders_Tickets(Order order) throws Exception{
        Connection con = MySqlConnection.getConnection();
        Statement st = con.createStatement();
        for(int i = 0; i < order.getTickets().size(); i++) {
            String sql = ("INSERT INTO orders_tickets VALUES (" + order.getOrderID() + ", "
                    + order.getTickets().get(i).getTicketID() + ") ");
            st.executeUpdate(sql);
        }

        con.close();
        st.close();
    }
}
