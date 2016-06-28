package www.bode.net.cachenews.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

import www.bode.net.cachenews.R;
import www.bode.net.cachenews.model.News;
import www.bode.net.cachenews.request.Request;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Request request = Request.getInstance(this);
        request.requestUrl();
        request.setRequestListener(new Request.RequestListener() {
            @Override
            public void succeed(News news) {
                List<News.ResultBean> resultBeen = news.getResult();
            }

            @Override
            public void failed() {
                Toast.makeText(MainActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
