package com.example.rawex;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    Button btnRaw;
    TextView tvResult;
    EditText edtResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnRaw = (Button)findViewById(R.id.btnRaw);
        tvResult = (TextView)findViewById(R.id.tvResult);
        edtResult = (EditText)findViewById(R.id.edtResult);

        btnRaw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //읽기전용은 어떻게????/어제는 파일 읽고/쓰기였고 오늘은 읽기전용
                //File이지만 저장장치의 파일을 가져오는(FileInputStream)게 아니기 때문에 그냥 그냥 inputstream



                try {

                    InputStream inputS = getResources().openRawResource(R.raw.rawtest);        //저장장치에서 가져오는게 아니기 때문에
                    //바이트 단위
                    //숫자면?????
                    byte[] txt = new byte[inputS.available()];
                    inputS.read(txt);
                    //String str = new String(txt);
                    //edtResult.setText(new String(txt));
                    tvResult.setText(new String(txt));  //string인데 바이트이니까...
                    inputS.close();

                } catch (IOException e) {
                    //파일을 읽을 수가 없을때, 확장자 변경등....
                    showToast("파일을 읽을 수가 없습니다.");
                }


            }
        });
    }
    void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
