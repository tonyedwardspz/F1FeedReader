package purelywebdesign.f1feedreader.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import purelywebdesign.f1feedreader.R;
import purelywebdesign.f1feedreader.entities.ConstructorStanding;
import purelywebdesign.f1feedreader.holders.StandingsHolder;

/**
 * Created by Anthony on 22/02/2015.
 */
public class ConstructorJSONAdapter extends JSONAdapter {

    ArrayList<ConstructorStanding> mItems;

    public ConstructorJSONAdapter(Context context, LayoutInflater inflater){
        super(context, inflater);
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

        // get the current constructor and set their details
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
