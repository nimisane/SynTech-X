package com.rdnational.syntechx.syntech_x;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class SocialMediaLinks extends AppCompatActivity {

    WebView page;
    ProgressBar progressBar;
    String page_url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social_media_links);
        Intent i = getIntent();
        page_url = i.getStringExtra("URL");
        page = findViewById(R.id.social_media_links);
        progressBar = findViewById(R.id.progress_bar);

        page.getSettings().setJavaScriptEnabled(true);
        page.setWebViewClient(new WebViewClient());
        page.loadUrl(page_url);
        page.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                if (progress == 100) {
                    progressBar.setVisibility(View.GONE);
                } else {
                    progressBar.setVisibility(View.VISIBLE);
                }
            }
        });
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
