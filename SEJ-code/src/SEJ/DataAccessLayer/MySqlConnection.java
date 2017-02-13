// Class to connect to the database - created by Dana

package SEJ.DataAccessLayer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConnection {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DATABASE_URL = "jdbc:mysql://localhost:3306/sej";
    static Connection con;

    // Establishing the connection
    public static Connection connect() throws SQLException {
        try{
            con = null;
            Class.forName (JDBC_DRIVER);
        }catch(ClassNotFoundException cnfe){
            System.err.println("Error: "+cnfe.getMessage());
        }
        // in the url we have to tell which account and password to use
        con =  DriverManager.getConnection(DATABASE_URL, "root", "numeletrandafirului2");
        return con;
    }

    // returns the open connection
    // used in the SQL classes
    public static Connection getConnection() throws SQLException, ClassNotFoundException{
        if(con !=null && !con.isClosed())
            return con;
        connect();
        return con;
    }
}
