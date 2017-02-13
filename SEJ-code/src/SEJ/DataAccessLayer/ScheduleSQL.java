// Class to write from and to the 'schedules' table in the database - created by Dana

package SEJ.DataAccessLayer;
import SEJ.ApplicationLayer.DataTypes.Schedule;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ScheduleSQL {
    private static ArrayList<Schedule> schedules = new ArrayList<Schedule>();

    // receive data from database, put in array
    public static List<Schedule> loadAllSchedules() throws Exception {
        Connection con = MySqlConnection.getConnection();
        Statement st = con.createStatement();
        String sql = ("SELECT * FROM schedules");
        ResultSet rs = st.executeQuery(sql);

        while (rs.next()) {
            int plane_id = rs.getInt("plane_id");
            int leg_id = rs.getInt("leg_id");
            schedules.add(new Schedule(plane_id, leg_id));
        }

        con.close();
        rs.close();
        st.close();

        return schedules;
    }

    //create new schedule and add it to schedules table
    public static void createSchedule(Schedule schedule) throws Exception{
        Connection con = MySqlConnection.getConnection();
        Statement st = con.createStatement();
        String sql = ("INSERT INTO schedules VALUES (" + schedule.getPlaneID() + ", " + schedule.getLegID() + ")");
        st.executeUpdate(sql);

        con.close();
        st.close();
    }

    // returns a the plane id that has the leg id specified in the schedules table
    // used for handling seats and booking a ticket
    public static int getSearchedPlane(int legId) throws Exception{
        int plane_id = 0;

        Connection con = MySqlConnection.getConnection();
        Statement st = con.createStatement();
        String sql = ("SELECT plane_id\n" +
                      "FROM schedules\n" +
                      "WHERE leg_id = " + legId);
        ResultSet rs = st.executeQuery(sql);

        while (rs.next()) {
            plane_id = rs.getInt("plane_id");
        }

        con.close();
        rs.close();
        st.close();

        return plane_id;
    }
}
