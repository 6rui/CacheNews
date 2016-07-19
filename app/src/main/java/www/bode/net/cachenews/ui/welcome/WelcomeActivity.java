package www.bode.net.cachenews.ui.welcome;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import www.bode.net.cachenews.R;
import www.bode.net.cachenews.ui.pager.PagerActivity;

public class WelcomeActivity extends AppCompatActivity
                             implements ViewPager.OnPageChangeListener {
    
    private CircleImage circleImage;
    
    private ViewPager viewPager;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        viewPager = ((ViewPager) findViewById(R.id.viewpager_welcome));
        circleImage = (CircleImage) findViewById(R.id.circle_image);
        WelcomePagerAdapter pagerAdapter =
                                         new WelcomePagerAdapter(this,
                                                                 new int[] { R.mipmap.ic_liuss1,
                                                                             R.mipmap.ic_liuss2,
                                                                             R.mipmap.ic_liuss3,
                                                                             R.mipmap.ic_liuss1 });
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(this);
    }
    
    @Override
    public void onPageScrolled(int position,
                               float positionOffset,
                               int positionOffsetPixels) {
        ViewCompat.animate(circleImage)
                  .rotation(positionOffsetPixels / 1080f * 360 + position * 360)
                  .setDuration(100)
                  .start();
        Log.i("offset",
              "offset: " + positionOffset + "、" + positionOffsetPixels);
        if (position == 2 && positionOffsetPixels > 500) {
            startActivity(new Intent(WelcomeActivity.this,
                                     PagerActivity.class));
            finish();
        }
        
    }
    
    @Override
    public void onPageSelected(int position) {
        
    }
    
    @Override
    public void onPageScrollStateChanged(int state) {
    }
}
