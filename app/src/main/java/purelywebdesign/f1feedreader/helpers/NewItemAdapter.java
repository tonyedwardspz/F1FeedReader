package purelywebdesign.f1feedreader.helpers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import purelywebdesign.f1feedreader.R;
import purelywebdesign.f1feedreader.entities.NewsItem;

/**
 * Created by Anthony on 14/03/2015.
 */
public class NewItemAdapter extends BaseAdapter {

    Context mContext;
    LayoutInflater mInflater;
    ArrayList<NewsItem> mItems;

    public NewItemAdapter(Context context, LayoutInflater inflater){
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
            holder.newsSource = (TextView) convertView.findViewById(R.id.newsSource);
            holder.image = (ImageView) convertView.findViewById(R.id.img_thumbnail);

            // hold onto it for future recycling
            convertView.setTag(holder);

        } else {
            // return the existing view
            holder = (ViewHolder) convertView.getTag();
        }

        NewsItem thisItem = getItem(position);

        holder.newsTitle.setText(thisItem.getTitle());
        holder.newsSource.setText(thisItem.getDescription());
        Picasso.with(mContext).load(thisItem.getThumbnailURL()).into(holder.image);


        return convertView;
    }

    public void updateData(ArrayList<NewsItem> items){
        mItems.addAll(items);
        notifyDataSetChanged();
    }

    private static class ViewHolder {
        public TextView newsTitle;
        public TextView newsSource;
        public ImageView image;
    }




}
