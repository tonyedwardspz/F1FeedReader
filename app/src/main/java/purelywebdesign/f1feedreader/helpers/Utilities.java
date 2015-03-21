package purelywebdesign.f1feedreader.helpers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Anthony on 12/03/2015.
 */
public class Utilities {


    private static final DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private static final DateFormat newsSdf = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z");
    private static final DateFormat sdfJustDate = new SimpleDateFormat("yyyy-MM-dd");
    private static final DateFormat sdfJustTime = new SimpleDateFormat("HH:mm:ss");

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
     * Parses a given date string to yyyy-MM-dd
     * @param  dateToParse: The date of the next race in the JSON array
     * @return Date: Date formateted to the DateFormat in sdfJustDate
     */
    public static Date parseJustDate(String dateToParse){
        Date parsedDate = null;

        try {
            parsedDate = sdfJustDate.parse(dateToParse);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return parsedDate;
    }

    /**
     * Parses a given date string to HH:mm
     * @param  dateToParse: The date of the next race in the JSON array
     * @return Date: Date formateted to the DateFormat in sdfJustTime
     */
    public static Date parseJustTime(String dateToParse){
        Date parsedTime = null;
        dateToParse = dateToParse.substring(0, dateToParse.length()-1);

        try {
            parsedTime = sdfJustTime.parse(dateToParse);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return parsedTime;
    }

    /**
     * Parses a given date string to yyyy-MM-dd HH:mm.
     * @param  dateToParse: The date of the next race in the JSON array
     * @return Date: Date formateted to the DateFormat in sdf
     */
    public static Date parseDate(String dateToParse){
        Date parsedDate = null;

        try {
            parsedDate = sdf.parse(dateToParse);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return parsedDate;
    }

    /**
     * Parses a given date string to EEE, dd MMM yyyy HH:mm:ss z.
     * @param  dateToParse: The date of a news item
     * @return Date: Date formateted to the DateFormat in newsSdf
     */
    public static Date parseLongDate(String dateToParse){
        Date parsedDate = null;

        try {
            parsedDate = newsSdf.parse(dateToParse);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return parsedDate;
    }

//    /**
//     * Parses a given date string to yyyy-MM-dd.
//     * @param  toParse: Date from an newsItem object
//     * @return Date: Date formatted to the DateFormat in sdf
//     */
//    public static String dateToString (Date toParse){
//        String parsedDateString = null;
//
//        try {
//            parsedDateString = sdf.format(toParse);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return parsedDateString;
//    }

    /**
     * Prepares the description from the telegraph feed by removing
     * unneeded html tags, and adding punctuation
     * @param  description: String containing lots of html
     * @return Date: Clean string
     */
    public static String prepareTelegraphDescription(String description){
        String[] splitDescription = description.split("<");
        return splitDescription[0] + ".";
    }
}
