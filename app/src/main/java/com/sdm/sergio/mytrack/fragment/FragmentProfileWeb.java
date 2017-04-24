package com.sdm.sergio.mytrack.fragment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.sdm.sergio.mytrack.R;

public class FragmentProfileWeb  extends android.support.v4.app.Fragment{
    private WebView mWebView;
    private String url ="https://trakt.tv/auth/signin/";
    private ProgressBar bar;
    private View v;

    private ProgressDialog progress;
    private WebView webView;

    public static FragmentProfileWeb newInstance(){
        FragmentProfileWeb fragment = new FragmentProfileWeb();
        return new FragmentProfileWeb();}


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mainView = (View) inflater.inflate(R.layout.f_profile_web, container, false);
        WebView webView = (WebView)mainView.findViewById(R.id.webview);
        webView.setWebViewClient(new MantenerDominioWeb());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setBuiltInZoomControls(false);
        webView.getSettings().setSupportZoom(false);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.getSettings().setAllowFileAccess(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.setBackgroundColor(Color.TRANSPARENT);
        webView.setBackgroundResource(R.color.colorPrimaryDark);
        webView.canGoBack();
        webView.goBack();
        webView.loadUrl(getString(R.string.urltrack));

        progress = ProgressDialog.show(getContext(), getString(R.string.esperar),
                getString(R.string.conectando), true);
        webView.setWebViewClient(new WebViewClient() {

            public void onPageFinished(WebView view, String url) {
                if (progress != null)
                    progress.dismiss();
            }
        });
        return mainView;
    }
    public class MantenerDominioWeb extends WebViewClient {

        private WebView webView;

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (Uri.parse(url).getHost().endsWith(getString(R.string.urlt))) {
                return false;
            }

            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            view.getContext().startActivity(intent);
            return true;
        }

    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}


