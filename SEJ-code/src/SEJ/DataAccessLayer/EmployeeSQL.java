// Class to write from and to the 'employees' table in the database - created by Dana

package SEJ.DataAccessLayer;
import SEJ.ApplicationLayer.DataTypes.Employee;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EmployeeSQL {
    private static ArrayList<Employee> employees = new ArrayList<Employee>();

    // receive data from database, put in array
    public static List<Employee> loadAllEmployees() throws Exception {
        Connection con = MySqlConnection.getConnection();
        Statement st = con.createStatement();
        String sql = ("SELECT * FROM employees");
        ResultSet rs = st.executeQuery(sql);

        while (rs.next()) {
            byte employee_id = rs.getByte("employee_id");
            String employee_first_name = rs.getString("employee_first_name");
            String employee_last_name = rs.getString("employee_last_name");
            String employee_title = rs.getString("employee_title");
            String employee_username = rs.getString("employee_username");
            String employee_password = rs.getString("employee_password");
            employees.add(new Employee(employee_id, employee_first_name, employee_last_name, employee_title,
                                       employee_username, employee_password));
        }

        con.close();
        rs.close();
        st.close();

        return employees;
    }
}
