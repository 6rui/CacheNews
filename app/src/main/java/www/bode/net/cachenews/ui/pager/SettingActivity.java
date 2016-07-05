package www.bode.net.cachenews.ui.pager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.SwipeDismissBehavior;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import www.bode.net.cachenews.R;

/**
 * 设置主视图 Created by Liu on 2016-07-04.
 */
public class SettingActivity extends AppCompatActivity {
    private TextView text;
    
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        text = ((TextView) findViewById(R.id.tv_coordinator));
//        CoordinatorLayout.LayoutParams params =
//                                              (CoordinatorLayout.LayoutParams) text.getLayoutParams();
//        params.setBehavior(new SwipeDismissBehavior<TextView>());
    }
}
