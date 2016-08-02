package www.bode.net.cachenews.ui.classify;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.greenrobot.eventbus.EventBus;

import www.bode.net.cachenews.R;
import www.bode.net.cachenews.ui.pager.OnRecyclerItemClickListener;

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
        recycler.addOnItemTouchListener(new OnRecyclerItemClickListener(recycler) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder vh) {
                // TextView textView =
                // (TextView) findViewById(R.id.tv_classify_fragment);
                // textView.setText(data.get(vh.getAdapterPosition()));
                EventBus.getDefault()
                        .post(new String(data.get(vh.getAdapterPosition())));
            }
        });
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
