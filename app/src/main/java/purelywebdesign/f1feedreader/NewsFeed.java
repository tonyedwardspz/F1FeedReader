package purelywebdesign.f1feedreader;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import java.util.ArrayList;

import purelywebdesign.f1feedreader.entities.BBCItem;
import purelywebdesign.f1feedreader.helpers.XMLHelper;


public class NewsFeed extends Fragment implements AdapterView.OnItemClickListener {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_SECTION_NUMBER = "section_number";

    public static TextView newsTitle;

    public static NewsFeed newInstance(int sectionNumber) {
        NewsFeed fragment = new NewsFeed();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }


    public NewsFeed() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_news_feed, container, false);

        newsTitle = (TextView) rootView.findViewById(R.id.newsTitle);
        XMLHelper.submitQuery("http://feeds.bbci.co.uk/sport/0/formula1/rss.xml");

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


    public static void prepareXML (ArrayList<BBCItem> items){
        Log.d("prepare xml", "");

        newsTitle.setText(items.get(0).getTitle());
    }
}
