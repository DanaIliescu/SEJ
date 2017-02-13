// FlightSearchResultTableView Class - created by Lei

package SEJ.PresentationLayer;
import SEJ.ApplicationLayer.DataTypes.FlightSearchResult;
import SEJ.ApplicationLayer.FlightSearchResultInfo;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

public class FlightSearchResultTableView
{
    // creates the necessary columns
    // returns the TableView with legs that match the search condition
    public static TableView makeTableView(String departureCity,
                                     String arrivalCity,
                                     String departureDate) throws Exception
    {
        TableView<FlightSearchResult> flightSearchTableView = new TableView<>();
        TableColumn<FlightSearchResult, String> column_departureCity, column_arrivalCity,  column_departureDate,
            column_departureTime, column_arrivalDate, column_arrivalTime;

        //create an Observable list of all flights that fits the search condition
        ObservableList<FlightSearchResult> allFlightSearchResults = FlightSearchResultInfo.createFlightSearchResultsProperty(departureCity, arrivalCity, departureDate);

        flightSearchTableView.setItems(allFlightSearchResults);

        //departure city column
        column_departureCity = new TableColumn<>("From");
        column_departureCity.setMinWidth(160);
        column_departureCity.setCellValueFactory(new PropertyValueFactory<>("departureCity"));
        column_departureCity.setCellFactory(TextFieldTableCell.<FlightSearchResult>forTableColumn());

        //arrival city column
        column_arrivalCity = new TableColumn<>("To");
        column_arrivalCity.setMinWidth(160);
        column_arrivalCity.setCellValueFactory(new PropertyValueFactory<>("arrivalCity"));
        column_arrivalCity.setCellFactory(TextFieldTableCell.<FlightSearchResult>forTableColumn());

        //departure date column
        column_departureDate = new TableColumn<>("Dep. Date");
        column_departureDate.setMinWidth(100);
        column_departureDate.setCellValueFactory(new PropertyValueFactory<>("departureDate"));
        column_departureDate.setCellFactory(TextFieldTableCell.<FlightSearchResult>forTableColumn());

        //departure time column
        column_departureTime = new TableColumn<>("Dep. Time");
        column_departureTime.setMinWidth(100);
        column_departureTime.setCellValueFactory(new PropertyValueFactory<>("departureTime"));
        column_departureTime.setCellFactory(TextFieldTableCell.<FlightSearchResult>forTableColumn());

        //arrival date column
        column_arrivalDate = new TableColumn<>("Arr. Date");
        column_arrivalDate.setMinWidth(100);
        column_arrivalDate.setCellValueFactory(new PropertyValueFactory<>("arrivalDate"));
        column_arrivalDate.setCellFactory(TextFieldTableCell.<FlightSearchResult>forTableColumn());

        //arrival time column
        column_arrivalTime = new TableColumn<>("Arr. Time");
        column_arrivalTime.setMinWidth(100);
        column_arrivalTime.setCellValueFactory(new PropertyValueFactory<>("arrivalTime"));
        column_arrivalTime.setCellFactory(TextFieldTableCell.<FlightSearchResult>forTableColumn());

        flightSearchTableView.getColumns().addAll(column_departureCity, column_arrivalCity,
                column_departureDate, column_departureTime, column_arrivalDate, column_arrivalTime);
        flightSearchTableView.setMaxHeight(250);

        return flightSearchTableView;
    }

}
