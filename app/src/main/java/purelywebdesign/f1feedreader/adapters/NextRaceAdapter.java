
package purelywebdesign.f1feedreader.adapters;

import android.content.Context;
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
public class NextRaceAdapter extends BaseAdapter {

    Context mContext;
    LayoutInflater mInflater;
    JSONArray mJsonArray;

    public NextRaceAdapter(Context context, LayoutInflater inflater){
        mContext = context;
        mInflater = inflater;
        mJsonArray = new JSONArray();
    }

    @Override
    public int getCount() {
        try {
            return mJsonArray.length();
        } catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public JSONObject getItem(int position) {
        return mJsonArray.optJSONObject(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        NextRace.nextRaceTitle = (TextView) convertView.findViewById(R.id.next_race_title);

        JSONObject jsonObject = getItem(position);

        String constructorPos = "Next Race";
        if (jsonObject.has("raceName")){
            constructorPos = jsonObject.optString("raceName");
        }

        NextRace.nextRaceTitle.setText(constructorPos);

        return convertView;
    }

    public void updateData(JSONArray jsonArray){
        mJsonArray = jsonArray;
        notifyDataSetChanged();
    }
}

