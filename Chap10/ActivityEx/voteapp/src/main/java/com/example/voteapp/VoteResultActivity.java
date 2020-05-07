package com.example.voteapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class VoteResultActivity extends AppCompatActivity {
    TextView[] tvName = new TextView[9];
    Integer tvNameID[] = {R.id.tvName1,R.id.tvName2,R.id.tvName3,
                          R.id.tvName4,R.id.tvName5,R.id.tvName6,
                          R.id.tvName7,R.id.tvName8,R.id.tvName9};

    RatingBar[] rBar = new RatingBar[9];
    Integer rBarID[] = {R.id.rBar1,R.id.rBar2,R.id.rBar3,
            R.id.rBar4,R.id.rBar5,R.id.rBar6,
            R.id.rBar7,R.id.rBar8,R.id.rBar9};

    ImageView[] ivPic = new ImageView[9];
    Integer[] ImageFileID = {R.drawable.pic1,R.drawable.pic2,R.drawable.pic3,
                             R.drawable.pic4,R.drawable.pic5,R.drawable.pic6,
                             R.drawable.pic7,R.drawable.pic8,R.drawable.pic9 };


    Button btnReturn;

    int[] voteResult;       //투표수를 받을 배열
    String[] imgName;       //영화이름을 받을 배열;

    TextView tvTopName;
    ImageView ivTopImage;
    int top=0;
    String topTitle="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote_result);

        ActionBar bar = getSupportActionBar();
        bar.setTitle("투표 결과");
        bar.setIcon(R.drawable.pici_icon);
        bar.setDisplayShowHomeEnabled(true);
        tvTopName = (TextView)findViewById(R.id.tvTopName);
        ivTopImage = (ImageView)findViewById(R.id.ivTopImage);


        for(int i=0; i<tvName.length; i++) {
            tvName[i] = (TextView)findViewById(tvNameID[i]);
            rBar[i] = (RatingBar)findViewById(rBarID[i]);
        }

        btnReturn = (Button)findViewById(R.id.btnReturn);
        Intent gIntent = getIntent();

        //int배열이니까 getIntArrayExtra, int 하나면 getIntExtra
        //앞에서 꼭 카피해서 붙여라, 대소문자 구분하니까...
        voteResult = gIntent.getIntArrayExtra("VoteCount");
        imgName = gIntent.getStringArrayExtra("ImageName");

        String topImage;

        // Making Me
        for(int i=0; i<tvName.length; i++) {
            if(top<voteResult[i]) {
                top = voteResult[i];
                topTitle = imgName[i];
                ivTopImage.setImageResource(ImageFileID[i]);
            }
            tvName[i].setText(imgName[i]+"(총"+voteResult[i]+"표)");
            rBar[i].setRating(voteResult[i]);
        }
        tvTopName.setText(topTitle);
        

        //From Teacher
        /*
        for(int i=0; i<tvName.length; i++) {
            if (voteResult[i] > voteResult[top]) {
                top = i;
            }
        }
        tvTopName.setText(imgName[top]);
        */


        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });




    }
}
