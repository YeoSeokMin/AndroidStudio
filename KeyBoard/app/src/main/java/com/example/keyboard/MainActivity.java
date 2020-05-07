package com.example.keyboard;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {
    Button btnPiano[] = new Button[8];
    Integer btnPianoID[] = {R.id.btnDo1, R.id.btnRe, R.id.btnMi, R.id.btnPa, R.id.btnSol, R.id.btnLa, R.id.btnSi, R.id.btnDo2}
    Switch swBtn1,swBtn2,swBtn3;
    ImageView ivView1,ivView2,ivView3;
    TextView tv1,tv2,tv3;
    ImageView ivBTcon;
    static final int REQUEST_ENABLE = 10; // 블루투스 퍼미션 요청(사용하겠다고) , 블루투스 사용 권한을 요청 허락(상수값)
    BluetoothAdapter bluetoothAdapter; // 블루투스와 연결하는 어댑터(장치)
    int mPairedDeviceCount = 0; // 페어링의 디바이스 체크 ( 페어링된 디바이스 몇개인지 체크 )
    Set<BluetoothDevice> mDevice; // 블루투스 장치에 연결된 디바이스를 체크
    BluetoothDevice mRemoteDevice; // 서로 통신하려고 하는 장치
    BluetoothSocket mSocket = null; // 통신소캣 , 연결 안된상태
    OutputStream mOutputStream = null; // 이동하는 스트림
    InputStream minputStream = null; // 이동하는 스트림
    Thread mWorkerThread = null; // 동시에 작업처리할때
    String mStrDelimiter = "\n"; // 문자에서 마지막으로 용도(" ")
    char mCharDelmitger = '\n'; // 바이트로 보낼때 마지막 신호 의미 (' ')
    // 아두이노에서 서로 공통된 단어 사용
    byte readBuffer[]; // 아두이노에서 데이터 수신할때 byte 단위로 받아서 여기서도 바이트 단위를 써줌
    int readBufferPosition; // 현재 읽은 Position
    boolean sw[]={false,false,false};
    String str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        for(int i=0; i<btnPianoID.length; i++){
            btnPiano[i] = (Button)findViewById(btnPianoID[i]);
        }
        for(int i=0; i<btnPianoID.length; i++) {
            final int index;
            index = i;
            btnPiano[index].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    str = Integer.toString(index+1);
                    sendData(str);
                }
            });
        }

    }
    // 아두이노로 데이터를 보내는 부분
    void sendData(String msg) {
        msg += mStrDelimiter; // 문자에 연결해서 알리는걸 보여줌
        try {
            mOutputStream.write(msg.getBytes());
        } catch (IOException e) {
            Toast.makeText(getApplicationContext(), "문자열을 전송할 수 없습니다.", Toast.LENGTH_SHORT).show();// 아두이노로 문자열 전송중 에러가 났을 때
        }
    }

    // 블루투스 지원하는 폰인지 체크
    void checkBlueTooth() { // 블루투스를 체크하는곳
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter(); // 블루투스 어댑터 얻기
        if (bluetoothAdapter == null) { // 블루투스 어댑터에 돌아온 값이 null 이면
            // 장치가 블루투스를 지원하지 않는 경우
            Toast.makeText(getApplicationContext(),"블루투스를 지원하지 않는 장치입니다.",Toast.LENGTH_SHORT).show(); // 알림
        } else {
            // 장치가 블루투스를 지원하는 경우
            if (!bluetoothAdapter.isEnabled()) { // 블루투스 어댑터가 이네이블이 아닐경우
                // 암시적 인텐트를 통해 블루투스 연결여부 결정
                Intent eintent = new Intent(bluetoothAdapter.ACTION_REQUEST_ENABLE); // 암시적 인텐트(허용 창 띄움)
                startActivityForResult(eintent, REQUEST_ENABLE); // 블루투스 요청하는 화면 호출(상수 선언한 값 집어넣음)
                // startActivityForResult(양방향 액티비티) - 전달하는데 반드시 받는데 요구 / startAcitivity 명시적,암시적이든 호출
            } else {
                selectDevice(); // 메서드 호출
            }
        }
    }

    // 블루투스 장치 선택
    void selectDevice() { // 블루투스를 켯을때 이쪽으로 실행
        mDevice = bluetoothAdapter.getBondedDevices(); // 페어링한 디바이스 목록을 가져옴(연결 장치가 여러개 있으면)
        mPairedDeviceCount = mDevice.size(); // 블루투스 정보를 mDevice가 가지고 있어서 size를 쓰면 디바이스 개수가 보임
        if (mPairedDeviceCount == 0) { // 연결할 장치가 하나도 없으면
            Toast.makeText(getApplicationContext(),"연결할 장치가 하나도 없습니다.",Toast.LENGTH_SHORT).show();
        }
        androidx.appcompat.app.AlertDialog.Builder dlg = new androidx.appcompat.app.AlertDialog.Builder(this);
        dlg.setTitle("블루투스 장치 선택"); // 제목
        List<String> listItems = new ArrayList<String>(); // 동적배열(개수만큼)

        for (BluetoothDevice device : mDevice) { // 향상된 포문(배열의 값만큼 넘겨줘서 받는거)
            listItems.add(device.getName());
        }

        listItems.add("취소");
        final CharSequence items[] = listItems.toArray(new CharSequence[listItems.size()]); // 항목들이 만들어짐
        dlg.setItems(items, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == mPairedDeviceCount) { // 위에 변수(which)에 mPairedDeviceCount와 같다면
                    Toast.makeText(getApplicationContext(),"취소버튼을 클릭하셨습니다.",Toast.LENGTH_SHORT).show(); //listItems
                } else {
                    // 디바이스로 연결
                    connectToSelectedDevice(items[which].toString()); // 밑에 메서드에 넘겨줌(connectToSelectedDevice)
                }
            }
        });
        dlg.setCancelable(false); // 뒤로 가기 버튼 사용 금지
        AlertDialog alert = dlg.create(); // alertDialog를 만들어서
        alert.show(); // 보이게 할거
    }

    //블루투스 장치를 소캣(UUID)에 연결
    void connectToSelectedDevice(String selectedDeviceName) { // 디바이스 장치를 선택하기 위해 만듬(실제 연결할 장치)
        mRemoteDevice = getDeviceFromBondedList(selectedDeviceName); // 밑에 getDeviceFromBondedList에서 선언한것을 가져옴 ,
        // 페어링한 디바이스 장치를 사용(아두이노 통신 사용)
        // Universally Unique IDentifier (블루투스 통신에 사용되는 클래스(객체))
        UUID uuid = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb"); // 문자값을 받아옴 (장치의 값을 가져옴(고유번호)) , 사용되는 소캣 장치 이름

        try {
            mSocket = mRemoteDevice.createRfcommSocketToServiceRecord(uuid); // 원격 블루투스와의 연결 장치
            mSocket.connect(); // 연결
            mOutputStream = mSocket.getOutputStream(); // 출력 정보 얻음
            minputStream = mSocket.getInputStream();
            ivBTcon.setImageResource(R.drawable.bluetooth_icon);
            beginListenForData(); // 데이터 송 수신
        } catch (Exception e) { // 장치번호를 못찾으면
            Toast.makeText(getApplicationContext(),selectedDeviceName+"와 연결할 수 없습니다.",Toast.LENGTH_SHORT).show(); // 다시 연결이 끊어지면
            ivBTcon.setImageResource(R.drawable.bluetooth_grayicon); // 이 아이콘으로 다시 바꿔줌
        }
    }

    // 데이터 수신
    void beginListenForData() {
        final Handler handler = new Handler(); // '잡고 있는 것'. splash를 3초간 잡고있다가 main 띄운 예제에서 사용.
        readBuffer = new byte[1024]; // 기본 1메가 , 아두이노 보낸 데이터 수신 버퍼
        readBufferPosition = 0;
        mWorkerThread = new Thread(new Runnable() { // 동시 작업 처리
            @Override
            public void run() {
                while (!Thread.currentThread().isInterrupted()) {
                    try { // 파일은 try/catch 써야됨
                        int bytesAvailable = minputStream.available(); // 수신데이터 확인
                        if (bytesAvailable > 0) {
                            byte[] packetBytes = new byte[bytesAvailable];
                            minputStream.read(packetBytes); // 위에 것을 읽어들여서 처리
                            for (int i = 0; i < bytesAvailable; i++) {
                                byte b = packetBytes[i];
                                if (b == mCharDelmitger) { // 다 받았을 때 처리
                                    byte encodeBytes[] = new byte[readBufferPosition];
                                    System.arraycopy(readBuffer, 0, encodeBytes, 0, encodeBytes.length); // 배열 카피 명령

                                    final String data = new String(encodeBytes, "US-ASCII");

                                    readBufferPosition = 0;
                                    handler.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            char buttondata[]=data.toCharArray(); // 배열로 처리
                                            Switch s=null;// 버튼3개

                                            switch (buttondata[0]) { // 첫번째 버튼값
                                                case '1': // 아스키코드값으로 와서 문자1로 표현
                                                    s=(Switch)findViewById(R.id.swBtn1); // 첫번째 스위치 버튼의 정보를 넣어줌
                                                    if(buttondata[1]=='0') {
                                                        if (sw[0] == false) {
                                                            sw[0] = true;
                                                            s.setChecked(true);
                                                            ivView1.setBackgroundColor(Color.RED);
                                                            tv1.setText("가동중");
                                                        } else {
                                                            s.setChecked(false);
                                                            sw[0] = false;
                                                            ivView1.setBackgroundColor(Color.WHITE);
                                                            tv1.setText("가동중지");
                                                        }
                                                    }
                                                    break;
                                                case '2':
                                                    s=(Switch)findViewById(R.id.swBtn2); // 두번째 스위치 버튼의 정보를 넣어줌
                                                    if(buttondata[1]=='0') {
                                                        if (sw[1] == false) {
                                                            sw[1] = true;
                                                            s.setChecked(true);
                                                            ivView2.setBackgroundColor(Color.RED);
                                                            tv2.setText("가동중");
                                                        } else {
                                                            s.setChecked(false);
                                                            sw[1] = false;
                                                            ivView2.setBackgroundColor(Color.WHITE);
                                                            tv2.setText("가동중지");
                                                        }
                                                    }
                                                    break;
                                                case '3':
                                                    s=(Switch)findViewById(R.id.swBtn3); // 세번째 스위치 버튼의 정보를 넣어줌
                                                    if(buttondata[1]=='0') {
                                                        if (sw[2] == false) {
                                                            sw[2] = true;
                                                            s.setChecked(true);
                                                            ivView3.setBackgroundColor(Color.RED);
                                                            tv3.setText("가동중");
                                                        } else {
                                                            s.setChecked(false);
                                                            sw[2] = false;
                                                            ivView3.setBackgroundColor(Color.WHITE);
                                                            tv3.setText("가동중지");
                                                        }
                                                    }
                                                    break;
                                            }
                                       /*     if(s != null) { // s가 눌러졌으면
                                                if(buttondata[1]=='0') {
                                                    s.setChecked(false);
                                                }else {
                                                    s.setChecked(true);
                                                }
                                            }*/
                                        }
                                    });
                                } else {
                                    readBuffer[readBufferPosition++] = b; // 패키지 바이트로 0이면 0번째 받음
                                }
                            }
                        }
                    } catch (IOException e) {
                        Toast.makeText(getApplicationContext(), "데이터 수신중 오류가 발생하였습니다.", Toast.LENGTH_SHORT).show(); // 데이터 수신 중 오류 발생
                    }
                }
            }
        });
        mWorkerThread.start();
    }

    // 페어링된 블루투스 장치를 이름으로 찾기
    BluetoothDevice getDeviceFromBondedList(String name) {
        BluetoothDevice selectDevice = null;
        for (BluetoothDevice device : mDevice) {
            if (name.equals(device.getName())) {
                selectDevice = device;
                break;
            }
        }
        return selectDevice;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) { // 양방향 액티비티에서 뭘 받아서 처리
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_ENABLE:
                if (resultCode == RESULT_OK) { // 예라고 누르면
                    selectDevice(); // 메서드 가서 페어링 장치를 찾음
                } else { // 아니오라 누르면
                    Toast.makeText(getApplicationContext(),"블루투스 연결 요청을 거부했습니다.",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}


