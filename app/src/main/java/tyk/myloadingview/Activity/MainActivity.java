package tyk.myloadingview.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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
        initView();
    }

    private void initView() {
        firstView= (FirstView) findViewById(R.id.first_view);
        startBtn = (Button) findViewById(R.id.start_btn);
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstView.startAnimation();
            }
        });
        endBtn = (Button) findViewById(R.id.end_btn);
        endBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstView.endAnimation();
            }
        });
    }
}
