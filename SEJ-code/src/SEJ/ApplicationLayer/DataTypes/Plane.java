// Plane Data Type - Created by Lei

package SEJ.ApplicationLayer.DataTypes;

public class Plane
{
    private int planeID;
    private String planeType;
    private int firstClassSeatTotal;
    private int businessSeatTotal;
    private int coachSeatTotal;

    // constructor with all fields
    public Plane(int planeID, String planeType, int firstClassSeatTotal, int businessSeatTotal, int coachSeatTotal)
    {
        this.planeID = planeID;
        this.planeType = planeType;
        this.firstClassSeatTotal = firstClassSeatTotal;
        this.businessSeatTotal = businessSeatTotal;
        this.coachSeatTotal = coachSeatTotal;
    }

    // constructor without plane id
    public Plane(String planeType, int firstClassSeatTotal, int businessSeatTotal, int coachSeatTotal)
    {
        this.planeType = planeType;
        this.firstClassSeatTotal = firstClassSeatTotal;
        this.businessSeatTotal = businessSeatTotal;
        this.coachSeatTotal = coachSeatTotal;
    }

    public Plane()
    {
    }

    public int getPlaneID()
    {
        return planeID;
    }

    public void setPlaneID(int planeID)
    {
        this.planeID = planeID;
    }

    public String getPlaneType()
    {
        return planeType;
    }

    public void setPlaneType(String planeType)
    {
        this.planeType = planeType;
    }

    public int getFirstClassSeatTotal()
    {
        return firstClassSeatTotal;
    }

    public void setFirstClassSeatTotal(int firstClassSeatTotal)
    {
        this.firstClassSeatTotal = firstClassSeatTotal;
    }

    public int getBusinessSeatTotal()
    {
        return businessSeatTotal;
    }

    public void setBusinessSeatTotal(int businessSeatTotal)
    {
        this.businessSeatTotal = businessSeatTotal;
    }

    public int getCoachSeatTotal()
    {
        return coachSeatTotal;
    }

    public void setCoachSeatTotal(int coachSeatTotal)
    {
        this.coachSeatTotal = coachSeatTotal;
    }

    @Override
    public String toString()
    {
        return planeID + "," + planeType + "," + firstClassSeatTotal + "," + businessSeatTotal + "," + coachSeatTotal + "\n";
    }

}
