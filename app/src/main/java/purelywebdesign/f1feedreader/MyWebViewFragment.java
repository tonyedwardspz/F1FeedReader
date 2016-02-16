package purelywebdesign.f1feedreader;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by Anthony on 18/03/2015.
 */
public class MyWebViewFragment extends Fragment {

    private ProgressDialog progress;
    private String title;
    private String url;
    private Bundle bundle;
    private View rootView;
    private WebView newsItemWebView;

    public MyWebViewFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        rootView = inflater.inflate(R.layout.web_view, container, false);

        bundle = this.getArguments();
        url = bundle.getString("url");
        title = bundle.getString("title");

        progress = ProgressDialog.show(getActivity(), "Fetching article", title, true, true);

        newsItemWebView = (WebView) rootView.findViewById(R.id.webView1);
        newsItemWebView.setWebViewClient(new MyWebViewClient());
        newsItemWebView.getSettings().setJavaScriptEnabled(true);

        newsItemWebView.loadUrl(url);

        return rootView;
    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return false;
        }

        @Override
        public void onLoadResource(WebView view, String url){
            String urlStart = url.substring(0, Math.min(url.length(), 15));

            // remove uneeded content from the pages. JavaScript gets everywhere!
            if (urlStart.contains("bbc")){
                view.loadUrl("javascript:"+
                        "if (document.querySelector('#sport')){" +
                            "document.querySelector('#sport').removeChild(document.querySelector('.nav-top'));" +
                            "document.querySelector('#sport-container').removeChild(document.querySelector('header'));" +
                            "document.querySelector('.main').removeChild(document.querySelector('.bg'));" +
                            "document.querySelector('.main').removeChild(document.querySelector('.bg'));" +
                            "document.querySelector('.main').removeChild(document.querySelector('.share-this'));" +
                            "document.querySelector('#sport').removeChild(document.querySelector('.nav-bottom'));" +
                            "document.querySelector('#sport-container').removeChild(document.querySelector('.orb-footer'));"+
                        "}"
                );
            } else if (urlStart.contains("crash")){
                view.loadUrl("javascript:"+
                        "if (document.querySelector('.container')){" +
                            "$('script').each(function () {" +
                            "    $(this).remove();" +
                            "});" +
                            "$('.header').remove();" +
                            "$('.comments-box').remove();" +
                            "$('.mobile-only').remove();" +
                            "$('.side-bar').remove();" +
                            "$('.footer').remove();" +
                            "$('#contentclick1148').remove();" +
                            "$('#contentclick225').remove();" +
                            "$('#taboola-below-article-thumbnails').remove();" +
                            "$('.comments-box ').remove();" +
                            "$('.article-footer').remove();" +
                            "$('.article-header').remove();" +
                            "$('.article-title span').remove();" +
                            "$('.clearfix ').remove();" +
                            "$('#rcjsload_bf95f0').remove();" +
                        "}"
                );
            } else if (urlStart.contains("tele")){
                view.loadUrl("javascript:"+
                        "if (document.querySelector('.outer')){" +
                            "$('script').each(function () {" +
                                "    $(this).remove();" +
                                "});"+
                            "$('.mobile-header').remove();" +
                            "$('.mobileAdvert').remove();" +
                            "$('#articleSectionWithArrow').remove();" +
                            "$('#followPlatforms').remove();" +
                            "$('#mobileShareTop').remove();" +
                            "$('.relatedLinks').remove();" +
                            "$('#outbrain-links').remove();" +
                            "$('#article_commercial_06').remove();" +
                            "$('#mostViewedTitleDiv').remove();" +
                            "$('.mostviewed').remove();" +
                            "$('.footer').remove();" +
                            "$('.under').remove();" +
                            "$('.mobileAdvertInStream').remove();" +
                        "}"

                );
            } else if (urlStart.contains("moto")){
                view.loadUrl("javascript:"+
                                "if (document.querySelector('.outer')){" +
                                "$('script').each(function () {" +
                                "    $(this).remove();" +
                                "});"+
                                "$('.header').remove();" +
                                "$('.secondMenu').remove();" +
                                "$('.contentNavigSmall').remove()" +
                                "$('.articleAuthorBox').remove()" +
                                "$('.contentNavig').remove()" +
                                "}"

                );
            }
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            progress.dismiss();
        }
    }
}
