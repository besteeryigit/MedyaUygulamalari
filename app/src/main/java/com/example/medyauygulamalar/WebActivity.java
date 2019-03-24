package com.example.medyauygulamalar;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class WebActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        WebView vw=(WebView)findViewById(R.id.webview);
        vw.getSettings().getJavaScriptEnabled();
        vw.loadUrl("https://gelecegiyazanlar.turkcell.com.tr");
        final ProgressDialog progress =ProgressDialog.show(this,"Geleceği Yazanlar","Yükleniyor...",true);
        progress.show();
        vw.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                Toast.makeText(getApplicationContext(),"Sayfa yüklendi.",Toast.LENGTH_SHORT).show();
                progress.dismiss();
            }
            public void onReceivedError(WebView view,int errorCode,String description,String failingUrl)
            {
                Toast.makeText(getApplicationContext(),"Bir hata oluştu.",Toast.LENGTH_SHORT).show();
                progress.dismiss();
            }

        });
    }
}
