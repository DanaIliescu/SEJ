// EmployeeInfo Class - Created by Marius

package SEJ.ApplicationLayer;
import SEJ.ApplicationLayer.DataTypes.Employee;
import SEJ.DataAccessLayer.EmployeeSQL;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

public class EmployeeInfo{
    private static List<Employee> employees;

    // gets all employees from the Data Access Layer
    public static List<Employee> selectAllEmployees() throws Exception
    {
        employees = EmployeeSQL.loadAllEmployees();
        return employees;
    }

    // OSCA part
    // loops through the array of all employees, writes them in CSV file
    public static void readEmployeesInTable(List<Employee> getEmployeesTable) throws Exception
    {
        FileOutputStream fos = new FileOutputStream("employees.CSV", false);

        fos.write(makeComment().getBytes() );

        for(Employee e : getEmployeesTable)
        {
            fos.write(e.toString().getBytes());
        }
        fos.close();
    }

    // adds a comment at the beginning of CSV file
    public static String makeComment(){
        String comment = "/*\n" +
                "         Employees Table\n" +
                "id,fname,lname,username,password\n" +
                "*/\n";
        return comment;
    }

    // ignores comment from the CSV file
    // writes data without comment in the employeeOutput.txt file
    public static void readIgnoreComment()throws Exception {
        Scanner input = new Scanner(new File("employees.CSV"));
        PrintStream output = new PrintStream (new File("employeeOutput.txt"));
        boolean flag = false;

        while (input.hasNextLine() ) {
            String line = input.nextLine();
            if(line.startsWith("/*"))
                flag = true;
            if(line.endsWith("*/")) {
                flag = false;
            }
            Scanner lineScan = new Scanner(line);
            while (lineScan.hasNextLine() && flag == false) {
                if (line.startsWith("*/"))
                    break;
                else
                    output.println(lineScan.nextLine());
            }
        }
    }

}
