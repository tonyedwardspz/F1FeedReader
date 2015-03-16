package purelywebdesign.f1feedreader.helpers;

import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

/**
 * Created by Anthony on 13/03/2015.
 */
public class XMLHelper {

    /**
     * Retries data from a XML feed, converting it to JSON object
     * @param  query_url: The REST url to call for data
     * @param  type: The source the query is aimed at (1:BBC, 2:Telegraph, 3:Sky)
     * @return jsonObject: The retrieved data
     */
    public static void submitQuery(String query_url, final int type){

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(query_url, new AsyncHttpResponseHandler(){

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody){

                String s = new String(responseBody);
                //Log.d("JSON object", s.toString());
                JSONObject results = null;
                try{
                    results = XML.toJSONObject(s);
                    switch (type) {
                        case 1:
                            JSONHelper.prepareBBCJSON(results);
                            break;
                        case 2:
                            JSONHelper.prepareTelegraphJSON(results);
                            break;
                        case 3:
                            JSONHelper.prepareCrashJSON(results);
                            break;
                    }

                } catch (JSONException e) {
                    Log.e("JSON exception", e.getMessage());
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Throwable throwable){
                // run and hide under a bush
                Log.e("On Failure: ", throwable.getMessage());
            }
        });
    }
}
