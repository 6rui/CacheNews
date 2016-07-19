package www.bode.net.cachenews.ui.search;

import java.util.List;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import www.bode.net.cachenews.R;
import www.bode.net.cachenews.model.News;
import www.bode.net.cachenews.request.Request;

public class SearchActivity extends AppCompatActivity implements
                          AppBarLayout.OnOffsetChangedListener,
                          Request.RequestListener,
                          SearchAdapter.OnItemClickListener {
    private EditText search;
    
    private AppBarLayout appbar;
    
    private RecyclerView recycler;
    
    private String searchKey;
    
    private Request request;
    
    private boolean isRequest = false;
    
    private SearchAdapter adapter;
    
    private static final String APP_KEY = "56fa863926e60793dc9a2b03d7de64f0";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        
    }
    
    /**
     * 初始化
     */
    private void init() {
        search = (EditText) findViewById(R.id.search_word);
        recycler = (RecyclerView) findViewById(R.id.recycler);
        appbar = (AppBarLayout) findViewById(R.id.appbar);
        appbar.addOnOffsetChangedListener(this);
    }
    
    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        Log.i("verticalOffset", "onOffsetChanged: " + verticalOffset);
        
        if (verticalOffset == -372) {
            if (search != null) {
                searchKey = search.getText().toString();
            }
            if (!isRequest) {
                request = Request.getInstance();
                request.setRequestListener(this);
                request.requestUrl(request.requestAPI("http://op.juhe.cn/")
                                          .getTopMovie(searchKey,
                                                       APP_KEY,
                                                       null));
                isRequest = true;
            }
        }
        if (verticalOffset == 0) {
            isRequest = false;
        }
    }
    
    @Override
    public void succeed(Object o) {
        News news = (News) o;
        List<News.ResultBean> resultBean = news.getResult();
        adapter = new SearchAdapter(resultBean);
        recycler.setLayoutManager(new LinearLayoutManager(SearchActivity.this));
        RecyclerAnimator animator = new RecyclerAnimator();
        recycler.setItemAnimator(animator);
        adapter.setOnItemClickListener(this);
        recycler.setAdapter(adapter);
        request = null;
    }
    
    @Override
    public void failed() {
        Toast.makeText(SearchActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
    }
    
    @Override
    public void onItemClick(int position) {
        adapter.notifyItemRemoved(position);
        Toast.makeText(SearchActivity.this, position + "", Toast.LENGTH_SHORT)
             .show();
    }
}
