package www.bode.net.cachenews.ui.pager;

import android.annotation.TargetApi;
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
import android.view.MenuItem;
import android.view.View;

import www.bode.net.cachenews.R;
import www.bode.net.cachenews.ui.setting.SettingActivity;

/**
 * viewpager+tab Created by Liu on 2016-07-01.
 */
public class PagerActivity extends AppCompatActivity implements
                           NavigationView.OnNavigationItemSelectedListener {
    
    private TabLayout tab;
    
    private ViewPager viewPager;
    
    private Toolbar toolbar;
    
    private DrawerLayout drawer;
    
    private NavigationView navigation;
    
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager);
        getWindow().setStatusBarColor(getResources().getColor(R.color.colorAccent));
        tab = (TabLayout) findViewById(R.id.tab);
        viewPager = (ViewPager) findViewById(R.id.pager);
        toolbar = ((Toolbar) findViewById(R.id.toolbar));
        drawer = ((DrawerLayout) findViewById(R.id.drawer));
        navigation = ((NavigationView) findViewById(R.id.navigation));
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
    }
    
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
                startActivity(new Intent(PagerActivity.this,SettingActivity.class));
                break;
        }
        return true;
    }
}
