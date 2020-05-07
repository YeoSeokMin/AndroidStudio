package com.example.gridview;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    GridView grid1;       //글씨보다는 그림등에 사용
    //Adapter는 할 수 없이 만들어야 한다.
    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        grid1 = (GridView)findViewById(R.id.grid1);
        adapter = new MyAdapter(this);      //this:장소(Adapter가 있을 장소
        grid1.setAdapter(adapter);
    }

    public class MyAdapter extends BaseAdapter {
        private Context context;
        Integer[] posterID = {R.drawable.mov01,R.drawable.mov02,R.drawable.mov03,R.drawable.mov04,R.drawable.mov05,
                R.drawable.mov06,R.drawable.mov07,R.drawable.mov08,R.drawable.mov09,R.drawable.mov10,
                R.drawable.mov01,R.drawable.mov02,R.drawable.mov03,R.drawable.mov04,R.drawable.mov05,
                R.drawable.mov06,R.drawable.mov07,R.drawable.mov08,R.drawable.mov09,R.drawable.mov10,
                R.drawable.mov01,R.drawable.mov02,R.drawable.mov03,R.drawable.mov04,R.drawable.mov05,
                R.drawable.mov06,R.drawable.mov07,R.drawable.mov08,R.drawable.mov09,R.drawable.mov10,
                R.drawable.mov01,R.drawable.mov02,R.drawable.mov03,R.drawable.mov04,R.drawable.mov05,
                R.drawable.mov06,R.drawable.mov07,R.drawable.mov08,R.drawable.mov09,R.drawable.mov10
        };
        String[] posterNameID = {"써니","완득이","괴물","라이오스타","비열한거리",
                "왕의남자","아이랜드","웰컴투동막골","헬보이","백투더피처",
                "써니","완득이","괴물","라이오스타","비열한거리",
                "왕의남자","아이랜드","웰컴투동막골","헬보이","백투더피처",
                "써니","완득이","괴물","라이오스타","비열한거리",
                "왕의남자","아이랜드","웰컴투동막골","헬보이","백투더피처",
                "써니","완득이","괴물","라이오스타","비열한거리",
                "왕의남자","아이랜드","웰컴투동막골","헬보이","백투더피처"
        };

        public MyAdapter(Context context) {
            this.context = context;
        }

        //돌면서 해주는거 만약4면 4개만 돌린다.그러면 이미지가 4개만 나온다.

        @Override
        public int getCount() {
            return posterID.length;
            //return 4;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return posterID[position];
        }


        //View는 Widget을 총칭
        //생성된 어댑터에서 가져올 뷰
        //position만큼 돌린다.(loop),
        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            //이미지뷰를 3개씩 놓을거니까....


            Display display = ((WindowManager)getSystemService(WINDOW_SERVICE)).getDefaultDisplay();
            int w = display.getWidth()/grid1.getNumColumns();
            int h = display.getHeight();
            int a = grid1.getNumColumns();

            ImageView ivMovie = new ImageView(context);
            ivMovie.setLayoutParams(new GridView.LayoutParams(w,(int)(w*1.5)));
            ivMovie.setScaleType(ImageView.ScaleType.FIT_CENTER);
            ivMovie.setPadding(5,5,5,5);
            ivMovie.setImageResource(posterID[position]);
            Button btn = new Button(context);
            btn.setText("b");

            ivMovie.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                    dlg.setTitle(posterNameID[position]);
                    //dlg.setIcon(R.drawable.android);
                    dlg.setIcon(R.drawable.movie_icon);

                    //다이얼로그 띄울때 같이 inflate시킬거다.
                    View dlgView = (View)View.inflate(MainActivity.this,R.layout.dialog,null);
                    ImageView ivDlgPoster = (ImageView)dlgView.findViewById(R.id.ivDlgPoster);
                    ivDlgPoster.setImageResource(posterID[position]);
                    dlg.setView(dlgView);
                    dlg.setPositiveButton("닫기",null);
                    dlg.show();
                }
            });


            return ivMovie;
        }
    }
}
