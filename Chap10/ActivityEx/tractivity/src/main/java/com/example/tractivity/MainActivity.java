package com.example.tractivity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText edtNum1,edtNum2;
    Button btnNewAddAct,btnNewDelAct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtNum1 = (EditText)findViewById(R.id.edtNum1);
        edtNum2 = (EditText)findViewById(R.id.edtNum2);
        btnNewAddAct = (Button)findViewById(R.id.btnNewAddAct);
        //btnNewDelAddAct = (Button)findViewById(R.id.btnNewDelAct);

        btnNewAddAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent;
                mIntent = new Intent(getApplicationContext(),AddActivity.class);
                //mIntent.putExtra("Num1",edtNum1.getText().toString());        //String
                mIntent.putExtra("Num1",Integer.parseInt(edtNum1.getText().toString()));
                mIntent.putExtra("Num2",Integer.parseInt(edtNum2.getText().toString()));
                startActivityForResult(mIntent,0);
                /*requestCode:
                >0이면 뭔가 돌려받겠다., 0이면 안쓰겠다.
                돌아올때 수행하는 메소드, 하나인데 어디서 왔는지를 구분하기 위한 것.
                */

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK) {
            int sum = data.getIntExtra("sum",0);
            Toast.makeText(this, "두 수의 합은 "+sum, Toast.LENGTH_SHORT).show();
        }
        //super.onActivityResult(requestCode, resultCode, data);
    }
}
