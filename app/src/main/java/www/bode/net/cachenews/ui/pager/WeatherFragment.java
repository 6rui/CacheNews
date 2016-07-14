package www.bode.net.cachenews.ui.pager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import www.bode.net.cachenews.R;
import www.bode.net.cachenews.request.Request;

/**
 * 天气预报页面 Created by Liu on 2016-07-14.
 */
public class WeatherFragment extends Fragment
                             implements Request.RequestListener {
    /**
     * 利用butterKnife初始化需要用到的控件
     */
    @BindView(R.id.edit_weather_query)
    private TextInputEditText inputCity;
    
    @BindView(R.id.weather_city)
    private TextView city;
    
    @BindView(R.id.weather_temp)
    private TextView temp;
    
    @BindView(R.id.weather_wind_dir)
    private TextView windDir;
    
    @BindView(R.id.weather_wind_str)
    private TextView windStr;
    
    @BindView(R.id.weather_humidity)
    private TextView humidity;
    
    @BindView(R.id.weather_time)
    private TextView time;
    
    @BindView(R.id.weather_img)
    private ImageView img;
    
    @BindView(R.id.weather)
    private TextView weather;
    
    @BindView(R.id.weather_temperature)
    private TextView temperature;
    
    @BindView(R.id.weather_dressing_index)
    private TextView dressingIndex;
    
    @BindView(R.id.weather_dressing_advise)
    private TextView dressingAdvise;
    
    @BindView(R.id.weather_travel_index)
    private TextView travelIndex;
    
    @BindView(R.id.weather_exercise_index)
    private TextView exerciseIndex;
    
    private Request reqeust;
    
    /**
     * 利用餐刀设置点击事件
     */
    @OnClick(R.id.tv_weather_query)
    private void query() {
        reqeust.requestUrl(reqeust.requestAPI("http://v.juhe.cn/")
                                  .getWeather("成都",
                                              null,
                                              0,
                                              "64a6e99c5bde45fd4b264fe9d3da3951"));
    }
    
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view =
                  inflater.inflate(R.layout.fragment_weather, container, false);
        ButterKnife.bind(getActivity());
        reqeust = Request.getInstance();
        reqeust.setRequestListener(this);
        return view;
    }
    
    @Override
    public void succeed(Object o) {
        
    }
    
    @Override
    public void failed() {
        
    }
}
