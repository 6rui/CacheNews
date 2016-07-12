package www.bode.net.cachenews.ui.welcome;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * 欢迎页pager适配器 Created by Liu on 2016-07-12.
 */
public class WelcomePagerAdapter extends PagerAdapter {
    private int[] pics;
    
    private Context context;
    
    public WelcomePagerAdapter(Context context, int[] pics) {
        this.pics = pics;
        this.context = context;
    }
    
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = new ImageView(context);
        ViewGroup.LayoutParams layoutParams =
                                            new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                                                       ViewGroup.LayoutParams.MATCH_PARENT);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setImageResource(pics[position]);
        container.addView(imageView, layoutParams);
        return imageView;
    }
    
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
    
    @Override
    public int getCount() {
        return pics.length;
    }
    
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
