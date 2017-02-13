// ShowAlert class - created by Lei
// Manages alerts - has three types of alerts with String parameters which will become the Content Text of the alert

package SEJ.PresentationLayer;
import javafx.scene.control.Alert;

public class ShowAlert
{
    public static void makeErrorAlert(String text)
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERROR");
        alert.setHeaderText(null);
        alert.setContentText(text);
        alert.showAndWait();
    }

    public static void makeInformationAlert(String text)
    {
        Alert refundAlert = new Alert(Alert.AlertType.INFORMATION);
        refundAlert.setTitle("Information");
        refundAlert.setHeaderText(null);
        refundAlert.setContentText(text);
        refundAlert.showAndWait();
    }

    public static Alert makeConfirmAlert(String text)
    {
        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmAlert.setTitle("Confirmation");
        confirmAlert.setHeaderText(null);
        confirmAlert.setContentText(text);
        return confirmAlert;
    }
}
