package com.example.customlistview;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView list1;
    MyAdapter adapter;
    Integer imgID[] = {R.drawable.mov01, R.drawable.mov02, R.drawable.mov03, R.drawable.mov04, R.drawable.mov05,
            R.drawable.mov06, R.drawable.mov07, R.drawable.mov08, R.drawable.mov09, R.drawable.mov10,
            R.drawable.mov01, R.drawable.mov02, R.drawable.mov03, R.drawable.mov04, R.drawable.mov05,
            R.drawable.mov06, R.drawable.mov07, R.drawable.mov08, R.drawable.mov09, R.drawable.mov10
    };
    String[] title = {"써니", "완득이", "괴물", "라이오스타", "비열한거리",
            "왕의남자", "아이랜드", "웰컴투동막골", "헬보이", "백투더피처",
            "써니", "완득이", "괴물", "라이오스타", "비열한거리",
            "왕의남자", "아이랜드", "웰컴투동막골", "헬보이", "백투더피처"
    };
    String[] subTitle = {"공주 프로젝트","내 인생이 꼬이기 시작했다.","가족의사투가 시작되었다.","언제나 나를 최고라고","지금 여기 그 남자의...",
            "질투의 열망이 부른","이제 거대한 미래가 창조","1950년 지금은 전쟁중...","잘 생긴 얼굴만 세상을 구하는 건 아니지","과거로 여행을",
            "공주 프로젝트","내 인생이 꼬이기 시작했다.","가족의사투가 시작되었다.","언제나 나를 최고라고","지금 여기 그 남자의...",
            "질투의 열망이 부른","이제 거대한 미래가 창조","1950년 지금은 전쟁중...","잘 생긴 얼굴만 세상을 구하는 건 아니지","과거로 여행을"
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list1 = (ListView)findViewById(R.id.list1);
        List<ItemData> data = new ArrayList<ItemData>();

        List<String> data = new ArrayList<String>();
        List<Image> data = new ArrayList<Image>();

        for(int i=0; i<imgID.length;i++) {
            data.add(new ItemData(imgID[i],title[i],subTitle[i]));              //익명
        }

        adapter = new MyAdapter(this,data);
        //list1.setAdapter(adapter);

    }


}
