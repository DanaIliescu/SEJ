// OutputCSV Class - created by Felix
// OSCA part
// creates the CSV files (with the comment) and the output files (without the comment)

package SEJ.PresentationLayer;
import SEJ.ApplicationLayer.EmployeeInfo;
import SEJ.ApplicationLayer.PlaneInfo;
import javafx.scene.control.Button;

public class OutputCSV
{
    public static Button makeOutputDatabase()
    {
        Button outputDatabase = new Button("Create CSV file");
        outputDatabase.setOnAction(e -> {
            try
            {
                EmployeeInfo.readEmployeesInTable(AdminScene.allEmployees);
                EmployeeInfo.readIgnoreComment();
                PlaneInfo.readPlanesInTable(AdminScene.allPlanes);
                PlaneInfo.readIgnoreComment();

                String alertText = "The files have been created.";
                ShowAlert.makeInformationAlert(alertText);
            } catch (Exception e1)
            {
                e1.printStackTrace();
            }
        });
        return outputDatabase;
    }
}
