package www.bode.net.cachenews.ui.pager;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
public class PagerFragment extends Fragment implements
                           Request.RequestListener,
                           SwipeRefreshLayout.OnRefreshListener {
    private static final String APP_KEY = "9f8938b97cbee96a7d0005d172b9db94";
    
    private Request request;
    
    private List<WxNews.ResultBean.ListBean> list;
    
    private RecyclerAdapter adapter;
    
    private SwipeRefreshLayout refresh;
    
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
        refresh = ((SwipeRefreshLayout) view.findViewById(R.id.refresh_pager));
        refresh.setOnRefreshListener(this);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new RecyclerAdapter(getContext(), list);
        recycler.setAdapter(adapter);
        recycler.addOnItemTouchListener(new OnRecyclerItemClickListener(recycler) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder vh) {
                Intent intent = new Intent(getActivity(),
                                           NewsDetailActivity.class);
                intent.putExtra("url",
                                list.get(vh.getAdapterPosition()).getUrl());
                startActivity(intent);
            }
        });
        recycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView,
                                             int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE
                    && ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastVisibleItemPosition()
                       + 1 == recyclerView.getAdapter().getItemCount()) {
                    requestNews();
                }
            }
            
        });return view;

    }
    
    private void requestNews() {
        int pager = 1;
        request.requestUrl(request.requestAPI("http://v.juhe.cn/")
                                  .getWXNews(++pager, 20, APP_KEY, null));
    }
    
    @Override
    public void onDestroy() {
        super.onDestroy();
        request = null;
    }
    
    @Override
    
    public void succeed(Object o) {
        WxNews news = (WxNews) o;
        list.addAll(news.getResult().getList());
        adapter.notifyDataSetChanged();
        refresh.setRefreshing(false);
    }
    
    @Override
    public void failed() {
        Toast.makeText(getContext(), "请求失败", Toast.LENGTH_SHORT).show();
    }
    
    /**
     * 下拉刷新数据
     */
    @Override
    public void onRefresh() {
        list.clear();
        adapter.notifyDataSetChanged();
        requestNews();
    }
}
