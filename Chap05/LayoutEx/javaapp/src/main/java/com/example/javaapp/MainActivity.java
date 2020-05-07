package com.example.javaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    String comment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Let's Go");

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        setContentView(layout);

        final EditText edtComment = new EditText(this);
        edtComment.setHint("Writing Your Comment");
        Button btnComment = new Button(this);
        btnComment.setText("Push me then Below You get Good comment");
        final TextView tvComment  = new TextView(this);

        layout.addView(edtComment);
        layout.addView(btnComment);
        layout.addView(tvComment);

        btnComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                comment = edtComment.getText().toString();
                tvComment.setText(comment);
            }
        });
    }
}
