// CheckInputFormat Class - Created by Lei
// Used for checking if the information typed in by user is in the right format

package SEJ.ApplicationLayer;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class CheckInputFormat
{
    /* The following method is copied from http://stackoverflow.com
    * http://stackoverflow.com/questions/1102891/how-to-check-if-a-string-is-numeric-in-java*/
    public static boolean isNumeric(String str) {
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c))
                return false;
        }
        return true;
    }

    /* The following method is copied from http://www.java2s.com
    http://www.java2s.com/Tutorial/Java/0120__Development/CheckifaStringisavaliddate.htm*/
    public static boolean isValidDate(String inDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(inDate.trim());
        } catch (ParseException pe) {
            return false;
        }
        return true;
    }

    // method created by Dana
    public static boolean isTime(String str){
        if(str.length() != 5)
            return false;
        if(!Character.isDigit(str.charAt(0)) || !Character.isDigit(str.charAt(1)) || !Character.isDigit(str.charAt(3))
                || !Character.isDigit(str.charAt(4)))
            return false;
        if(str.charAt(2)!= ':')
            return false;
        return true;
    }
}
