package com.example.lenovo.myapplication;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.View;

public class Panel extends View {

    Paint mBallPaint;
    Paint mBoardPaint;


    int mWindowWidth;
    int mWindowHeight;

    public final int BOARD_WIDTH = 200;
    public final int BOARD_HEIGHT = 20;
    int mBoardX;
    int mBoardY;

    int mBallX;
    int mBallY;

    int mBallStepX;
    int mBallStepY;


    Handler handler;

    boolean mRun;
    public Panel(Context context, final Handler handler) {
        super(context);
        this.handler = handler;
        mBallPaint = new Paint();
        mBallPaint.setColor(Color.GREEN);

        mBoardPaint = new Paint();
        mBoardPaint.setColor(Color.GREEN);

        Resources resources = this.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        mWindowWidth = dm.widthPixels;
        mWindowHeight = dm.heightPixels;

        mBoardY = mWindowHeight - 150;
        mBoardX = (mWindowWidth - BOARD_WIDTH) / 2;


        mBallX = 0;
        mBallY = 0;
        mBallStepX = 3;
        mBallStepY = 3;

        mRun = true;
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(mRun) {
                    mBallX += mBallStepX;
                    mBallY += mBallStepY;
                    if (mBallX > mWindowWidth) {
                        mBallStepX = 0 - mBallStepX;
                    }
                    handler.sendEmptyMessage(1);
                    try {
                        Thread.sleep(30);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawCircle(mBallX, mBallY, 50, mBallPaint);
        canvas.drawRect(mBoardX, mBoardY, mBoardX + BOARD_WIDTH, mBoardY + BOARD_HEIGHT, mBoardPaint);

    }
}
