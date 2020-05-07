package com.example.listview2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //동적 ListView를 만드는 것
    EditText edtItem;
    Button btnAdd;
    ListView list1;
    ArrayList<String> foodList ;        //data
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtItem = (EditText)findViewById(R.id.edtItem);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        list1 = (ListView)findViewById(R.id.list1);
        foodList = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,foodList);
        list1.setAdapter(adapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                foodList.add(edtItem.getText().toString());
                adapter.notifyDataSetChanged();
            }
        });

        list1.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("MyApp","position"+position);
                Log.i("MyApp","id"+id);
                AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                dlg.setTitle("정말로 지우시겠습니까?");
                dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                dlg.show();

                foodList.remove(position);
                adapter.notifyDataSetChanged();


                return false;
            }
        });

    }
}
