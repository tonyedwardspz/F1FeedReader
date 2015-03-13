package purelywebdesign.f1feedreader.helpers;

import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.apache.http.Header;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;

import purelywebdesign.f1feedreader.NewsFeed;
import purelywebdesign.f1feedreader.entities.BBCItem;

/**
 * Created by Anthony on 13/03/2015.
 */
public class XMLHelper {

    /**
     * Retries data from a XML feed
     * @param  query_url: The REST url to call for data
     * @return jsonObject: The retrieved data
     */
    public static void submitQuery(String query_url){

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(query_url, new AsyncHttpResponseHandler(){

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody){

                String s = new String(responseBody);
                InputStream is = new ByteArrayInputStream(responseBody);
                ArrayList<BBCItem> items = new ArrayList();

                try {
                    items = BBCXMLParser.parse(is);
                } catch (Throwable throwable){
                    Log.e("Try bbc feed: ", throwable.getMessage());
                }
                NewsFeed.prepareXML(items);
            }

            @Override
            public void onFailure(Throwable throwable){
                // run and hide under a bush
                Log.e("On Failure: ", throwable.getMessage());
            }
        });
    }
}
