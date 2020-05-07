package com.example.permission;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class MyImageView extends View {

    String imagePath = null;



    //장소, 속성
    public MyImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

    }

    //canvas 도화지(부모로부터 그림을 그릴 수 있는 도화지를 받음)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (imagePath != null) {
            Bitmap bm = BitmapFactory.decodeFile(imagePath);
            Paint paint = new Paint();
            ColorMatrix cm = new ColorMatrix();
            //cm.setSaturation(colorsw);
            int picX = (this.getWidth()-bm.getWidth())/2;
            int picY = (this.getHeight()-bm.getHeight())/2;

            canvas.drawBitmap(bm,picX,picY,paint);     //paint는 붓

            bm.recycle();                                       //bitmap 해제(리소스 해제)

        }
    }
}

