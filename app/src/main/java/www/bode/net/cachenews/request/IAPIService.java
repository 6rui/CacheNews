package www.bode.net.cachenews.request;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;
import www.bode.net.cachenews.model.News;

/**
 * 网络请求类 Created by Liu on 2016-06-22.
 */
public interface IAPIService<T extends Object> {
    @GET("/onebox/news/query")
    Observable<News> getTopMovie(@Query("q") String q,
                                 @Query("key") String key,
                                 @Query("dtype") String dtype);
    
}
