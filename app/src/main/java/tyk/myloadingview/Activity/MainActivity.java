package tyk.myloadingview.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import tyk.myloadingview.R;
import tyk.myloadingview.View.FirstView;

public class MainActivity extends AppCompatActivity {
    private Button startBtn, endBtn;
    private FirstView firstView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        firstView= (FirstView) findViewById(R.id.first_view);
        startBtn = (Button) findViewById(R.id.start_btn);
        endBtn = (Button) findViewById(R.id.end_btn);
    }
}
