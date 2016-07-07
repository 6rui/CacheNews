package www.bode.net.cachenews.ui.pager;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import www.bode.net.cachenews.R;
import www.bode.net.cachenews.model.WxNews;
import www.bode.net.cachenews.request.Request;

/**
 * fragment Created by Liu on 2016-07-01.
 */
public class PagerFragment extends Fragment implements Request.RequestListener {
    private static final String APP_KEY = "9f8938b97cbee96a7d0005d172b9db94";
    
    private Request request;
    
    private List<WxNews.ResultBean.ListBean> list;
    
    private RecyclerAdapter adapter;
    
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        list = new ArrayList<>();
        request = new Request();
        request.requestUrl(request.requestAPI("http://v.juhe.cn/")
                                  .getWXNews(1, 20, APP_KEY, null));
        request.setRequestListener(this);
        View view = inflater.inflate(R.layout.fragment_pager, container, false);
        RecyclerView recycler = (RecyclerView) view.findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new RecyclerAdapter(getContext(), list);
        recycler.setAdapter(adapter);
        recycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (ViewCompat.canScrollVertically(recyclerView,1)){
                    request.requestUrl(request.requestAPI("http://v.juhe.cn/")
                            .getWXNews(2, 20, APP_KEY, null));
                }
            }
        });
        return view;
    }
    
    @Override
    public void succeed(Object o) {
        WxNews news = (WxNews) o;
        list.addAll(news.getResult().getList());
        adapter.notifyDataSetChanged();
    }
    
    @Override
    public void failed() {
        Toast.makeText(getContext(), "请求失败", Toast.LENGTH_SHORT).show();
    }
}
