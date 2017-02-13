// OrderSearchResultInfo Class - Created by Lei

package SEJ.ApplicationLayer;
import SEJ.ApplicationLayer.DataTypes.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.List;

public class OrderSearchResultInfo
{
    static Ticket selectedTicket;

    // gets all orders that satisfy that have the specified order id
    private static List<OrderContent> getAllSearchedOrders(int orderId) throws Exception
    {
        List<OrderContent> allSearchedOrders = OrderInfo.createSearchedOrder(orderId);
        return allSearchedOrders;
    }

    // creates Observable List with OrderSearchResult objects
    // takes information from each OrderContent object and creates an OrderSearchResult object (that has StringProperties as fields)
    // returns the allOrdersResults Observable List that will be used in the TableView
    public static ObservableList<OrderSearchResult> createOrderSearchResultsProperty(int orderId) throws Exception{
        List<OrderContent> allSearchedOrders = getAllSearchedOrders(orderId);

        ObservableList<OrderSearchResult> allOrdersResults = FXCollections.observableArrayList();

        for(OrderContent orderContent: allSearchedOrders)
        {
            int orderID = orderContent.getOrderID();
            int ticketID = orderContent.getTicketID();
            String customerFirstName = orderContent.getCustomerFirstName();
            String customerLastName = orderContent.getCustomerLastName();
            int ticketClass = orderContent.getTicketClass();
            int ticketPrice = orderContent.getTicketPrice();
            int ticketSeatNumber = orderContent.getTicketSeatNumber();
            String ticketState = orderContent.getTicketState();
            OrderSearchResult orderSearchResult = new OrderSearchResult(orderID, ticketID, customerFirstName, customerLastName,
                                                                ticketClass, ticketPrice, ticketSeatNumber, ticketState);
            allOrdersResults.add(orderSearchResult);
        }

        return allOrdersResults;
    }

    // each row from the TableView corresponds to an OrderSearchResult object
    // this method returns the ticket that is containted by that OrderSearchResult
    // created by Dana
    public static Ticket findSelectedTicket(OrderSearchResult selectedOrder) throws Exception{
        if(selectedOrder != null) {
            List<Ticket> allTickets = TicketInfo.selectAllTickets();

            //loops through the tickets array and finds the selected ticket
            for (int i = 0; i < allTickets.size(); i++) {
                if (selectedOrder.getTicketID() == allTickets.get(i).getTicketID())
                    selectedTicket = allTickets.get(i);
            }
            return selectedTicket;
        }
        else
            return selectedTicket = new Ticket();
    }
}
