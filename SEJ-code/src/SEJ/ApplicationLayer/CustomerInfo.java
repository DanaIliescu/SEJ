// CustomerInfo Class - Created by Marius

package SEJ.ApplicationLayer;
import SEJ.ApplicationLayer.DataTypes.Customer;
import SEJ.DataAccessLayer.CustomerSQL;
import java.util.List;

public class CustomerInfo{
    private static List<Customer> customers;
    static Customer customer;

    // gets all customers from the Data Access Layer
    public static List<Customer> selectAllCustomers() throws Exception
    {
        customers = CustomerSQL.loadAllCustomers();
        return customers;
    }

    // sends customer to the Data Access Layer
    public static void createCustomer2(String firstName, String lastName, String gender, String birthday,
                                       String nationality, int pasNumber, String pasExpirationDate, int addressID,
                                       String email, String phone)throws Exception {
        customer = new Customer(firstName, lastName, gender, birthday, nationality, pasNumber, pasExpirationDate, addressID,
                                email, phone);
        CustomerSQL.createCustomer(customer);
    }
}