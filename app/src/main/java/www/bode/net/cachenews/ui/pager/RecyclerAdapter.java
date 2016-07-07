package www.bode.net.cachenews.ui.pager;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;
import java.util.Locale;

import www.bode.net.cachenews.R;
import www.bode.net.cachenews.model.WxNews;

/**
 * recycler的数据适配器 Created by Liu on 2016-07-01.
 */
public class RecyclerAdapter extends
                             RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder> {
    private List<WxNews.ResultBean.ListBean> list;
    
    private Context context;
    
    public RecyclerAdapter(Context context,
                           List<WxNews.ResultBean.ListBean> list) {
        this.list = list;
        this.context = context;
    }
    
    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent,
                                                 int viewType) {
        return new RecyclerViewHolder(LayoutInflater.from(parent.getContext())
                                                    .inflate(R.layout.item_pager,
                                                             parent,
                                                             false));
    }
    
    @Override
    public int getItemCount() {
        return list.size();
    }
    
    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        holder.title.setText(list.get(position).getTitle());
        holder.source.setText(String.format(Locale.CHINA,"来自于：%s",list.get(position).getSource()));
        Glide.with(context)
             .load(list.get(position).getFirstImg())
             .into(holder.image);
    }
    
    class RecyclerViewHolder extends RecyclerView.ViewHolder {
        
        private final ImageView image;
        
        private TextView source;
        
        private TextView title;
        
        public RecyclerViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            source = (TextView) itemView.findViewById(R.id.source);
            image = ((ImageView) itemView.findViewById(R.id.image));
        }
    }
    
}
