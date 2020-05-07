package com.example.singerdbex;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btnInit,btnInsert,btnSelect,btnUpdate,btnDelete;
    EditText edtName,edtNumber;
    TextView tvName,tvNumber;

    SQLiteOpenHelper myDBHelper;
    //public class MyDB extends SQLiteOpenHelper {
    MyDB myDB;        //DB, Table, 담당
    SQLiteDatabase sqlDB;       //4대 쿼리 담당
    String sql;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInit = (Button)findViewById(R.id.btnInit);
        btnInsert = (Button)findViewById(R.id.btnInsert);
        btnSelect = (Button)findViewById(R.id.btnSelect);
        btnUpdate = (Button)findViewById(R.id.btnUpdate);
        btnDelete = (Button)findViewById(R.id.btnDelete);
        edtName = (EditText) findViewById(R.id.edtName);
        edtNumber = (EditText) findViewById(R.id.edtNumber);
        tvName = (TextView)findViewById(R.id.tvName);
        tvNumber = (TextView)findViewById(R.id.tvNumber);

        //DBConn
        //TableConn
        //Query
        myDB = new MyDB(this);


        btnInit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqlDB = myDB.getWritableDatabase();
                myDB.onUpgrade(sqlDB,1,2);
                sqlDB.close();
                showToast("테이블을 삭제하고 재생성하였습니다.");
            }
        });
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqlDB = myDB.getWritableDatabase();
                Log.i("MyApp","INSERT INTO groupTBL VALUES('"+edtName.getText().toString()+"',"+edtNumber.getText().toString()+");");
                sqlDB.execSQL("INSERT INTO groupTBL VALUES('"+edtName.getText().toString()+"',"+edtNumber.getText().toString()+");");

                sqlDB.close();
                edtName.setText("");
                edtNumber.setText("");
                //edtName.setFocusable(true);
                showToast("자료가 저장되었습니다.");

                btnSelect.callOnClick();
            }
        });
        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqlDB = myDB.getReadableDatabase();
                sql ="SELECT * FROM groupTBL WHERE gName LIKE '"+edtName.getText().toString()+"%"+"';";
                Log.i("MyApp",sql);
                //cursor = sqlDB.rawQuery("SELECT * FROM groupTBL WHERE gName LIKE '"+edtName.getText().toString()+"%"+"';",null);    //where 조건문이 여기에는 없다.
                cursor = sqlDB.rawQuery(sql,null);
                String strName = "그룹이름\n=================\n";
                String strNumber = "인원\n================\n";

                while(cursor.moveToNext()) {
                    strName += cursor.getString(0)+"\r\n";
                    strNumber += cursor.getInt(1)+"\r\n";
                }
                tvName.setText(strName);
                tvNumber.setText(strNumber);
                cursor.close();
                sqlDB.close();
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqlDB = myDB.getWritableDatabase();
                sql = "UPDATE groupTBL set gName ='"+edtName.getText().toString()+"',gNumber="+edtNumber.getText().toString()+" WHERE gName='"+edtName.getText().toString() +"'     ;";
                Log.i("MyApp",sql);
                sqlDB.execSQL("UPDATE groupTBL set gName='"+edtName.getText().toString()+"',gNumber="+edtNumber.getText().toString()+" WHERE gName='"+edtName.getText().toString()+"'   ;");

                tvName.setText("");
                tvNumber.setText("");
                sqlDB.close();
                showToast("자료가 수정되었습니다.");
                btnSelect.callOnClick();
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqlDB = myDB.getWritableDatabase();
                sql = "DELETE FROM groupTBL WHERE gName='"+edtName.getText().toString()+"' ;";
                Log.i("MyApp",sql);
                sqlDB.execSQL(sql);

                tvName.setText("");
                tvNumber.setText("");
                sqlDB.close();
                showToast("자료가 삭제되었습니다.");
                btnSelect.callOnClick();
            }
        });

    }
    void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    //DB생성, Table 생성, Table 삭제
    public class MyDB extends SQLiteOpenHelper {


        public MyDB(Context context) {      //DB생성담당
            //super(context, "groupDB", null, 1);
            //장소, Db이름,db에서 외부에 연결할 때 사용(factory), 처음만들때 버전을 만들어야 한다.
            super(context,"groupDB.db",null,1);
        }

        //테이블 생성담당
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE groupTBL(gName TEXT(20) PRIMARY KEY, gNumber INTEGER);");
        }

        //테이블 삭제하고 다시 생성하고 싶을때
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS groupTBL");
            onCreate(db);       //onCreate를 다시 호출한다.
        }
    }

}
