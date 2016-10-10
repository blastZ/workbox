package com.z.blast.workbox;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WeatherActivity extends Activity{
    private WebView webView;	//声明WebView组件的对象
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather_main);
        webView=(WebView)findViewById(R.id.webView1);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("http://m.weather.com.cn/mweather/101210101.shtml");
        webView.setInitialScale(57*4);

    }
    private void openUrl(String id){
        webView.loadUrl("http://m.weather.com.cn/mweather/"+id+".shtml");
    }
}
