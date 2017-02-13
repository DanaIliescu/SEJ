// Class to read from and write to the 'addresses' table in the database - created by Dana

package SEJ.DataAccessLayer;
import SEJ.ApplicationLayer.DataTypes.Address;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AddressSQL {

    // receive data from database, put in array
    public static List<Address> loadAllAddresses() throws Exception {
        List<Address> addresses = new ArrayList<>();
        Connection con = MySqlConnection.getConnection();
        Statement st = con.createStatement();
        String sql = ("SELECT * FROM addresses");
        ResultSet rs = st.executeQuery(sql);

        while (rs.next()) {
            int address_id = rs.getInt("address_id");
            String street_number = rs.getString("address_street_number");
            String address_city = rs.getString("address_city");
            int address_zip_code = rs.getInt("address_zip_code");
            String address_country = rs.getString("address_country");
            addresses.add(new Address(address_id, street_number, address_city, address_zip_code, address_country));
        }

        con.close();
        rs.close();
        st.close();

        return addresses;
    }

    //create new address and add it to addresses table
    public static void createAddress(Address address) throws Exception{
        Connection con = MySqlConnection.getConnection();
        Statement st = con.createStatement();
        String sql = ("INSERT INTO addresses VALUES (DEFAULT, "
                + "\"" + address.getStreetAndNumber() + "\""+ ", "
                + "\"" + address.getCity() + "\"" + ", "
                +        address.getZip() + ", "
                + "\"" + address.getCountry() + "\"" + ")");
        st.executeUpdate(sql);

        con.close();
        st.close();
    }
}