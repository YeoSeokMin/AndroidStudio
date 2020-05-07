package com.example.toctoc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {
    //쓰레드에서 네크워크 관련처리를 알 수 있도록 저장하는 부분
    EditText edtSendMsg;
    Button btnsend;
    TextView tvReMsg;

    InputStream is;
    ObjectInputStream ois;

    OutputStream os;
    ObjectOutputStream oos;

    Socket socket;

    String rMsg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        edtSendMsg = findViewById(R.id.edtSendMsg);
        btnsend = findViewById(R.id.btnSend);
        tvReMsg = findViewById(R.id.tvReMsg);

        btnsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start();
                tvReMsg.setText(rMsg);
            }
        });
    }
    //통신 시작
    public void start(){
        try {
            socket = new Socket("192.168.3.128",7000);
            sendMessage(socket);
            receiveMessage(socket);
        }catch (Exception e){
            //서버와 연결이 안됩니다.
        }finally {
            try {
                socket.close();
            }catch (Exception e){

            }
        }

    }
    //서버에서 받는 메서드
    public void receiveMessage(Socket socket){
        try {
            is = socket.getInputStream();
            ois = new ObjectInputStream(is);
            rMsg = (String)ois.readObject();

            }catch (Exception e){

        }
    }
    //서버에 보내는 메서드
    public void sendMessage(Socket socket){
        try {
            os = socket.getOutputStream();
            oos = new ObjectOutputStream(os);
            String msg = "여석민 : "+edtSendMsg.getText().toString();
            oos.writeObject(msg);

        }catch (Exception e){

        }
    }
}
