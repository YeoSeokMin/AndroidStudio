package com.example.flashlight;

import android.app.Service;
import android.content.Intent;
import android.hardware.camera2.CameraAccessException;
import android.os.Build;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class TorchService extends Service {

    Torch torch;
    Boolean isRunning = false;  //


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onCreate() {
        super.onCreate();
        try{
            torch = new Torch(this);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if(intent.getAction() == "on") {
            try {
                torch.flashOn();
            } catch (CameraAccessException e) {
                e.printStackTrace();
            }

        } else if (intent.getAction()=="off") {
            try {
                torch.flashOff();
            } catch (CameraAccessException e) {
                e.printStackTrace();
            }
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
