package purelywebdesign.f1feedreader;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by Anthony on 18/03/2015.
 */
public class MyWebViewFragment extends Fragment {

    public MyWebViewFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View rootView = inflater.inflate(R.layout.web_view, container, false);

        Bundle bundle = this.getArguments();
        String url = bundle.getString("url");


        WebView newsItemWebView = (WebView) rootView.findViewById(R.id.webView1);
        newsItemWebView.setWebViewClient(new MyWebViewClient());
        WebSettings webSettings = newsItemWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        newsItemWebView.loadUrl(url);

        return rootView;
    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return false;
        }

        @Override
        public void onPageFinished(WebView view, String url)
        {
            view.loadUrl("javascript:document.querySelector('#sport').removeChild(document.querySelector('.nav-top'));" +
                    "document.querySelector('#sport-container').removeChild(document.querySelector('header'));" +
                    "document.querySelector('.main').removeChild(document.querySelector('.bg'));" +
                    "document.querySelector('.main').removeChild(document.querySelector('.bg'));" +
                    "document.querySelector('.main').removeChild(document.querySelector('.share-this'));" +
                    "document.querySelector('#sport').removeChild(document.querySelector('.nav-bottom'));" +
                    "document.querySelector('#sport-container').removeChild(document.querySelector('.orb-footer'));");
        }
    }
}
