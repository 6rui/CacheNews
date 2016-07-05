package www.bode.net.cachenews.ui.main;

import java.util.List;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import www.bode.net.cachenews.R;
import www.bode.net.cachenews.model.News;
import www.bode.net.cachenews.request.Request;

public class MainActivity extends AppCompatActivity
                          implements AppBarLayout.OnOffsetChangedListener {
    private EditText search;
    
    private AppBarLayout appbar;
    
    private RecyclerView recycler;
    
    private ContentLoadingProgressBar loadingBar;
    
    private String searchKey;
    
    private Request request;
    
    private boolean isRequest = false;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        request.setRequestListener(new Request.RequestListener() {
            
            @Override
            public void succeed(News news) {
                
                List<News.ResultBean> resultBean = news.getResult();
                final MainAdapter adapter = new MainAdapter(resultBean);
                
                recycler.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                RecyclerAnimator animator = new RecyclerAnimator();
                recycler.setItemAnimator(animator);
                adapter.setOnItemClickListener(new MainAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        adapter.notifyItemRemoved(position);
                        Toast.makeText(MainActivity.this,
                                       position + "",
                                       Toast.LENGTH_SHORT)
                             .show();
                    }
                });
                recycler.setAdapter(adapter);
            }
            
            @Override
            public void failed() {
                Toast.makeText(MainActivity.this, "请求失败", Toast.LENGTH_SHORT)
                     .show();
            }
        });
            }
    
    /**
     * 初始化
     */
    private void init() {
        search = (EditText) findViewById(R.id.search_word);
        recycler = (RecyclerView) findViewById(R.id.recycler);
        loadingBar = (ContentLoadingProgressBar) findViewById(R.id.loading_bar);
        appbar = (AppBarLayout) findViewById(R.id.appbar);
        request = Request.getInstance(this);
        appbar.addOnOffsetChangedListener(this);
    }
    
    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        Log.i("verticalOffset", "onOffsetChanged: " + verticalOffset);
        
        if (verticalOffset == -372) {
            if (search != null) {
                searchKey = search.getText().toString();
            }
            loadingBar.show();
            if (!isRequest) {
                request.requestUrl(searchKey);
                isRequest = true;
            }
        }
        if (verticalOffset == 0) {
            isRequest = false;
        }
    }
}
