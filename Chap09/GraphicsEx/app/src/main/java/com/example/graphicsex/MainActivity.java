package com.example.graphicsex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        setContentView(new MyCanvas(this));

    }

    private static class MyCanvas extends View {
        public MyCanvas(Context context) {
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            Paint paint = new Paint();
            Rect rect1 = new Rect();
            RectF rectF = new RectF();      //타원

            paint.setAntiAlias(true);   //
            paint.setColor(Color.GREEN);
            canvas.drawLine(10,10,300,10,paint);

            //Rect rect1 = new Rect(130,50,130+100,50+100);
            canvas.drawRect(10,10,300,10,paint);

            paint.setStrokeWidth(30);
            canvas.drawLine(30,500,300,500,paint);

            paint.setStrokeCap(Paint.Cap.ROUND);
            canvas.drawLine(30,600,300,600,paint);

            rectF.set(60,650,260,730);
            canvas.drawOval(rectF,paint);

            rectF.set(60,700,160,800);
            canvas.drawArc(rectF,40,110,true,paint);

            paint.setColor(Color.BLUE);
            rectF.set(60,820,160,920);
            canvas.drawRect(rectF,paint);
            paint.setColor(Color.argb(120,255,0,0));
            rectF.set(90,850,190,950);
            canvas.drawRect(rectF,paint);


        }


    }
}
