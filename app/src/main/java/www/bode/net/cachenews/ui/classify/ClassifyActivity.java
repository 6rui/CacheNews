package www.bode.net.cachenews.ui.classify;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import www.bode.net.cachenews.R;

/**
 * 分类页面，EventBus框架实践
 */
public class ClassifyActivity extends AppCompatActivity {
    
    private RecyclerView recycler;
    
    private ArrayList<String> data;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classify);
        recycler = ((RecyclerView) findViewById(R.id.recycler_classify));
        recycler.setLayoutManager(new LinearLayoutManager(this));
        setData();
        ClassifyAdapter adapter = new ClassifyAdapter(this, data);
        recycler.setAdapter(adapter);
        
    }
    
    /**
     * 手动添加数据
     */
    private void setData() {
        data = new ArrayList<>();
        data.add("军事");
        data.add("政治");
        data.add("文化");
        data.add("娱乐");
        data.add("体育");
        data.add("国际");
    }
}
