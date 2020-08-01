package com.example.ssfirstweek;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class GridAdapter extends BaseAdapter {
    Context context;
    int layout;
    LayoutInflater inf;
    ArrayList<Profill> profills = new ArrayList<>();
    boolean visible = false;

    public GridAdapter(Context context, int layout, ArrayList<Profill> profills) {
        this.context = context;
        this.layout = layout;
        this.profills = profills;
        inf = (LayoutInflater) context.getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return profills.size();
    }

    @Override
    public Object getItem(int position) {
        return profills.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null)
            convertView = inf.inflate(layout, null);

        ImageView iv = (ImageView)convertView.findViewById(R.id.img1);
        TextView tv = convertView.findViewById(R.id.tv1);
        ImageView garbage = convertView.findViewById(R.id.iv_garbage);

        if(visible){
            Animation animation = new AlphaAnimation(0,1);
            animation.setDuration(500);
            garbage.setAnimation(animation);
            garbage.setVisibility(View.VISIBLE);
            iv.setColorFilter(Color.parseColor("#BDBDBD"), PorterDuff.Mode.MULTIPLY);
        }
        else{
            garbage.setVisibility(View.INVISIBLE);
            iv.setColorFilter(null);
        }

        Profill pf = profills.get(position);
        iv.setImageResource(pf.getBm_id());
        tv.setText(pf.getName());

        return convertView;
    }

    public void showGarbage(boolean visible){
        this.visible = visible;
        this.notifyDataSetChanged();
    }

    public void deleteItem(Object object)
    {
        profills.remove(object);
        setArrayList(profills);
        this.notifyDataSetChanged();
    }

    public void clear(){
        profills.clear();
    }
    public void setArrayList(ArrayList<Profill> pfs){
        this.profills = pfs;
    }
    public ArrayList<Profill> getArrayList(){
        return this.profills;
    }
}
