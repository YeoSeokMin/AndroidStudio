package com.example.customlistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends BaseAdapter {
    private Context mContext;
    List<ItemData> itemDataList;            //동적배열을 만든다.
    LayoutInflater inflater;

    public MyAdapter(Context mContext,List<ItemData> itemDataList) {
        this.mContext = mContext;
        this.itemDataList = itemDataList;
        this.inflater = LayoutInflater.from(mContext);      //장착해줘야 함.중요하다.
    }

    @Override
    public int getCount() {
        return itemDataList.size();
    }


    //item의 위치값을 돌려준다.
    @Override
    public Object getItem(int position) {
        return itemDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //XML을 담을 수 있는 View가 필요함.
        View itemView = convertView;
        if(itemView == null) {
            itemView = inflater.inflate(R.layout.item_listview,null);
        }
        ImageView ivPoster = (ImageView)itemView.findViewById(R.id.ivPoster);
        TextView tvTitle = (TextView)itemView.findViewById(R.id.tvTitle);
        TextView tvSubTitle = (TextView)itemView.findViewById(R.id.tvSubTitle);
        ItemData itemData = itemDataList.get(position);
        ivPoster.setImageResource(itemData.getImgID());
        tvTitle.setText(itemData.getTitle());
        tvSubTitle.setText(itemData.getSubTitle());
        return itemView;
    }
}
