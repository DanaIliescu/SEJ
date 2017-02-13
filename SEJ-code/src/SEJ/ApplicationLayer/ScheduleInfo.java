// ScheduleInfo Class - created by Marius

package SEJ.ApplicationLayer;
import SEJ.ApplicationLayer.DataTypes.Schedule;
import SEJ.DataAccessLayer.ScheduleSQL;
import java.util.List;

public class ScheduleInfo {
    private static List<Schedule> schedules;
    static Schedule schedule;

    // unused method
    // gets all schedules from the Data Access Layer
    public static List<Schedule> selectAllSchedules() throws Exception
    {
        schedules = ScheduleSQL.loadAllSchedules();
        return schedules;
    }

    // sends schedule to the Data Access Layer
    public static void createSchedule(int planeID, int legID) throws Exception{
        schedule = new Schedule(planeID, legID);
        ScheduleSQL.createSchedule(schedule);
    }

}
