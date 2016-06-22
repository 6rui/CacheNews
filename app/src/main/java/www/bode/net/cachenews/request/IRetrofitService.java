package www.bode.net.cachenews.request;

import rx.Subscriber;

/**
 * 网络请求接口 Created by Liu on 2016-06-22.
 */
public interface IRetrofitService<IREQUESTSERVICE> {
    /**
     * 创建访问网络的service
     * 
     * @return 返回service对象
     */
    IREQUESTSERVICE buidService();
    
    /**
     * 创建带授权的访问网络的service
     * 
     * @return 返回带授权的service对象
     */
    IREQUESTSERVICE buildHeaderService();
    
    /**
     * 请求数据
     * 
     * @param subscriber
     *            订阅者
     */
    void requestObservableData(Subscriber subscriber);
    
    /**
     * 取消订阅
     * 
     * @param subscriber
     *            订阅者
     */
    void requestCancel(Subscriber subscriber);
}
