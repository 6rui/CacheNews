package www.bode.net.cachenews.ui.main;

import java.util.List;

import com.bumptech.glide.Glide;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import www.bode.net.cachenews.R;
import www.bode.net.cachenews.model.News;

/**
 * 新闻数据适配器 Created by Liu on 2016-06-30.
 */
public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {
    private Context context;
    
    private List<News.ResultBean> mNewses;
    
    public OnItemClickListener onItemClickListener;
    
    public MainAdapter(List<News.ResultBean> newses) {
        this.mNewses = newses;
    }
    
    /**
     * 监听点击事件的接口
     */
    public interface OnItemClickListener {
        void onItemClick(int position);
    }
    
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
    
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_main,
                                                         parent,
                                                         false);
        return new ViewHolder(view);
    }
    
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        News.ResultBean bean = mNewses.get(position);
        String title = bean.getFull_title();
        String url = bean.getImg();
        String content = bean.getContent();
        String time = bean.getPdate_src();
        Glide.with(context).load(url).into(holder.imageView);
        holder.title.setText(title);
        holder.content.setText(content);
        holder.time.setText(time);
    }
    
    @Override
    public int getItemCount() {
        return mNewses.size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    class ViewHolder extends RecyclerView.ViewHolder
                     implements View.OnClickListener {
        public ImageView imageView;
        
        public TextView title;
        
        public TextView content;
        
        public TextView time;
        
        public ViewHolder(View view) {
            super(view);
            view.setOnClickListener(this);
            title = (TextView) view.findViewById(R.id.title);
            imageView = (ImageView) view.findViewById(R.id.image);
            content = (TextView) view.findViewById(R.id.content);
            time = (TextView) view.findViewById(R.id.update_time);
        }
        
        @Override
        public void onClick(View v) {
            onItemClickListener.onItemClick(getAdapterPosition());
        }
    }
    
}
