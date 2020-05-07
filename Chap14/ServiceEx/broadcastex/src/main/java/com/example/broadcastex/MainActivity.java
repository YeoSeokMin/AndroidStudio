package com.example.broadcastex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    ImageView ivBattery;
    TextView tvBattery;
    //문자받을 변수
    String str="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ivBattery = (ImageView)findViewById(R.id.ivBattery);
        tvBattery = (TextView)findViewById(R.id.tvBattery);


    }
    BroadcastReceiver br = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if(action.equals(intent.ACTION_BATTERY_CHANGED)) {
                int remain = intent.getIntExtra(BatteryManager.EXTRA_LEVEL,0);
                str =  "현재충전량"+remain+"%\n";
                if(remain >= 95) {
                    ivBattery.setImageResource(R.drawable.betteryfull);
                } else if(remain >= 85) {
                    ivBattery.setImageResource(R.drawable.bettery90);
                } else if(remain >= 70) {
                    ivBattery.setImageResource(R.drawable.bettery70);
                } else if(remain >=40) {
                    ivBattery.setImageResource(R.drawable.bettery40);
                } else if(remain >=10) {
                    ivBattery.setImageResource(R.drawable.bettery10);
                } else {
                    ivBattery.setImageResource(R.drawable.bettery0);
                    str += "곧 전원이 꺼집니다. 충전하세요\n";
                }
                int plug = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED,0);
                switch (plug) {
                    case 0:
                        str+="전원연결 : 안됨";
                        break;
                    //case 1:
                    case BatteryManager.BATTERY_PLUGGED_AC:
                        str+="전원연결 : 충전기(어댑터) 연결됨";
                        break;
                    case BatteryManager.BATTERY_PLUGGED_USB:
                        str+="전원연결 : 충전기(USB) 연결됨";
                        break;
                }
                tvBattery.setText(str);
            }
        }
    };

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(br);

    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(br,filter);
    }
}
