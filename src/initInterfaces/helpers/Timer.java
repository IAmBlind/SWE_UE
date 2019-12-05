package initInterfaces.helpers;

import java.text.*;
import java.util.*;

public class Timer {

    //Function to get time. Information from: https://www.edureka.co/blog/date-format-in-java/
    public static String time(){
        // Initializing calender object
        Calendar calendar = Calendar.getInstance();
        // Initializing the data formatter
        SimpleDateFormat date = new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss z :", Locale.GERMANY);
        // Set Timezone
        date.setTimeZone(TimeZone.getTimeZone("GMT"));
        // Return the date
        return date.format(calendar.getTime());
    }

    // check for valid date
    /*public static boolean valid(){
        boolean validate = false;
        // Initializing a date format
        SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd");

        //TODO: funciton to check the date

        return validate;
    }*/
}
