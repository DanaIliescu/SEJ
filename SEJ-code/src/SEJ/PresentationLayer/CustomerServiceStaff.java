// CustomerServiceStaff Class - created by Lei, Dana, Felix
// handles searching an order, searching a flight and it is connected to booking a ticket
package SEJ.PresentationLayer;
import SEJ.ApplicationLayer.*;
import SEJ.ApplicationLayer.DataTypes.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;
import java.util.Optional;

public class CustomerServiceStaff {
    static Stage window;
    static Button buttonSearchFlight, buttonSearchOrder, book, buttonLogOut;
    static Button buttonSearch;
    static TextField textFieldDepartAirport, textFieldArrivalAirport, textFieldOrderID, textFieldFirstName, textFieldLastName;
    static Label labelDate, labelDeparturePlace, labelArrivalPlace, labelOrderID, labelFirstName, labelLastName;
    static GridPane gridPane;
    static HBox layout, ticketButtons;
    static TableView flightSearchResult, orderSearchResult;
    static DatePicker datePickerDepart;
    static VBox rightSide;
    static Scene customerScene;
    static int customersSize;
    static int ticketsSize;

    public static Scene returnCustomerService (Stage primaryStage) throws Exception {
        List<Customer> customers = CustomerInfo.selectAllCustomers();
        List<Ticket> tickets = TicketInfo.selectAllTickets();

        customersSize = customers.size();
        ticketsSize = tickets.size();

        window = primaryStage;
        window.setTitle("Customer Service");

        gridPane = new GridPane();
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setHgap(65);
        gridPane.setVgap(15);

        // The buttons on the left side are created, set on action and added to a VBox
        buttonSearchFlight = new Button("Book Flight");
        buttonSearchFlight.setMinWidth(100);
        buttonSearchFlight.setMaxWidth(100);
        buttonSearchFlight.setOnAction(e -> {
            try {
                layout.getChildren().remove(rightSide);
                layout.getChildren().add(makeRightPart());

            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        buttonSearchOrder = new Button("Search Order");
        buttonSearchOrder.setMinWidth(100);
        buttonSearchOrder.setMaxWidth(100);
        buttonSearchOrder.setOnAction((e -> {
            try {
                layout.getChildren().remove(searchOrderClicked());
                layout.getChildren().addAll(searchOrderClicked());
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }));

        buttonLogOut = new Button("Log out");
        buttonLogOut.setMinWidth(100);
        buttonLogOut.setMaxWidth(100);
        buttonLogOut.setOnAction(e -> {
            try{
                VBox login = LogInScene.makeLogInScene_logo(window);
                Scene scene = new Scene(login, 400, 500);
                scene.getStylesheets().add("CSS.css");
                window.setScene(scene);
            }
            catch (Exception e2){
                e2.printStackTrace();
            }
        });

        VBox vBoxButtons = new VBox(15);
        vBoxButtons.setPadding(new Insets(10,10,10,10));
        vBoxButtons.setId("vBox");
        vBoxButtons.getChildren().addAll(buttonSearchFlight, buttonSearchOrder, buttonLogOut);

        // the layout is a HBox, containing a VBox (the buttons on the left) and the right part which is also a VBox
        layout = new HBox(50);
        layout.getChildren().addAll(vBoxButtons, makeRightPart());

        customerScene = new Scene(layout, 943, 470);
        customerScene.getStylesheets().add("CustomerCSS");
        return customerScene;
    }

    // VBox with GridPane (containing Labels, TextFields and Search Button for order) and TableView
    public static VBox searchOrderClicked() throws Exception{
        buttonSearchFlight.setDisable(false);
        buttonSearchOrder.setDisable(true);

        layout.getChildren().remove(gridPane);
        layout.getChildren().remove(rightSide);
        rightSide.getChildren().clear();
        gridPane.getChildren().clear();

        labelOrderID = new Label("Order ID: ");
        gridPane.setConstraints(labelOrderID, 0, 0);

        textFieldOrderID = new TextField();
        textFieldOrderID.setPromptText("Order ID");
        textFieldOrderID.setMaxWidth(130);
        gridPane.setConstraints(textFieldOrderID, 0, 1);
        textFieldSetOnAction(textFieldOrderID);

        labelFirstName = new Label("First Name: ");
        gridPane.setConstraints(labelFirstName, 1, 0);

        textFieldFirstName = new TextField();
        textFieldFirstName.setPromptText("First Name");
        textFieldFirstName.setMaxWidth(150);
        gridPane.setConstraints(textFieldFirstName, 1, 1);

        labelLastName = new Label("Last Name: ");
        gridPane.setConstraints(labelLastName, 2, 0);

        textFieldLastName = new TextField();
        textFieldLastName.setPromptText("Last Name");
        textFieldLastName.setMaxWidth(150);
        gridPane.setConstraints(textFieldLastName, 2, 1);
        textFieldSetOnAction(textFieldLastName);

        buttonSearch.setOnAction(e -> { try {
            searchOrder();
        }catch (Exception ex) {
            ex.printStackTrace();
        }
        });

        gridPane.setConstraints(buttonSearch, 3, 1);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.getChildren().addAll(labelOrderID, textFieldOrderID, labelFirstName,
                textFieldFirstName, labelLastName, textFieldLastName, buttonSearch);

        // starts with TableView with no content, so that it won't be a blank space there
        orderSearchResult = OrderSearchResultTableView.makeTableView(0);

        rightSide.getChildren().addAll(gridPane, orderSearchResult);

        return rightSide;
    }

    public static void searchOrder() throws Exception {
        rightSide.getChildren().remove(orderSearchResult);
        rightSide.getChildren().remove(ticketButtons);

        // if the TextField with the order ID is not empty, it searches by order ID
        if(!textFieldOrderID.getText().isEmpty())
            orderSearchResult = OrderSearchResultTableView.makeTableView(Integer.parseInt(textFieldOrderID.getText()));
        // else, if first name and last name are typed in, it searches by name
        else
        if(!textFieldFirstName.getText().isEmpty() && !textFieldLastName.getText().isEmpty())
            orderSearchResult = OrderSearchResultTableView.makeTableView(OrderInfo.findOrderId  //returns order id
                            (textFieldFirstName.getText(), textFieldLastName.getText()));
        // else, it displays error alert with message
        else{
            String textAlert = "Enter only order ID or first name and last name";
            ShowAlert.makeErrorAlert(textAlert);
        }

        // when a row is selected, it finds the ticket corresponding
        orderSearchResult.setOnMouseClicked(ev -> {
            try{
                if(orderSearchResult.getSelectionModel().getSelectedItem() != null) {
                    rightSide.getChildren().removeAll(ticketButtons);
                    Ticket ticket = OrderSearchResultInfo.findSelectedTicket((OrderSearchResult) orderSearchResult.getSelectionModel().getSelectedItem());
                    ticketButtons = makeTicketButtons(ticket);   // it displays buttons according to the ticket found
                    rightSide.getChildren().addAll(ticketButtons);
                }
            }
            catch (Exception ex){
                ex.printStackTrace();
            }});
        rightSide.getChildren().add(orderSearchResult);
        textFieldOrderID.clear();
        textFieldFirstName.clear();
        textFieldLastName.clear();
    }

    // VBox with GridPane (TextFields, Labels, DatePicker and search Button for leg) and TableView
    public static VBox makeRightPart()throws Exception{
        buttonSearchFlight.setDisable(true);
        buttonSearchOrder.setDisable(false);
        gridPane.getChildren().clear();

        layout.getChildren().remove(gridPane);
        layout.getChildren().remove(rightSide);

        rightSide = new VBox(20);
        rightSide.setAlignment(Pos.TOP_CENTER);

        labelDeparturePlace = new Label("From: ");
        gridPane.setConstraints(labelDeparturePlace, 0, 0);

        textFieldDepartAirport = new TextField();
        textFieldDepartAirport.setPromptText("City");
        textFieldDepartAirport.setMaxWidth(150);
        gridPane.setConstraints(textFieldDepartAirport, 0, 1);

        labelArrivalPlace = new Label("To: ");
        gridPane.setConstraints(labelArrivalPlace, 1, 0);

        textFieldArrivalAirport= new TextField();
        textFieldArrivalAirport.setPromptText("City");
        textFieldArrivalAirport.setMaxWidth(150);
        gridPane.setConstraints(textFieldArrivalAirport, 1, 1);

        labelDate = new Label("Departure date: ");
        gridPane.setConstraints(labelDate, 2, 0);

        datePickerDepart = new DatePicker();
        datePickerDepart.setPromptText("Departure");
        datePickerDepart.setMaxWidth(130);
        AdminScene.changeDatePickerFormat(datePickerDepart);
        gridPane.setConstraints(datePickerDepart, 2, 1);

        buttonSearch = new Button("Search");
        buttonSearch.setMinWidth(70);
        gridPane.setConstraints(buttonSearch, 3, 1);
        buttonSearch.setOnAction(e -> {
            if(textFieldDepartAirport.getText().isEmpty() || textFieldArrivalAirport.getText().isEmpty() ||
                    datePickerDepart.getValue() == null){
                String alertText = "Please fill out all fields.";
                ShowAlert.makeErrorAlert(alertText);             // displays error if fields are not filled out
            }else
            try {
                searchFlight();             // searches flight
            }
            catch (Exception e2){
                System.out.println(e2.getMessage());
            }
        } );

        // starts with TableView with no content, so that it won't be a blank space there
        flightSearchResult = FlightSearchResultTableView.makeTableView("", "", "");

        gridPane.getChildren().addAll(labelDeparturePlace, textFieldDepartAirport, labelArrivalPlace, textFieldArrivalAirport, labelDate,
                datePickerDepart, buttonSearch);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setId("gridPane");

        rightSide.getChildren().addAll(gridPane, flightSearchResult);

        return rightSide;
    }

    public static void searchFlight() throws Exception {
        rightSide.getChildren().removeAll(flightSearchResult, book);
        // the TableView with all legs that satisfy the search condition
        flightSearchResult = FlightSearchResultTableView.makeTableView(textFieldDepartAirport.getText(),
                textFieldArrivalAirport.getText(), datePickerDepart.getEditor().getText());

        book = new Button("Book Ticket");

        //when a row is clicked, the book ticket button appears
        flightSearchResult.setOnMouseClicked(event -> {
            rightSide.getChildren().remove(book);
            // the book button makes the transition to the booking scene
            book.setOnAction(eve -> {
                try {
                    Leg leg = FlightSearchResultInfo.findSelectedLeg ((FlightSearchResult)flightSearchResult.getSelectionModel().getSelectedItem()); //create in GUI, not okay
                    BookTicket.makeLayout(leg, window);
                }
                catch (Exception exception){
                    exception.printStackTrace();
                }
            });
            rightSide.getChildren().add(book);
        });

        rightSide.getChildren().addAll(flightSearchResult);
    }

    // returns the buttons according to the ticket state
    public static HBox makeTicketButtons(Ticket ticket) {
        HBox buttons = new HBox(20);

        Button buttonConfirm = new Button("Confirm");
        Button buttonRefund = new Button("Refund");
        Button buttonCancel = new Button("Cancel");

        buttonRefund.setOnAction(e -> { try {
            makeRefundAlert(ticket, TicketInfo.refundTicketClicked(ticket));
            buttons.getChildren().remove(buttonRefund);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        });

        buttonCancel.setOnAction(e -> { try {
            makeCancelAlert(ticket);
            buttons.getChildren().remove(buttonCancel);
            buttons.getChildren().remove(buttonConfirm);
        }
        catch (Exception exception){
            exception.printStackTrace();
        }
        });

        buttonConfirm.setOnAction(e -> {
            try {
            makeConfirmAlert(ticket);
            buttons.getChildren().remove(buttonConfirm);
            buttons.getChildren().remove(buttonCancel);
        }
            catch (Exception e1){
                e1.printStackTrace();
            }
        });

        // for a booked ticket, it returns confirm and cancel buttons
        if(ticket.getTicketState().equalsIgnoreCase("booked"))
            buttons.getChildren().addAll(buttonConfirm, buttonCancel);
        // for a confirmed ticket, it returns a refund button
        if(ticket.getTicketState().equalsIgnoreCase("confirmed"))
            buttons.getChildren().add(buttonRefund);

        buttons.setAlignment(Pos.CENTER);

        return buttons;
    }

    // handles the refund option
    public static void makeRefundAlert(Ticket ticket, String legDate) throws Exception{
        // if it is an economy class
        if(ticket.getTicketClass() == 3) {
            // and there is less than 2 weeks before the flight
            if (TicketInfo.calculateDateDifference(legDate) <= 14) {
                String alertText = "There is less than 2 weeks to departure date, " +
                        "so there will be no refund, but the ticket will be canceled. " +
                        "Are you sure you want to cancel the ticket?";
                Alert confirmAlert = ShowAlert.makeConfirmAlert(alertText);

                Optional<ButtonType> result = confirmAlert.showAndWait();
                // if the user chooses to cancel although there will be no refund
                if (result.get() == ButtonType.OK) {
                    TicketInfo.cancelTicketClicked(ticket);      // ticket is cancelled
                    OrderSearchResultTableView.deleteRow(orderSearchResult); // TableView updated
                }
                // the user can cancel the refunding - the ticket won't we deleted, nothing happens
                if (result.get() == ButtonType.CANCEL) {
                    confirmAlert.close();
                }
            }
            // if there is more than 2 weeks before the flight
            // the ticket is refunded
            else {
                makeRefundAlert(ticket);
            }
        }
        // if it is a first class or a business class ticket
        // the ticket is refunded
        else
        {
            makeRefundAlert(ticket);
        }
    }

    // the user has to choose whether to cancel or not
    public static void makeCancelAlert(Ticket ticket) throws Exception{
        String alertText = "Are you sure you want to cancel the ticket?";
        Alert cancelAlert = ShowAlert.makeConfirmAlert(alertText);
        ButtonType buttonTypeYes = new ButtonType("Yes");
        ButtonType buttonTypeNo = new ButtonType("No");

        cancelAlert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

        // if the user clicks yes - the ticket is cancelled and the TableView is
        Optional<ButtonType> result = cancelAlert.showAndWait();
        if (result.get() == buttonTypeYes) {
            TicketInfo.cancelTicketClicked(ticket);
            OrderSearchResultTableView.deleteRow(orderSearchResult);
        }
        // if the user clicks no - nothing happens
        if(result.get() == buttonTypeNo){
            cancelAlert.close();
        }
    }

    // shows message that the ticket will be refunded
    // the ticket is deleted
    // updates TableView
    public static void makeRefundAlert(Ticket ticket) throws Exception
    {
        String alertText = "The ticket has been refunded. "
                + ticket.getTicketPrice() + " kr will be transferred to the customer's account within 30 days.";
        ShowAlert.makeInformationAlert(alertText);
        TicketInfo.cancelTicketClicked(ticket);
        OrderSearchResultTableView.deleteRow(orderSearchResult);
    }

    // shows message that the ticket has been confirmed
    // sends information to the Application Layer - the ticket
    // updates TableView
    public static void makeConfirmAlert(Ticket ticket) throws Exception
    {
        String alertText = "The ticket is now confirmed.";
        ShowAlert.makeInformationAlert(alertText);
        TicketInfo.confirmTicketClicked(ticket);
        OrderSearchResultTableView.updateRow(orderSearchResult);
    }

    // on the order ID and the last name TextFields the user can press enter and search order
    public static void textFieldSetOnAction(TextField textField)
    {
        textField.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) try {
                searchOrder();
            }
            catch (Exception e1){
                e1.printStackTrace();
            }
        });
    }
}
