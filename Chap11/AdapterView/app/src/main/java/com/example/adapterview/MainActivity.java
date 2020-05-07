package com.example.adapterview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ListView list1;     //List형식으로 View, ListView는 글자만 나열할 수 있음.
    ListView list2,list3,list4;

    final String[] data = {"Banana","Apple","PineApple","Tomato","Strawberry",
            "Graph","Fruits","Kiwi","Mango","Cookie",
            "Graph","Fruits","Kiwi","Mango","Cookie",
            "Graph","Fruits","Kiwi","Mango","Cookie"
    };
    final Integer[] price = {6000,5000,4000,5500,6500,
            6600,5600,4600,5600,6600,
            6700,5700,4700,5700,6700,
            6800,5800,4800,5800,6800
    };

    ArrayAdapter<String> adapterS;                //간편하게 자료만 나열하는 ArrayAdapter;
    ArrayAdapter<Integer> adapterI;
    //ArrayAdapter<Character> adapterC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list1 = (ListView)findViewById(R.id.list1);
        adapterS = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,data);
        list1.setAdapter(adapterS);
        list1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, data[position]+"의 가격은 "+price[position], Toast.LENGTH_SHORT).show();
                Log.i("MyApp","long id(배열인덱스):"+id);
            }
        });

        list2 = (ListView)findViewById(R.id.list2);
        list2.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        adapterS = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_multiple_choice,data);
        list2.setAdapter(adapterS);


        list3 = (ListView)findViewById(R.id.list3);
        list3.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        adapterS = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_single_choice,data);
        list3.setAdapter(adapterS);

        list4 = (ListView)findViewById(R.id.list4);
        list4.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        adapterI = new ArrayAdapter<Integer>(this,android.R.layout.simple_list_item_single_choice,price);
        list4.setAdapter(adapterI);


    }
}
