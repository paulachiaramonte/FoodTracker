package com.example.foodtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_page);

        Bundle bundle = getIntent().getExtras();
        String urlString = bundle.getString("urlPage");

        WebView webView = findViewById(R.id.webView);
        webView.loadUrl(urlString);
        webView.setWebViewClient(new WebViewClient());


    }
}