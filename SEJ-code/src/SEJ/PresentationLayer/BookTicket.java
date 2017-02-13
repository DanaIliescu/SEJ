// BookTicket Class - created by Dana, Lei, Felix

package SEJ.PresentationLayer;
import SEJ.ApplicationLayer.*;
import SEJ.ApplicationLayer.DataTypes.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;

public class BookTicket {
    static TextField phoneInput;
    static TextField emailInput;
    static TextField lastNameInput;
    static TextField firstNameInput;
    static ComboBox genderInput;
    static TextField birthdayInput;
    static TextField nationalityInput;
    static TextField passportNumberInput;
    static TextField streetAndNumberInput;
    static TextField cityInput;
    static TextField zipcodeInput;
    static TextField countryInput;
    static TextField expDate;
    static ComboBox comboBoxClass;
    static VBox orderViewBox;
    static HBox orderBuyButtons;
    static Label ticketPrice;
    static Label seatNo;
    static Label labelOrdes;
    static Label totalPrice;
    static Button makeOrderButton;
    static Button buyButton;
    static int total;
    static int seat_Number;

    // GridPane with Labels, Textfields, ComboBox
    // General information about the customer
    public static GridPane makeGeneralInfo(){
        //GridPane with 10px padding around edge
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        //First Name Label + TextField
        Label firstNameLabel = new Label("First name: ");
        grid.setConstraints(firstNameLabel, 0, 0);
        firstNameInput = new TextField();
        firstNameInput.setId("customerInputTextfield");
        firstNameInput.setPromptText("First name ");
        firstNameInput.setAlignment(Pos.CENTER);
        firstNameInput.setMaxWidth(100);
        grid.setConstraints(firstNameInput, 1, 0);

        //Last Name Label + TextField
        Label lastNameLabel = new Label("Last name: ");
        grid.setConstraints(lastNameLabel, 0, 1);
        lastNameInput = new TextField();
        lastNameInput.setId("customerInputTextfield");
        lastNameInput.setPromptText("Last name");
        lastNameInput.setAlignment(Pos.CENTER);
        lastNameInput.setMaxWidth(100);
        grid.setConstraints(lastNameInput, 1, 1);

        //Gender Label + ComboBox
        Label gender = new Label("Gender:");
        grid.setConstraints(gender, 0, 2);
        genderInput = new ComboBox();
        genderInput.setMinWidth(100);
        genderInput.setPromptText("Select ...");
        genderInput.getItems().addAll("Male", "Female");
        grid.setConstraints(genderInput, 1, 2);

        // Birthday Label + TextField
        Label birthday = new Label("Date of Birth:");
        grid.setConstraints(birthday, 0, 3);
        birthdayInput = new TextField();
        birthdayInput.setId("customerInputTextfield");
        birthdayInput.setPromptText("dd/MM/yyyy");
        birthdayInput.setAlignment(Pos.CENTER);
        birthdayInput.setMaxWidth(100);
        grid.setConstraints(birthdayInput, 1, 3);

        // Nationality Label + TextField
        Label nationality = new Label("Nationality:");
        grid.setConstraints(nationality, 0, 4);
        nationalityInput = new TextField();
        nationalityInput.setId("customerInputTextfield");
        nationalityInput.setPromptText("Nationality");
        nationalityInput.setAlignment(Pos.CENTER);
        nationalityInput.setMaxWidth(100);
        grid.setConstraints(nationalityInput, 1, 4);

        // Passport Number Label + TextField
        Label passportNumber = new Label("Passport Number: ");
        grid.setConstraints(passportNumber, 0, 5);
        passportNumberInput = new TextField();
        passportNumberInput.setId("customerInputTextfield");
        passportNumberInput.setPromptText("Passport Nr.");
        passportNumberInput.setAlignment(Pos.CENTER);
        passportNumberInput.setMaxWidth(100);
        grid.setConstraints(passportNumberInput, 1, 5);
        addListenerToTextFieldProperty(passportNumberInput);

        // Passport Expiration Date Label + TextField
        Label pasExpDate = new Label("Valid through: ");
        grid.setConstraints(pasExpDate, 0, 6);
        expDate = new TextField();
        expDate.setId("customerInputTextfield");
        expDate.setPromptText("dd/MM/yyyy");
        expDate.setAlignment(Pos.CENTER);
        expDate.setMaxWidth(100);
        grid.setConstraints(expDate, 1 ,6);

        grid.setAlignment(Pos.CENTER);
        grid.getChildren().addAll(firstNameLabel, firstNameInput, lastNameLabel, lastNameInput, gender, genderInput,
                birthday, birthdayInput, nationality, nationalityInput, passportNumber,
                passportNumberInput, pasExpDate, expDate);
        return grid;
    }

