
package purelywebdesign.f1feedreader.helpers;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import purelywebdesign.f1feedreader.NextRace;
import purelywebdesign.f1feedreader.R;

/**
 * Created by Anthony on 22/02/2015.
 */
public class NextRaceJSONAdapter extends BaseAdapter {

    Context mContext;
    LayoutInflater mInflater;
    JSONArray mJsonArray;

    public NextRaceJSONAdapter(Context context, LayoutInflater inflater){
        Log.d("JSONAdapter: ", "Constructor");
        mContext = context;
        mInflater = inflater;
        mJsonArray = new JSONArray();
    }

    @Override
    public int getCount() {
        Log.d("JSONAdapter: ", "getCount = " + mJsonArray.length());
        try {
            return mJsonArray.length();
        } catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public JSONObject getItem(int position) {
        Log.d("JSONAdapter: ", "get item");
        return mJsonArray.optJSONObject(position);
    }

    @Override
    public long getItemId(int position) {
        Log.d("JSONAdapter: ", "get item id");
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.d("JSONAdapter: ", "getView");

        NextRace.nextRaceTitle = (TextView) convertView.findViewById(R.id.next_race_title);

        JSONObject jsonObject = getItem(position);

        String constructorPos = "Next Race";
        Log.d("Next race location",constructorPos);
        if (jsonObject.has("raceName")){
            constructorPos = jsonObject.optString("raceName");
            Log.d("Next race location",constructorPos);
        }

        NextRace.nextRaceTitle.setText(constructorPos);

        return convertView;
    }

    public void updateData(JSONArray jsonArray){
        Log.d("JSONAdapter: ", "update Data");

        mJsonArray = jsonArray;
        notifyDataSetChanged();
    }

    private static class ViewHolder {
        public TextView nextRace;
    }
}

