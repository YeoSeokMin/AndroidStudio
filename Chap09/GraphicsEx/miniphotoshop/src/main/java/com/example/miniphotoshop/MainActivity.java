package com.example.miniphotoshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.EmbossMaskFilter;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ImageButton ibZoomIn,ibZoomOut,ibRotate,ibBright,ibDark,ibGray,ibBlur,ibEmboss;
    LinearLayout pictureLayout;
    MyImageView myPicture;

    float scale = 1.0f;
    int rotate = 0;
    static float color=1;
    static int satur =1;
    static boolean blur = false;
    static boolean emboss = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ibZoomIn = (ImageButton)findViewById(R.id.ibZoomIn);
        ibZoomOut = (ImageButton)findViewById(R.id.ibZoomOut);
        ibRotate = (ImageButton)findViewById(R.id.ibRotate);
        ibBright = (ImageButton)findViewById(R.id.ibBright);
        ibDark = (ImageButton)findViewById(R.id.ibDark);
        ibGray = (ImageButton)findViewById(R.id.ibGray);
        ibBlur = (ImageButton)findViewById(R.id.ibBlur);
        ibEmboss = (ImageButton)findViewById(R.id.ibEmboss);
        pictureLayout = (LinearLayout)findViewById(R.id.pictureLayout);

        myPicture = new MyImageView(this);
        pictureLayout.addView(myPicture);



        ibZoomIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scale += 0.2f;
                myPicture.setScaleX(scale);
                myPicture.setScaleY(scale);
                myPicture.invalidate();

            }
        });
        ibZoomOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scale -= 0.2f;
                Log.i("MyApp",""+scale);
                if(scale >=0.1f) {
                    myPicture.setScaleX(scale);
                    myPicture.setScaleY(scale);

                } else {
                    Toast.makeText(MainActivity.this, "더이상 ZoomOut할 수 없습니다.", Toast.LENGTH_SHORT).show();
                }
                myPicture.invalidate();
            }
        });

        ibRotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                rotate += 15;
                myPicture.setRotation(rotate);
                myPicture.invalidate();
            }
        });

        ibBright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                color += 0.2f;
                myPicture.invalidate();
            }
        });
        ibDark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                color -= 0.2f;
                myPicture.invalidate();
            }
        });

        ibGray.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(satur==0)
                    satur = 1;
                else
                    satur = 0;
                Log.i("MyApp","satur:"+satur);
                myPicture.invalidate();

            }
        });
        ibBlur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(blur == true)
                    blur=false;
                else blur=true;

                myPicture.invalidate();
            }
        });
        ibEmboss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(emboss == true) {
                    emboss = false;
                } else  {
                    emboss=true;
                }
                myPicture.invalidate();
            }
        });



    }

    private static class MyImageView extends View {
        public MyImageView(Context context) {
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            Paint paint = new Paint();
            Bitmap picture = new BitmapFactory().decodeResource(getResources(),R.drawable.lena256);
            int picX = (int)(this.getWidth()-picture.getWidth())/2;
            int picY = (int)(this.getHeight()-picture.getHeight())/2;

            float[] array =
                    {color,0,0,0,-25,
                            0,color,0,0,-25,
                            0,0,color,0,-25,
                            0,0,0,1,0};
            ColorMatrix cm = new ColorMatrix(array);
            if (satur ==0) cm.setSaturation(satur);
            if (blur == true ) {
                BlurMaskFilter bMask;
                bMask = new BlurMaskFilter(120,BlurMaskFilter.Blur.NORMAL);
                paint.setMaskFilter(bMask);
            }
            if(emboss == true) {
                EmbossMaskFilter eMask;
                //                                                      빛의 밝기       빛이 반사
                eMask = new EmbossMaskFilter(new float[] {3,3,3},0.5f,5,10);
                paint.setMaskFilter(eMask);
            }

            paint.setColorFilter(new ColorMatrixColorFilter(cm));
            canvas.drawBitmap(picture,picX,picY,paint);
            picture.recycle();
        }
    }



}
