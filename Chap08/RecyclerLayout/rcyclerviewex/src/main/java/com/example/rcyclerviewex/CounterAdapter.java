package com.example.rcyclerviewex;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CounterAdapter extends RecyclerView.Adapter<CounterAdapter.MyViewHolder> {
    final static String TAG = "MyRecyclerView";

    Context mContext;
    List<Integer> items;
    int i = 0;

    public CounterAdapter(Context mContext, List<Integer> items, int i) {

        this.mContext = mContext;
        Log.d(TAG,"==========CountAdapter============");
        Log.d(TAG,"this.items==>"+this.items);
        Log.d(TAG,"this.i==>"+this.i);

        this.items = items;
        this.i = i;
        Log.d(TAG,"건네받고 this.items==>"+this.items);
        Log.d(TAG,"건네받고 this.i==>"+this.i);

    }

    @NonNull
    @Override
    public CounterAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG,"onCreateViewHolder");
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_custom,parent,false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CounterAdapter.MyViewHolder holder, int position) {
        Log.d(TAG,"onBindViewHolder");
        final Integer item = items.get(position);
        holder.tv.setText(""+item);
    }

    @Override
    public int getItemCount() {
        Log.d(TAG,"getItemCount");
        return this.items.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv;

        public MyViewHolder(@NonNull View itemView) {

            super(itemView);
            Log.d(TAG,"MyViewHolder");
            tv = (TextView)itemView.findViewById(R.id.tv);
        }
    }


}
