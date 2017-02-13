// TicketInfo Class - created by Marius

package SEJ.ApplicationLayer;
import SEJ.ApplicationLayer.DataTypes.Leg;
import SEJ.ApplicationLayer.DataTypes.Ticket;
import SEJ.DataAccessLayer.TicketSQL;
import SEJ.PresentationLayer.CustomerServiceStaff;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class TicketInfo {
    private static List<Ticket> tickets;
    static List<Ticket> newBookedTickets = new ArrayList<>();

    // gets all tickets from the Data Access Layer
    public static List<Ticket> selectAllTickets() throws Exception
    {
        tickets = TicketSQL.loadAllTickets();
        return tickets;
    }

    // converts ticket class from int to String
    // sends necessary information to the Data Access Layer for updating the tables
    // created by Dana
    public static void cancelTicketClicked(Ticket ticket) throws Exception{
        int ticket_id = ticket.getTicketID();
        int leg_id = ticket.getLegID();
        int ticketClass = ticket.getTicketClass();
        String leg_column_to_update = "";
        if (ticketClass == 1)
            leg_column_to_update = "first_seat_booked";
        if (ticketClass == 2)
            leg_column_to_update = "business_seat_booked";
        if (ticketClass == 3)
            leg_column_to_update = "coach_seat_booked";

        TicketSQL.deleteTicket(ticket_id, leg_id, leg_column_to_update);
    }

    // returns the date of the leg on which the ticket is booked
    // used for calculating how many days there are until the departure date
    // created by Felix
    public static String refundTicketClicked(Ticket ticket) throws Exception {
        int leg_id = ticket.getLegID();
        List<Leg> allLegs = LegInfo.selectAllLegs();
        String leg_date = "";

        // loops through the legs array and finds the leg date
        for (int i = 0; i < allLegs.size(); i++ ) {
            if(leg_id == allLegs.get(i).getLegID()) {
                leg_date = allLegs.get(i).getDepartureDate();
            }
        }

        return leg_date;
    }

    // sends necessary information to the Data Access Layer (the ticket id) for updating tables
    public static void confirmTicketClicked(Ticket ticket) throws Exception{
        TicketSQL.confirmTicket(ticket.getTicketID());
    }

    // calculates the date difference between the present date and the departure date of a leg
    // created by Dana
    public static long calculateDateDifference(String leg_date) throws Exception {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date today = new Date();
        dateFormat.format(today);

        Date legDate = dateFormat.parse(leg_date);

        LocalDate localDateLEG = legDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate localDateTODAY = today.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        long days = ChronoUnit.DAYS.between(localDateTODAY, localDateLEG);

        return days;
    }

    // sends ticket to the Data Access Layer
    // adds it to the array
    // sends information for updating the seats
    // created by Lei
    public static void bookTicket(Ticket ticket) throws Exception
    {
        TicketSQL.createTicket(ticket);
        tickets.add(ticket);
        //LegInfo.updateSeat(ticket.getLegID(), ticket.getTicketClass());
    }

    // unused method
    // it is meant to avoid creating of Ticket objects in the Presentation Layer
    public static void createTicket(int ticketId, int legId, int customerId, int ticketClass, int seatNr, int ticketPrice, String ticketState)throws Exception{
        Ticket ticket = new Ticket(ticketId,legId, customerId, ticketClass, seatNr, ticketPrice, ticketState);
        TicketSQL.createTicket(ticket);
    }

    // unused method
    // it is meant to avoid addding ticket objects to an array in the Presentation Layer
    public static void addTicketToArray(int ticketId, int legId, int customerId, int ticketClass, int seatNr, int ticketPrice, String ticketState){
        newBookedTickets = new ArrayList<>();
        Ticket ticket = new Ticket(ticketId,legId, customerId, ticketClass, seatNr, ticketPrice, ticketState);
        newBookedTickets.add(ticket);
    }
}