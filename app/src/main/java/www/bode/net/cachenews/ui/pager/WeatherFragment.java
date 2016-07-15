package www.bode.net.cachenews.ui.pager;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Locale;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import www.bode.net.cachenews.R;
import www.bode.net.cachenews.model.WeatherInfo;
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
    EditText inputCity;
    
    @BindView(R.id.weather_city)
    TextView city;
    
    @BindView(R.id.weather_temp)
    TextView temp;
    
    @BindView(R.id.weather_wind_dir)
    TextView windDir;
    
    @BindView(R.id.weather_wind_str)
    TextView windStr;
    
    @BindView(R.id.weather_humidity)
    TextView humidity;
    
    @BindView(R.id.weather_time)
    TextView time;
    
    @BindView(R.id.weather_img)
    ImageView img;
    
    @BindView(R.id.weather)
    TextView weather;
    
    @BindView(R.id.weather_temperature)
    TextView temperature;
    
    @BindView(R.id.weather_dressing_index)
    TextView dressingIndex;
    
    @BindView(R.id.weather_dressing_advise)
    TextView dressingAdvise;
    
    @BindView(R.id.weather_travel_index)
    TextView travelIndex;
    
    @BindView(R.id.weather_exercise_index)
    TextView exerciseIndex;
    
    @BindView(R.id.weather_uv_index)
    TextView uvIndex;
    
    private Request request;
    
    private WeatherInfo weatherInfo;
    
    /**
     * 利用餐刀设置点击事件
     */
    @OnClick(R.id.tv_weather_query)
    void query() {
        request.requestUrl(request.requestAPI("http://v.juhe.cn/")
                                  .getWeather(cityToUTF8(inputCity.getText()
                                                                  .toString()),
                                              null,
                                              2,
                                              "64a6e99c5bde45fd4b264fe9d3da3951"));
        InputMethodManager imm =
                               (InputMethodManager) getActivity().getSystemService(getActivity().INPUT_METHOD_SERVICE);
        // imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
        inputCity.setFocusable(false);
        imm.hideSoftInputFromWindow(inputCity.getWindowToken(), 0);
    }
    
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view =
                  inflater.inflate(R.layout.fragment_weather, container, false);
        request = Request.getInstance();
        request.setRequestListener(this);
        ButterKnife.bind(this, view);
        return view;
    }
    
    @Override
    public void succeed(Object o) {
        weatherInfo = ((WeatherInfo) o);
        WeatherInfo.ResultBean.TodayBean today = weatherInfo.getResult()
                                                            .getToday();
        WeatherInfo.ResultBean.SkBean sk = weatherInfo.getResult().getSk();
        dressingAdvise.setText(today.getDressing_advice());
        city.setText(inputCity.getText().toString());
        temp.setText(sk.getTemp());
        windDir.setText(sk.getWind_direction());
        windStr.setText(sk.getWind_strength());
        humidity.setText(String.format(Locale.CHINA,
                                       "湿度：%s",
                                       sk.getHumidity()));
        time.setText(String.format(Locale.CHINA, "更新时间：%s", sk.getTime()));
        weather.setText(today.getWeather());
        temperature.setText(today.getTemperature());
        dressingIndex.setText(today.getDressing_index());
        dressingAdvise.setText(today.getDressing_advice());
        dressingAdvise.setSelected(true);
        uvIndex.setText(String.format(Locale.CHINA,
                                      "紫外线：%s",
                                      today.getUv_index()));
        travelIndex.setText(String.format(Locale.CHINA,
                                          "%s旅游",
                                          today.getTravel_index()));
        exerciseIndex.setText(String.format(Locale.CHINA,
                                            "%s晨练",
                                            today.getExercise_index()));
    }
    
    @Override
    public void failed() {
    }
    
    /**
     * 转为UTF-8格式
     */
    private String cityToUTF8(String s) {
        String utfString = "";
        try {
            utfString = URLDecoder.decode(s, "UTF-8");
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return utfString;
    }
}
