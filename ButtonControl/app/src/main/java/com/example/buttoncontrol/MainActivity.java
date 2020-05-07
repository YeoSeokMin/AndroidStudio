package com.example.buttoncontrol;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Switch;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {
    Switch No1, No2, No3;

    BluetoothAdapter bluetoothAdapter;
    static final int REQUEST_ENABLE_BT=10;
    int mPairedDeviceCount=0;
    Set<BluetoothDevice> mDevices;
    BluetoothDevice mRemoteDevice;
    BluetoothSocket mSocket=null;
    OutputStream outputStream=null;
    InputStream inputStream=null;
    Thread mWorkerThread=null;
    String mStrDelimiter="\n";
    char mCharDelimiter='\n';
    byte readBuffer[];
    int readBufferPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        No1 = (Switch)findViewById(R.id.No1);
        No2 = (Switch)findViewById(R.id.No2);
        No3 = (Switch)findViewById(R.id.No3);
    }

    //블루투스 체크
    void checkBluetooth() {
        bluetoothAdapter=BluetoothAdapter.getDefaultAdapter();
        if(bluetoothAdapter==null) {
            //장치가 블루투스를 지원하지 않는 경우
            showToast("블루투스를 지원하지 않습니다.");
        }else {
            //장치가 블루투스를 지원하는 경우
            if(!bluetoothAdapter.isEnabled()){
                Intent eintent=new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(eintent,REQUEST_ENABLE_BT);
            }else {
                selectDevice();
            }
        }
    }
    //블루투스에 연결된 장치선택
    void selectDevice() {
        mDevices=bluetoothAdapter.getBondedDevices();
        mPairedDeviceCount=mDevices.size();
        if(mPairedDeviceCount==0){
            showToast("연결할 블루투스 장치가 하나도 없습니다.");
        }else {
            AlertDialog.Builder dlg=new AlertDialog.Builder(this);
            dlg.setTitle("블루투스 장치 선택");
            List<String> listitems=new ArrayList<String>();
            for(BluetoothDevice device:mDevices){
                listitems.add(device.getName());
            }
            listitems.add("취소");
            final CharSequence items[]=listitems.toArray(new CharSequence[listitems.size()]);
            dlg.setItems(items, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int which) {
                    if(which==mPairedDeviceCount){
                        showToast("장치 연결을 취소했습니다.");
                    }else {
                        connectToSelectedDevice(items[which].toString());
                    }
                }
            });
            dlg.setCancelable(false); //뒤로 가기 버튼 사용금지
            dlg.show();
        }
    }
    //선택한 장치와 연결작업
    void connectToSelectedDevice(String selectedDeviceName){
        mRemoteDevice=getDeviceFromBoundedList(selectedDeviceName);
        UUID uuid=UUID.fromString("00001101-0000-1000-8000-00805f9b34fb");
        try{
            //소켓생성
            mSocket=mRemoteDevice.createRfcommSocketToServiceRecord(uuid);
            mSocket.connect();

            outputStream=mSocket.getOutputStream();
            inputStream=mSocket.getInputStream();
            beginListenForData();
        }catch (Exception e) {
            showToast("장치 연결에 실패했습니다.");
        }
    }
    //아두이노에서 데이터 받기
    void beginListenForData() {
        final Handler handler=new Handler();
        readBuffer=new byte[1024]; //수신 버퍼
        readBufferPosition=0;  //버퍼내 수니 문자 저장 위치
        //문자열 수신 쓰레드
        mWorkerThread=new Thread(new Runnable() {
            @Override
            public void run() {
            while (!Thread.currentThread().isInterrupted()){
                try{
                    int byteAvailable=inputStream.available(); //수신 데이터
                    if(byteAvailable > 0){
                        byte packetBytes[]=new byte[byteAvailable];
                        inputStream.read(packetBytes);
                        for(int i=0; i < byteAvailable; i++) {
                            byte b=packetBytes[i];
                            if(b==mCharDelimiter){
                                byte encodedBytes[]=new byte[readBufferPosition];
                                System.arraycopy(readBuffer,0,encodedBytes,0,encodedBytes.length);
                                final String data=new String(encodedBytes,"US-ASCII");
                                readBufferPosition=0;
                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        //수신된 문자열 데이터에 대한 처리 작업
                                        int v_resistor=Integer.parseInt(data);
                                        tvValue.setText("가변저항값=" + v_resistor);
                                        sbValue.setProgress(v_resistor);
                                        if(v_resistor>700){
                                            ivPT.setImageResource(R.drawable.hot);
                                        }else if(v_resistor>300){
                                            ivPT.setImageResource(R.drawable.normal);
                                        }else {
                                            ivPT.setImageResource(R.drawable.cold);
                                        }
                                    }
                                });
                            }else {
                                readBuffer[readBufferPosition++]=b;
                            }
                        }
                    }
                }catch (IOException e) {
                    showToast("수신 중에 오류가 발생했습니다");
                }
            }
            }
        });
        mWorkerThread.start();
    }
    //아두이노로 데이터 보내기
    void sendData(String data){
        data+=mStrDelimiter;
        try {
            outputStream.write(data.getBytes());
        }catch (Exception e){
            showToast("데이터 전송중 오류가 발생했습니다.");
        }
    }

    @Override
    protected void onDestroy() {
        try {
            mWorkerThread.interrupt();
            inputStream.close();
            outputStream.close();
            mSocket.close();
        }catch (Exception e) {
            showToast("앱 종료하는 중에 오류발생!!");
        }
        super.onDestroy();
    }

    //페이링된 블루투스 장치를 이름으로 찾기
    BluetoothDevice getDeviceFromBoundedList(String name) {
        BluetoothDevice selectedDevice=null;
        for(BluetoothDevice device:mDevices){
            if(name.equals(device.getName())){
                selectedDevice=device;
                break;
            }
        }
        return selectedDevice;
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==REQUEST_ENABLE_BT){
            if(resultCode==RESULT_OK){
                selectDevice();
            }else {
                showToast("블루투스 연결을 취소했습니다.");
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    void showToast(String msg) {
        Toast.makeText(MainActivity.this,msg, Toast.LENGTH_SHORT).show();
    }
}

