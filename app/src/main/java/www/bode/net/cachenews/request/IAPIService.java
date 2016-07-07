package www.bode.net.cachenews.request;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;
import www.bode.net.cachenews.model.News;
import www.bode.net.cachenews.model.WxNews;

/**
 * 网络请求类 Created by Liu on 2016-06-22.
 */
public interface IAPIService {
    // 360搜索
    @GET("onebox/news/query")
    Observable<News> getTopMovie(@Query("q") String q,
                                 @Query("key") String key,
                                 @Query("dtype") String dtype);
    
    // 微信推送
    @GET("weixin/query")
    Observable<WxNews> getWXNews(@Query("pno") int pno,
                                 @Query("ps") int ps,
                                 @Query("key") String key,
                                 @Query("dtype") String dtype);

}
