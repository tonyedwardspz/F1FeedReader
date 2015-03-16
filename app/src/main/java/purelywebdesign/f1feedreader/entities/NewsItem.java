package purelywebdesign.f1feedreader.entities;

import java.util.Date;

import purelywebdesign.f1feedreader.helpers.Utilities;

/**
 * Created by Anthony on 13/03/2015.
 */
public class NewsItem implements Comparable<NewsItem>{

    private String title;
    private String description;
    private String url;
    private Date pubDate;
    private String thumbnailURL;

    public NewsItem(String title, String description, String url, String pubDate,
                    String thumbnailUrl) {
        this.title = title;
        this.description = description;
        this.url = url;
        this.pubDate = Utilities.parseLongDate(pubDate);
        this.thumbnailURL = thumbnailUrl;
    }

    /**
     * Overload constructor for items without a thumbnail
     */
    public NewsItem(String title, String description, String url, String pubDate) {
        this.title = title;
        this.description = description;
        this.url = url;
        this.pubDate = Utilities.parseLongDate(pubDate);
        this.thumbnailURL = null;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public Date getPubDate() {
        return pubDate;
    }

    public String getThumbnailURL() {
        return thumbnailURL;
    }


    @Override
    public int compareTo(NewsItem another) {
        return getPubDate().compareTo(another.getPubDate());
    }
}
