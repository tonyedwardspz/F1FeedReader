package purelywebdesign.f1feedreader.helpers;

import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import purelywebdesign.f1feedreader.ConstructorStandings;
import purelywebdesign.f1feedreader.DriverStandings;
import purelywebdesign.f1feedreader.NewsFeed;
import purelywebdesign.f1feedreader.NextRace;
import purelywebdesign.f1feedreader.entities.ConstructorStanding;
import purelywebdesign.f1feedreader.entities.DriverStanding;
import purelywebdesign.f1feedreader.entities.NewsItem;

/**
 * Created by Anthony on 22/02/2015.
 */
public class JSONHelper {

    /**
    * Retries data from an JSON api for the standings and next race info
    * @param  query_url: The REST url to call for data
    * @param  reqType: Indicates the type of request to determine where to return data.
    * 1:Driver data, 2: Constructor data, 3: Next Race Data
    * @return jsonObject: The retrieved data
    */
    public static void submitQuery(String query_url, final int reqType){

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(query_url, new JsonHttpResponseHandler(){

            @Override
            public void onSuccess(JSONObject jsonObject){
                switch (reqType){
                    case 1:
                        prepareDriverJSON(jsonObject);
                        break;
                    case 2:
                        prepareConstructorJSON(jsonObject);
                        break;
                    case 3:
                        NextRace.prepareNextRaceJson(jsonObject);
                        break;
                }
            }

            @Override
            public void onFailure(int statusCode, Throwable throwable, JSONObject error){
                // run and hide under a bush
                Log.e("On Failure: ", statusCode + " " + throwable.getMessage());
            }
        });
    }

    /**
    * Takes the BBC news feed and creates a set of NewsItem objects.
    * Sends the array of objects to the adapter for displaying
    */
    public static void prepareBBCJSON (JSONObject jsonObject){
        ArrayList<NewsItem> newsItems = new ArrayList();
        JSONObject thisItem;
        NewsItem newsItem;
        String title;
        String description;
        String link;
        String pubDate;
        String thumb;

        try {
            JSONArray items = getNewsItems(jsonObject);

            for (int i = 0; i < 10; i++){
                thisItem = items.getJSONObject(i);
                title = thisItem .optString("title");
                description = thisItem .optString("description");
                link = thisItem .optString("link");
                pubDate = thisItem .optString("pubDate");
                thumb = thisItem .getJSONArray("media:thumbnail").getJSONObject(1).optString("url");

                newsItem = new NewsItem( title, description, link, pubDate, thumb );
                newsItems.add(newsItem);
            }

            NewsFeed.newsAdapter.updateData(newsItems);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Takes the Telegraph news feed and creates a set of NewsItem objects.
     * Sends the array of objects to the adapter for displaying.
     */
    public static void prepareTelegraphJSON (JSONObject jsonObject){
        ArrayList<NewsItem> newsItems = new ArrayList();
        NewsItem newsItem;
        JSONObject thisItem;
        String title;
        String description;
        String link;
        String pubDate;

        try {
            JSONArray items = getNewsItems(jsonObject);

            for (int i = 0; i < 10; i++){
                thisItem = items.getJSONObject(i);
                title = thisItem .optString("title");
                description = Utilities.prepareTelegraphDescription(thisItem .optString("description"));
                link = thisItem .optString("link");
                pubDate = thisItem .optString("pubDate");

                newsItem = new NewsItem( title, description, link, pubDate );
                newsItems.add(newsItem);
            }

            NewsFeed.newsAdapter.updateData(newsItems);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Takes the Crash.net news feed and creates a set of NewsItem objects.
     * Sends the array of objects to the adapter for displaying.
     */
    public static void prepareCrashJSON (JSONObject jsonObject){
        ArrayList<NewsItem> newsItems = new ArrayList();
        JSONObject thisItem;
        NewsItem newsItem;
        String title;
        String description;
        String link;
        String pubDate;
        String thumb;

        try {
            JSONArray items = getNewsItems(jsonObject);

            for (int i = 0; i < 10; i++){
                thisItem = items.getJSONObject(i);

                title = thisItem .optString("title");
                description = thisItem .optString("description");
                description = Utilities.prepareTelegraphDescription(description);
                link = thisItem .optString("link");
                pubDate = thisItem .optString("pubDate");
                thumb = thisItem .getJSONObject("media:thumbnail").optString("url");

                newsItem = new NewsItem( title, description, link, pubDate, thumb );
                newsItems.add(newsItem);
            }
            NewsFeed.newsAdapter.updateData(newsItems);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Extracts and returns array of news items from provided JSONObject
     */
    public static JSONArray getNewsItems(JSONObject newsObject){
        JSONArray items = new JSONArray();
        try {
            items = newsObject.getJSONObject("rss").getJSONObject("channel").getJSONArray("item");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return items;
    }

    /**
     * Takes the ergast.com constructor standings JSON reply and creates a set of objects
     * Sends the array of objects to the adapter for displaying.
     */
    public static void prepareConstructorJSON(JSONObject jsonObject){
        ArrayList<ConstructorStanding> cons = new ArrayList();
        String pos;
        String name;
        String points;

        try {
            JSONObject js1 = jsonObject.getJSONObject("MRData").getJSONObject("StandingsTable");
            JSONArray js2 = js1.getJSONArray("StandingsLists").getJSONObject(0).
                                getJSONArray("ConstructorStandings");

            for (int i = 0; i < js2.length(); i++) {
                JSONObject thisCon = js2.getJSONObject(i);

                pos = thisCon.optString("position");
                name = thisCon.getJSONObject("Constructor").optString("name");
                points = thisCon.optString("points");

                ConstructorStanding cs = new ConstructorStanding(pos, name, points);
                cons.add(cs);
            }
            ConstructorStandings.constructorJSONAdapterDriver.updateData(cons);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Takes the ergast.com driver standings JSON reply and creates a set of objects
     * Sends the array of objects to the adapter for displaying.
     */
    public static void prepareDriverJSON(JSONObject jsonObject){
        ArrayList<DriverStanding> drivers = new ArrayList<>();
        String pos;
        String name;
        String points;

        try {
            JSONObject js1 = jsonObject.getJSONObject("MRData").getJSONObject("StandingsTable");
            JSONArray js2 = js1.getJSONArray("StandingsLists").getJSONObject(0).
                            getJSONArray("DriverStandings");

            for (int i = 0; i < js2.length(); i++){
                JSONObject thisDriver = js2.getJSONObject(i);

                pos = thisDriver.optString("position");
                points = thisDriver.optString("points");
                JSONObject jsDriver = thisDriver.getJSONObject("Driver");
                name = jsDriver.optString("givenName") + " " + jsDriver.optString("familyName");

                DriverStanding drv = new DriverStanding(pos, name, points);
                drivers.add(drv);
            }
            DriverStandings.driverJSONAdapterDriver.updateData(drivers);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
