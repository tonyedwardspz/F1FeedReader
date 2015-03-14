package purelywebdesign.f1feedreader.helpers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import purelywebdesign.f1feedreader.R;
import purelywebdesign.f1feedreader.entities.BBCItem;

/**
 * Created by Anthony on 14/03/2015.
 */
public class NewsArrayAdapter extends ArrayAdapter<BBCItem>{

    Context mContext;
    LayoutInflater mInflater;
    ArrayList<BBCItem> items;


    public NewsArrayAdapter(Context context, LayoutInflater inflater){
        super(context, 0);
        mContext = context;
        mInflater = inflater;
        items = new ArrayList<BBCItem>();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        BBCItem item = getItem(position);

        ViewHolder  viewHolder;

        if (convertView == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.news_feed_row, parent, false);
            viewHolder.title = (TextView) convertView.findViewById(R.id.newsTitle);
            viewHolder.source = (TextView) convertView.findViewById(R.id.newsSource);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        //populate data
        viewHolder.title.setText(item.getTitle());
        viewHolder.title.setText(item.getUrl());

        return convertView;

    }

    @Override
    public int getCount(){
        try {
            return items.size();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public BBCItem getItem(int position){
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void updateData(ArrayList<BBCItem> items){
        this.items = items;
        notifyDataSetChanged();
    }


    private static class ViewHolder {
        ImageView image;
        TextView title;
        TextView source;
    }
}
