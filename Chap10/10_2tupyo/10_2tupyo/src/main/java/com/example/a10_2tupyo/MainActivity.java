package com.example.a10_2tupyo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("영화 선호도 투표");

        final int voteCount[] = new int[9];
        for (int i = 0; i<voteCount.length; i++){
            voteCount[i]=0;
        }
        ImageView image[] = new ImageView[9];
        Integer imageID[] = {R.id.iv1,R.id.iv2,R.id.iv3,R.id.iv4,R.id.iv5,R.id.iv6,R.id.iv7,R.id.iv8,R.id.iv9,};
        final String imgName[]= {"써니","완득이","괴물","라디오스타","비열한거리","왕의남자","아일랜드","웰컴투동막골","헬보이"};

        for (int i = 0; i<image.length; i++){
            final int index;
            index = i;
            image[index] = this.findViewById(imageID[index]);
            image[index].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    voteCount[index]++;
                    Toast.makeText(getApplicationContext(), imgName+":총"+voteCount[index]+"표", Toast.LENGTH_SHORT).show();
                }
            });
        }
        Button btnFinish = this.findViewById(R.id.btnFinish);
        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goResult = new Intent(getApplicationContext(),Result.class);
                goResult.putExtra("VoteCount", voteCount);
                goResult.putExtra("ImgName", imgName);

                startActivity(goResult);
            }
        });
    }
}
