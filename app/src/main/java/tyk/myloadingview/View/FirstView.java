package tyk.myloadingview.View;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;

import tyk.myloadingview.R;

/**
 * Created by tangyangkai on 2016/12/8.
 */

public class FirstView extends View {


    private int firstColor, secondColor;
    private int mWidth, mHeight, mstartWidth;
    private Paint mPaint;
    private Thread thread;
    private Handler handler;
    private final int START_FLAG = 0;
    private final int END_FLAG = 1;
    private int nubmer = 0;
    private int mRadius;
    private ValueAnimator valueAnimator;

    public FirstView(Context context) {
        this(context, null);
    }

    public FirstView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FirstView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //获取我们自定义的样式属性
        TypedArray array = context.getTheme().obtainStyledAttributes(attrs, R.styleable.FirstView, defStyleAttr, 0);
        int n = array.getIndexCount();
        for (int i = 0; i < n; i++) {
            int attr = array.getIndex(i);
            switch (attr) {

                case R.styleable.FirstView_firstColor:
                    // 默认颜色设置为黑色
                    firstColor = array.getColor(attr, Color.BLACK);
                    break;
                case R.styleable.FirstView_secondColor:
                    secondColor = array.getColor(attr, Color.BLACK);
                    break;

            }

        }
        init();
        array.recycle();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);

                if (msg.what == START_FLAG) {
                    nubmer++;
                    if (nubmer >= 3) {
                        nubmer = 0;
                    }
                    invalidate();
                    handler.sendEmptyMessageDelayed(START_FLAG, 500);
                } else {
                    handler.removeMessages(START_FLAG);
                }

            }
        };


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int width;
        int height;
        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        } else {
            width = widthSize * 1 / 2;
        }
        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else {
            height = heightSize * 1 / 2;
        }
        mWidth = width;
        mHeight = height;
        mRadius = mHeight / 2;
        setMeasuredDimension(width, height);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mstartWidth = mWidth / 2 - mHeight * 2;
        mPaint.setColor(firstColor);
        mPaint.setStyle(Paint.Style.FILL);
        for (int i = 0; i < 3; i++) {
            if (i == nubmer) {
                mPaint.setColor(secondColor);
            } else {
                mPaint.setColor(firstColor);
            }
            canvas.drawCircle(mstartWidth, mHeight / 2, mRadius, mPaint);
            mstartWidth += mHeight * 2;
        }


    }


    public void startAnimation() {
//        thread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                Message message = new Message();
//                message.what = START_FLAG;
//                handler.sendMessage(message);
//            }
//        });
//        thread.start();


        handler.post(new Runnable() {
            @Override
            public void run() {
                Message message = new Message();
                message.what = START_FLAG;
                handler.sendMessage(message);
            }
        });

        valueAnimator = ValueAnimator.ofInt(mHeight / 2, mHeight / 8, mHeight / 2);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mRadius = (int) valueAnimator.getAnimatedValue();
            }
        });
        valueAnimator.setDuration(1000);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.start();

    }

    public void endAnimation() {
//        thread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                Message message = new Message();
//                message.what = END_FLAG;
//                handler.sendMessage(message);
//            }
//        });
//        thread.start();
        handler.post(new Runnable() {
            @Override
            public void run() {
                Message message = new Message();
                message.what = END_FLAG;
                handler.sendMessage(message);
            }
        });
        valueAnimator.end();
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
        if (!hasWindowFocus) {
            endAnimation();
        }
    }
}
