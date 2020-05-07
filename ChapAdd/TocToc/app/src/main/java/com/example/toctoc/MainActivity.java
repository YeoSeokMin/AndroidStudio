package com.example.toctoc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {
    EditText edtSendMsg;
    Button btnSend;
    TextView tvRcvMsg;

    InputStream is;
    ObjectInputStream ois;
    OutputStream os;
    ObjectOutputStream oos;
    Socket socket;

    String rMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //통신을 준비하는 단계(쓰레드 관련 네트워크관련처리메소드)
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        setContentView(R.layout.activity_main);

        edtSendMsg = (EditText)findViewById(R.id.edtSendMsg);
        btnSend = (Button)findViewById(R.id.btnSend);
        tvRcvMsg = (TextView)findViewById(R.id.tvRcvMsg);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start();
                tvRcvMsg.setText(rMsg);
            }
        });

    }
    //1.통신시작
    public void start() {
        try{
            socket = new Socket("192.168.3.128",7000);
            sendMessage(socket);
            receiveMessage(socket);
        } catch(Exception e) {
            Toast.makeText(this, "서버에 연결할 수 없습니다.", Toast.LENGTH_SHORT).show();
        } finally {
            try {
                socket.close();
            } catch (Exception e) {

            }
        }
    }
    //2.서버로 보내는 메소드
    public void sendMessage(Socket socket) {
        try{
            os = socket.getOutputStream();
            oos = new ObjectOutputStream(os);
            String msg = "흔호:"+edtSendMsg.getText().toString();
            oos.writeObject(msg);
        } catch (Exception e) {
            Toast.makeText(this, "서버로 보낼 수 없습니다.", Toast.LENGTH_SHORT).show();
        }
    }

    //3.서버에서 받는 메소드
    public void receiveMessage(Socket socket) {
        try{
            is = socket.getInputStream();
            ois = new ObjectInputStream(is);
            rMsg = (String)ois.readObject();
        } catch (Exception e) {
            Toast.makeText(this, "서버로 보낼 수 없습니다.", Toast.LENGTH_SHORT).show();
        }
    }
}
