package com.example.petview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Switch swStart;
    TextView tvTitle;
    RadioGroup rdoGrp;
    ImageView imgPet;
    Button btnClose,btnFirst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        swStart = this.findViewById(R.id.swStart);
        tvTitle = this.findViewById(R.id.tvTitle);
        rdoGrp = this.findViewById(R.id.rdoGrp);
        imgPet = this.findViewById(R.id.imgPet);
        btnClose = this.findViewById(R.id.btnClose);
        btnFirst = this.findViewById(R.id.btnFirst);

        swStart.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //3가지 방법이 존재
                //3.1
                if(swStart.isChecked()) {
                    tvTitle.setVisibility(View.VISIBLE);
                    rdoGrp.setVisibility(View.VISIBLE);
                    imgPet.setVisibility(View.VISIBLE);
                    btnClose.setVisibility(View.VISIBLE);
                    btnFirst.setVisibility(View.VISIBLE);
                } else {
                    tvTitle.setVisibility(View.INVISIBLE);
                    rdoGrp.setVisibility(View.INVISIBLE);
                    imgPet.setVisibility(View.INVISIBLE);
                    btnClose.setVisibility(View.INVISIBLE);
                    btnFirst.setVisibility(View.INVISIBLE);
                }
                //3.2
                //if(chkStart.isChecked()==true) {}
                //3.3
                //if(isChecked == true) {}

            }
        });

        rdoGrp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                //Toast.makeText(getApplicationContext(), String.valueOf(rdoGrp.getCheckedRadioButtonId()), Toast.LENGTH_SHORT).show();
                switch (rdoGrp.getCheckedRadioButtonId()) {
                    case R.id.rdo1:
                        imgPet.setImageResource(R.drawable.dog);
                        break;
                    case R.id.rdo2:
                        imgPet.setImageResource(R.drawable.cat);
                        break;
                    case R.id.rdo3:
                        imgPet.setImageResource(R.drawable.rabbit);
                        break;
                    default:
                        rdoGrp.getCheckedRadioButtonId();
                        Toast.makeText(getApplicationContext(), "안드로이드버전을 선택해주세요.", Toast.LENGTH_SHORT).show();
                        imgPet.setImageResource(R.drawable.donut);
                }
            }
        });

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rdoGrp.clearCheck();
                swStart.setChecked(false);
                imgPet.setImageResource(0);
                tvTitle.setVisibility(View.INVISIBLE);
                rdoGrp.setVisibility(View.INVISIBLE);
                imgPet.setVisibility(View.INVISIBLE);
                btnClose.setVisibility(View.INVISIBLE);
                btnFirst.setVisibility(View.INVISIBLE);
            }
        });



    }
}
