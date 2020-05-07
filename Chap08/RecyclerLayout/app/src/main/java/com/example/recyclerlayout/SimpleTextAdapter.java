package com.example.recyclerlayout;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SimpleTextAdapter extends RecyclerView.Adapter<SimpleTextAdapter.ViewHolder> {
    private ArrayList<String> mData = null;

    //Item View를 저장하는 뷰홀더 클래스.
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView1;
        Button btnRe;


        ViewHolder(View itemView) {
            super(itemView);

            textView1 = itemView.findViewById(R.id.text1);
            btnRe = itemView.findViewById(R.id.btnRe);
        }
    }

    public SimpleTextAdapter(ArrayList<String> list) {
        mData = list;
    }

    @NonNull
    @Override
    public SimpleTextAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.recycleview_item,parent,false);
        SimpleTextAdapter.ViewHolder vh = new SimpleTextAdapter.ViewHolder(view);


        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull SimpleTextAdapter.ViewHolder holder, int position) {
        String text = mData.get(position) ;
        holder.textView1.setText(text) ;
        //holder.btnRe.setText(text);
    }

    @Override
    public int getItemCount() {

        return mData.size();

        //Log.d("RecyclerView", "");
        //https://recipes4dev.tistory.com/154
    }
}

