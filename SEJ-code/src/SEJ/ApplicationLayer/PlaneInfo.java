// PlaneInfo Class - created by Felix

package SEJ.ApplicationLayer;
import SEJ.ApplicationLayer.DataTypes.Plane;
import SEJ.DataAccessLayer.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

public class PlaneInfo {
    private static List<Plane> planes;
    static Plane plane;

    // gets all planes from the Data Access Layer
    public static List<Plane> selectAllPlanes() throws Exception
    {
        planes = PlaneSQL.loadAllPlanes();
        return planes;
    }

    // sends plane to the Data Access Layer
    // adds it to the array
    public static void createPlane(String planeType, int firstClassSeats, int businessClassSeats, int econClassSeats) throws Exception {
        plane = new Plane(planeType, firstClassSeats, businessClassSeats, econClassSeats);
        PlaneSQL.createPlane(plane);
        planes.add(new Plane(planes.size()+1, planeType, firstClassSeats, businessClassSeats, econClassSeats));
    }

    // OSCA part
    // loops through the array of all employees, writes them in CSV file
    public static void readPlanesInTable(List<Plane> getPlanesTable) throws Exception
    {
        FileOutputStream fos = new FileOutputStream("planes.CSV", false);

        fos.write(makeComment().getBytes());

        for(Plane p : getPlanesTable)
        {
            fos.write(p.toString().getBytes());
        }
        fos.close();
    }

    // adds a comment at the beginning of CSV file
    public static String makeComment(){
        String comment = "/*\n" +
                "           Planes Table\n" +
                "id,     type,   first, business, economy\n" +
                "*/\n";
        return comment;
    }

    // ignores comment from the CSV file
    // writes data without comment in the planeOutput.txt file
    public static void readIgnoreComment()throws Exception {
        Scanner input = new Scanner(new File("planes.CSV"));
        PrintStream output = new PrintStream (new File("planesOutput.txt"));
        boolean flag = false;

        while (input.hasNextLine() ) {
            String line = input.nextLine();
            if(line.startsWith("/*"))
                flag = true;
            if(line.endsWith("*/"))
                flag = false;
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
