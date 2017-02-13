// Main Class - created by Marius

package SEJ.PresentationLayer;
import SEJ.ApplicationLayer.DataTypes.Employee;
import SEJ.ApplicationLayer.DataTypes.Leg;
import SEJ.ApplicationLayer.DataTypes.Plane;
import SEJ.ApplicationLayer.EmployeeInfo;
import SEJ.ApplicationLayer.LegInfo;
import SEJ.ApplicationLayer.PlaneInfo;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.List;

// starts with loading information into arrays
// it leads to the login scene
public class Main extends Application {

    static List<Employee> allEmployees;
    static List<Plane> allPlanes;
    static List<Leg> allLegs;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        allEmployees = EmployeeInfo.selectAllEmployees();
        allPlanes = PlaneInfo.selectAllPlanes();
        allLegs = LegInfo.selectAllLegs();

        primaryStage.setTitle("SEJ");

        // VBox with the login scene
        VBox logInScene_pic = LogInScene.makeLogInScene_logo(primaryStage);

        Scene scene = new Scene(logInScene_pic, 400, 500);
        scene.getStylesheets().add("CSS.css");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

}
