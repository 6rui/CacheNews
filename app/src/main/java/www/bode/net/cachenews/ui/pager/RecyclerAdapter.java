package www.bode.net.cachenews.ui.pager;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import www.bode.net.cachenews.R;

/**
 * Created by Liu on 2016-07-01.
 */
public class RecyclerAdapter extends
                             RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder> {
    
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
        return 50;
    }
    
    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        holder.text.setText("Recycler" + position);
    }
    
    class RecyclerViewHolder extends RecyclerView.ViewHolder {
        
        private TextView text;
        
        public RecyclerViewHolder(View itemView) {
            super(itemView);
            text = (TextView) itemView.findViewById(R.id.text);
        }
    }
    
}
