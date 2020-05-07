package com.example.publicdb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Spinner spCity,spName;
    TextView tvResult;
    String result;      //여기에 다 붙일것이다.(tvResult);
    boolean check;
    SQLiteDatabase sqlDB;
    ArrayList<String> citydata;
    ArrayList<String> namedata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spCity = (Spinner)findViewById(R.id.spCity);
        spName = (Spinner)findViewById(R.id.spName);
        tvResult = (TextView)findViewById(R.id.tvResult);
        citydata = new ArrayList<String>();
        namedata = new ArrayList<String>();

        check = isCheckDB(this);
        try {

            if (!check) {
                copyDB(this);
            }
        } catch (Exception e){
            //DB를 저장할 수 없습니다.
        }
        sqlDB = SQLiteDatabase.openDatabase("/data/data/com.example.publicdb/databases/PetHospDB.db",null,SQLiteDatabase.OPEN_READONLY);
        final Cursor cursor;
        String sql;
        sql = "SELECT distinct(city) FROM PetHospTBL";
        cursor = sqlDB.rawQuery(sql,null);
        while(cursor.moveToNext()) {
            citydata.add(cursor.getString(0));
        }
        cursor.close();
        //sqlDB.close()는 안한다. 왜냐하면 아래에서 또 사용하니까..^^

        //spinner는 adapter가 필요.(spinner는 넓은거 좁은거 2개중 하나 선택)
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_spinner_dropdown_item,citydata);
        spCity.setAdapter(adapter);

        //SubSpinner
        spCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //동적배열을 만든거 clear한다.
                //왜나하면 나중에 계속 추가되니까...두번선택하면 첫번째 + 두번째니까 선택할때 clear();
                namedata.clear();

                Cursor cursor1;
                String sql1;
                sql1 = "SELECT Name FROM PetHospTBL WHERE City = '"+spCity.getSelectedItem().toString()+"'   ;";
                cursor1 = sqlDB.rawQuery(sql1,null);
                while(cursor1.moveToNext()) {
                    namedata.add(cursor1.getString(0));
                }
                cursor1.close();

                ArrayAdapter<String> adapter1 = new ArrayAdapter<String >(MainActivity.this,android.R.layout.simple_spinner_dropdown_item,namedata);
                spName.setAdapter(adapter1);


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //지도를 보여줘라.
        spName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //병원이름: ~~~~
                //개원일: ~~~~
                //상태: ~~~~if(정상: 영업중만 list      /if(상태) 폐업 or말소: 폐업,말소를 찍어주고 날짜(endData)를 찍어줘라.
                Cursor cursor2;
                String sql2;
                sql2 = "SELECT Name,OpenDate,Status,EndDate,Tel,Post,Address FROM PetHospTBL WHERE City = '"+spCity.getSelectedItem().toString()+"' AND Name='"+spName.getSelectedItem().toString()+"'  ;";

                cursor2 = sqlDB.rawQuery(sql2,null);
                cursor2.moveToFirst();
                String name,opendate,status,enddate,tel,post,address;
                name = "동물병원이름: "+cursor2.getString(0);
                opendate = "개업일: "+cursor2.getString(1);
                status = cursor2.getString(2);
                enddate = cursor2.getString(3);
                if(status.equals("정상")) {
                    status = "상태 : 영업중";
                } else {
                    status += "("+enddate+")";
                }

                tel = "전화번호 : "+cursor2.getString(4);
                post = "우편번호 : "+cursor2.getString(5);
                address = "주소 : "+cursor2.getString(6);
                cursor2.close();

                result = name+"\n"+opendate+"\n"+status+"\n"+post+"\n"+tel+"\n"+address;

                tvResult.setText(result);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



    }
    public boolean isCheckDB(Context context){//DB가 databases폴더에 없거나 크기가 다르면 false
        String filepath="/data/data/com.example.publicdb/databases/PetHospDB.db";//db가 설치되어 있는 경로
        File file = new File(filepath);
        long newdb_size=0;
        long olddb_size=file.length();
        AssetManager manager=context.getAssets(); // asset폴더에 접근합니다.
        try {
            InputStream inputS=manager.open("PetHospDB.db");
            newdb_size=inputS.available();
        }catch (IOException e){
            //파일이 존재하지 않음.
        }
        if(file.exists()){
            if(newdb_size!=olddb_size){
                return false;
            } else {
                return true;
            }
        }
        return false;
    }
    public void copyDB(Context context){//assets폴더에 있는 DB를 패키지안에 databases폴더로 옮기는 작업
        AssetManager manager=context.getAssets();
        String folderPath = "/data/data/com.example.publicdb/databases";
        String filePath = "/data/data/com.example.publicdb/databases/PetHospDB.db";
        File folder = new File(folderPath);
        File file = new File(filePath);
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        try {
            InputStream inputS=manager.open("PetHospDB.db");
            BufferedInputStream bis=new BufferedInputStream(inputS);//바이트로 바꿔서 담는 과정
            if (!folder.exists()) { //databases폴더가 존재하지 않느냐? 라고 물어봄
                folder.mkdir(); //없다면 폴더생성 .mkdir();
            }
            if (file.exists()){ //petHDB가 존재하냐? 물어봄
                file.delete();
                file.createNewFile();
            }
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            int read = -1;
            byte buffer[]=new byte[1024]; // 1메가(1024byte) 제한
            while ((read=bis.read(buffer,0,1024)) != -1){
                bos.write(buffer,0,read);
            }
            bos.flush();
            bos.close();
            fos.close();
            bis.close();
            inputS.close();
        } catch (Exception e) {
            //파일을 저장할 수 없습니다.
        }
    }
}
