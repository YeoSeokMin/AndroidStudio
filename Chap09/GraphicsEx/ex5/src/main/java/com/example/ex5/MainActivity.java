package com.example.ex5;

import androidx.annotation.NonNull;
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
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ImageView ivPaint;
    float scaleX = 1.0f;
    float scaleY = 1.0f;
    int rotate = 0;
    static float color = 0.2f;
    static int satur =1;
    static boolean blur = false;
    static boolean emboss = false;
    static final int ZOOMIN = 1;
    static final int ZOOMOUT =2;
    static final int ROTATE = 3;
    static final int BRIGHT =4;
    static final int DARK = 5;
    static final int GRAY =6;

    MyImageView myCanvas;           //사용자위젯

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        myCanvas = new MyImageView(this);
        registerForContextMenu(myCanvas);


        //ivPaint = (ImageView)findViewById(R.id.ivPaint);


    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater mInflater = getMenuInflater();

        if(v ==myCanvas) {

            menu.add(0,ZOOMIN,0,"확대");
            menu.add(0,ZOOMOUT,0,"축소");
            menu.add(0,ROTATE,0,"회전");
            menu.add(0,BRIGHT,0,"밝게");
            menu.add(0,DARK,0,"어둡게");
            menu.add(0,GRAY,0,"칼라/흑백");

        }
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {

            case ZOOMIN :
                Log.i("MyApp:",""+item.getItemId());
                scaleX += 0.2f;
                scaleY += 0.2f;
                //ivPaint.setScaleX(scale);
                //ivPaint.setScaleY(scale);
                //ivPaint.invalidate();
                break;
            case ZOOMOUT :
                Log.i("MyApp:",""+item.getItemId());
                scaleX -= 0.2f;
                scaleY -= 0.2f;
                //ivPaint.setScaleX(scale);
                //ivPaint.setScaleY(scale);
                //ivPaint.invalidate();
                break;
            case 3:
                Log.i("MyApp:",""+item.getItemId());
                rotate += 10;
                //ivPaint.invalidate();
                break;
            case 4:
                Log.i("MyApp:",""+item.getItemId());
                color += 0.2f;
                //ivPaint.invalidate();
                break;
            case 5:
                Log.i("MyApp:",""+item.getItemId());
                color -= 0.2f;
                //ivPaint.invalidate();
                break;
            case 6:
                Log.i("MyApp:",""+item.getItemId());
                if(satur==0)
                    satur = 1;
                else
                    satur = 0;
                //Log.i("MyApp","satur:"+satur);
                //ivPaint.invalidate();
                break;
            default:
                Toast.makeText(this, "Default", Toast.LENGTH_SHORT).show();
                break;
        }
        myCanvas.invalidate();
        return true;
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
