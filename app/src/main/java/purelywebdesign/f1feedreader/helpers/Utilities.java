package purelywebdesign.f1feedreader.helpers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Anthony on 12/03/2015.
 */
public class Utilities {


    private static final DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    /**
     * Compares the next race date to the current date. Formats the date
     * to allow comparisons
     * @param  raceDate: The date of the next race in the JSON array
     * @return Boolean: whether the race is next the calendar
     */
    public static Boolean compareDate(String raceDate) throws Exception {
        Date today = sdf.parse(sdf.format(new Date()));
        Date parsedRaceDate = parseDate(raceDate);

        if (parsedRaceDate.compareTo(today) > 0){
            return true;
        } else {
            return false;
        }
    }

    /**
     * Parses a given date string to yyyy-MM-dd HH:mm.
     * @param  dateToParse: The date of the next race in the JSON array
     * @return Date: Date formateted to the DateFormat in sdf
     */
    public static Date parseDate(String dateToParse){
        Date parsedDate = null;

        try {
            parsedDate = (Date) sdf.parse(dateToParse);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return parsedDate;
    }


}
