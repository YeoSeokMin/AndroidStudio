package com.example.graphicex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.EmbossMaskFilter;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        setContentView(new MyImageView(this));
    }

    //image를 변환(흑백/진하게 등)을 하기 위해서
    private static class MyImageView extends View {
        public MyImageView(Context context) {
            super(context);

        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            Bitmap picture = new BitmapFactory().decodeResource(getResources(),R.drawable.chuja);
            int cenX = this.getWidth()/2;
            int cenY = this.getHeight()/2;
            int picX = (int)(this.getWidth()-picture.getWidth())/2;
            int picY = (int)(this.getHeight()-picture.getHeight())/2;
            /*
            //canvas.rotate(45,cenX,cenY);                              //이미지회전
            //canvas.translate(-150,200);                               //이미지이동
            //canvas.scale(2,2,cenX,cenY);                      //이미지확대/축소
            //canvas.skew(0.3f,0.3f);                         //이미지 비튼다.
            //여기까지는 canvas만 건드렸다.
            canvas.drawBitmap(picture,picX,picY,null);
            */

            //블러링과 엠보싱을 위해서..
            Paint paint = new Paint();
            //BlurMaskFilter bMask = new BlurMaskFilter(120,BlurMaskFilter.Blur.NORMAL);
            //BlurMaskFilter bMask = new BlurMaskFilter(120,BlurMaskFilter.Blur.INNER);
            //BlurMaskFilter bMask = new BlurMaskFilter(120,BlurMaskFilter.Blur.OUTER);
            //BlurMaskFilter bMask = new BlurMaskFilter(120,BlurMaskFilter.Blur.SOLID);
            //paint.setMaskFilter(bMask);
            //canvas.drawBitmap(picture,picX,picY,paint);
            //picture.recycle();//다 그렸으니 해제시킨다.

            /*EmbossMaskFilter eMask;
            //빛의 방향(x,y,z), 빛의밝기(0~1), 반사개수(5~8), 엠보싱크기
            eMask = new EmbossMaskFilter(new float[] {3,3,3},0.5f,5,10);
            paint.setMaskFilter(eMask);
            canvas.drawBitmap(picture,picX,picY,paint);
            picture.recycle();//다 그렸으니 해제시킨다.
            */

            //

            /*
            2,1,1: red만 밝아져 보인다.
             */
            /*float[] array = {2,0,0,0,-25,           //red,0,0   -25색상을 밝게하는거(빨간색을 밝게하면 옅은 빨간색이 된다.
                            0,2,0,0,-25,            //0,green,0 -는 어둡게 하는 것(그린을 어둡게 하는 거)
                            0,0,2,0,-25,            //0,0,blue  1은 기본값
                            0,0,0,1,0};             //1(투명도)
            ColorMatrix cm = new ColorMatrix(array);
            paint.setColorFilter(new ColorMatrixColorFilter(cm));
            canvas.drawBitmap(picture,picX,picY,paint);
            picture.recycle();*/

            //흑백
            ColorMatrix cm = new ColorMatrix();
            cm.setSaturation(1);                //0:회색으로 함(채도)
            paint.setColorFilter(new ColorMatrixColorFilter(cm));
            canvas.drawBitmap(picture,picX,picY,paint);
            picture.recycle();


        }
    }
}
