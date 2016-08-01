package www.bode.net.cachenews.ui.classify;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import www.bode.net.cachenews.R;

/**
 * 分类页面详情的fragment Created by Liu on 2016-07-29.
 */
public class ClassifyFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        LayoutInflater.from(getActivity()).inflate(R.layout.fragment_classify,
                                          container,
                                          false);
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
