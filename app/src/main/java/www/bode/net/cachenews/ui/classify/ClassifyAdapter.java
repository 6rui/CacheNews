package www.bode.net.cachenews.ui.classify;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import www.bode.net.cachenews.R;

/**
 * 分类列表适配器 Created by Liu on 2016-08-01.
 */
public class ClassifyAdapter extends
                             RecyclerView.Adapter<ClassifyAdapter.ClassifyViewHolder> {
    public ClassifyAdapter(Context context, ArrayList<String> data) {
        this.context = context;
        this.data = data;
    }
    
    private Context context;
    
    private ArrayList<String> data;
    
    @Override
    public ClassifyViewHolder onCreateViewHolder(ViewGroup parent,
                                                 int viewType) {
        return new ClassifyViewHolder(LayoutInflater.from(context)
                                                    .inflate(R.layout.item_classify,
                                                             parent,
                                                             false));
    }
    
    @Override
    public void onBindViewHolder(ClassifyViewHolder holder, int position) {
        holder.tv.setText(data.get(position));
        
    }
    
    @Override
    public int getItemCount() {
        return data.size();
    }
    
    /**
     * Created by Liu on 2016-08-01.
     */
    class ClassifyViewHolder extends RecyclerView.ViewHolder {
        
        private final TextView tv;
        
        public ClassifyViewHolder(View itemView) {
            super(itemView);
            tv = ((TextView) itemView.findViewById(R.id.tv));
        }
    }
}
