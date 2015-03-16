package purelywebdesign.f1feedreader.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import purelywebdesign.f1feedreader.holders.StandingsHolder;
import purelywebdesign.f1feedreader.R;
import purelywebdesign.f1feedreader.entities.ConstructorStanding;

/**
 * Created by Anthony on 22/02/2015.
 */
public class ConstructorJSONAdapter extends BaseAdapter {

    Context mContext;
    LayoutInflater mInflater;
    ArrayList<ConstructorStanding> mItems;

    public ConstructorJSONAdapter(Context context, LayoutInflater inflater){
        mContext = context;
        mInflater = inflater;
        mItems = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public ConstructorStanding getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        StandingsHolder holder;

        // check that the view does not exist
        if (convertView == null){
            convertView = mInflater.inflate(R.layout.standings_row, null);

            // create a new holder with the subviews
            holder = new StandingsHolder();
            holder.setPosition((TextView) convertView.findViewById(R.id.standings_position));
            holder.setName((TextView) convertView.findViewById(R.id.standings_name));
            holder.setPoints((TextView) convertView.findViewById(R.id.standings_points));

            // hold onto it for future recycling
            convertView.setTag(holder);
        } else {
            // return the existing view
            holder = (StandingsHolder) convertView.getTag();
        }

        ConstructorStanding thisCon = getItem(position);

        holder.getPosition().setText(thisCon.getPosition());
        holder.getName().setText(thisCon.getName());
        holder.getPoints().setText(thisCon.getPoints());

        return convertView;
    }

    public void updateData(ArrayList<ConstructorStanding> items){
        mItems.addAll(items);
        notifyDataSetChanged();
    }
}
