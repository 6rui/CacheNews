package www.bode.net.cachenews.ui.pager;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;

/**
 * 自定义Behavior Created by Liu on 2016-07-04.
 */
public class SettingBehavior extends CoordinatorLayout.Behavior<View> {
    public SettingBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    
    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout,
                                       View child,
                                       View directTargetChild,
                                       View target,
                                       int nestedScrollAxes) {
        return true;
    }
    
    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout,
                                  View child,
                                  View target,
                                  int dx,
                                  int dy,
                                  int[] consumed) {
        super.onNestedPreScroll(coordinatorLayout,
                                child,
                                target,
                                dx,
                                dy,
                                consumed);
        if (dy > 0) {
            ViewCompat.animate(child).rotation(360).scaleX(0).scaleY(0).start();
        }
        else {
            ViewCompat.animate(child).rotation(0).scaleX(1).scaleY(1).start();
        }
    }
}
