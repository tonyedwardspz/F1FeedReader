package purelywebdesign.f1feedreader.helpers;

import android.util.Log;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import purelywebdesign.f1feedreader.entities.BBCItem;

/**
 * Created by Anthony on 13/03/2015.
 */
public class BBCXMLParser {

    private static final String ns = null;

    public static ArrayList<BBCItem> parse(InputStream in) throws XmlPullParserException, IOException {
        try {
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(in, null);
            parser.nextTag();
            return readFeed(parser);
        } finally {
            in.close();
        }
    }

    private static ArrayList<BBCItem> readFeed(XmlPullParser parser) throws XmlPullParserException, IOException {
        //Log.d("read feed", "");
        ArrayList<BBCItem> items = new ArrayList();

        parser.require(XmlPullParser.START_TAG, ns, "rss");
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            // Starts by looking for the channel tag
            if (name.equals("channel")) {
                // navigate to the item tag here
                parser.require(XmlPullParser.START_TAG, ns, "channel");
                while (parser.next() != XmlPullParser.END_TAG){
                    if (parser.getEventType() != XmlPullParser.START_TAG) {
                        continue;
                    }
                    name = parser.getName();
                    if (name.equals("item")){
                        items.add(bbcReadEntry(parser));
                    }
                    else {
                        skip(parser);
                    }
                }
            } else {
                skip(parser);
            }
        }
        return items;
    }

    public static BBCItem bbcReadEntry(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, ns, "item");
        String title = null;
        String description = null;
        String url = null;
        String pubDate = null;
        String thumbnailURL = null;
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            if (name.equals("title")) {
                title = readString(parser, "title");
            } else if (name.equals("description")) {
                description = readString(parser, "description");
            } else if (name.equals("link")) {
                url = readString(parser, "link");
            } else if (name.equals("pubDate")) {
                pubDate = readString(parser, "pubDate");
            } else if (name.equals("media:thumbnail")) {
                //thumbnailURL = readThumbnail(parser);
                thumbnailURL = "http://news.bbcimg.co.uk/media/images/81615000/jpg/_81615988_nicorosberg.jpg";
            }
            else {
                skip(parser);
            }
        }
        Log.d("bbc title", title);
        return new BBCItem(title, description, url, pubDate, thumbnailURL);
    }

    // Processes title tags in the feed.
    private static String readString(XmlPullParser parser, String item) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, item);
        String title = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, item);
        return title;
    }

    // Processes link tags in the feed.
    private static String readThumbnail(XmlPullParser parser) throws IOException, XmlPullParserException {
        String img = "";
        parser.require(XmlPullParser.START_TAG, ns, "link");
        String tag = parser.getName();
        if (tag.equals("link")) {
            img = parser.getAttributeValue(null, "url");
            parser.nextTag();

        }
        parser.require(XmlPullParser.END_TAG, ns, "link");
        return img;
    }

    private static String readText(XmlPullParser parser) throws IOException, XmlPullParserException {
        String result = "";
        if (parser.next() == XmlPullParser.TEXT) {
            result = parser.getText();
            parser.nextTag();
        }
        return result;
    }

    private static void skip(XmlPullParser parser) throws XmlPullParserException, IOException {
        if (parser.getEventType() != XmlPullParser.START_TAG) {
            throw new IllegalStateException();
        }
        int depth = 1;
        while (depth != 0) {
            switch (parser.next()) {
                case XmlPullParser.END_TAG:
                    depth--;
                    break;
                case XmlPullParser.START_TAG:
                    depth++;
                    break;
            }
        }
    }
}
