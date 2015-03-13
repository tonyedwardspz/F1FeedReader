package purelywebdesign.f1feedreader.helpers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import purelywebdesign.f1feedreader.R;

/**
 * Created by Anthony on 22/02/2015.
 */
public class ConstructorJSONAdapter extends BaseAdapter{

    Context mContext;
    LayoutInflater mInflater;
    JSONArray mJsonArray;

    public ConstructorJSONAdapter(Context context, LayoutInflater inflater){
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
        ViewHolder holder;

        // check that the view does not exist
        if (convertView == null){
            convertView = mInflater.inflate(R.layout.constructor_standings_row, null);

            // create a new holder with the subviews
            holder = new ViewHolder();
            holder.constructorPosition = (TextView) convertView.findViewById(R.id.constructor_position);
            holder.constructorName = (TextView) convertView.findViewById(R.id.constructor_name);
            holder.constructorPoints = (TextView) convertView.findViewById(R.id.constructor_points);

            // hold onto it for future recycling
            convertView.setTag(holder);

        } else {
            // return the existing view
            holder = (ViewHolder) convertView.getTag();
        }

        JSONObject jsonObject = getItem(position);

        String constructorPos = "1";
        String constructorName = "Constructor name";
        String constructorPoints = "0";

        if (jsonObject.has("position")){
            constructorPos = jsonObject.optString("position");
        }

        if (jsonObject.has("points")){
            constructorPoints = jsonObject.optString("points");
        }

        JSONObject jsDriver = null;
        try {
            jsDriver = jsonObject.getJSONObject("Constructor");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (jsDriver.has("name")){
            constructorName = jsDriver.optString("name");
        }

        holder.constructorPosition.setText(constructorPos);
        holder.constructorName.setText(constructorName);
        holder.constructorPoints.setText(constructorPoints);

        return convertView;
    }

    public void updateData(JSONArray jsonArray){

        mJsonArray = jsonArray;
        notifyDataSetChanged();
    }

    private static class ViewHolder {
        public TextView constructorPosition;
        public TextView constructorName;
        public TextView constructorPoints;
    }
}
