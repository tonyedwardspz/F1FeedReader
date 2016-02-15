package purelywebdesign.f1feedreader.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import purelywebdesign.f1feedreader.R;
import purelywebdesign.f1feedreader.entities.DriverStanding;
import purelywebdesign.f1feedreader.Holders.StandingsHolder;

/**
 * Created by Anthony on 22/02/2015.
 */
public class DriverJSONAdapter extends JSONAdapter {

    ArrayList<DriverStanding> mItems;

    public DriverJSONAdapter(Context context, LayoutInflater inflater){
        super(context, inflater);
        mItems = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public DriverStanding getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        StandingsHolder holder;

        // check if the view is available for recycling
        if (convertView == null){
            convertView = mInflater.inflate(R.layout.standings_row, null);
            holder = super.getStandingsHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (StandingsHolder) convertView.getTag();
        }

        // get the current driver and set the details
        DriverStanding thisDriver = getItem(position);
        holder.getPosition().setText(thisDriver.getPosition());
        holder.getName().setText(thisDriver.getName());
        holder.getPoints().setText(thisDriver.getPoints());

        return convertView;
    }

    public void updateData(ArrayList<DriverStanding> items){
        mItems.addAll(items);
        notifyDataSetChanged();
    }
}
