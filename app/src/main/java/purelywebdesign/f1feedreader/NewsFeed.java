package purelywebdesign.f1feedreader;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import purelywebdesign.f1feedreader.adapters.NewsItemAdapter;
import purelywebdesign.f1feedreader.helpers.XMLHelper;


public class NewsFeed extends Fragment implements AdapterView.OnItemClickListener {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_SECTION_NUMBER = "section_number";
    private static final String BBC_QUERY_URL = "http://feeds.bbci.co.uk/sport/0/formula1/rss.xml";
    private static final String CRASH_QUERY_URL = "http://rss.feedsportal.com/c/350/f/537798/index.rss";
    private static final String TELEGRAPH_QUERY_URL = "http://www.telegraph.co.uk/sport/motorsport/formulaone/rss";
    public static NewsItemAdapter newsAdapter = null;
    public static ListView newsList;


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

        newsAdapter = new NewsItemAdapter(getActivity(), inflater);
        newsList = (ListView) rootView.findViewById(R.id.news_listview);
        newsList.setAdapter(newsAdapter);

        newsList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapt, View view, int position, long id) {

                MyWebViewFragment webFragment = new MyWebViewFragment();

                Bundle bundle = new Bundle();
                bundle.putString("url", newsAdapter.getItem(position).getUrl());

                FragmentManager fragmentManager = getFragmentManager();
                webFragment.setArguments(bundle);
                fragmentManager.beginTransaction()
                        .replace(R.id.container, webFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });

        XMLHelper.submitQuery(BBC_QUERY_URL, 1);
        XMLHelper.submitQuery(TELEGRAPH_QUERY_URL, 2);
        XMLHelper.submitQuery(CRASH_QUERY_URL, 3);

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
