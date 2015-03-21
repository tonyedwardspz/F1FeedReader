package purelywebdesign.f1feedreader;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import java.util.ArrayList;

import purelywebdesign.f1feedreader.adapters.NextRaceAdapter;
import purelywebdesign.f1feedreader.entities.Race;
import purelywebdesign.f1feedreader.helpers.JSONHelper;
import purelywebdesign.f1feedreader.helpers.Utilities;


public class NextRace extends Fragment implements AdapterView.OnItemClickListener {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_SECTION_NUMBER = "section_number";
    private static final String QUERY_URL = "http://ergast.com/api/f1/2015.json";
    public static NextRaceAdapter NextRaceJSONAdapterDriver = null;

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
        NextRaceJSONAdapterDriver = new NextRaceAdapter(getActivity(), inflater);

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

    public static void displayData(ArrayList<Race> allRaces ) throws Exception {

        for (int i = 0; i < allRaces.size(); i++) {
            Race thisRace = allRaces.get(i);

            if (Utilities.compareDate(thisRace.getRaceDateTime())) {
                nextRaceRound.setText(Integer.toString(thisRace.getRound()));
                nextRaceTitle.setText(thisRace.getRaceName());
                break;
            }
        }


    }
}
