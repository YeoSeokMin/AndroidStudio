package com.example.chap07ex06;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {
    RadioGroup rdoGrp;
    //RadioButton rdoDog,rdoCat,rdoRabbit,rdoHorse;
    RadioButton rdoPet[] = new RadioButton[4];
    Integer rdoPetID[] = {R.id.rdoDog,R.id.rdoCat,R.id.rdoRabbit,R.id.rdoHorse};
    Integer rdoPetImg[] = {R.drawable.dog,R.drawable.cat,R.drawable.rabbit,R.drawable.cat};

    Button btnShowPic;
    View dlgView;
    ImageView dlgivPet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("연습문제 7-6");

        rdoGrp = (RadioGroup)findViewById(R.id.rdoGrp);
        for(int i=0; i<rdoPet.length;i++) {
            rdoPet[i] = (RadioButton)findViewById(rdoPetID[i]);

        }
        /*rdoDog = (RadioButton)findViewById(R.id.rdoDog);
        rdoCat = (RadioButton)findViewById(R.id.rdoCat);
        rdoRabbit = (RadioButton)findViewById(R.id.rdoRabbit);
        rdoHorse = (RadioButton)findViewById(R.id.rdoHorse);*/
        btnShowPic = (Button)findViewById(R.id.btnShowPic);


        btnShowPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //context는 장소
                AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                //dlgView = (View)View.inflate(getApplicationContext(),R.layout.dialog,null);
                dlgView = (View) View.inflate(getApplicationContext(), R.layout.dialog, null);

                //find하면 무조건 main에서 찾는다.
                dlgivPet = (ImageView) dlgView.findViewById(R.id.dlgivPet);

                //방법1
                /*if(rdoDog.isChecked()==true) {
                    dlgivPet.setImageResource(R.drawable.dog);
                } else if(rdoCat.isChecked()==true) {

                } else if(rdoRabbit.isChecked()==true) {

                } else if(rdoHorse.isChecked()==true) {

                } else {
                    Toast.makeText(MainActivity.this, "애완동물을 선택해주세요.", Toast.LENGTH_SHORT).show();
                }
                //dlg.setTitle(rdoDog.getText().toString());
                dlg.setView(dlgView);
                dlg.setPositiveButton("닫기",null);
                dlg.setCancelable(false);
                dlg.show();*/

                //내방법(방법2)
                /*switch (rdoGrp.getCheckedRadioButtonId()) {
                    case R.id.rdoDog :
                        dlg.setTitle(rdoDog.getText().toString());
                        dlgivPet.setImageResource(R.drawable.dog);
                        break;
                    case R.id.rdoCat :
                        dlg.setTitle(rdoCat.getText().toString());
                        dlgivPet.setImageResource(R.drawable.cat);
                        break;
                    case R.id.rdoRabbit :
                        dlg.setTitle(rdoRabbit.getText().toString());
                        dlgivPet.setImageResource(R.drawable.rabbit);
                        break;
                    default:
                        Toast.makeText(MainActivity.this, "애완동물을 선택해주세요.", Toast.LENGTH_SHORT).show();
                }
                //dlg.setTitle(id.name);
                dlg.setView(dlgView);
                dlg.setPositiveButton("닫기",null);
                dlg.setCancelable(false);
                dlg.show();*/

                //방법3(radioButton이 많을 때)
                for (int i = 0; i < rdoPet.length; i++) {
                    if (rdoPet[i].isChecked()) {
                        dlg.setTitle(rdoPet[i].getText().toString());
                        dlgivPet.setImageResource(rdoPetImg[i]);
                        break;
                    }
                }
                dlg.setView(dlgView);
                dlg.setPositiveButton("닫기",null);
                dlg.setCancelable(false);
                dlg.show();
            }
        });



    }
}
