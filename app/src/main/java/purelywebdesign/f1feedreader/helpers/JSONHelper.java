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
                        DriverStandings.prepareDriverJSON(jsonObject);
                        break;
                    case 2:
                        ConstructorStandings.prepareConstructorJSON(jsonObject);
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

                NewsItem newsItem = new NewsItem( title, description, link, pubDate, thumb );
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

        try {
            JSONObject js1 = jsonObject.getJSONObject("rss");
            JSONObject js2 = js1.getJSONObject("channel");
            JSONArray items = js2.getJSONArray("item");

            for (int i = 0; i < 10; i++){
                JSONObject thisItem = items.getJSONObject(i);
                String title = thisItem .optString("title");

                String description = thisItem .optString("description");
                description = Utilities.prepareTelegraphDescription(description);

                String link = thisItem .optString("link");
                String pubDate = thisItem .optString("pubDate");

                NewsItem newsItem = new NewsItem( title, description, link, pubDate );
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

        try {
            JSONObject js1 = jsonObject.getJSONObject("rss");
            JSONObject js2 = js1.getJSONObject("channel");
            JSONArray items = js2.getJSONArray("item");

            for (int i = 0; i < 10; i++){
                JSONObject thisItem = items.getJSONObject(i);
                String title = thisItem .optString("title");
                String description = thisItem .optString("description");
                description = Utilities.prepareTelegraphDescription(description);
                String link = thisItem .optString("link");
                String pubDate = thisItem .optString("pubDate");
                String thumb = thisItem .getJSONObject("media:thumbnail").optString("url");

                NewsItem newsItem = new NewsItem( title, description, link, pubDate, thumb );
                newsItems.add(newsItem);
            }

            NewsFeed.newsAdapter.updateData(newsItems);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
