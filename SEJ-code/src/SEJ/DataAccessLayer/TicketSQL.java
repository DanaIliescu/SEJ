// Class to write from and to the 'tickets' table in the database - created by Dana

package SEJ.DataAccessLayer;
import SEJ.ApplicationLayer.DataTypes.Ticket;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TicketSQL {
    private static ArrayList<Ticket> tickets = new ArrayList<Ticket>();

    // receive data from database, put in array
    public static List<Ticket> loadAllTickets() throws Exception {
        Connection con = MySqlConnection.getConnection();
        Statement st = con.createStatement();
        String sql = ("SELECT * FROM tickets");
        ResultSet rs = st.executeQuery(sql);

        while (rs.next()) {
            int ticket_id = rs.getInt("ticket_id");
            int leg_id = rs.getInt("leg_id");
            int customer_id = rs.getInt("customer_id");
            int ticket_class = rs.getInt("ticket_class");
            int ticket_seat_number = rs.getInt("ticket_seat_number");
            int ticket_price = rs.getInt("ticket_price");
            String ticket_state = rs.getString("ticket_state");
            tickets.add(new Ticket(ticket_id, leg_id, customer_id, ticket_class, ticket_seat_number, ticket_price, ticket_state));
        }

        con.close();
        rs.close();
        st.close();

        return tickets;
    }

    //create new ticket and add it to tickets table
    public static void createTicket(Ticket ticket) throws Exception{
        Connection con = MySqlConnection.getConnection();
        Statement st = con.createStatement();
        String sql = ("INSERT INTO tickets VALUES (DEFAULT, "
                + ticket.getLegID() + ", "
                + ticket.getCustomerID() + ", "
                + ticket.getTicketClass() + ", "
                + ticket.getTicketSeatNumber() + ", "
                + ticket.getTicketPrice() + ", "
                + "\"" + ticket.getTicketState() + "\"" + ")");

        st.executeUpdate(sql);

        con.close();
        st.close();
    }

    // updates the tables when a ticket is deleted
    public static void deleteTicket(int ticketID, int legID, String ticketClass) throws Exception{
        Connection con = MySqlConnection.getConnection();
        Statement st = con.createStatement();
        String sql = ("DELETE from tickets\n" +
                "WHERE ticket_id = " + ticketID);

        String sql2 = ("DELETE from orders_tickets\n" +
                "WHERE ticket_id = " + ticketID + " AND order_id = (SELECT order_id FROM \n" +
                "(SELECT order_id FROM orders_tickets WHERE ticket_id =  " + ticketID + ") x)");

        String sql3 = ("UPDATE legs \n" +
                "SET " +  ticketClass  + " =  "  + ticketClass  + " - 1\n" +
                "WHERE leg_id = " + legID );
        st.executeUpdate(sql);
        st.executeUpdate(sql2);
        st.executeUpdate(sql3);

        con.close();
        st.close();
    }

    // updates the ticket state from 'booked' to 'confirmed' in the tickets table
    public static void confirmTicket(int ticketID) throws Exception{
        Connection con = MySqlConnection.getConnection();
        Statement st = con.createStatement();
        String sql = ("UPDATE tickets\n" +
                      "SET ticket_state = 'confirmed'\n" +
                      "WHERE ticket_id = " + ticketID);
        st.executeUpdate(sql);

        con.close();
        st.close();
    }
}
