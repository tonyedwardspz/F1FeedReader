package purelywebdesign.f1feedreader.entities;

/**
 * Created by Anthony on 13/03/2015.
 */
public class BBCItem {

    public final String title;
    public final String description;
    public final String url;
    public final String pubDate;
    public final String thumbnailURL;

    public BBCItem(String title, String description, String url, String pubDate,
                   String thumbnailUrl) {
        this.title = title;
        this.description = description;
        this.url = url;
        this.pubDate = pubDate;
        this.thumbnailURL = thumbnailUrl;
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

    public String getPubDate() {
        return pubDate;
    }

    public String getThumbnailURL() {
        return thumbnailURL;
    }
}
