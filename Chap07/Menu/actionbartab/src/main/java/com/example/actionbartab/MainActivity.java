package com.example.actionbartab;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

//ActionBar를 확장기능으로 사용할려고
public class MainActivity extends AppCompatActivity implements ActionBar.TabListener {
    ActionBar.Tab petTab[] = new ActionBar.Tab[4];

    //Fragment는 작은 화면단위
    MyTabFragment[] myFrags = new MyTabFragment[4];
    //작은이미지
    static Integer iconIDs[] = {R.drawable.icon_dog,R.drawable.icon_cat,R.drawable.icon_rabbit,R.drawable.icon_horse};
    //큰이미지
    static Integer imageIDs[] = {R.drawable.dog,R.drawable.cat,R.drawable.rabbit,R.drawable.horse};

    static int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        ActionBar bar = getSupportActionBar();

        //ActionBar 확장
        bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        for(int i=0; i<petTab.length;i++) {
            petTab[i] = bar.newTab();
            petTab[i].setIcon(iconIDs[i]);
            petTab[i].setTabListener(this);
            bar.addTab(petTab[i]);
        }
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
        MyTabFragment myTabFrags = null;
        pos = tab.getPosition();

        if(myFrags[pos] == null) {
            myTabFrags = new MyTabFragment();
            Bundle data = new Bundle();
            data.putInt("iconID",iconIDs[tab.getPosition()]);
            myTabFrags.setArguments(data);
            myFrags[pos]=myTabFrags;

        } else {
            myTabFrags = myFrags[pos];
        }

        ft.replace(android.R.id.content,myTabFrags);
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    //2작은 단위인 Fragment작성(xml없이 순수java로 만들어야 함)
    public static class MyTabFragment extends Fragment {
        //String strName;                 //이름가지고 탭을 구분하기위해;
        int iconID;

//        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//        LinearLayout baseLayout = new LinearLayout(super.getActivity());

        //fragment 하나생성
        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            //외부에서 argument를 받는 Bundle; key value
            Bundle data = getArguments();

            iconID = data.getInt("tabID");
        }


        //oncreate에서 만든 fragment를 View에 넣을려고
        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View petView = inflater.inflate(R.layout.fragment,null);
            ImageView flagivPet = (ImageView)petView.findViewById(R.id.fragivPet);


            if(iconID == iconIDs[pos]) {
                flagivPet.setImageResource(imageIDs[pos]);
            }
            return  petView;

        }
    }
}
