
package purelywebdesign.f1feedreader.helpers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONObject;

import purelywebdesign.f1feedreader.NextRace;
import purelywebdesign.f1feedreader.R;

/**
 * Created by Anthony on 22/02/2015.
 */
public class NextRaceJSONAdapter extends JSONAdapter {

    public NextRaceJSONAdapter(Context context, LayoutInflater inflater){
        super(context, inflater);
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

    private static class ViewHolder {
        public TextView nextRace;
    }
}

