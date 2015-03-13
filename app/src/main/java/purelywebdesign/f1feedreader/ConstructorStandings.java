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

import purelywebdesign.f1feedreader.helpers.ConstructorJSONAdapter;
import purelywebdesign.f1feedreader.helpers.JSONHelper;


public class ConstructorStandings extends Fragment implements AdapterView.OnItemClickListener {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_SECTION_NUMBER = "section_number";
    private static final String QUERY_URL = "http://ergast.com/api/f1/current/constructorStandings.json";
    public static ConstructorJSONAdapter constructorJSONAdapterDriver = null;
    ListView constructorListView;


    public ConstructorStandings() {}


    public static ConstructorStandings newInstance(int sectionNumber) {
        ConstructorStandings fragment = new ConstructorStandings();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_constructor_standings, container, false);

        // set up the list view adapter and get the data
        constructorJSONAdapterDriver = new ConstructorJSONAdapter(getActivity(), inflater);
        constructorListView = (ListView) rootView.findViewById(R.id.constructor_listview);
        constructorListView.setAdapter(constructorJSONAdapterDriver);
        JSONHelper.submitQuery(QUERY_URL, 2);

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

    public static void prepareConstructorJSON(JSONObject jsonObject){
        Log.d("JSON REPLY: ", jsonObject.toString());

        try {
            JSONObject js1 = jsonObject.getJSONObject("MRData");
            JSONObject js2 = js1.getJSONObject("StandingsTable");
            JSONArray js3 = js2.getJSONArray("StandingsLists");
            JSONObject js4 = js3.getJSONObject(0);

            constructorJSONAdapterDriver.updateData(js4.getJSONArray("ConstructorStandings"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
