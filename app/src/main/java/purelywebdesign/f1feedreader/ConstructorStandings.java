package purelywebdesign.f1feedreader;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import purelywebdesign.f1feedreader.adapters.ConstructorJSONAdapter;
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
        View rootView = inflater.inflate(R.layout.fragment_standings, container, false);

        // set up the list view adapter and get the data
        constructorJSONAdapterDriver = new ConstructorJSONAdapter(getActivity(), inflater);
        constructorListView = (ListView) rootView.findViewById(R.id.standings_listview);
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


}
