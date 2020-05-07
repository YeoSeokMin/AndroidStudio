package com.example.a10_2tupyo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class Result extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        setTitle("투표 결과");

        Intent intent = getIntent();
        int[] voteResult = intent.getIntArrayExtra("VoteCount");
        String[] imageName = intent.getStringArrayExtra("ImgName");
        //메인엑티비티에서 넘긴 인텐트를 getIntent로 받은 후 배열변수를 voteCount와 imgName에 저장

        TextView tv[] = new TextView[imageName.length];
        RatingBar rbar[] = new RatingBar[imageName.length];

        Integer tvID[] = {R.id.tv1,R.id.tv2,R.id.tv3,R.id.tv4,R.id.tv5,R.id.tv6,R.id.tv7,R.id.tv8,R.id.tv9,};
        Integer rbarID[] = {R.id.rBar1,R.id.rBar2,R.id.rBar3,R.id.rBar4,R.id.rBar5,R.id.rBar6,R.id.rBar7,R.id.rBar8,R.id.rBar9};
        Integer imageFileID[] = {R.drawable.mov01,R.drawable.mov02,R.drawable.mov03,R.drawable.mov04,R.drawable.mov05,R.drawable.mov06,R.drawable.mov07,R.drawable.mov08,R.drawable.mov09,};

        ImageView ivTopImage = findViewById(R.id.ivTopImage);
        TextView tvName = findViewById(R.id.tvTopName);

        int maxEntry=0;

        for (int i=0; i<tvID.length; i++){
            tv[i] = this.findViewById(tvID[i]);
            rbar[i] = this.findViewById(rbarID[i]);
        }
        for (int i=0; i<voteResult.length; i++){
            tv[i].setText(imageName[i]);
            rbar[i].setRating((float) voteResult[i]);
        }

        for (int i = 0; i<tvName.length(); i++){
            tvName.setText(imageName[i]+"(총"+voteResult[i]+"표)");
            rbar[i].setRating(voteResult[i]);
            if (voteResult[i]>voteResult[maxEntry]){
                maxEntry=i;
            }
        }

        tvName.setText(imageName[maxEntry]);
        ivTopImage.setImageResource(imageFileID[maxEntry]);

        Button btnResult = this.findViewById(R.id.btnResult);
        btnResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
