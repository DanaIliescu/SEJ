// AdminScene Class - created by Felix

package SEJ.PresentationLayer;
import SEJ.ApplicationLayer.*;
import SEJ.ApplicationLayer.DataTypes.Employee;
import SEJ.ApplicationLayer.DataTypes.Leg;
import SEJ.ApplicationLayer.DataTypes.Plane;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class AdminScene {

    static Stage window;
    static Button buttonAddPlane, buttonSavePlane, buttonNewSchedule, buttonNewLeg, buttonCSV, buttonLogOut;
    static TextField textFieldPlaneType, textFieldFirstClass, textFieldBusinessClass, textFieldEconClass, textFieldDepartAirport, textFieldArrivalAirport,
            textFieldDepartureTime, textFieldArrivalTime, textFieldPrice;
    static Label labelPlaneType, labelFirstClass, labelBusinessClass, labelEconClass, labelSelectPlane, labelAirport, labelDeparture, labelArrival, labelPrice;
    static GridPane gridPane;
    static HBox layout, hBoxNewFlightButtons;
    static List<Plane> allPlanes = new ArrayList<>();
    static List<Leg> allLegs = new ArrayList<>();
    static List<Employee> allEmployees = new ArrayList<>();
    static ComboBox<String> comboBoxPlanes;
    static DatePicker datePickerDepart, datePickerArrival;

    public static Scene returnAdminScene(Stage primaryStage) throws Exception{
        window = primaryStage;

        allPlanes = Main.allPlanes;
        allEmployees = Main.allEmployees;
        allLegs = Main.allLegs;

        gridPane = new GridPane();
        gridPane.setPadding(new Insets(10,10,10,10));
        gridPane.setHgap(10);
        gridPane.setVgap(15);
        gridPane.setId("gridPane");

        // The buttons on the left side are created, set on action and added to a VBox
        buttonNewSchedule = new Button("New Schedule");
        buttonNewSchedule.setMinWidth(120);
        buttonNewSchedule.setMaxWidth(120);
        buttonNewSchedule.setOnAction((e -> {
            try {
                newScheduleClicked();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }));

        buttonAddPlane = new Button("Add Plane");
        buttonAddPlane.setMinWidth(120);
        buttonAddPlane.setMaxWidth(120);
        buttonAddPlane.setOnAction(e -> {
            try {
                addPlaneClicked();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        buttonCSV = new Button();
        buttonCSV = OutputCSV.makeOutputDatabase();
        buttonCSV.setMinWidth(120);
        buttonCSV.setMaxWidth(120);

        buttonLogOut = new Button("Log out");
        buttonLogOut.setMinWidth(120);
        buttonLogOut.setMaxWidth(120);

        // logs out, goes back to the login scene
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
        vBoxButtons.getChildren().addAll(buttonNewSchedule, buttonAddPlane, buttonCSV, buttonLogOut);

        // the layout is a HBox, containing a VBox (the buttons on the left) and a GridPane (right side)
        layout = new HBox(50);
        layout.getChildren().addAll(vBoxButtons);

        // the empty new schedule GridPane pops up directly
        // that is because we think scheduling a new leg is the most frequent operation of the admin
        newScheduleClicked();

        Scene adminScene = new Scene(layout, 600, 450);
        adminScene.getStylesheets().add("CustomerCSS");
        return adminScene;
    }

    // when the add plane button is clicked
    // the gridpane will contain information about the plane to be added( Labels, TextFields)
    public static void addPlaneClicked() throws Exception {
        buttonAddPlane.setDisable(true);
        buttonNewSchedule.setDisable(false);
        gridPane.getChildren().clear();
        layout.getChildren().remove(gridPane);

        labelPlaneType = new Label("Plane Type: ");
        gridPane.setConstraints(labelPlaneType, 0, 0);

        textFieldPlaneType = new TextField();
        textFieldPlaneType.setPromptText("Plane Type");
        gridPane.setConstraints(textFieldPlaneType, 1, 0);

        labelFirstClass = new Label("First Class Seats: ");
        gridPane.setConstraints(labelFirstClass, 0, 1);

        textFieldFirstClass = new TextField();
        textFieldFirstClass.setPromptText("# first class seats");
        gridPane.setConstraints(textFieldFirstClass, 1, 1);
        addListenerToTextFieldProperty(textFieldFirstClass);

        labelBusinessClass = new Label("Business Class Seats: ");
        gridPane.setConstraints(labelBusinessClass, 0, 2);

        textFieldBusinessClass = new TextField();
        textFieldBusinessClass.setPromptText("# business class seats");
        gridPane.setConstraints(textFieldBusinessClass, 1, 2);
        addListenerToTextFieldProperty(textFieldBusinessClass);

        labelEconClass = new Label("Economy Class Seats: ");
        gridPane.setConstraints(labelEconClass, 0, 3);

        textFieldEconClass = new TextField();
        textFieldEconClass.setPromptText("# economy class seats");
        gridPane.setConstraints(textFieldEconClass, 1, 3);
        textFieldEconClass.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!CheckInputFormat.isNumeric(textFieldEconClass.getText())) {
                String alertText = "please enter numeric value";               // checking for the right input
                ShowAlert.makeErrorAlert(alertText);
                textFieldEconClass.clear();
            }
        });
        textFieldEconClass.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                savePlaneButtonClicked();        // on enter pressed it saves the plane
            }
        });

        buttonSavePlane = new Button("Save");
        gridPane.setConstraints(buttonSavePlane, 1, 4);
        buttonSavePlane.setOnAction(e -> savePlaneButtonClicked());

        gridPane.getChildren().addAll(labelPlaneType, textFieldPlaneType, labelFirstClass, textFieldFirstClass, labelBusinessClass,
                textFieldBusinessClass, labelEconClass, textFieldEconClass, buttonSavePlane);

        layout.getChildren().add(gridPane);
    }

    public static void savePlaneButtonClicked() {
        if(textFieldPlaneType.getText().isEmpty() || textFieldFirstClass.getText().isEmpty() || textFieldBusinessClass.getText().isEmpty() ||
                textFieldEconClass.getText().isEmpty()) {
            String alertText = "Please fill out all fields.";
            ShowAlert.makeErrorAlert(alertText);
        } else {
            // the information is collected from the TextFields and sent to the Application Layer for a plane to be created there
            try {
                PlaneInfo.createPlane(textFieldPlaneType.getText(), Integer.parseInt(textFieldFirstClass.getText()),
                        Integer.parseInt(textFieldBusinessClass.getText()), Integer.parseInt(textFieldEconClass.getText()));
                String alertText = textFieldPlaneType.getText() + " has been added to the fleet.";
                ShowAlert.makeInformationAlert(alertText);    // confirms adding a new plane
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }

        textFieldPlaneType.clear();   // clearing the TextFields
        textFieldFirstClass.clear();
        textFieldBusinessClass.clear();
        textFieldEconClass.clear();

    }


    public static void newScheduleClicked() throws Exception {
        // it creates the Labels, TextFields and DatePickers for new schedule
        gridPane.getChildren().clear();
        layout.getChildren().remove(gridPane);

        buttonNewSchedule.setDisable(true);
        buttonAddPlane.setDisable(false);

        labelSelectPlane = new Label("Select Plane:");
        gridPane.setConstraints(labelSelectPlane, 0, 0);

        // ComboBox with the plane id and plane type
        comboBoxPlanes = new ComboBox<>();
        comboBoxPlanes.setPromptText("Select...");
        for(int i = 0; i < allPlanes.size(); i++) {
            comboBoxPlanes.getItems().addAll(allPlanes.get(i).getPlaneID() + " " + allPlanes.get(i).getPlaneType());
        }
        gridPane.setConstraints(comboBoxPlanes, 1, 0);

        labelAirport = new Label("Airport info:");
        gridPane.setConstraints(labelAirport, 0, 1);

        textFieldDepartAirport = new TextField();
        textFieldDepartAirport.setPromptText("Departure");
        gridPane.setConstraints(textFieldDepartAirport, 1, 1);

        textFieldArrivalAirport = new TextField();
        textFieldArrivalAirport.setPromptText("Arrival");
        gridPane.setConstraints(textFieldArrivalAirport, 1, 2);

        labelDeparture = new Label("Departure info:");
        gridPane.setConstraints(labelDeparture, 0, 3);

        datePickerDepart = new DatePicker();
        datePickerDepart.setPromptText("Date");
        changeDatePickerFormat(datePickerDepart);
        gridPane.setConstraints(datePickerDepart, 1, 3);

        textFieldDepartureTime = new TextField();
        textFieldDepartureTime.setPromptText("Time (hh:mm)");
        gridPane.setConstraints(textFieldDepartureTime, 1, 4);

        labelArrival = new Label("Arrival info:");
        gridPane.setConstraints(labelArrival, 0, 5);

        datePickerArrival = new DatePicker();
        datePickerArrival.setPromptText("Date");
        changeDatePickerFormat(datePickerArrival);
        gridPane.setConstraints(datePickerArrival, 1, 5);

        textFieldArrivalTime = new TextField();
        textFieldArrivalTime.setPromptText("Time (hh:mm)");
        gridPane.setConstraints(textFieldArrivalTime, 1, 6);

        labelPrice = new Label("Price (kr):");
        gridPane.setConstraints(labelPrice, 0, 7);

        // the price has to be inputed for each leg for the first class
        // the price for the other classes will be calculated according to this price
        textFieldPrice = new TextField();
        textFieldPrice.setPromptText("Price (1st Class)");
        gridPane.setConstraints(textFieldPrice, 1, 7);
        textFieldPrice.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                scheduleLegClicked();
            }
        });
        addListenerToTextFieldProperty(textFieldPrice);

        buttonNewLeg = new Button("Schedule Leg");
        buttonNewLeg.setOnAction(e -> scheduleLegClicked());

        hBoxNewFlightButtons = new HBox(15);
        hBoxNewFlightButtons.getChildren().addAll(buttonNewLeg);
        gridPane.setConstraints(hBoxNewFlightButtons, 1, 8);

        // adding all children to the gridpane
        gridPane.getChildren().addAll(labelSelectPlane, comboBoxPlanes, labelAirport, textFieldDepartAirport, textFieldArrivalAirport,
                labelDeparture, datePickerDepart, textFieldDepartureTime, labelArrival, datePickerArrival, textFieldArrivalTime,
                labelPrice, textFieldPrice, hBoxNewFlightButtons);

        // adding the gridpane to the layout
        layout.getChildren().addAll(gridPane);
    }

    public static void scheduleLegClicked() {
        if(comboBoxPlanes.getValue() == null || textFieldDepartAirport.getText().isEmpty() || textFieldArrivalTime.getText().isEmpty() ||
                datePickerDepart.getValue() == null || textFieldDepartureTime.getText().isEmpty() || datePickerArrival.getValue() == null||
                textFieldArrivalAirport.getText().isEmpty()) {
            String alertText = "Please fill out all fields";
            ShowAlert.makeErrorAlert(alertText);                    // checks if all fields are filled out
        }
        else if (!CheckInputFormat.isTime(textFieldDepartureTime.getText()) || !CheckInputFormat.isTime(textFieldArrivalTime.getText())) {
            String alertText = "Wrong time format.";
            ShowAlert.makeErrorAlert(alertText);                    // checks for correct time format
        } else {

            try {
                // the information is taken from the TextFields, ComboBoxes and DatePickers and sent to the Application Layer
                LegInfo.createLeg(textFieldDepartAirport.getText(), textFieldArrivalAirport.getText(), datePickerDepart.getEditor().getText(),
                        textFieldDepartureTime.getText(), datePickerArrival.getEditor().getText(), textFieldArrivalTime.getText(), Integer.parseInt(textFieldPrice.getText()));
                ScheduleInfo.createSchedule(Integer.parseInt(comboBoxPlanes.getValue().substring(0,1)), allLegs.size());

                String alertText = "The new schedule has been added to the system.";
                ShowAlert.makeInformationAlert(alertText);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        textFieldDepartAirport.clear();   // clears all TextFields, DatePickers
        textFieldArrivalAirport.clear();
        datePickerDepart.setValue(null);
        datePickerDepart.getEditor().clear();
        textFieldDepartureTime.clear();
        datePickerArrival.setValue(null);
        datePickerArrival.getEditor().clear();
        textFieldArrivalTime.clear();
        textFieldPrice.clear();
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

    /* The following method is copied from
    http://code.makery.ch/blog/javafx-8-date-picker/
     */
    // it changes the default format of 'yyyy-mm-dd' to 'dd/MM/yyyy'
    public static void changeDatePickerFormat(DatePicker datePicker){
        String pattern = "dd/MM/yyyy";

        datePicker.setConverter(new StringConverter<LocalDate>() {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);

            @Override
            public String toString(LocalDate date) {
                if (date != null) {
                    return dateFormatter.format(date);
                } else {
                    return "";
                }
            }

            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dateFormatter);
                } else {
                    return null;
                }
            }
        });
    }
}


