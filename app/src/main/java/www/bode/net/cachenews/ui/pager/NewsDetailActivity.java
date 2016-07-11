package www.bode.net.cachenews.ui.pager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import www.bode.net.cachenews.R;

public class NewsDetailActivity extends AppCompatActivity {
    
    private WebView web;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detil);
        web = ((WebView) findViewById(R.id.news_detail_web));
        String url_intent = getIntent().getExtras().getString("url");
        web.loadUrl(url_intent);
        web.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                web.loadUrl(url);
                return true;
            }
        });
    }
}
