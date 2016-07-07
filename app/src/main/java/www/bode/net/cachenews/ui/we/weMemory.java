package www.bode.net.cachenews.ui.we;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import www.bode.net.cachenews.R;

public class weMemory extends AppCompatActivity {
    
    private Toolbar toolbar;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_we_memory);
        toolbar = ((Toolbar) findViewById(R.id.toolbar_we_memory));
        setSupportActionBar(toolbar);
    }
}
