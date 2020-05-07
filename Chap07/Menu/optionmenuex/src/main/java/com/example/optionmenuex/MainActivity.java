package com.example.optionmenuex;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView txtView;
    EditText edtRotate;
    ImageView ivPic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtView = this.findViewById(R.id.txtView);
        edtRotate = this.findViewById(R.id.edtRotate);
        ivPic = this.findViewById(R.id.ivPIc);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        MenuInflater mInflater = getMenuInflater();
        mInflater.inflate(R.menu.mainmenu,menu);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item0:
                //Integer.valueOf(edtRotate.getText().toString());
                ivPic.setRotation(Float.parseFloat(edtRotate.getText().toString()));

            case R.id.item1:
                ivPic.setImageResource(R.drawable.jeju);
                item.setChecked(true);

                break;
            case R.id.item2:
                ivPic.setImageResource(R.drawable.chuja);
                item.setChecked(true);
                break;
            case R.id.item3:
                ivPic.setImageResource(R.drawable.bum);
                item.setChecked(true);
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
