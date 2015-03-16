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

import java.util.ArrayList;

import purelywebdesign.f1feedreader.entities.BBCItem;
import purelywebdesign.f1feedreader.helpers.NewItemAdapter;
import purelywebdesign.f1feedreader.helpers.XMLHelper;


public class NewsFeed extends Fragment implements AdapterView.OnItemClickListener {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_SECTION_NUMBER = "section_number";
    private static final String BBC_QUERY_URL = "http://feeds.bbci.co.uk/sport/0/formula1/rss.xml";
    private static final String GUARDIAN_QUERY_URL = "http://www.theguardian.com/sport/formulaone/rss";
    public static NewItemAdapter newsAdapter = null;
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

        newsAdapter = new NewItemAdapter(getActivity(), inflater);
        newsList = (ListView) rootView.findViewById(R.id.news_listview);
        newsList.setAdapter(newsAdapter);
        XMLHelper.submitQuery(BBC_QUERY_URL, 1);
        XMLHelper.submitQuery("http://www.telegraph.co.uk/sport/motorsport/formulaone/rss", 2);

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


    public static void prepareBBCJSON (JSONObject jsonObject){
        Log.d("NEWS JSON REPLY", jsonObject.toString());
        ArrayList<BBCItem> bbcItems = new ArrayList();

        try {
            JSONObject js1 = jsonObject.getJSONObject("rss");
            JSONObject js2 = js1.getJSONObject("channel");
            JSONArray items = js2.getJSONArray("item");

            for (int i = 0; i < 10; i++){
                JSONObject thisItem = items.getJSONObject(i);
                String title = thisItem .optString("title");
                String description = thisItem .optString("description");
                String link = thisItem .optString("link");
                String pubDate = thisItem .optString("pubDate");
                String thumb = thisItem .getJSONArray("media:thumbnail").getJSONObject(1).optString("url");

                BBCItem newsItem = new BBCItem( title, description, link, pubDate, thumb );
                bbcItems.add(newsItem);
            }

            newsAdapter.updateData(bbcItems);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void prepareTelegraphJSON (JSONObject jsonObject){
        Log.d("NEWS JSON REPLY", jsonObject.toString());
        ArrayList<BBCItem> bbcItems = new ArrayList();

        try {
            JSONObject js1 = jsonObject.getJSONObject("rss");
            JSONObject js2 = js1.getJSONObject("channel");
            JSONArray items = js2.getJSONArray("item");

            for (int i = 0; i < 10; i++){
                JSONObject thisItem = items.getJSONObject(i);
                String title = thisItem .optString("title");
                String description = thisItem .optString("description");


                String[] splitDescription = description.split("<");
                description = splitDescription[0] + ".";

                String link = thisItem .optString("link");
                String pubDate = thisItem .optString("pubDate");
                String thumb = null;

                BBCItem newsItem = new BBCItem( title, description, link, pubDate, thumb );
                bbcItems.add(newsItem);
            }

            newsAdapter.updateData(bbcItems);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
