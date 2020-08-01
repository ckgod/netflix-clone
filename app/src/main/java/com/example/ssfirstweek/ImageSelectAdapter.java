package com.example.ssfirstweek;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.ArrayList;

public class ImageSelectAdapter extends BaseAdapter {
    Context context;
    int layout;
    LayoutInflater inf;
    ArrayList<SelectImg> imgList = new ArrayList<>();

    public ImageSelectAdapter(Context context, int layout, ArrayList<SelectImg> imgList) {
        this.context = context;
        this.layout = layout;
        this.imgList = imgList;
        inf = (LayoutInflater) context.getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return imgList.size();
    }

    @Override
    public SelectImg getItem(int position) {
        return imgList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null)
            convertView = inf.inflate(layout, null);

        ImageView ib = convertView.findViewById(R.id.btn_imgSelect);

        SelectImg stImg = imgList.get(position);

        ib.setImageResource(stImg.getImgsrc());

        return convertView;
    }


    public void clear(){
        imgList.clear();
    }


    public void setArrayList(ArrayList<SelectImg> imgList){
        this.imgList = imgList;
    }


}
