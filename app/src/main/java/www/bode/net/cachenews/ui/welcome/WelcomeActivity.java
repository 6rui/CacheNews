package www.bode.net.cachenews.ui.welcome;

import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import www.bode.net.cachenews.R;

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
                        new int[]{R.mipmap.ic_liuss1,
                                R.mipmap.ic_liuss2,
                                R.mipmap.ic_liuss3});
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(this);
    }

    @Override
    public void onPageScrolled(int position,
                               float positionOffset,
                               int positionOffsetPixels) {
        int centerX = (int) (circleImage.getX() + circleImage.getWidth() / 2);
        int centerY = (int) (circleImage.getY() + circleImage.getHeight() / 2);
        ViewCompat.animate(circleImage).rotation(positionOffsetPixels / 1080f * 360 + position * 360).setDuration(100).start();
        Log.i("offset", "offset: " + positionOffset + "、" + positionOffsetPixels);

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
