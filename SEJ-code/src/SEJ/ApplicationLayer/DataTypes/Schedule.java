// Schedule Data Type - Created by Lei

package SEJ.ApplicationLayer.DataTypes;

public class Schedule
{
    int planeID;
    int legID;

    public Schedule(int planeID, int legID)
    {
        this.planeID = planeID;
        this.legID = legID;
    }

    public int getPlaneID() {
        return planeID;
    }

    public void setPlaneID(int planeID) {
        this.planeID = planeID;
    }

    public int getLegID() {
        return legID;
    }

    public void setLegID(int legID) {
        this.legID = legID;
    }
}
