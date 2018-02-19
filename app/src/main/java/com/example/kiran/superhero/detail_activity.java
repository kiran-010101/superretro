package com.example.kiran.superhero;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

public class detail_activity extends AppCompatActivity {

    Toolbar toolbar;
    ProgressBar progressBar;
    WebView webview;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_activity);


        toolbar = (Toolbar)findViewById(R.id.toolbar);
        progressBar = (ProgressBar)findViewById(R.id.progressbar);
        webview = (WebView)findViewById(R.id.detailview);

        setSupportActionBar(toolbar);

        //now to make sure that webview is invisible when progress bar is visible ..and when page is loade we have to hide progressbar and show webview

        webview.setVisibility(View.INVISIBLE);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.setWebChromeClient(new WebChromeClient());
        //there is event in webview like page is loading or it is loaded so for that we used

        webview.setWebViewClient(new WebViewClient(){
            //overriding methods here


            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                //progressbar is by default visible maybe
                Toast.makeText(detail_activity.this,"Page started loading",Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressBar.setVisibility(View.GONE);//after webpage loaded we remove the progressbar by makng its visibility gone
                webview.setVisibility(View.VISIBLE);
                Toast.makeText(detail_activity.this,"page loaded",Toast.LENGTH_SHORT).show();


            }
        });

        //at last we set url to oue webview ..webview allows us to display webpages

        webview.loadUrl(getIntent().getStringExtra("url"));//url is te key we sent using intent from our rcycleradpater class



    }
}
