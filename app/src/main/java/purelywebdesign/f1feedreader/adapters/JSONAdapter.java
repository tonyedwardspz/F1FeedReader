package purelywebdesign.f1feedreader.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import purelywebdesign.f1feedreader.R;
import purelywebdesign.f1feedreader.Holders.NewsItemHolder;
import purelywebdesign.f1feedreader.Holders.StandingsHolder;

/**
 * Created by Anthony on 22/03/2015.
 */
public class JSONAdapter extends BaseAdapter {

    Context mContext;
    LayoutInflater mInflater;

    public JSONAdapter(Context context, LayoutInflater inflater){
        mContext = context;
        mInflater = inflater;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }

    public StandingsHolder getStandingsHolder(View convertView){
        StandingsHolder holder = new StandingsHolder();

        holder.setPosition((TextView) convertView.findViewById(R.id.standings_position));
        holder.setName((TextView) convertView.findViewById(R.id.standings_name));
        holder.setPoints((TextView) convertView.findViewById(R.id.standings_points));
        
        return holder;
    }

    public NewsItemHolder getNewsItemHolder(View convertView){
        NewsItemHolder holder = new NewsItemHolder();

        holder.setNewsTitle((TextView) convertView.findViewById(R.id.newsTitle));
        holder.setNewsDescription((TextView) convertView.findViewById(R.id.newsSource));
        holder.setImage((ImageView) convertView.findViewById(R.id.img_thumbnail));
        holder.setDate((TextView) convertView.findViewById(R.id.publicationDate));

        return holder;
    }
}
