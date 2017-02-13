// OrderSearchResultTableView - created by Felix, Dana

package SEJ.PresentationLayer;
import SEJ.ApplicationLayer.DataTypes.OrderSearchResult;
import SEJ.ApplicationLayer.OrderSearchResultInfo;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;

public class OrderSearchResultTableView {
    // creates the necessary columns
    // returns the TableView with orders searched
    public static TableView makeTableView(int orderId) throws Exception
    {
        TableView<OrderSearchResult> orderSearchTableView = new TableView<>();
        TableColumn<OrderSearchResult, String> column_firstName,  column_lastName, column_ticketState;
        TableColumn<OrderSearchResult, Integer> column_orderId, column_ticketId, column_ticketClass, column_ticketPrice, column_ticketSeatNr;

        //create an Observable list of all orders that fit the search condition

        ObservableList<OrderSearchResult> allOrderSearchResults = OrderSearchResultInfo.createOrderSearchResultsProperty(orderId);
        orderSearchTableView.setItems(allOrderSearchResults);

        //order ID column
        column_orderId = new TableColumn<>("Order Id");
        column_orderId.setMinWidth(80);
        column_orderId.setMaxWidth(80);
        column_orderId.setCellValueFactory(new PropertyValueFactory<>("orderID"));
        column_orderId.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

        //ticket ID column
        column_ticketId = new TableColumn<>("Ticket Id");
        column_ticketId.setMinWidth(80);
        column_ticketId.setMaxWidth(80);
        column_ticketId.setCellValueFactory(new PropertyValueFactory<>("ticketID"));
        column_ticketId.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

        //first name column
        column_firstName = new TableColumn<>("First Name");
        column_firstName.setMinWidth(120);
        column_firstName.setMaxWidth(120);
        column_firstName.setCellValueFactory(new PropertyValueFactory<>("customerFirstName"));
        column_firstName.setCellFactory(TextFieldTableCell.forTableColumn());

        //last name column
        column_lastName = new TableColumn<>("Last Name");
        column_lastName.setMinWidth(120);
        column_lastName.setMaxWidth(120);
        column_lastName.setCellValueFactory(new PropertyValueFactory<>("customerLastName"));
        column_lastName.setCellFactory(TextFieldTableCell.<OrderSearchResult>forTableColumn());

        //ticket class column
        column_ticketClass = new TableColumn<>("Class");
        column_ticketClass.setMinWidth(80);
        column_ticketClass.setMaxWidth(80);
        column_ticketClass.setCellValueFactory(new PropertyValueFactory<>("ticketClass"));
        column_ticketClass.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

        //ticket price column
        column_ticketPrice = new TableColumn<>("Price");
        column_ticketPrice.setMinWidth(80);
        column_ticketPrice.setMaxWidth(80);
        column_ticketPrice.setCellValueFactory(new PropertyValueFactory<>("ticketPrice"));
        column_ticketPrice.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

        //ticket seat number column
        column_ticketSeatNr = new TableColumn<>("Seat Nr.");
        column_ticketSeatNr.setMinWidth(80);
        column_ticketSeatNr.setMaxWidth(80);
        column_ticketSeatNr.setCellValueFactory(new PropertyValueFactory<>("ticketSeatNumber"));
        column_ticketSeatNr.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

        //ticket state
        column_ticketState = new TableColumn<>("Ticket State");
        column_ticketState.setMinWidth(80);
        column_ticketState.setMaxWidth(80);
        column_ticketState.setCellValueFactory(new PropertyValueFactory<>("ticketState"));
        column_ticketState.setCellFactory(TextFieldTableCell.<OrderSearchResult>forTableColumn());

        orderSearchTableView.getColumns().addAll(column_orderId, column_ticketId, column_firstName,
                column_lastName, column_ticketClass, column_ticketPrice, column_ticketSeatNr, column_ticketState);
        orderSearchTableView.setMaxHeight(250);

        return orderSearchTableView;
    }

    // when a ticket is cancelled or refunded, it is removed from the Observable List and consequently from the TableView
    public static void deleteRow(TableView tableView){
        try {
            ObservableList<OrderSearchResult> allOrderResults = tableView.getItems();
            ObservableList<OrderSearchResult> readOnlyItems = tableView.getSelectionModel().getSelectedItems();

            //Removes all selected elements in the table
            readOnlyItems.stream().forEach((item) -> {
                allOrderResults.remove(item);
            });

            //Clear the selection
            tableView.getSelectionModel().clearSelection();

            }catch (Exception exception){
            exception.printStackTrace();
        }
    }

    // when a ticket is confirmed, the tableview is updated
    public static void updateRow(TableView tableView){
        try {
            ObservableList<OrderSearchResult> allOrderResults = tableView.getItems();
            ObservableList<OrderSearchResult> readOnlyItems = tableView.getSelectionModel().getSelectedItems();

            // finds the ticket chosen and changes its state to "confirmed"
            for(int i = 0; i < allOrderResults.size(); i++){
                if (allOrderResults.get(i) == readOnlyItems.get(0))
                    allOrderResults.get(i).ticketStateProperty().setValue("confirmed");
            }

            //Clear the selection
            tableView.getSelectionModel().clearSelection();

        }catch (Exception exception){
            exception.printStackTrace();
        }
    }

}
