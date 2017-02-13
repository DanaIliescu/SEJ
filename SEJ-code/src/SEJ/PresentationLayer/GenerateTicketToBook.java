// GenerateTicketToBook Class - created by Lei
// used when booking tickets (it will return a GridPane with ticket information that appears under 'Orders')

package SEJ.PresentationLayer;
import SEJ.ApplicationLayer.DataTypes.Leg;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class GenerateTicketToBook
{
    public static HBox makeOneTicket(Leg leg)
             throws Exception
    {
        // generates tickets according to the size of the array

        String passengerN = BookTicket.firstNameInput.getText() + " " + BookTicket.lastNameInput.getText();
        String seat_No = "" + BookTicket.seatNo.getText();
        String depAirp = leg.getDepartureAirport();
        String arrAirp = leg.getArrivalAirport();
        String depDateTime = leg.getDepartureDate() + " " + leg.getDepartureTime();
        String arrDateTime = leg.getArrivalDate() + " " + leg.getArrivalTime();
        String price = BookTicket.ticketPrice.getText();

        HBox orderBox = new HBox(15);
        orderBox.setPrefSize(650, 130);
        orderBox.setPadding(new Insets(0, 10, 10, 10));

        GridPane ticketOrder = makeTicket(passengerN, seat_No, depAirp, arrAirp, depDateTime, arrDateTime, price);
        ticketOrder.setAlignment(Pos.CENTER_RIGHT);

        orderBox.setId("vBox");

        orderBox.getChildren().add(ticketOrder);
        orderBox.setAlignment(Pos.CENTER);

        return orderBox;
    }

    public static GridPane makeTicket(String passengerN, String seat_No, String depAirp, String arrAirp,
                                      String depDateTime, String arrDateTime, String ticketPrice) throws Exception
    {
        //the main layout for a ticket
        GridPane ticketPane = new GridPane();
        ticketPane.setPrefSize(600, 110);
        ticketPane.setHgap(20);
        ticketPane.setVgap(10);

        //passenger info
        Label passenger = new Label("Passenger: ");
        Label passenger_info = new Label(passengerN);

        // seat and seat_info labels
        Label seat = new Label("Seat: ");
        Label seat_info = new Label(seat_No);

        // from and from_info labels
        Label depAirport = new Label("From: ");
        Label depAirport_info = new Label(depAirp);

        // to label and to_info label
        Label arrAirport = new Label("To: ");
        Label arrAirport_info = new Label(arrAirp);

        // departure and departure_info labels
        Label depTime = new Label("Dep.: ");
        Label depTime_info = new Label(depDateTime);

        // arrival and arrival_info labels
        Label arrTime = new Label("Arr.: ");
        Label arrTime_info = new Label(arrDateTime);

        // price and price_info labels
        Label price = new Label("Price: ");
        Label price_info = new Label(ticketPrice);

        ticketPane.setConstraints(passenger, 0, 0);
        ticketPane.setConstraints(passenger_info, 1, 0);
        ticketPane.setConstraints(depAirport, 0, 1);
        ticketPane.setConstraints(depAirport_info, 1, 1);
        ticketPane.setConstraints(arrAirport, 2, 1);
        ticketPane.setConstraints(arrAirport_info, 3, 1);
        ticketPane.setConstraints(seat, 4, 1);
        ticketPane.setConstraints(seat_info, 5, 1);
        ticketPane.setConstraints(price, 4, 2);
        ticketPane.setConstraints(price_info, 5, 2);
        ticketPane.setConstraints(depTime, 0, 2);
        ticketPane.setConstraints(depTime_info, 1, 2);
        ticketPane.setConstraints(arrTime, 2, 2);
        ticketPane.setConstraints(arrTime_info, 3, 2);

        ticketPane.getChildren().addAll(passenger, passenger_info, depAirport, depAirport_info,
                arrAirport, arrAirport_info, seat, seat_info,price, price_info, depTime, depTime_info, arrTime, arrTime_info);

        return ticketPane;
    }

}
