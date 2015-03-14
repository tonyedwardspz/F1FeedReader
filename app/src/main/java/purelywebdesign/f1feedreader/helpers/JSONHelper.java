package purelywebdesign.f1feedreader.helpers;

import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

import purelywebdesign.f1feedreader.ConstructorStandings;
import purelywebdesign.f1feedreader.DriverStandings;
import purelywebdesign.f1feedreader.NextRace;

/**
 * Created by Anthony on 22/02/2015.
 */
public class JSONHelper {

    /**
    * Retries data from an JSON api for
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
                //Log.d("JSON REPLY: ", jsonObject.toString());

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
}
