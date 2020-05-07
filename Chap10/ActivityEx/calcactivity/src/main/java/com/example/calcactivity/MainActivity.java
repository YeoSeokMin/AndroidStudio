package com.example.calcactivity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText edtNum1,edtNum2;
    RadioGroup rdoGrpCalc;
    //RadioButton rdoAdd,rdoSub,rdoMul,rdoDiv;
    Button btnCalc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtNum1 = (EditText)findViewById(R.id.edtNum1);
        edtNum2 = (EditText)findViewById(R.id.edtNum2);
        rdoGrpCalc = (RadioGroup)findViewById(R.id.rdoGrpCalc);
        //rdoAdd = (RadioButton)findViewById(R.id.rdoAdd);
        //rdoSub = (RadioButton)findViewById(R.id.rdoSub);
        //rdoMul = (RadioButton)findViewById(R.id.rdoMul);
        //rdoDiv = (RadioButton)findViewById(R.id.rdoDiv);
        btnCalc = (Button)findViewById(R.id.btnCalc);

        btnCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(getApplicationContext(),CalcReturnActivity.class);
                //mIntent.putExtra("CalcMethod",rdoGrpCalc.getCheckedRadioButtonId());
                //Log.i("MyApp",""+rdoGrpCalc.getCheckedRadioButtonId());
                mIntent.putExtra("Num1",Double.parseDouble(edtNum1.getText().toString()));
                mIntent.putExtra("Num2",Double.parseDouble(edtNum2.getText().toString()));

                switch (rdoGrpCalc.getCheckedRadioButtonId()) {
                    case R.id.rdoAdd:
                        mIntent.putExtra("Symbol","+");
                        break;
                    case R.id.rdoSub:
                        mIntent.putExtra("Symbol","-");
                        break;
                    case R.id.rdoMul:
                        mIntent.putExtra("Symbol","*");
                        break;
                    case R.id.rdoDiv:
                        mIntent.putExtra("Symbol","/");
                        break;
                }

                startActivityForResult(mIntent,0);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(resultCode == RESULT_OK) {
            double result = data.getDoubleExtra("result",0);
            Toast.makeText(this, ""+Math.round(result*100)/100.00, Toast.LENGTH_SHORT).show();
        }

    }
}
