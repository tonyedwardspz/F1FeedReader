package purelywebdesign.f1feedreader.helpers;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.CalendarContract;

import purelywebdesign.f1feedreader.entities.Race;

/**
 * Created by Anthony on 22/03/2015.
 */
public class IntentHelper {

    private static Intent intent;


    /**
     * Sends a new intent into the ether, aimed at displaying the location on a map
     */
    public static void mapIntent(Race thisRace, Context context){
        intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("geo:" + thisRace.getLatitude() + "," + thisRace.getLongitude()));
        if (intent.resolveActivity(context.getPackageManager()) != null){
            context.startActivity(intent);
        }
    }

    /**
     * Sends a new intent into the ether, aimed at adding the race to a calendar
     */
    public static void calendarIntent(Race thisRace, Context context){
        String description = "Round " + thisRace.getRound() + " of the F1 championship at the " +
                             thisRace.getCircuitName() + ", " + thisRace.getCountry() + ".";


        intent = new Intent(Intent.ACTION_INSERT);
        intent.setData(CalendarContract.Events.CONTENT_URI);

        intent.putExtra(CalendarContract.Events.TITLE, thisRace.getRaceName());
        intent.putExtra(CalendarContract.Events.EVENT_LOCATION, thisRace.getLocality() + ", "
                                                              + thisRace.getCountry());
        intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, Utilities.parseDate(thisRace.getRaceDateTime()).getTime());
        intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, Utilities.parseDate(thisRace.getRaceDateTime()).getTime() + 7200000);
        intent.putExtra(CalendarContract.Events.DESCRIPTION, description);

        if (intent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(intent);
        }
    }
}
