package purelywebdesign.f1feedreader;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import purelywebdesign.f1feedreader.helpers.JSONHelper;
import purelywebdesign.f1feedreader.helpers.NextRaceJSONAdapter;
import purelywebdesign.f1feedreader.helpers.Utilities;


public class NextRace extends Fragment implements AdapterView.OnItemClickListener {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_SECTION_NUMBER = "section_number";
    private static final String QUERY_URL = "http://ergast.com/api/f1/2015.json";
    public static NextRaceJSONAdapter NextRaceJSONAdapterDriver = null;

    public static TextView nextRaceRound;
    public static TextView nextRaceTitle;


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

        // set up the list view adapter and get the data
        NextRaceJSONAdapterDriver = new NextRaceJSONAdapter(getActivity(), inflater);

        nextRaceTitle = (TextView) rootView.findViewById(R.id.next_race_title);
        nextRaceRound = (TextView) rootView.findViewById(R.id.next_race_round);

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

    /**
     * Cycles through the jsonObject looking for the next race, displaying results
     * if a match is found.
     * @param  jsonObject: Object containing the complete race data.
     */
    public static void prepareNextRaceJson(JSONObject jsonObject){
        JSONObject thisRace;
        String raceName = "Rubbish title";
        String raceDate = "";
        String roundNumber = "";
        JSONArray races;

        try {
            races = jsonObject.getJSONObject("MRData").getJSONObject("RaceTable").getJSONArray("Races");
            NextRaceJSONAdapterDriver.updateData(races);

            for (int i = 0; i < NextRaceJSONAdapterDriver.getCount(); i++){
                thisRace = races.getJSONObject(i);
                raceDate = thisRace.optString("date");
                raceDate += " ";
                raceDate += thisRace.optString("time");

                Boolean isNextRace = Utilities.compareDate(raceDate);
                if (isNextRace) {
                    raceName = thisRace.optString("raceName");
                    roundNumber = thisRace.optString("round");
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        nextRaceTitle.setText(raceName);
        nextRaceRound.setText(roundNumber);
    }
}
