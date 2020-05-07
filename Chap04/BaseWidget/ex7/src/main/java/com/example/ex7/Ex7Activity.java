package com.example.ex7;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

public class Ex7Activity extends AppCompatActivity {
    CheckBox chkBoxEnabled, chkBox45;
    Button btnEnabled;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex7);
        setTitle("연습문제4-7");

        chkBoxEnabled = this.findViewById(R.id.chkBoxEnabled);
        chkBox45 = this.findViewById(R.id.chkBox45);
        btnEnabled = this.findViewById(R.id.btnEnabled);


        chkBoxEnabled.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(chkBoxEnabled.isChecked()==true) {
                    btnEnabled.setEnabled(true);
                } else {
                    btnEnabled.setEnabled(false);
                }
            }
        });

        chkBox45.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(chkBox45.isChecked()==true) {
                    btnEnabled.setEnabled(true);
                    btnEnabled.setRotation(45);
                } else {
                    btnEnabled.setEnabled(false);
                }
            }
        });

        btnEnabled.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Toast", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
