package purelywebdesign.f1feedreader;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import purelywebdesign.f1feedreader.entities.Race;
import purelywebdesign.f1feedreader.helpers.IntentHelper;
import purelywebdesign.f1feedreader.helpers.JSONHelper;
import purelywebdesign.f1feedreader.helpers.LocationHelper;
import purelywebdesign.f1feedreader.helpers.Utilities;


public class NextRace extends Fragment implements AdapterView.OnItemClickListener {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_SECTION_NUMBER = "section_number";
    private static final String QUERY_URL = "http://ergast.com/api/f1/2015.json";
    public static Context mContext;
    private static LocationHelper locationHelper;

    private static TextView nextRaceRound;
    private static TextView nextRaceTitle;
    private static TextView nextRaceCircuit;
    private static TextView nextRaceLocality;
    private static TextView nextRaceCountry;
    private static TextView nextRaceDate;
    private static TextView nextRaceTime;
    private static Button addToCalendar;
    private static Button viewMap;
    private static ImageView trackImage;
    private static TextView  userLocation;

    private static Race thisRace;

    public static NextRace newInstance(int sectionNumber) {
        NextRace fragment = new NextRace();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }


    public NextRace() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_next_race, container, false);
        mContext = getActivity();

        locationHelper = new LocationHelper(mContext, rootView);


        nextRaceTitle = (TextView) rootView.findViewById(R.id.next_race_title);
        nextRaceRound = (TextView) rootView.findViewById(R.id.next_race_round);
        nextRaceCircuit = (TextView) rootView.findViewById(R.id.next_race_circuit_name);
        nextRaceLocality = (TextView) rootView.findViewById(R.id.next_race_locality);
        nextRaceCountry = (TextView) rootView.findViewById(R.id.next_race_country);
        nextRaceDate = (TextView) rootView.findViewById(R.id.next_race_date);
        nextRaceTime = (TextView) rootView.findViewById(R.id.next_race_time);
        trackImage = (ImageView) rootView.findViewById(R.id.track_image);
        userLocation = (TextView) rootView.findViewById(R.id.userLocation);


        addToCalendar = (Button) rootView.findViewById(R.id.next_race_calendar_button);
        addToCalendar.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                IntentHelper.calendarIntent(thisRace, getActivity());
            }
        });

        viewMap = (Button) rootView.findViewById(R.id.next_race_map_button);
        viewMap.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                IntentHelper.mapIntent(thisRace, getActivity());
            }
        });

        JSONHelper.submitQuery(QUERY_URL, 3);

        return rootView;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION_NUMBER));
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }


    public static void displayData(ArrayList<Race> allRaces ) throws Exception {

        for (int i = 0; i < allRaces.size(); i++) {
            thisRace = allRaces.get(i);

            if (Utilities.compareDate(thisRace.getRaceDateTime())) {
                nextRaceRound.setText("Round " + Integer.toString(thisRace.getRound()));
                nextRaceTitle.setText(thisRace.getRaceName());
                nextRaceCircuit.setText(thisRace.getCircuitName());
                nextRaceLocality.setText(thisRace.getLocality());
                nextRaceCountry.setText(thisRace.getCountry());
                nextRaceDate.setText("Race Date: " + Utilities.parseDisplayDate(thisRace.getRaceDate()));
                nextRaceTime.setText("Start time: " + Utilities.parseDisplayTime(thisRace.getRaceTime()));
                int resId = mContext.getResources().getIdentifier(thisRace.getCircuitID(), "drawable", "purelywebdesign.f1feedreader");
                trackImage.setImageResource(resId);

                locationHelper.calculateUserDistance(thisRace.getLatitude(), thisRace.getLongitude(), userLocation);
                break;
            }
        }
    }
}
