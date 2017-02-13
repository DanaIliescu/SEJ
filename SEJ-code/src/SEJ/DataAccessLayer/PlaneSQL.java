// Class to write from and to the 'planes' table in the database - created by Dana

package SEJ.DataAccessLayer;
import SEJ.ApplicationLayer.DataTypes.Plane;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PlaneSQL {
    private static ArrayList<Plane> planes = new ArrayList<Plane>();

    // receive data from database, put in array
    public static List<Plane> loadAllPlanes() throws Exception {
        Connection con = MySqlConnection.getConnection();
        Statement st = con.createStatement();
        String sql = ("SELECT * FROM planes");
        ResultSet rs = st.executeQuery(sql);

        while (rs.next()) {
            int plane_id = rs.getInt("plane_id");
            String plane_type = rs.getString("plane_type");
            int first_class_seats = rs.getInt("first_class_seats");
            int business_class_seats = rs.getInt("business_class_seats");
            int economy_class_seats = rs.getInt("economy_class_seats");
            planes.add(new Plane(plane_id, plane_type, first_class_seats, business_class_seats, economy_class_seats));
        }

        con.close();
        rs.close();
        st.close();

        return planes;
    }

    //create new plane and add it to planes table
    public static void createPlane(Plane plane) throws Exception{
        Connection con = MySqlConnection.getConnection();
        Statement st = con.createStatement();
        String sql = ("INSERT INTO planes VALUES (" + plane.getPlaneID() + ", "
                + "\""+ plane.getPlaneType() + "\""+ ", "
                + plane.getFirstClassSeatTotal() + ", "
                + plane.getBusinessSeatTotal() + ", "
                + plane.getCoachSeatTotal() + ")");
        st.executeUpdate(sql);

        con.close();
        st.close();
    }
}
