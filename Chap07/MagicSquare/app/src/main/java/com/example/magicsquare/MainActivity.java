package com.example.magicsquare;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    /*처리조건(algorithm)
    1.1행의 중간에 1을 넣어라.
    2.값을 입력한 후 대각선 위로 이동            ==>(row:-1, column:+1)
    3.행이 존재하지 않을 경우 마지막행에 다음 숫자를 넣는다.
    4.열이 존재하지 않을 경우 첫열에 다음 숫자를 넣는다.
    5.이미 값이 존재하면 행+2, 열-1 위치에 다음 숫자를 넣는다.
    6.행과 행이 존재하지 않으면 행+2, 열-1 위치에 다음 숫자를 넣는다
    */
    int[][] ma = new int[3][3];
    int row =0;
    int column = 0;

    int num=1;  //배열의 숫자값

    String result = "";

    TextView tvResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvResult = (TextView)findViewById(R.id.tvResult);

        column = (int)ma[0].length/2;
        ma[row][column] = num;

        for(num=2; num<ma.length*ma.length; num++) {
            //대각선으로 이동
            row--;
            column++;

            //row가 존재하지 않으면
            if(row < 0 && column >= ma.length) {
                row+=2;
                column--;
            } else if(row<0) {
                row = ma.length-1;
            } else if(column >= ma.length) {
                column = 0;
            } else if(ma[row][column] !=0) {
                row += 2;
                column--;
            }
            ma[row][column] = num;
        }

        for(int j=0; j<ma.length; j++) {
            for(int k = 0; k<ma.length; k++) {
                result += ma[j][k] + " ";
            }
            result += "\n";
        }
        tvResult.setText(result);
    }
}
