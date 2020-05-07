package com.example.flashlight;

import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.os.Build;

import androidx.annotation.RequiresApi;

public class Torch {
    //기계적인 값에서 Flash를 Torch하는거.
    Context context;
    String cammeraID;
    CameraManager cameraManager;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public Torch(Context context) throws CameraAccessException {
        this.context = context;
        cameraManager = (CameraManager)context.getSystemService(Context.CAMERA_SERVICE);
        cammeraID = getCammeraID();
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public String getCammeraID() throws CameraAccessException {
        //phone Info
        String[] cameraID = cameraManager.getCameraIdList();
        for(String id:cameraID) {
            //Camera 기기에 있는 카메라 flash 정보를 전부 가져온다.
            CameraCharacteristics info = cameraManager.getCameraCharacteristics(id);
            //Camera Flash 할 수 있는 경우(배터리 몇 이하면 안켜지게 하는 기능.)
            Boolean flashAailable = info.get(CameraCharacteristics.FLASH_INFO_AVAILABLE);
            //Lens 방향
            Integer lensFacing = info.get(CameraCharacteristics.LENS_FACING);
            //Camera Flash가 없다면 && flash 가능하다면 && lens방향이 맞다면 && lens가 뒤로되어 있으면(앞은 안돼)
            if(flashAailable != null && flashAailable && lensFacing != null && lensFacing==CameraCharacteristics.LENS_FACING_BACK) {
                return id;
            }
        }
        return null;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void flashOn() throws CameraAccessException{
        cameraManager.setTorchMode(cammeraID,true);
    }
    @RequiresApi(api = Build.VERSION_CODES.M)

    public void flashOff() throws CameraAccessException {
        cameraManager.setTorchMode(cammeraID,false);
    }
}
