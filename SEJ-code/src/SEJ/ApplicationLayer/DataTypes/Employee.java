// Employee Data Type - Created by Lei

package SEJ.ApplicationLayer.DataTypes;

public class Employee
{
    private byte employeeID;
    private String firstName;
    private String lastName;
    private String title;
    private String userName;
    private String password;

    // constructor
    public Employee(byte employeeID, String firstName, String lastName, String title, String userName, String password)
    {
        this.employeeID = employeeID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.title = title;
        this.userName = userName;
        this.password = password;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public int getEmployeeID()
    {
        return employeeID;
    }

    public void setEmployeeID(byte employeeID)
    {
        this.employeeID = employeeID;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    @Override
    public String toString()
    {
        return employeeID + "," + firstName + "," + lastName + "," + userName + "," + password + "\n";
    }
}
