// Class to write from and to the 'legs' table in the database - created by Dana

package SEJ.DataAccessLayer;
import SEJ.ApplicationLayer.DataTypes.Leg;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LegSQL {

    //receive data, put in array
    public static List<Leg> loadAllLegs() throws Exception {
        List<Leg> legs = new ArrayList<>();
        Connection con = MySqlConnection.getConnection();
        Statement st = con.createStatement();
        String sql = ("SELECT * FROM legs");
        ResultSet rs = st.executeQuery(sql);

        while (rs.next()) {
            int leg_id = rs.getInt("leg_id");
            String departure_airport = rs.getString("departure_airport");
            String arrival_airport = rs.getString("arrival_airport");
            String departure_date = rs.getString("departure_date");
            String arrival_date = rs.getString("arrival_date");
            String departure_time = rs.getString("departure_time");
            String arrival_time = rs.getString("arrival_time");
            byte first_seat_booked = (byte) rs.getInt("first_seat_booked");
            byte business_seat_booked = (byte) rs.getInt("business_seat_booked");
            short coach_seat_booked = (short) rs.getInt("coach_seat_booked");
            int price = rs.getInt("price_first_class");

            legs.add(new Leg(leg_id, departure_airport, arrival_airport, departure_date, departure_time, arrival_date,
                            arrival_time, first_seat_booked, business_seat_booked, coach_seat_booked, price));
        }

        con.close();
        rs.close();
        st.close();

        return legs;
    }

    // method used to increse number of seats booked for a specific class
    public static void updateSeatBookedState(int legId, int classType) throws Exception
    {
        Connection con = MySqlConnection.getConnection();
        Statement st = con.createStatement();

        String sql1 = "";
        String sql2 = "";
        String sql3 = "";

            sql1 = ("UPDATE legs \n"
                    + "SET first_seat_booked" + " = first_seat_booked + 1 "
                    + "WHERE leg_id = " + legId);

            sql2 = ("UPDATE legs \n"
                    + "SET business_seat_booked" + " =  business_seat_booked + 1"
                    + "WHERE leg_id = " + legId);

            sql3 = ("UPDATE legs \n"
                    + "SET coach_seat_booked" + " = coach_seat_booked + 1 "
                    + "WHERE leg_id = " + legId);
        if(classType == 1)
            st.executeUpdate(sql1);
        else if (classType == 2)
                st.executeUpdate(sql2);
                else
                    st.executeUpdate(sql3);
        con.close();
        st.close();
    }

    //create new leg and add it to legs table
    public static void createLeg(Leg leg) throws Exception{
        Connection con = MySqlConnection.getConnection();
        Statement st = con.createStatement();
        String sql = ("INSERT INTO legs VALUES (" + leg.getLegID() + ", "
                + "\"" + leg.getDepartureAirport() + "\""+ ", "
                + "\"" + leg.getArrivalAirport() + "\"" + ", "
                + "\"" + leg.getDepartureDate() + "\""+ ", "
                + "\"" + leg.getDepartureTime() + "\""+ ", "
                + "\"" + leg.getArrivalDate() + "\""+ ", "
                + "\"" + leg.getArrivalTime() + "\""+ ", "
                + leg.getFirstSeatBooked() + ", "
                + leg.getBusinessSeatBooked() + ", "
                + leg.getCoachSeatBooked() + ", "
                + leg.getPrice()+ ")");
        st.executeUpdate(sql);

        con.close();
        st.close();
    }

}