    // GridPane with Labels and Textfields
    // Address information
    public static GridPane addressInfo(){
        //GridPane with 10px padding around edge
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(1, 1, 1, 1));
        grid.setVgap(8);
        grid.setHgap(5);

        // Street and Number Label + TextField
        Label street = new Label("Street&Nr.:");
        grid.setConstraints(street, 0, 0);
        streetAndNumberInput = new TextField();
        streetAndNumberInput.setId("customerInputTextfield");
        streetAndNumberInput.setPromptText("Street and number");
        streetAndNumberInput.setAlignment(Pos.CENTER);
        grid.setConstraints(streetAndNumberInput, 1, 0);

        // City HBox
        HBox cityHbox = new HBox(2);
        Label city = new Label("City: ");
        cityInput = new TextField();
        cityInput.setId("customerInputTextfield");
        cityInput.setPromptText("City");
        cityInput.setAlignment(Pos.CENTER);
        cityInput.setMinWidth(80);
        cityInput.setMaxWidth(80);
        cityHbox.getChildren().addAll(city, cityInput);
        grid.setConstraints(cityHbox, 0, 1);

        // Zip code HBox
        HBox zipHbox = new HBox(5);
        Label zip = new Label("ZIP code: ");
        zipcodeInput = new TextField();
        zipcodeInput.setId("customerInputTextfield");
        zipcodeInput.setPromptText("zip");
        zipcodeInput.setAlignment(Pos.CENTER);
        zipcodeInput.setMinWidth(90);
        zipcodeInput.setMaxWidth(90);
        zipHbox.getChildren().addAll(zip, zipcodeInput);
        grid.setConstraints(zipHbox, 1, 1);
        addListenerToTextFieldProperty(zipcodeInput);

        Label country = new Label("Country: ");
        grid.setConstraints(country, 0, 2);
        countryInput = new TextField();
        countryInput.setId("customerInputTextfield");
        countryInput.setPromptText("Country");
        countryInput.setAlignment(Pos.CENTER);
        grid.setConstraints(countryInput, 1, 2);

        grid.getChildren().addAll(street, streetAndNumberInput, cityHbox, zipHbox, country, countryInput);
        grid.setAlignment(Pos.CENTER);

