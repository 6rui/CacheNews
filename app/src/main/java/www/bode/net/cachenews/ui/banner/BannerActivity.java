package www.bode.net.cachenews.ui.banner;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import www.bode.net.cachenews.R;

public class BannerActivity extends AppCompatActivity {

    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner);
        viewPager = ((ViewPager) findViewById(R.id.viewpager_banner));
        PagerAdapter adapter = new PagerAdapter() {
            int[] imgs = {R.mipmap.ic_liuss1,
                    R.mipmap.ic_liuss2,
                    R.mipmap.ic_liuss3,
                    R.mipmap.ic_liuss,};

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                ImageView view = new ImageView(BannerActivity.this);
                view.setScaleType(ImageView.ScaleType.FIT_XY);
                view.setImageResource(imgs[position]);
                container.addView(view);
                return view;
            }

            @Override
            public void destroyItem(ViewGroup container,
                                    int position,
                                    Object object) {
                container.removeView((View) object);
            }

            @Override
            public int getCount() {
                return imgs.length;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }
        };
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(4);
        viewPager.setPageMargin(20);
        viewPager.setPageTransformer(true, new ViewPager.PageTransformer() {
            /**
             * @param page
             *            和当前页面距离为position对应的页面
             * @param position
             *            和当前page的距离
             */
            @Override
            public void transformPage(View page, float position) {
                Log.i("transformPage", "transformPage: " + position);
                if (position > 0 && position <= 1.1) {// 和当前item的距离为0-1之间表示为当前page右边的page
                    // page.setAlpha(1.2f - position);
                    page.setPivotX(page.getWidth() * 0.5f * (1 - position));
                    page.setPivotY(page.getHeight());
                    page.setRotation(30 * position);

                }
                if (position >= -1 && position < 0) {// 和当前page的距离为-1-0之间表示为当前page左边的page
                    // page.setAlpha(position + 1.2f);
                    page.setPivotX(page.getWidth());
                    page.setPivotY(page.getHeight() * 0.5f * (1 - position));
                    page.setRotation(30 * position);
                }
            }
        });
    }
}
