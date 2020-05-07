package com.example.gallery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.nio.channels.GatheringByteChannel;

public class MainActivity extends AppCompatActivity {
    //Gallery 취소선....이제는 안쓴다.(HorizontalScrollView가 있으므로)
    Gallery gallery1;
    ImageView ivPoster;
    //String이 아니고 이미지니까 어뎁터를 만들어야 한다.
    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gallery1 = (Gallery)findViewById(R.id.gallery1);
        ivPoster = (ImageView)findViewById(R.id.ivPoster);
        adapter = new MyAdapter(this);
        gallery1.setAdapter(adapter);
    }

    public class MyAdapter extends BaseAdapter{
        private Context context;
        Integer[] posterID = {R.drawable.mov01, R.drawable.mov02, R.drawable.mov03, R.drawable.mov04, R.drawable.mov05,
                R.drawable.mov06, R.drawable.mov07, R.drawable.mov08, R.drawable.mov09, R.drawable.mov10
        };
        String[] posterNameID = {"써니", "완득이", "괴물", "라이오스타", "비열한거리",
                "왕의남자", "아이랜드", "웰컴투동막골", "헬보이", "백투더피처"
        };

        public MyAdapter(Context context) {
            this.context = context;
        }

        @Override
        public int getCount() {
            return posterID.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return posterID[position];
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            final ImageView ivMovie = new ImageView(MainActivity.this);
            ivMovie.setLayoutParams(new Gallery.LayoutParams(200,300));
            ivMovie.setScaleType(ImageView.ScaleType.FIT_CENTER);
            ivMovie.setPadding(5,5,5,5);
            ivMovie.setImageResource(posterID[position]);

            ivMovie.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    ivPoster.setImageResource(posterID[position]);

                    Toast toast = new Toast(context);
                    View toastView = (View)View.inflate(getApplicationContext(),R.layout.toast,null);
                    TextView toastText = (TextView)toastView.findViewById(R.id.textView1);
                    toastText.setText(posterNameID[position]);
                    toastText.setTextColor(Color.BLACK);
                    toast.setView(toastView);
                    toast.show();

                    return false;
                }
            });



            return ivMovie;

        }
    }
}
