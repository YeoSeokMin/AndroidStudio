package com.example.voteapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

public class ViewFlipperActivity extends AppCompatActivity {
    Button btnStart,btnStop;
    ViewFlipper vFlipper1;
    TextView tvRank[] = new TextView[9];
    Integer tvRankID[] = {
                            R.id.tvRank1,R.id.tvRank2,R.id.tvRank3,R.id.tvRank4,
                R.id.tvRank5,R.id.tvRank6,R.id.tvRank7,R.id.tvRank8,R.id.tvRank9
    };
    ImageView vfiv[] = new ImageView[9];        //sort해서 득표수가 많은 것을 맨앞으로 놓을 것.
    Integer vfivID[] = {
                            R.id.vfiv1,R.id.vfiv2,R.id.vfiv3,R.id.vfiv4,
                    R.id.vfiv5,R.id.vfiv6,R.id.vfiv7,R.id.vfiv8,R.id.vfiv9
    };
    TextView tvName[] = new TextView[9];
    Integer tvNameID[] = {
                            R.id.tvName1,R.id.tvName2,R.id.tvName3,R.id.tvName4,
                            R.id.tvName5,R.id.tvName6,R.id.tvName7,R.id.tvName8,R.id.tvName9
    };
    Integer imgID[] = {
                            R.drawable.pic1,R.drawable.pic2,R.drawable.pic3,R.drawable.pic4,
            R.drawable.pic5,R.drawable.pic6,R.drawable.pic7,R.drawable.pic8,R.drawable.pic9
    };


    //Sort에 필요한 변수
    int temp;
    String tempName;

    int voteResult[] = new int[9];
    String imgName[] = new String[9];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_flipper);
        ActionBar ab = getSupportActionBar();
        ab.setTitle("투표결과");
        ab.setIcon(R.drawable.pici_icon);
        ab.setDisplayShowHomeEnabled(true);
        ab.setDisplayHomeAsUpEnabled(true);

        btnStart = (Button)findViewById(R.id.btnStart);
        btnStop = (Button)findViewById(R.id.btnStop);
        vFlipper1 = (ViewFlipper)findViewById(R.id.vFlipper1);

        for(int i=0; i<imgID.length; i++) {
            tvRank[i] = (TextView)findViewById(tvRankID[i]);
            vfiv[i] = (ImageView)findViewById(vfivID[i]);
            tvName[i] = (TextView)findViewById(tvNameID[i]);
        }
        Intent gIntent = getIntent();
        voteResult= gIntent.getIntArrayExtra("VoteCount");
        imgName = gIntent.getStringArrayExtra("ImageName");
        //Sort Algorithm implement, 결과를 ViewFlipper에 출력
        for(int a=0; a<voteResult.length-1; a++) {
            for(int b=a+1; b<voteResult.length; b++) {
                if(voteResult[a] < voteResult[b]) {
                    temp = voteResult[a];
                    voteResult[a] = voteResult[b];
                    voteResult[b] = temp;

                    tempName = imgName[a];
                    imgName[a] = imgName[b];
                    imgName[b] = tempName;

                    temp = imgID[a];
                    imgID[a] = imgID[b];
                    imgID[b] = temp;
                }
            }
        }
        vFlipper1.setFlipInterval(2000);
        for(int i=0; i<imgID.length;i++) {
            tvRank[i].setText((i+1)+"등 그림");
            vfiv[i].setImageResource(imgID[i]);
            tvName[i].setText(imgName[i]+"("+voteResult[i]+"표)");
            //tvName[i].setText(imgName[i]+"");
        }

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vFlipper1.startFlipping();
            }
        });
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vFlipper1.stopFlipping();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
