package www.bode.net.cachenews.request;

import android.widget.Toast;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import www.bode.net.cachenews.CacheApplication;

/**
 * Retrofit RxJava网络请求封装 Created by Liu on 2016-06-28.
 */
public class Request {
    
    private static Request mRequest;
    
    public RequestListener mRequestListener;
    

    /**
     * 对外接口
     */
    public interface RequestListener {
        void succeed(Object o);
        
        void failed();
    }
    
    public void setRequestListener(RequestListener requestListener) {
        this.mRequestListener = requestListener;
        
    }
    
    public IAPIService requestAPI(String URL) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(URL)
                                                  .addConverterFactory(GsonConverterFactory.create())
                                                  .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                                                  .build();
        return retrofit.create(IAPIService.class);
    }
    
    /**
     * 单列获取对象
     */
    public static Request getInstance() {
        if (mRequest == null) {
            synchronized (Request.class) {
                mRequest = new Request();
            }
        }
        return mRequest;
    }
    
    public void requestUrl(Observable observable) {
        observable.subscribeOn(Schedulers.io())
                  .unsubscribeOn(Schedulers.io())
                  .observeOn(AndroidSchedulers.mainThread())
                  .subscribe(new Subscriber<Object>() {
                      @Override
                      public void onCompleted() {
                          Toast.makeText(CacheApplication.getContext(),
                                         "请求成功",
                                         Toast.LENGTH_SHORT)
                               .show();
                      }
                      
                      @Override
                      public void onError(Throwable e) {
                          e.printStackTrace();
                          mRequestListener.failed();
                      }
                      
                      @Override
                      public void onNext(Object o) {
                          mRequestListener.succeed(o);
                      }
                  });
    }
}
