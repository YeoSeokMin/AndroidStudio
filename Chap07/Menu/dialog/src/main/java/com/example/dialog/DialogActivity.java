package com.example.dialog;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DialogActivity extends AppCompatActivity {
    Button btnInfo;
    TextView tvResult;
    ImageView ivCold;
    /*
    1.대화상자 생성   new
    2.설정            ~~~~~set
    3.출력(show)      show
     */
    String pet[] = {"강아지","고양이","토끼"};
    int pos;

    //String[] fav = getResources().getStringArray(R.array.Favorite);
    String[] fav = {"요리1","요가","수영"};
    boolean[] checkValue =  {false,false,true};
    int sum;
    String result,subject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);

        btnInfo = (Button)findViewById(R.id.btnInfo);
        ivCold = (ImageView)findViewById(R.id.ivCold);
        tvResult = (TextView)findViewById(R.id.tvResult);

        btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               AlertDialog.Builder dlg = new AlertDialog.Builder(DialogActivity.this);
               dlg.setTitle("좋아하는 동물은?");
                dlg.setIcon(R.drawable.apple);

               /*dlg.setMessage("몸조심하세요.\n푹쉬세요.");
               dlg.setPositiveButton("예", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {
                       ivCold.setImageResource(R.drawable.cold);
                   }
               });
               dlg.setNegativeButton("종료(Activity)", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {
                       finish();
                   }
               });
               dlg.setNeutralButton("취소",null);
               //dlg.setSingleChoiceItems()

               dlg.setCancelable(false);
               dlg.show();*/

               //두번째 예시
               /*dlg.setItems(pet, new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {
                       tvResult.setText(pet[which]+"을 좋아하시는군요.");
                   }
               });
               dlg.show();*/

               //세번째 예시
               // -1은 아무것도 선택안한 상태

                /*dlg.setSingleChoiceItems(pet, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //msg = ""+pet[which];
                        //tvResult.setText(pet[which]+"을 좋아하시는군요.");
                        pos = which;
                    }
                });

                dlg.setPositiveButton("선택", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tvResult.setText(pet[pos]+"를 좋아하시는군요.");
                    }
                });
                dlg.setCancelable(false);
                dlg.show();*/

                //네번째 예시
                dlg.setMultiChoiceItems(fav, checkValue, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {

                    }
                });
                dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        result = "수강료는 총 " ;
                        subject = "";
                        sum = 0;

                        for(int a=0; a<fav.length;a++) {
                            if(checkValue[a] == true) {
                                sum+=30000;
                                subject+=fav[a];
                            }
                        }

                        if(sum==0) {
                            result = "선택과목이 없어요.";
                        } else {
                            result +=sum+"원 입니다.\n수강과목:"+subject;
                        }
                        tvResult.setText(result);

                    }
                });

                dlg.setCancelable(false);
                dlg.show();


            }
        });
    }
}
