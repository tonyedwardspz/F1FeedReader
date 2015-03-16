package purelywebdesign.f1feedreader;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import purelywebdesign.f1feedreader.helpers.DriverJSONAdapter;
import purelywebdesign.f1feedreader.helpers.JSONHelper;


public class DriverStandings extends Fragment implements AdapterView.OnItemClickListener {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_SECTION_NUMBER = "section_number";
    private static final String QUERY_URL = "http://ergast.com/api/f1/current/driverStandings.json";
    public static DriverJSONAdapter driverJSONAdapterDriver = null;
    ListView driverListView;


    public DriverStandings() {}


    public static DriverStandings newInstance(int sectionNumber) {
        DriverStandings fragment = new DriverStandings();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_standings, container, false);

        // set up the list view adapter and get the data
        driverJSONAdapterDriver = new DriverJSONAdapter(getActivity(), inflater);
        driverListView = (ListView) rootView.findViewById(R.id.standings_listview);
        driverListView.setAdapter(driverJSONAdapterDriver);
        JSONHelper.submitQuery(QUERY_URL, 1);

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

    public static void prepareDriverJSON(JSONObject jsonObject){
        Log.d("JSON REPLY: ", jsonObject.toString());

        try {
            JSONObject js1 = jsonObject.getJSONObject("MRData");
            JSONObject js2 = js1.getJSONObject("StandingsTable");
            JSONArray js3 = js2.getJSONArray("StandingsLists");
            JSONObject js4 = js3.getJSONObject(0);

            driverJSONAdapterDriver.updateData(js4.getJSONArray("DriverStandings"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
