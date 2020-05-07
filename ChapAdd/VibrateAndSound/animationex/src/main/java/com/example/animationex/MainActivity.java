package com.example.animationex;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {
    ImageView iv1,iv2;
    Animation ani1,ani2,ani3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv1 = (ImageView)findViewById(R.id.iv1);
        iv2 = (ImageView)findViewById(R.id.iv2);

        Glide.with(this).load(R.drawable.leopard).into(iv1);
        ani1 = AnimationUtils.loadAnimation(this,R.anim.in);
        iv1.startAnimation(ani1);

        ani3 = AnimationUtils.loadAnimation(this,R.anim.in);
        iv2.startAnimation(ani3);

        ani1.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                Toast.makeText(MainActivity.this, "표범출몰", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //this가 아니라 정확한 이름의 this를 줘야 한다.
                ani2 = AnimationUtils.loadAnimation(MainActivity.this,R.anim.move);
                iv1.startAnimation(ani2);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }
}
