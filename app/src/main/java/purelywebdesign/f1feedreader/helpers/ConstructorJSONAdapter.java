package purelywebdesign.f1feedreader.helpers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

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
        ViewHolder holder;

        // check that the view does not exist
        if (convertView == null){
            convertView = mInflater.inflate(R.layout.standings_row, null);

            // create a new holder with the subviews
            holder = new ViewHolder();
            holder.constructorPosition = (TextView) convertView.findViewById(R.id.standings_position);
            holder.constructorName = (TextView) convertView.findViewById(R.id.standings_name);
            holder.constructorPoints = (TextView) convertView.findViewById(R.id.standings_points);

            // hold onto it for future recycling
            convertView.setTag(holder);

        } else {
            // return the existing view
            holder = (ViewHolder) convertView.getTag();
        }

        ConstructorStanding thisCon = getItem(position);

        String constructorPos = thisCon.getPosition();
        String constructorName = thisCon.getName();
        String constructorPoints = thisCon.getPoints();

        holder.constructorPosition.setText(constructorPos);
        holder.constructorName.setText(constructorName);
        holder.constructorPoints.setText(constructorPoints);

        return convertView;
    }

    public void updateData(ArrayList<ConstructorStanding> items){
        mItems.addAll(items);
        notifyDataSetChanged();
    }

    private static class ViewHolder {
        public TextView constructorPosition;
        public TextView constructorName;
        public TextView constructorPoints;
    }
}
