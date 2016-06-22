package www.bode.net.cachenews.request;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 网络请求类 Created by Liu on 2016-06-22.
 */
public interface IAPIService {
    @GET("top250")
    Observable getTopMovie(@Query("start") int start,
                           @Query("count") int count);
}
