package com.example.ssfirstweek;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;

import java.util.ArrayList;

public class modifyImg extends AppCompatActivity {

    ArrayList<SelectImg> selectImgs = new ArrayList<>();
    private ImageSelectAdapter adapter;
    int RESULT_OK = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_img);

        GridView grid_img = findViewById(R.id.Gv_imageSelect);
        plusImage();

        Button back = findViewById(R.id.btn_modify_img_back);

        adapter = new ImageSelectAdapter(
                getApplicationContext(),
                R.layout.selectitem,
                selectImgs );

        grid_img.setAdapter(adapter);
        grid_img.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.putExtra("thumbnail",adapter.getItem(position).getImgsrc());
                setResult(RESULT_OK,intent);
                finish();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    public void plusImage(){
        selectImgs.add(new SelectImg(R.drawable.proimg1));
        selectImgs.add(new SelectImg(R.drawable.proimg2));
        selectImgs.add(new SelectImg(R.drawable.proimg3));
        selectImgs.add(new SelectImg(R.drawable.proimg4));
        selectImgs.add(new SelectImg(R.drawable.proimg5));
        selectImgs.add(new SelectImg(R.drawable.proimg6));
        selectImgs.add(new SelectImg(R.drawable.proimg7));
        selectImgs.add(new SelectImg(R.drawable.proimg8));
        selectImgs.add(new SelectImg(R.drawable.proimg9));
        selectImgs.add(new SelectImg(R.drawable.proimg10));
        selectImgs.add(new SelectImg(R.drawable.proimg11));
        selectImgs.add(new SelectImg(R.drawable.proimg12));
        selectImgs.add(new SelectImg(R.drawable.proimg13));
        selectImgs.add(new SelectImg(R.drawable.proimg14));
        selectImgs.add(new SelectImg(R.drawable.proimg15));
        selectImgs.add(new SelectImg(R.drawable.proimg16));
        selectImgs.add(new SelectImg(R.drawable.proimg17));
        selectImgs.add(new SelectImg(R.drawable.proimg18));
        selectImgs.add(new SelectImg(R.drawable.proimg19));
        selectImgs.add(new SelectImg(R.drawable.proimg20));
        selectImgs.add(new SelectImg(R.drawable.proimg21));
        selectImgs.add(new SelectImg(R.drawable.proimg22));
        selectImgs.add(new SelectImg(R.drawable.proimg23));
        selectImgs.add(new SelectImg(R.drawable.proimg24));
        selectImgs.add(new SelectImg(R.drawable.proimg25));
        selectImgs.add(new SelectImg(R.drawable.proimg26));
        selectImgs.add(new SelectImg(R.drawable.proimg27));
        selectImgs.add(new SelectImg(R.drawable.proimg28));
        selectImgs.add(new SelectImg(R.drawable.proimg29));
        selectImgs.add(new SelectImg(R.drawable.proimg30));
        selectImgs.add(new SelectImg(R.drawable.proimg31));
        selectImgs.add(new SelectImg(R.drawable.proimg32));
        selectImgs.add(new SelectImg(R.drawable.proimg33));
        selectImgs.add(new SelectImg(R.drawable.proimg34));
        selectImgs.add(new SelectImg(R.drawable.proimg35));
        selectImgs.add(new SelectImg(R.drawable.proimg36));
        selectImgs.add(new SelectImg(R.drawable.proimg37));
        selectImgs.add(new SelectImg(R.drawable.proimg38));
        selectImgs.add(new SelectImg(R.drawable.proimg39));
        selectImgs.add(new SelectImg(R.drawable.proimg40));
        selectImgs.add(new SelectImg(R.drawable.proimg41));
        selectImgs.add(new SelectImg(R.drawable.proimg42));
        selectImgs.add(new SelectImg(R.drawable.proimg43));
        selectImgs.add(new SelectImg(R.drawable.proimg44));
        selectImgs.add(new SelectImg(R.drawable.proimg45));
        selectImgs.add(new SelectImg(R.drawable.proimg46));
        selectImgs.add(new SelectImg(R.drawable.proimg47));
        selectImgs.add(new SelectImg(R.drawable.proimg48));
        selectImgs.add(new SelectImg(R.drawable.proimg49));
        selectImgs.add(new SelectImg(R.drawable.proimg50));
        selectImgs.add(new SelectImg(R.drawable.proimg51));
        selectImgs.add(new SelectImg(R.drawable.proimg52));
    }



}
