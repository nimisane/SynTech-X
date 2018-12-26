package com.rdnational.syntechx.syntech_x;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.r0adkll.slidr.Slidr;

public class SocialMediaLinks extends AppCompatActivity {

    WebView page;
    ProgressBar progressBar;
    String page_url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social_media_links);
        Slidr.attach(this);
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

    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.share_links,menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem items)
    {
        switch (items.getItemId())
        {
            case R.id.share_link:
                share_news();
                return true;
        }

        return super.onOptionsItemSelected(items);
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void share_news()   //share option menu
    {
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("text/plain");
        share.putExtra(Intent.EXTRA_SUBJECT,"Follow Us");
        share.putExtra(Intent.EXTRA_TEXT,page_url);
        startActivity(Intent.createChooser(share,"Share via"));
    }
}
