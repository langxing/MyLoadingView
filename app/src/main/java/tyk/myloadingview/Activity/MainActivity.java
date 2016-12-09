package tyk.myloadingview.Activity;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import tyk.myloadingview.R;
import tyk.myloadingview.View.CircleColorView;
import tyk.myloadingview.View.CircleMoveView;

public class MainActivity extends AppCompatActivity {
    private Button startBtn, endBtn;
    private CircleColorView circleColorView;
    private CircleMoveView circleMoveView;
    private ObjectAnimator rotate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        circleColorView = (CircleColorView) findViewById(R.id.first_view);
        circleMoveView = (CircleMoveView) findViewById(R.id.second_view);
        startBtn = (Button) findViewById(R.id.start_btn);
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                circleColorView.startAnimation();
                circleMoveView.startAnimation();


                rotate = ObjectAnimator.ofFloat(circleMoveView, "rotation", 0f, 360f);
                rotate.setRepeatCount(ValueAnimator.INFINITE);
                rotate.setDuration(1000);
                rotate.start();

            }
        });
        endBtn = (Button) findViewById(R.id.end_btn);
        endBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                circleColorView.endAnimation();
                circleMoveView.endAnimation();
                rotate.end();
            }
        });
    }
}
