package purelywebdesign.f1feedreader.helpers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import purelywebdesign.f1feedreader.R;

/**
 * Created by Anthony on 22/02/2015.
 */
public class DriverJSONAdapter extends JSONAdapter{

    public DriverJSONAdapter(Context context, LayoutInflater inflater){

        super(context, inflater);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        // check that the view does not exist
        if (convertView == null){
            convertView = mInflater.inflate(R.layout.standings_row, null);

            // create a new holder with the subviews
            holder = new ViewHolder();
            holder.driverPosition = (TextView) convertView.findViewById(R.id.standings_position);
            holder.driverName = (TextView) convertView.findViewById(R.id.standings_name);
            holder.driverPoints = (TextView) convertView.findViewById(R.id.standings_points);

            // hold onto it for future recycling
            convertView.setTag(holder);

        } else {
            // return the existing view
            holder = (ViewHolder) convertView.getTag();
        }

        JSONObject jsonObject = getItem(position);

        String driverPos = "1";
        String driverName = "Driver name";
        String driverPoints = "0";

        if (jsonObject.has("position")){
            driverPos = jsonObject.optString("position");
        }

        if (jsonObject.has("points")){
            driverPoints = jsonObject.optString("points");
        }

        JSONObject jsDriver = null;
        try {
            jsDriver = jsonObject.getJSONObject("Driver");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (jsDriver.has("givenName")){
            driverName = jsDriver.optString("givenName") + " " + jsDriver.optString("familyName");
        }

        holder.driverPosition.setText(driverPos);
        holder.driverName.setText(driverName);
        holder.driverPoints.setText(driverPoints);

        return convertView;
    }

    private static class ViewHolder {
        public TextView driverPosition;
        public TextView driverName;
        public TextView driverPoints;
    }
}
