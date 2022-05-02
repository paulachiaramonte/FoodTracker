package com.example.foodtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

public class WebPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_page);

        Bundle bundle = getIntent().getExtras();
        String urlString = bundle.getString("urlPage");

        WebView webView = findViewById(R.id.webView);
        webView.loadUrl("https://www.google.com/");
    }
}