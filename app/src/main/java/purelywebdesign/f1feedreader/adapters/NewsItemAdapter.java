package purelywebdesign.f1feedreader.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.Collections;
import purelywebdesign.f1feedreader.R;
import purelywebdesign.f1feedreader.entities.NewsItem;
import purelywebdesign.f1feedreader.Holders.NewsItemHolder;

/**
 * Created by Anthony on 14/03/2015.
 */
public class NewsItemAdapter extends JSONAdapter {

    ArrayList<NewsItem> mItems;

    public NewsItemAdapter(Context context, LayoutInflater inflater){
        super(context, inflater);
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
    public View getView(int position, View convertView, ViewGroup parent) {
        NewsItemHolder holder;

        // check if the view is available for recycling
        if (convertView == null){
            convertView = mInflater.inflate(R.layout.news_feed_row, null);
            holder = getNewsItemHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (NewsItemHolder) convertView.getTag();
        }

        // get the news item and display details
        NewsItem thisItem = getItem(position);
        holder.getNewsTitle().setText(thisItem.getTitle());
        holder.getNewsDescription().setText(thisItem.getDescription());
        Picasso.with(mContext).load(thisItem.getThumbnailURL()).into(holder.getImage());
        holder.getDate().setText(thisItem.getPubDate().toString());

        return convertView;
    }


    public void updateData(ArrayList<NewsItem> items){
        mItems.addAll(items);
        Collections.sort(mItems, Collections.reverseOrder());
        notifyDataSetChanged();
    }
}
