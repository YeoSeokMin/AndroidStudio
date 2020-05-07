package com.example.dialog2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.zip.Inflater;


public class MainActivity extends AppCompatActivity {
    TextView edtName,edtEmail;
    Button btnInput;
    View dlgView;
    EditText dlgEdtName,dlgEdtEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtName = (EditText)findViewById(R.id.edtName);
        edtEmail = (TextView)findViewById(R.id.edtEmail);
        btnInput = (Button)findViewById(R.id.btnInput);


        btnInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                dlg.setTitle("개인정보입력");
                dlg.setIcon(R.drawable.apple);

                dlgView = (View) View.inflate(getApplicationContext(),R.layout.dialog,null);

                dlgEdtName = (EditText)dlgView.findViewById(R.id.dlgEdtName);
                dlgEdtEmail = (EditText)dlgView.findViewById(R.id.dlgEdtEmail);

                dlgEdtName.setText(edtName.getText().toString());
                dlgEdtEmail.setText(edtEmail.getText().toString());

                dlg.setView(dlgView);
                
                dlg.setNegativeButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        edtName.setText(dlgEdtName.getText().toString());
                        edtEmail.setText(dlgEdtEmail.getText().toString());
                    }
                });
                dlg.setPositiveButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "취소되었습니다.", Toast.LENGTH_SHORT).show();
                    }
                });

                dlg.setCancelable(false);
                dlg.show();
            }
        });

    }
}
