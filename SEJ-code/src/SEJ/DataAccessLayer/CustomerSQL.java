// Class to read from and write to the 'customers' table in the database - created by Dana

package SEJ.DataAccessLayer;
import SEJ.ApplicationLayer.DataTypes.Customer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CustomerSQL {
    private static ArrayList<Customer> customers = new ArrayList<Customer>();

    //receive data, put in array
    public static List<Customer> loadAllCustomers() throws Exception {
        Connection con = MySqlConnection.getConnection();
        Statement st = con.createStatement();
        String sql = ("SELECT * FROM customers");
        ResultSet rs = st.executeQuery(sql);

        while (rs.next()) {
            int customer_id = rs.getInt("customer_id");
            String customer_first_name = rs.getString("customer_first_name");
            String customer_last_name = rs.getString("customer_last_name");
            String customer_gender = rs.getString("customer_gender");
            String customer_birthday = rs.getString("customer_birthday");
            String customer_nationality = rs.getString("customer_nationality");
            int pasNumber = rs.getInt("passport_number");
            String passExpirationDate = rs.getString("passport_expiration_date");
            int address_id = rs.getInt("address_id");
            String customer_email = rs.getString("customer_email");
            String customer_phone = rs.getString("customer_phone_number");
            customers.add(new Customer(customer_id, customer_first_name, customer_last_name, customer_gender,
                    customer_birthday, customer_nationality, pasNumber, passExpirationDate, address_id, customer_email,
                    customer_phone));
        }

        con.close();
        rs.close();
        st.close();

        return customers;
    }

    //create new customer and add it to customers table
    public static void createCustomer(Customer customer) throws Exception{
        Connection con = MySqlConnection.getConnection();
        Statement st = con.createStatement();
        String sql = ("INSERT INTO customers VALUES (DEFAULT" + ", "
                + "\"" + customer.getFirstName() + "\""+ ", "
                + "\"" + customer.getLastName()+ "\"" + ", "
                + "\"" + customer.getGender()+ "\"" + ", "
                + "\"" + customer.getBirthday()+ "\"" + ", "
                + "\"" + customer.getNationality()+ "\"" + ", "
                +        customer.getPasNumber() + ", "
                + "\"" + customer.getPassExpirationDate() +"\""  + ", "
                +        customer.getAddressID() + ", "
                + "\"" + customer.getEmail()+ "\"" + ", "
                + "\"" + customer.getPhone()+ "\"" + ")");
        st.executeUpdate(sql);

        con.close();
        st.close();
    }
}
