package com.example.customlistview;

import android.widget.ImageView;
import android.widget.TextView;

public class ItemData {
    private int imgID;
    private String title;
    private String subTitle;

    public ItemData(int imgID, String title, String subTitle) {
        this.imgID = imgID;
        this.title = title;
        this.subTitle = subTitle;
    }

    public int getImgID() {
        return imgID;
    }

    public String getTitle() {
        return title;
    }

    public String getSubTitle() {
        return subTitle;
    }
}
