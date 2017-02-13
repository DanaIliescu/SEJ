// Customer Data Type - Created by Dana

package SEJ.ApplicationLayer.DataTypes;

public class Customer
{
    private int customerID;
    private String firstName;
    private String lastName;
    private String gender;
    private String birthday;
    private String nationality;
    private int passNumber;
    private String passExpirationDate;
    private String email;
    private String phone;
    private int addressID;

    // unused constructor
    public Customer(int customerID, String firstName, String lastName, String email, String phone)
    {
        this.customerID = customerID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
    }

    // to construct a customer without customer ID, used for writing in database
    // in the CustomerSQL when we write a new Customer the customer ID is 'DEFAULT'
    public Customer(String firstName, String lastName, String gender, String birthday, String nationality,
                    int pasNumber, String pasExpirationDate, int addressID, String email, String phone)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.birthday = birthday;
        this.nationality = nationality;
        this.passNumber = pasNumber;
        this.passExpirationDate = pasExpirationDate;
        this.addressID = addressID;
        this.email = email;
        this.phone = phone;
    }

    //to construct a customer with Customer ID
    public Customer(int customerID, String firstName, String lastName, String gender, String birthday,
                    String nationality, int passNumber, String pasExpiredDate, int addressID, String email, String phone)
    {
        this.customerID = customerID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.birthday = birthday;
        this.nationality = nationality;
        this.passNumber = passNumber;
        this.passExpirationDate = pasExpiredDate;
        this.email = email;
        this.phone = phone;
        this.addressID = addressID;
    }

    public int getCustomerID()
    {
        return customerID;
    }

    public void setCustomerID(int customerID)
    {
        this.customerID = customerID;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getGender()
    {
        return gender;
    }

    public void setGender(String gender)
    {
        this.gender = gender;
    }

    public String getBirthday()
    {
        return birthday;
    }

    public void setBirthday(String birthday)
    {
        this.birthday = birthday;
    }

    public void setPassExpirationDate(String passExpirationDate)
    {
        this.passExpirationDate = passExpirationDate;
    }

    public int getAddressID()
    {
        return addressID;
    }

    public void setAddressID(int addressID)
    {
        this.addressID = addressID;
    }

    public String getNationality()
    {
        return nationality;
    }

    public void setNationality(String nationality)
    {
        this.nationality = nationality;
    }

    public int getPasNumber()
    {
        return passNumber;
    }

    public void setPasNumber(int pasNumber)
    {
        this.passNumber = pasNumber;
    }

    public String getPassExpirationDate()
    {
        return passExpirationDate;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    @Override
    public String toString()
    {
        return "Customer{" +
                "customerID='" + customerID + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender='" + gender + '\'' +
                ", birthday=" + birthday +
                ", nationality='" + nationality + '\'' +
                ", pasNumber='" + passNumber + '\'' +
                ", passExpirationDate=" + passExpirationDate +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + addressID + '\'' +
                '}';
    }
}
