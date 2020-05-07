package com.example.activityex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btnNewAct;
    Button rdo2ndAct,rdo3rdAct;
    RadioGroup rdoGrpAct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnNewAct = (Button)findViewById(R.id.btnNewAct);
        rdoGrpAct = (RadioGroup)findViewById(R.id.rdoGrpAct);
        rdo2ndAct = (Button)findViewById(R.id.rdo2ndAct);
        rdo3rdAct = (Button)findViewById(R.id.rdo3rdAct);

        btnNewAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //방법1:rdoGrp이용
                /*switch(rdoGrpAct.getCheckedRadioButtonId()) {
                    case R.id.rdo2ndAct :
                        Intent mintent2 = new Intent(getApplicationContext(),SecondActivity.class);
                        startActivity(mintent2);
                        break;
                    case R.id.rdo3rdAct :
                        Intent mintent3 = new Intent(getApplicationContext(),ThiredActivity.class);
                        startActivity(mintent3);
                        break;
                    default:
                        Toast.makeText(MainActivity.this, "Intent를 선택해주세요", Toast.LENGTH_SHORT).show();
                        break;
                }*/
                //방법2:rdo이용
                Intent mIntent;
                if(rdo2ndAct.isClickable()==true) {
                    mIntent = new Intent(getApplicationContext(),SecondActivity.class);
                    startActivity(mIntent);
                } else if (rdo3rdAct.isClickable()== true) {
                    mIntent = new Intent(getApplicationContext(),ThiredActivity.class);
                    startActivity(mIntent);
                }
            }
        });
    }
}
