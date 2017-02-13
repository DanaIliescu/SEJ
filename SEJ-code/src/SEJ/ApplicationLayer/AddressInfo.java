// AddressInfo Class - Created by Lei

package SEJ.ApplicationLayer;
import SEJ.ApplicationLayer.DataTypes.Address;
import SEJ.DataAccessLayer.AddressSQL;
import java.util.List;

public class AddressInfo {
    static Address address;

    // gets all addresses from the Data Access Layer
    public static List<Address> selectAllAddresses() throws Exception {
        List<Address> addresses;
        addresses = AddressSQL.loadAllAddresses();
        return addresses;
    }

    // sends address to the Data Access Layer
    public static void createAddress(String streetAndNumber, String city, int zip, String country) throws Exception {
        address = new Address(streetAndNumber, city, zip, country);
        AddressSQL.createAddress(address);
    }
}
