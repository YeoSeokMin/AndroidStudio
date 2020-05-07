package com.example.webview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText edtUrl;
    Button btnGo, btnBack;
    WebView webView1;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtUrl = this.findViewById(R.id.edtUrl);
        btnGo = this.findViewById(R.id.btnGo);
        btnBack = this.findViewById(R.id.btnBack);
        webView1 = this.findViewById(R.id.webView1);

        webView1.setWebViewClient(new myBrowser());
        WebSettings settings = webView1.getSettings();

        webView1.getSettings().setJavaScriptEnabled(true);


        settings.setBuiltInZoomControls(true);

        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                url = edtUrl.getText().toString();
                if(!url.substring(0,7).equals("http://")) {
                    url = "http://"+url;
                }
                webView1.loadUrl(url);
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webView1.goBack();
            }
        });

    }

    class myBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return super.shouldOverrideUrlLoading(view, url);
        }
    }
}
