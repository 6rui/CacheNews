package www.bode.net.cachenews.ui.pager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

import www.bode.net.cachenews.model.WxNews;

/**
 * 数据适配器 Created by Liu on 2016-07-01.
 */
public class PagerAdapter extends FragmentStatePagerAdapter {
    
    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }
    
    @Override
    public Fragment getItem(int position) {
        return position == 0 ? new PagerFragment() : new WeatherFragment();
    }
    
    @Override
    public int getCount() {
        return 2;
    }
    
    @Override
    public CharSequence getPageTitle(int position) {
        return position == 0 ? "新闻" : "天气";
    }
}
