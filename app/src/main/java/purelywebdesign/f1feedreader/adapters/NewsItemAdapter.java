package purelywebdesign.f1feedreader.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;

import purelywebdesign.f1feedreader.R;
import purelywebdesign.f1feedreader.entities.NewsItem;

/**
 * Created by Anthony on 14/03/2015.
 */
public class NewsItemAdapter extends BaseAdapter {

    Context mContext;
    LayoutInflater mInflater;
    ArrayList<NewsItem> mItems;

    public NewsItemAdapter(Context context, LayoutInflater inflater){
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
    public NewsItem getItem(int position) {
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
            convertView = mInflater.inflate(R.layout.news_feed_row, null);

            // create a new holder with the subviews
            holder = new ViewHolder();
            holder.newsTitle = (TextView) convertView.findViewById(R.id.newsTitle);
            holder.newsDescription = (TextView) convertView.findViewById(R.id.newsSource);
            holder.image = (ImageView) convertView.findViewById(R.id.img_thumbnail);
            holder.date = (TextView) convertView.findViewById(R.id.publicationDate);

            // hold onto it for future recycling
            convertView.setTag(holder);

        } else {
            // return the existing view
            holder = (ViewHolder) convertView.getTag();
        }

        NewsItem thisItem = getItem(position);

        holder.newsTitle.setText(thisItem.getTitle());
        holder.newsDescription.setText(thisItem.getDescription());
        Picasso.with(mContext).load(thisItem.getThumbnailURL()).into(holder.image);
        holder.date.setText(thisItem.getPubDate().toString());


        return convertView;
    }

    public void updateData(ArrayList<NewsItem> items){
        mItems.addAll(items);
        //ArrayList sortedItems = new ArrayList();
        Collections.sort(mItems, Collections.reverseOrder());

        for (NewsItem i: mItems){
            Log.d("date ", i.getPubDate() + " : " + i.getUrl());
        }

        notifyDataSetChanged();
    }

    private static class ViewHolder {
        public TextView newsTitle;
        public TextView newsDescription;
        public ImageView image;
        public TextView date;
    }




}
