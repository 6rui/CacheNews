package www.bode.net.cachenews.request;

import android.content.Context;
import android.widget.Toast;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import www.bode.net.cachenews.model.News;

/**
 * Retrofit RxJava网络请求封装 Created by Liu on 2016-06-28.
 */
public class Request {
    private Context mContext;
    
    private static Request mRequest;
    
    public RequestListener mRequestListener;
    
    private static final String URL = "http://op.juhe.cn";
    
    private static final String APP_KEY = "56fa863926e60793dc9a2b03d7de64f0";
    
    /**
     * 对外接口
     */
    public interface RequestListener {
        void succeed(News news);
        
        void failed();
    }
    
    public void setRequestListener(RequestListener requestListener) {
        this.mRequestListener = requestListener;
    }
    
    private Request(Context context) {
        mContext = context;
    }
    
    /**
     * 单列获取对象
     */
    public static Request getInstance(Context context) {
        if (mRequest == null) {
            synchronized (Request.class) {
                mRequest = new Request(context);
            }
        }
        return mRequest;
    }
    
    public void requestUrl(String searchKey) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(URL)
                                                  .addConverterFactory(GsonConverterFactory.create())
                                                  .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                                                  .build();
        IAPIService service = retrofit.create(IAPIService.class);
        Observable observable = service.getTopMovie(searchKey, APP_KEY, null);
        observable.subscribeOn(Schedulers.io())
                  .unsubscribeOn(Schedulers.io())
                  .observeOn(AndroidSchedulers.mainThread())
                  .subscribe(new Subscriber<News>() {
                      @Override
                      public void onCompleted() {
                          Toast.makeText(mContext, "请求成功", Toast.LENGTH_SHORT)
                               .show();
                      }
                      
                      @Override
                      public void onError(Throwable e) {
                          mRequestListener.failed();
                      }
                      
                      @Override
                      public void onNext(News news) {
                          mRequestListener.succeed(news);
                      }
                  });
    }
}
