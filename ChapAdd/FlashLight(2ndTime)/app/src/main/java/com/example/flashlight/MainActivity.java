package com.example.flashlight;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.hardware.camera2.CameraAccessException;
import android.os.Build;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class MainActivity extends AppCompatActivity {
    Switch swFlash;
    //Torch torch;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        swFlash = (Switch)findViewById(R.id.swFlash);
        /*try {
            torch = new Torch(this);

        } catch(CameraAccessException e) {
            e.printStackTrace();
        }*/
        swFlash.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Intent intent = new Intent(MainActivity.this,TorchService.class);

                //기계적이니까 클래스 만든다.
                if(isChecked) {
                    intent.setAction("on");
                    //Flash On
                    /*try {
                        torch.flashOn();

                    } catch(CameraAccessException e) {
                        e.printStackTrace();
                    }*/
                } else {
                    intent.setAction("off");
                    //Flash Off
                   /* try {
                        torch.flashOff();

                    } catch(CameraAccessException e) {
                        e.printStackTrace();
                    }*/
                }
                startService(intent);
            }
        });

    }
}