        return grid;
    }

    // GridPane with Labels, TextFields
    // Contact information
    public static GridPane makeContactInfo(){
        //GridPane with 10px padding around edge
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(1, 1, 1, 1));
        grid.setVgap(8);
        grid.setHgap(10);

        //Telephone Number Label + TextField
        Label phoneLabel = new Label("Phone Number:");
        grid.setConstraints(phoneLabel, 0, 0);
        phoneInput = new TextField();
        phoneInput.setId("customerInputTextfield");
        phoneInput.setPromptText("Phone Number");
        phoneInput.setAlignment(Pos.CENTER);
        grid.setConstraints(phoneInput, 1, 0);
        addListenerToTextFieldProperty(phoneInput);

        //Email Label + TextField
        Label emailLabel = new Label("Email:");
        grid.setConstraints(emailLabel, 0, 1);
        emailInput = new TextField();
        emailInput.setId("customerInputTextfield");
        emailInput.setPromptText("Email");
        emailInput.setAlignment(Pos.CENTER);
        grid.setConstraints(emailInput, 1, 1);

        grid.getChildren().addAll(phoneLabel, phoneInput, emailLabel, emailInput);
        grid.setAlignment(Pos.CENTER);

        return grid;
    }

    // This is the layout of the left part of the scene - a VBox
    // it contains the leg information, the ticket information and the customer input
    public static void makeLayout(Leg leg, Stage window) throws Exception {
        List<Ticket> newBookedTickets = new ArrayList<>();
        total = 0;

        VBox layout = new VBox(20);
        HBox hBox = new HBox(30);
        HBox layout1 = new HBox(30);

        Label customerInfo = new Label("Customer information ");
        Label contactInfo = new Label("Contact information ");
        Label addressInfo = new Label("Address");
        Label flightInfo = new Label("Flight information");
        Label flightType = new Label("Choose Class");

        VBox vBox2 = new VBox(5);
        vBox2.getChildren().addAll(flightInfo, makeLegInfo(leg));
        vBox2.setAlignment(Pos.CENTER);

        VBox vBox = new VBox(20);
        vBox.getChildren().addAll(customerInfo, makeGeneralInfo());
        vBox.setAlignment(Pos.CENTER);

        VBox vBox1 = new VBox(30);
        vBox1.getChildren().addAll(addressInfo, addressInfo(), contactInfo, makeContactInfo());
        vBox1.setAlignment(Pos.CENTER);

        hBox.getChildren().addAll(vBox, vBox1);
        hBox.setAlignment(Pos.CENTER);

        VBox vBox3 = new VBox(20);
        vBox3.getChildren().addAll(flightType, makeFlightTypeInfo(leg, newBookedTickets));
        vBox3.setAlignment(Pos.CENTER);

        // generate order with tickets - the right side
        orderViewBox = new VBox(10);
        orderViewBox.setAlignment(Pos.CENTER_LEFT);

        //
        HBox saveAndNewCustomerBox = new HBox(20);

        GridPane showTicketPane = new GridPane();
        showTicketPane.setPrefSize(650, 540);
        showTicketPane.setHgap(30);

        Button saveButton = new Button("Save");
        saveButton.setPrefWidth(100);

        // save button is set on action
        saveButton.setOnAction(e -> {
            if(genderInput.getValue() == null || firstNameInput.getText().isEmpty() || lastNameInput.getText().isEmpty()
                    || birthdayInput.getText().isEmpty()|| nationalityInput.getText().isEmpty()
                    || passportNumberInput.getText().isEmpty() || expDate.getText().isEmpty()
                    || streetAndNumberInput.getText().isEmpty() || cityInput.getText().isEmpty()
                    || zipcodeInput.getText().isEmpty() || countryInput.getText().isEmpty()
                    || comboBoxClass.getValue() == null || emailInput.getText().isEmpty()
                    || phoneInput.getText().isEmpty()) {
                        String alertText = "Please fill out all fields.";
                        ShowAlert.makeErrorAlert(alertText);                   // checks if all fields are filled out
            }
            else {
                if (!CheckInputFormat.isValidDate(expDate.getText()) || !CheckInputFormat.isValidDate(birthdayInput.getText())) {
                    showDateAlert();         // checks if the date (birthday, passport expiration date) are in the right format
                } else

                    try {
                        orderViewBox.getChildren().removeAll(totalPrice, orderBuyButtons);

                        // saves the address and the customer input
                        saveAddress();
                        saveCustomerInput();

                        // we need the ticket class, the seat number and the ticket price
                        int classType = convertClassToInt(comboBoxClass.getValue().toString());
                        int seatNo = seat_Number;
                        int ticketPrice = LegInfo.getPrice(leg, comboBoxClass.getValue().toString());

                        // shows total price
                        total = total + ticketPrice;
                        totalPrice.setText("TOTAL: " + Integer.toString(total) + " kr");

                        // creates a ticket with all this information
                        // it will be added to the newBookedTickets array
                        Ticket ticket = new Ticket(CustomerServiceStaff.ticketsSize + 1, leg.getLegID(), CustomerServiceStaff.customersSize + 1,
                                classType, seatNo, ticketPrice, "booked");

                        CustomerServiceStaff.ticketsSize += 1;
                        CustomerServiceStaff.customersSize += 1;

                        newBookedTickets.add(ticket);

                        // generates ticket and adds it to the right side (under the 'Orders' Label)
                        HBox ticketsViewBox = GenerateTicketToBook.makeOneTicket(leg);
                        showTicketPane.setConstraints(ticketsViewBox, 0, newBookedTickets.size() - 1);
                        showTicketPane.getChildren().add(ticketsViewBox);

                        // all TextFields are cleared in case there is another customer with on the same flight
                                                                                      // and on the same order
                        clearInputTextFields();

                        orderViewBox.getChildren().addAll(totalPrice, orderBuyButtons);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
            }});

        saveAndNewCustomerBox.getChildren().addAll(saveButton);
        saveAndNewCustomerBox.setAlignment(Pos.CENTER);

        buyButton = new Button("Buy Tickets");
        buyButton.setPrefWidth(100);
        buyButton.setOnAction(e ->
        {
            try
            {
                // Send information about each ticket to the Application Layer
                // They will be sent to the database and the tables will be updated
                for(Ticket ticket: newBookedTickets)
                {
                    ticket.setTicketState("confirmed");  // the state of the tickets is changed to 'confirmed'
                    TicketInfo.bookTicket(ticket);                 // because the tickets are bought directly
                    LegInfo.updateSeat(ticket.getLegID(), ticket.getTicketClass());
                    OrderInfo.addTicketToOrder(ticket);
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }

            try
            {   // creates a new order
                // information will be sent in the orders_tickets table as well
                OrderInfo.createOrders_Tickets(newBookedTickets);
                OrderInfo.createOrder(LogInScene.employeeID, CustomerServiceStaff.customersSize - newBookedTickets.size() + 1);
            }
            catch (Exception e1) {
                e1.printStackTrace();
            }
            try {
                makeConfirmAlert();       // informs user that the tickets have been bought
            }catch (Exception e2){
                e2.printStackTrace();
            }
            newBookedTickets.clear();
        });

        makeOrderButton = new Button("Make order");
        makeOrderButton.setPrefWidth(100);
        makeOrderButton.setOnAction(e ->
        {
            try
            {   // Send information about each ticket to the Application Layer
                // They will be sent to the database and the tables will be updated

                for(Ticket ticket: newBookedTickets)
                {
                    TicketInfo.bookTicket(ticket);
                    LegInfo.updateSeat(ticket.getLegID(), ticket.getTicketClass());
                    OrderInfo.addTicketToOrder(ticket);
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }

            try
            {   // creates a new order
                // information will be sent in the orders_tickets table as well
                OrderInfo.createOrders_Tickets(newBookedTickets);
                OrderInfo.createOrder(LogInScene.employeeID, CustomerServiceStaff.customersSize - newBookedTickets.size() + 1);

            } catch (Exception e1) {
                e1.printStackTrace();
            }
            try {
                makeConfirmAlert();         // informs the user that the tickets have been booked
            }catch (Exception e2){
                e2.printStackTrace();
            }
            newBookedTickets.clear();
            showTicketPane.getChildren().clear();
            orderViewBox.getChildren().removeAll(totalPrice, makeOrderButton);
        });

        orderBuyButtons = new HBox(20);
        orderBuyButtons.getChildren().addAll(makeOrderButton, buyButton);
        orderBuyButtons.setAlignment(Pos.CENTER);

        labelOrdes = new Label("Orders");
        labelOrdes.setId("labelOrders");

        totalPrice = new Label();

        orderViewBox.getChildren().addAll(labelOrdes, showTicketPane);
        orderViewBox.setAlignment(Pos.CENTER);

        layout.getChildren().addAll(vBox2, vBox3, hBox, saveAndNewCustomerBox);
        layout.setAlignment(Pos.CENTER);
        layout.setId("vBox");

        // everything is added to the layout
        layout1.getChildren().addAll(layout, orderViewBox);

        Scene scene = new Scene(layout1, 1250, 700);
        scene.getStylesheets().add("CustomerCSS");
        window.setScene(scene);
        window.show();
    }

    // collects information from the TextFields and sends it to the Application Layer
    public static void saveCustomerInput() throws Exception{
        List<Address> allAddresses = AddressInfo.selectAllAddresses();

        CustomerInfo.createCustomer2(firstNameInput.getText(), lastNameInput.getText(), genderInput.getValue().toString(), birthdayInput.getText(),
                nationalityInput.getText(), Integer.parseInt(passportNumberInput.getText()), expDate.getText(), allAddresses.size(),
                emailInput.getText(), phoneInput.getText());
    }

    // collects information from the TextFields and sends it to the Application Layer
    public static void saveAddress() throws Exception{
        AddressInfo.createAddress(streetAndNumberInput.getText(), cityInput.getText(),
                                Integer.parseInt(zipcodeInput.getText()), countryInput.getText());
    }

    // clears all TextFields, ComboBoxes
    public static void clearInputTextFields()
    {
        //clears the selected flight input
        comboBoxClass.setValue("Select...");
        seatNo.setText("");
        ticketPrice.setText("");

        //clear customer input
        firstNameInput.clear();
        lastNameInput.clear();
        genderInput.setValue(null);
        birthdayInput.clear();
        nationalityInput.clear();
        passportNumberInput.clear();
        expDate.clear();
        emailInput.clear();
        phoneInput.clear();

        //clear address input
        streetAndNumberInput.clear();
        cityInput.clear();
        zipcodeInput.clear();
        countryInput.clear();
    }

    // the upper part of the scene
    // the information about the leg
    // contains also the change flight button that leads back to searching a flight
    public static GridPane makeLegInfo(Leg leg){
        GridPane gridPaneLegInfo = new GridPane();
        gridPaneLegInfo.setPadding(new Insets(20, 10, 10, 10));
        gridPaneLegInfo.setVgap(8);
        gridPaneLegInfo.setHgap(15);

        Label labelDepartAirport = new Label("From: " + leg.getDepartureAirport());
        Label labelArrivalAirport = new Label("To: " + leg.getArrivalAirport());
        Label labelDepartDate = new Label("Depart. Date: " + leg.getDepartureDate());
        Label labelDepartTime = new Label("Depart. Time: " + leg.getDepartureTime());
        Label labelArrivalDate = new Label("Arrival Date: " + leg.getArrivalDate());
        Label labelArrivalTime = new Label("Arrival Time: " + leg.getArrivalTime());

        Button buttonChange = new Button("Change Flight");
        buttonChange.setOnAction(e -> {
            CustomerServiceStaff.window.setScene(CustomerServiceStaff.customerScene);
        });

        gridPaneLegInfo.setConstraints(labelDepartAirport, 0, 0);
        gridPaneLegInfo.setConstraints(labelArrivalAirport, 0, 1);
        gridPaneLegInfo.setConstraints(labelDepartDate, 1, 0);
        gridPaneLegInfo.setConstraints(labelDepartTime, 1, 1);
        gridPaneLegInfo.setConstraints(labelArrivalDate, 2, 0);
        gridPaneLegInfo.setConstraints(labelArrivalTime, 2, 1);
        gridPaneLegInfo.setConstraints(buttonChange, 2, 2);

        gridPaneLegInfo.getChildren().addAll(labelDepartAirport, labelArrivalAirport, labelDepartDate, labelDepartTime,
                                            labelArrivalDate, labelArrivalTime, buttonChange);
        gridPaneLegInfo.setAlignment(Pos.CENTER);

        return gridPaneLegInfo;
    }

    // information about the ticket type
    // ComboBox for selecting a ticket class: first class, business or economy
    // the seat number and the ticket price
    public static GridPane makeFlightTypeInfo(Leg leg, List<Ticket> newBookedTickets){
        GridPane gridFlightTypeInfo = new GridPane();
        gridFlightTypeInfo.setPadding(new Insets(10, 10, 10, 10));
        gridFlightTypeInfo.setVgap(8);
        gridFlightTypeInfo.setHgap(20);

        Label labelClass = new Label("Choose class: ");

        Label price = new Label("Price: ");
        ticketPrice = new Label("       ");

        Label seat = new Label("Seat No: ");
        seatNo = new Label("        ");

        comboBoxClass = new ComboBox();
        comboBoxClass.setPromptText("Select...");
        comboBoxClass.getItems().addAll("First Class", "Business Class", "Economy Class");

        // when a ticket class is chosen, the seat number is generate accordingly and also the ticket price
        comboBoxClass.setOnAction(e ->
        {   try {
            //show price and seat
            int classType = convertClassToInt(comboBoxClass.getValue().toString());
            seat_Number = SeatInfo.method(leg, classType) + newBookedTickets.size();
            ticketPrice.setText(LegInfo.getPrice(leg, comboBoxClass.getValue().toString()) + "kr");
            seatNo.setText("" + seat_Number);

        }catch (Exception e2){
            e2.printStackTrace();
        }
        });

        seatNo.textProperty().addListener((observable, oldValue, newValue) -> {
            if(seatNo.getText().contains("-1"))
                showSeatAlert();                   // if there are no availble seats, it will show alert
        });

        gridFlightTypeInfo.setConstraints(labelClass, 0, 0);
        gridFlightTypeInfo.setConstraints(comboBoxClass, 1, 0);
        gridFlightTypeInfo.setConstraints(price, 2, 0 );
        gridFlightTypeInfo.setConstraints(ticketPrice, 3, 0);
        gridFlightTypeInfo.setConstraints(seat, 4, 0);
        gridFlightTypeInfo.setConstraints(seatNo, 5, 0);

        gridFlightTypeInfo.getChildren().addAll(labelClass, comboBoxClass, price, ticketPrice, seat, seatNo);
        gridFlightTypeInfo.setAlignment(Pos.CENTER);

        return gridFlightTypeInfo;
    }

    // converts the String from the ComboBox to the related int value
    public static int convertClassToInt(String classType)
    {
        int customerClassType = 0;
        if(classType.equalsIgnoreCase("First Class"))
        {
            customerClassType = 1;
        }
        else if (classType.equalsIgnoreCase("Business Class"))
        {
            customerClassType = 2;
        }
        else if (classType.equalsIgnoreCase("Economy Class"))
        {
            customerClassType = 3;
        }
        return customerClassType;
    }

    // after the tickets are booked/bought, it shows information message and it goes back to the flight search scene
    public static void makeConfirmAlert() throws Exception{
        Alert confirmAlert = new Alert(Alert.AlertType.INFORMATION);
        confirmAlert.setTitle("Information");
        confirmAlert.setHeaderText(null);
        confirmAlert.setContentText("The order has been successfully placed.");
        confirmAlert.showAndWait();
        CustomerServiceStaff.window.setScene(CustomerServiceStaff.customerScene);
    }

    // shows error alert if all seats are booked
    private static void showSeatAlert()
    {
        String alertText = "There are no available seats for this class. Please choose another class or change flight.";
        ShowAlert.makeErrorAlert(alertText);
        seatNo.setText("");
        ticketPrice.setText("");
    }

    // shows error alert if the date format is wrong
    // clears the TextField with wrong format input
    public static void showDateAlert() {
        String alertText = "Wrong date format.";
        ShowAlert.makeErrorAlert(alertText);
        if (!CheckInputFormat.isValidDate(birthdayInput.getText()))
            birthdayInput.clear();
        if (!CheckInputFormat.isValidDate(expDate.getText()))
            expDate.clear();
    }

    // adds a listener to the TextFields that should have numeric input
    public static void addListenerToTextFieldProperty(TextField textField)
    {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!CheckInputFormat.isNumeric(textField.getText())) {
                String alertText = "Please enter numeric value.";
                ShowAlert.makeErrorAlert(alertText);
                textField.clear();
            }
        });
    }
}
