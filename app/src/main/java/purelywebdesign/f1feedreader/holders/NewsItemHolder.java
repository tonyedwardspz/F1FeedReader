package purelywebdesign.f1feedreader.holders;

import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Anthony on 22/03/2015.
 */
public class NewsItemHolder {

    private TextView newsTitle;
    private TextView newsDescription;
    private ImageView image;
    private TextView date;

    public NewsItemHolder(){}

    public TextView getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(TextView newsTitle) {
        this.newsTitle = newsTitle;
    }

    public TextView getNewsDescription() {
        return newsDescription;
    }

    public void setNewsDescription(TextView newsDescription) {
        this.newsDescription = newsDescription;
    }

    public ImageView getImage() {
        return image;
    }

    public void setImage(ImageView image) {
        this.image = image;
    }

    public TextView getDate() {
        return date;
    }

    public void setDate(TextView date) {
        this.date = date;
    }
}
