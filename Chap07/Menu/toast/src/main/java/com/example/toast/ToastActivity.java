package com.example.toast;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class ToastActivity extends AppCompatActivity {
    Button btnGoodMorning;
    Toast toast;
    View toastView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toast);

        btnGoodMorning = (Button)findViewById(R.id.btnGoodMorning);

        btnGoodMorning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*toast = Toast.makeText(ToastActivity.this, "aaa", Toast.LENGTH_SHORT);

                Display display = ((WindowManager)getSystemService(WINDOW_SERVICE)).getDefaultDisplay();
                int x = (int)(Math.random()*display.getWidth());
                int y = (int)(Math.random()*display.getHeight());

                toast.setGravity(Gravity.TOP | Gravity.LEFT,x,y);

                toast.setDuration(Toast.LENGTH_LONG);
                toast.show();*/

                //1.생성
                toast = new Toast(ToastActivity.this);

                //2.inflate
                toastView = (View)View.inflate(ToastActivity.this,R.layout.toast,null);    //root ViewGroup은 따로 없어서 null

                //3.보여줄거 연결
                TextView tvToast = (TextView)toastView.findViewById(R.id.tvToast);
                tvToast.setText("새해 복많이 받으세요.");
                toast.setView(tvToast);

                //4.show
                toast.show();






            }
        });
    }
}
