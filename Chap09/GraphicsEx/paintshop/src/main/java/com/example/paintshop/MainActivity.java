package com.example.paintshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.solver.widgets.Rectangle;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SubMenu;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    final static int LINE=1, CIRCLE=2, RECTANGLE=3;
    static int curShape = LINE;

    final static int RED=4,GREEN=5,BLUE=6,DEFAULT=7;
    static int curColor = Color.BLACK;

    //ArrayList[]가 아닌 객체동적리스트를 만들 거다.
    static List<MyShape> myShapes = new ArrayList<MyShape>();
    static boolean isFinish = false;                        //현재 도형을 그리는 것을 완료했느냐의여부;(ACTION_UP)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        setContentView(new MyCanvas(this));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0,LINE,0,"선그리기");
        menu.add(0,CIRCLE,0,"원그리기");
        menu.add(0,RECTANGLE,0,"사각형그리기");

        SubMenu smenu = menu.addSubMenu("색상변경 >>");
        smenu.add(0,RED,0,"빨강");
        smenu.add(0,GREEN,0,"초록");
        smenu.add(0,BLUE,0,"파랑");
        smenu.add(0,DEFAULT,0,"디폴트");


        return true;     //내가 만든거 그냥 보여준다.
        //return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case LINE:
                curShape = LINE;
                break;
            case CIRCLE:
                curShape = CIRCLE;
                break;
            case RECTANGLE:
                curShape = RECTANGLE;
                break;
            case RED:
                curColor = Color.RED;
                break;
            case GREEN:
                curColor = Color.GREEN;
                break;
            case BLUE:
                curColor = Color.BLUE;
                break;
            case DEFAULT:
                curColor = Color.BLACK;
                break;


        }
        return super.onOptionsItemSelected(item);
    }

    private static class MyCanvas extends View {
        //-1 기본값을 주기 위해
        int startX = -1, startY = -1, stopX = -1, stopY = -1;

        public MyCanvas(Context context) {
            super(context);
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    startX = (int)event.getX();
                    startY = (int)event.getY();
                    isFinish = false;
                    break;
                case MotionEvent.ACTION_MOVE:
                    stopX = (int)event.getX();
                    stopY = (int)event.getY();
                    //Log.i("MyApp","x:"+stopX+"y:"+stopY);
                    this.invalidate();          //화면을 무효화한다.onDraw메서드 호출
                    break;
                case MotionEvent.ACTION_UP:
                    isFinish = true;
                    MyShape shape = new MyShape();
                    shape.shapeType = curShape;
                    shape.startX = startX;
                    shape.startY = startY;
                    shape.stopX = stopX;
                    shape.stopY = stopY;
                    shape.color = curColor;
                    myShapes.add(shape);
                    this.invalidate();
                    break;
            }
            return true;
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            Paint paint = new Paint();
            paint.setAntiAlias(true);
            paint.setStrokeWidth(5);
            paint.setStyle(Paint.Style.STROKE);

            //일단 myShape배열에 있는 도형을 모두 그린다.
            for (int i = 0; i < myShapes.size(); i++) {
                MyShape shape = myShapes.get(i);
                paint.setColor(shape.color);
                switch (shape.shapeType) {
                    case LINE:
                        canvas.drawLine(shape.startX, shape.startY, shape.stopX, shape.stopY, paint);
                        break;
                    case CIRCLE:
                        //반지름은 여기에서만 사용하니까...
                        //sqrt는 루트
                        //pow(2,2) 2의 제곱, pow(2,3) 2의 3승
                        int radius = (int) Math.sqrt(
                                Math.pow(shape.stopX - shape.startX, 2) +
                                        Math.pow(shape.stopY - shape.startY, 2)
                        );
                        canvas.drawCircle(shape.startX, shape.startY, radius, paint);
                        paint.setColor(Color.BLUE);
                        break;
                    case RECTANGLE:
                        Rect rect = new Rect(shape.startX, shape.startY, shape.stopX, shape.stopY);
                        canvas.drawRect(rect, paint);
                        break;
                    case RED:
                        paint.setColor(RED);
                        break;
                    case GREEN:
                        paint.setColor(GREEN);
                        break;
                    case BLUE:
                        paint.setColor(BLUE);
                        break;
                }

            }

            if (isFinish == false) {
                paint.setColor(curColor);
                switch (curShape) {

                    case LINE:
                        canvas.drawLine(startX, startY, stopX, stopY, paint);
                        break;
                    case CIRCLE:
                        //반지름은 여기에서만 사용하니까...
                        //sqrt는 루트
                        //pow(2,2) 2의 제곱, pow(2,3) 2의 3승
                        int radius = (int) Math.sqrt(
                                Math.pow(stopX - startX, 2) +
                                        Math.pow(stopY - startY, 2)
                        );
                        canvas.drawCircle(startX, startY, radius, paint);
                        paint.setColor(Color.BLUE);
                        break;
                    case RECTANGLE:
                        Rect rect = new Rect(startX, startY, stopX, stopY);
                        canvas.drawRect(rect, paint);
                        break;
                    case RED:
                        paint.setColor(RED);
                        break;
                    case GREEN:
                        paint.setColor(GREEN);
                        break;
                    case BLUE:
                        paint.setColor(BLUE);
                        break;
                }

            }
        }
    }
    //도형 클래스
    private static class MyShape {
        //메소드는 필요없고 필드만 있으면 된다.
        int shapeType;                      //그전에 그렸던게 어떤 도형으로 그렸는가?????
        int startX,startY,stopX,stopY;      //도형의 좌표값
        int color;                          //도형의 색상
    }
}
