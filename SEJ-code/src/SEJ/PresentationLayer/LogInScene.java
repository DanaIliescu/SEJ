// LoginScene Class - created by Marius

package SEJ.PresentationLayer;
import SEJ.ApplicationLayer.DataTypes.Employee;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LogInScene {

    static private GridPane logInPane;
    static private TextField employeeUsername;
    static private PasswordField employeePWInput;
    static private Label logInMessage, welcomeToSystem;
    static private Button logInButton;
    static private ImageView imageView_logo;
    static int employeeID;

    // returns a VBox containing the login info
    public static VBox makeLogInScene_logo(Stage primaryStage)
    {
        logInPane = makeLogInScene(primaryStage);
        imageView_logo = makeLogo();

        welcomeToSystem = new Label("Welcome");
        welcomeToSystem.setId("welcomeLabel");
        Label label= new Label();
        VBox logInScene_pic = new VBox(10);
        logInScene_pic.getChildren().addAll(label, welcomeToSystem, imageView_logo, logInPane);
        logInScene_pic.setAlignment(Pos.CENTER);

        return logInScene_pic;
    }

    // creates the login using a GridPane
    // it contains Labels and TextFields
    public static GridPane makeLogInScene(Stage primaryStage)
    {
        logInPane = new GridPane();
        logInPane.setMinWidth(250);
        logInPane.setMinHeight(200);
        logInPane.setHgap(10);
        logInPane.setVgap(10);

        Label employeeName_label = new Label("Username");
        employeeUsername = new TextField();
        Label password = new Label("Password");
        employeePWInput = new PasswordField();
        employeePWInput.setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.ENTER) {
                setLogInButtonAction(primaryStage);
            }
        });
        logInMessage = new Label();
        logInButton = new Button("Log in");
        logInButton.setMinWidth(80);
        logInButton.setOnAction(e -> setLogInButtonAction(primaryStage));

        logInPane.add(employeeName_label, 0, 0);
        logInPane.add(employeeUsername, 1, 0);
        logInPane.add(password, 0, 1);
        logInPane.add(employeePWInput, 1, 1);
        logInPane.add(logInButton, 1, 2);
        logInPane.add(logInMessage, 1, 3, 2, 3);

        logInPane.setAlignment(Pos.CENTER);

        return logInPane;
    }

    // when the login button is pressed, the scene is changed to either admin scene or customer service scene
    // depending on the username and password the user typed in
    public static void setLogInButtonAction(Stage primaryStage)
    {
        {
            String checkUsername = employeeUsername.getText();
            String checkPassword = employeePWInput.getText();
            try
            {
                int employeesSize = Main.allEmployees.size();

                // loops through the employees array, finds the one that matches username and password
                for(int i = 0; i < employeesSize; i++)
                {
                    Employee employee = Main.allEmployees.get(i);
                    String employeeUsername = employee.getUserName();
                    String employeePW = employee.getPassword();
                    String employeeTitle = employee.getTitle();
                    employeeID = employee.getEmployeeID();

                    // if the title of the employee is 'admin', the scene is changed to the admin scene
                    if (checkPassword.equalsIgnoreCase(employeePW) && checkUsername.equalsIgnoreCase(employeeUsername) && employeeTitle.equalsIgnoreCase("admin"))
                    {
                        Scene adminScene = AdminScene.returnAdminScene(primaryStage);
                        primaryStage.setScene(adminScene);
                        primaryStage.show();
                        break;
                        // if the title of the employee is 'customer_service', the scene is changed to the customer service scene
                    }else if (checkPassword.equalsIgnoreCase(employeePW) && checkUsername.equalsIgnoreCase(employeeUsername) && employeeTitle.equalsIgnoreCase("customer_service"))
                    {
                        Scene adminScene = CustomerServiceStaff.returnCustomerService(primaryStage);
                        primaryStage.setScene(adminScene);
                        primaryStage.show();
                        break;
                    }
                    // if the username and password do not match with any of the employee's, displays message
                    else
                    {
                        logInMessage.setText("Sorry, user does not exist.");
                    }
                }
            } catch (Exception e1)
            {
                e1.printStackTrace();
            }
        }
    }

    // the logo
    public static ImageView makeLogo()
    {
        Image logoImage = new Image(LogInScene.class.getResourceAsStream("SEJ_LOGO.jpg"));
        logoImage.getClass().getResourceAsStream("SEJ_LOGO.jpg");
        ImageView imageView_logo = new ImageView(logoImage);
        imageView_logo.setFitWidth(280);
        imageView_logo.setPreserveRatio(true);
        imageView_logo.setSmooth(true);
        return imageView_logo;
    }
}
