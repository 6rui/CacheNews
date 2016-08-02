package www.bode.net.cachenews.ui.classify;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import www.bode.net.cachenews.R;

/**
 * 分类页面详情的fragment Created by Liu on 2016-07-29.
 */
public class ClassifyFragment extends Fragment {
    private TextView text;
    
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity())
                                  .inflate(R.layout.fragment_classify,
                                           container,
                                           false);
        text = ((TextView) view.findViewById(R.id.tv_classify_fragment));
        return view;
    }
    
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        
    }
    
    @Override
    public void onDestroy() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }
    
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(String event) {
        text.setText(event);
    }
    
}
