package www.bode.net.cachenews.ui.pager;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import www.bode.net.cachenews.R;
import www.bode.net.cachenews.ui.map.MapActivity;
import www.bode.net.cachenews.ui.search.SearchActivity;
import www.bode.net.cachenews.ui.setting.SettingActivity;

/**
 * viewpager+tab Created by Liu on 2016-07-01.
 */
@TargetApi(Build.VERSION_CODES.M)
public class PagerActivity extends AppCompatActivity implements
                           NavigationView.OnNavigationItemSelectedListener,
                           View.OnClickListener {
    
    private TabLayout tab;
    
    private ViewPager viewPager;
    
    private Toolbar toolbar;
    
    private DrawerLayout drawer;
    
    private NavigationView navigation;
    
    private ImageView menu;
    
    private ImageView main;
    
    private ImageView mine;
    
    private ImageView news;
    
    private ImageView note;
    
    private boolean isOpening;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // activity之间切换的动画
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        Transition explode =
                           TransitionInflater.from(this)
                                             .inflateTransition(R.transition.explode);
        getWindow().setExitTransition(explode);
        getWindow().setEnterTransition(explode);
        getWindow().setReenterTransition(explode);
        
        setContentView(R.layout.activity_pager);
        getWindow().setStatusBarColor(getResources().getColor(R.color.colorAccent));
        init();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,
                                                                 drawer,
                                                                 toolbar,
                                                                 0,
                                                                 0);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tab.setupWithViewPager(viewPager);
        navigation.setNavigationItemSelectedListener(this);
        // 浮动按钮监听
        menu.setOnClickListener(this);
        main.setOnClickListener(this);
        mine.setOnClickListener(this);
        news.setOnClickListener(this);
        note.setOnClickListener(this);
    }
    
    private void init() {
        tab = (TabLayout) findViewById(R.id.tab);
        viewPager = (ViewPager) findViewById(R.id.pager);
        toolbar = ((Toolbar) findViewById(R.id.toolbar));
        drawer = ((DrawerLayout) findViewById(R.id.drawer));
        navigation = ((NavigationView) findViewById(R.id.navigation));
        menu = ((ImageView) findViewById(R.id.menu));
        main = ((ImageView) findViewById(R.id.menu_main));
        mine = ((ImageView) findViewById(R.id.menu_mine));
        news = ((ImageView) findViewById(R.id.menu_news));
        note = ((ImageView) findViewById(R.id.menu_note));
        isOpening = false;
    }
    
    /**
     * 抽屉导航监听
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.info:
                drawer.closeDrawer(GravityCompat.START);
                Snackbar.make(navigation, "导航栏关闭", Snackbar.LENGTH_LONG)
                        .setAction("撤销操作", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                drawer.openDrawer(navigation);
                            }
                        })
                        .show();
                break;
            case R.id.setting:
                startActivity(new Intent(PagerActivity.this,
                                         SettingActivity.class),
                              ActivityOptions.makeSceneTransitionAnimation(this)
                                             .toBundle());
                break;
            case R.id.tel:
                startActivity(new Intent(PagerActivity.this,
                                         SearchActivity.class));
                overridePendingTransition(R.anim.scale_out, R.anim.scale_in);
                break;
            case R.id.map:
                startActivity(new Intent(PagerActivity.this,
                                         MapActivity.class));
                break;
        }
        return true;
    }
    
    /**
     * 圆形浮动菜单栏监听
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.menu:
                if (!isOpening) {
                    openCircleMenu();
                    isOpening = !isOpening;
                }
                else {
                    closeCircleMenu();
                    isOpening = !isOpening;
                }
                break;
            case R.id.menu_main:
                startActivity(new Intent(PagerActivity.this,
                                         SearchActivity.class));
                break;
            case R.id.menu_mine:
                startActivity(new Intent(PagerActivity.this,
                                         SettingActivity.class));
                break;
            case R.id.menu_news:
                break;
            case R.id.menu_note:
                break;
        }
    }
    
    /**
     * 展开圆形菜单栏
     */
    private void openCircleMenu() {
        main.setVisibility(View.VISIBLE);
        mine.setVisibility(View.VISIBLE);
        news.setVisibility(View.VISIBLE);
        note.setVisibility(View.VISIBLE);
        ObjectAnimator.ofFloat(main, "translationY", 0, -200f)
                      .setDuration(400)
                      .start();
        ObjectAnimator.ofFloat(mine, "translationY", 0, -400f)
                      .setDuration(400)
                      .start();
        ObjectAnimator.ofFloat(news, "translationY", 0, -600f)
                      .setDuration(400)
                      .start();
        ObjectAnimator.ofFloat(note, "translationY", 0, -800f)
                      .setDuration(400)
                      .start();
    }
    
    /**
     * 关闭圆形菜单栏
     */
    private void closeCircleMenu() {
        ObjectAnimator.ofFloat(main, "translationY", -200f, 0)
                      .setDuration(400)
                      .start();
        ObjectAnimator.ofFloat(mine, "translationY", -400f, 0)
                      .setDuration(400)
                      .start();
        ObjectAnimator.ofFloat(news, "translationY", -600f, 0)
                      .setDuration(400)
                      .start();
        ObjectAnimator anim = ObjectAnimator.ofFloat(note,
                                                     "translationY",
                                                     -800f,
                                                     0);
        anim.addListener(new AnimatorListenerAdapter() {
            
            @Override
            public void onAnimationEnd(Animator animation) {
                main.setVisibility(View.INVISIBLE);
                mine.setVisibility(View.INVISIBLE);
                news.setVisibility(View.INVISIBLE);
                note.setVisibility(View.INVISIBLE);
            }
        });
        anim.setDuration(400).start();
        
    }
    
}
