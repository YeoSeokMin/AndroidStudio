package com.example.bluetoothtest;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {
    ImageView ivConnect;
    BluetoothAdapter bluetoothAdapter;
    static final int REQUEST_ENABLE_BT=10;
    int mPairedDeviceCount=0;
    Set<BluetoothDevice> mDevices;
    BluetoothDevice mRemoteDevice;
    BluetoothSocket mSocket=null;
    OutputStream outputStream=null;
    InputStream inputStream=null;
    Thread mWorkerThread=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ivConnect=(ImageView)findViewById(R.id.ivConnect);
        ivConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkBluetooth();
            }
        });
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
        mDevices = bluetoothAdapter.getBondedDevices();
        mPairedDeviceCount = mDevices.size();
        if(mPairedDeviceCount == 0) {
            //연결할장치가 0개일 경우
            showToast("연결할 수 있는 장치가 없습니다.");
        } else {
            Context context;
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setTitle("블루투스 장치 선택");
            List<String> listitems = new ArrayList<String>();
            for(BluetoothDevice device:mDevices) {
                listitems.add(device.getName());
            }
            listitems.add("취소");
            final CharSequence items[] = listitems.toArray(new CharSequence[listitems.size()]);
            dlg.setItems(items, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if(which == mPairedDeviceCount) {
                        showToast("장치 연결을 취소했습니다.");
                    } else {
                        connectToSelectedDevice(items[which].toString());
                    }
                }
            });
            dlg.setCancelable(false);
            dlg.show();
        }
    }

    //선택한 장치와 연결 작업
    void connectToSelectedDevice(String selectedDeviceName) {
        mRemoteDevice = getDeviceFromBoundedList(selectedDeviceName);
        UUID uuid = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb"); //소켓식별자
        try {
            //소켓생성
            mSocket = mRemoteDevice.createRfcommSocketToServiceRecord(uuid);
            mSocket.connect();
            ivConnect.setImageResource(R.drawable.bluetooth_icon);
            outputStream = mSocket.getOutputStream();
            inputStream = mSocket.getInputStream();
            beginListenForData();
        } catch (Exception e) {
            showToast("장치 연결에 실패했습니다.");
        }
    }
    //아두이노에서 데이터 받기
    void beginListenForData() {

    }
    //아두이노로 데이터 보내기
    void sendData(String data) {

    }

    @Override
    protected void onDestroy() {
        try {
            mWorkerThread.interrupt();
            inputStream.close();
            outputStream.close();
            mSocket.close();
        } catch (Exception e) {
            showToast("앱 종료 도중 에러 발생!!");
        }
        super.onDestroy();
    }

    //페어링된 블루투스 장치를 이름으로 찾기
    BluetoothDevice getDeviceFromBoundedList(String name) {
        BluetoothDevice selectedDevice=null;
        for(BluetoothDevice device : mDevices) {
            selectedDevice = device;
            break;
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
