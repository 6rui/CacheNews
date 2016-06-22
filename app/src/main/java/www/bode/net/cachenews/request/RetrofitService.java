package www.bode.net.cachenews.request;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 网络请求封装类 Created by Liu on 2016-06-22.
 */
public class RetrofitService<IAPISERVICE> {
    /**
     * 无参构造器
     */
    public RetrofitService() {
    }
    
    /**
     * 获取service
     * 
     * @param url
     *            请求地址
     * @param clazz
     *            服务器接口类型
     * @return 返回的service
     */
    public IAPISERVICE buildService(String url, Class<IAPISERVICE> clazz) {
        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
        okHttpClient.connectTimeout(5, TimeUnit.SECONDS).build();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(url)
                                                  .addConverterFactory(GsonConverterFactory.create())
                                                  .build();
        return retrofit.create(clazz);
    }
    
    public void requestObservableData(IAPIService service) {
        service.getTopMovie(0, 100)
               .subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread())
               .subscribe(new Subscriber() {
                   @Override
                   public void onCompleted() {
                       
                   }
                   
                   @Override
                   public void onError(Throwable e) {
                       
                   }
                   
                   @Override
                   public void onNext(Object o) {
                       
                   }
               });
    }
}
