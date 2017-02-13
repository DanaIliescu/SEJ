// Address Data Type - created by Dana

package SEJ.ApplicationLayer.DataTypes;

public class Address {
    private int addressID;
    private String streetAndNumber;
    private String city;
    private int zip;
    private String country;

    public Address(int addressID, String streetAndNumber, String city, int zip, String country) {
        this.addressID = addressID;
        this.streetAndNumber = streetAndNumber;
        this.city = city;
        this.zip = zip;
        this.country = country;
    }

    public Address(String streetAndNumber, String city, int zip, String country) {
        this.streetAndNumber = streetAndNumber;
        this.city = city;
        this.zip = zip;
        this.country = country;
    }

    public int getAddressID() {
        return addressID;
    }

    public void setAddressID(int addressID) {
        this.addressID = addressID;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public String getStreetAndNumber() {
        return streetAndNumber;
    }

    public void setStreetAndNumber(String streetAndNumber) {
        this.streetAndNumber = streetAndNumber;
    }

    @Override
    public String toString() {
        return "Address{" +
                "addressID=" + addressID +
                ", streetAndNumber='" + streetAndNumber + '\'' +
                ", city='" + city + '\'' +
                ", zip=" + zip +
                ", country='" + country + '\'' +
                '}';
    }
}
