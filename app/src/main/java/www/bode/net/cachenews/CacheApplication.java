package www.bode.net.cachenews;

import android.app.Application;
import android.content.Context;

/**
 * 全局application Created by Liu on 2016-06-22.
 */
public class CacheApplication extends Application {
    private static Context context;
    
    @Override
    public void onCreate() {
        if (context == null)
            context = getApplicationContext();
    }
    
    public static Context getContext() {
        return context;
    }
}
