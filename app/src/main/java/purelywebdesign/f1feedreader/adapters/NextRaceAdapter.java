
package purelywebdesign.f1feedreader.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import purelywebdesign.f1feedreader.NextRace;
import purelywebdesign.f1feedreader.R;
import purelywebdesign.f1feedreader.entities.Race;

/**
 * Created by Anthony on 22/02/2015.
 */
public class NextRaceAdapter extends BaseAdapter {

    Context mContext;
    LayoutInflater mInflater;
    ArrayList<Race> mItems;

    public NextRaceAdapter(Context context, LayoutInflater inflater){
        mContext = context;
        mInflater = inflater;
        mItems = new ArrayList<>();
    }

    @Override
    public int getCount() {
        try {
            return mItems.size();
        } catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public Race getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        NextRace.nextRaceTitle = (TextView) convertView.findViewById(R.id.next_race_title);

        Race thisRace = getItem(position);

//        String constructorPos = "Next Race";
//        if (jsonObject.has("raceName")){
//            constructorPos = jsonObject.optString("raceName");
//        }

        NextRace.nextRaceTitle.setText("next race");

        return convertView;
    }

    public void updateData(ArrayList<Race> items){
        mItems.addAll(items);
        notifyDataSetChanged();
    }
}

