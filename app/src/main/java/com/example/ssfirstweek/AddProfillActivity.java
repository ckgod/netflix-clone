package com.example.ssfirstweek;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class AddProfillActivity extends AppCompatActivity {

    private static final String PROFIILS = "profills";

    private final int request = 0;
    private GridAdapter adapter;
    ArrayList<Profill> pfs = new ArrayList<>();
    private ImageView imageView_thumbnail;
    private EditText editText_name;
    private int thumbnailID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_profill);

        thumbnailID = R.drawable.proimg1;

        editText_name = findViewById(R.id.proname);
        imageView_thumbnail = findViewById(R.id.imageView_profile);


        Button back = findViewById(R.id.backtouser);
        Button complete = findViewById(R.id.complete);
        Button modify_proimg = findViewById(R.id.modify_proimg);


        // 이미지 변경 버튼
        modify_proimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectmodifyImg();
            }
        });

        // 저장버튼 -> shared pre펠어런스에 저장하고 fin이쉬
        complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText name = findViewById(R.id.proname);
                String nm = name.getText().toString();
                addnewUser(nm);
                finish();
            }
        });

        // 취소버튼
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void redirectmodifyImg() {
        Intent intent = new Intent(this, modifyImg.class);
        startActivityForResult(intent, request);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case 1:
                if(requestCode == request) {
                    Toast.makeText(this, "image selected", Toast.LENGTH_SHORT).show();
                    thumbnailID = data.getIntExtra("thumbnail", 0);
                    imageView_thumbnail.setImageResource(thumbnailID);
                }
                break;
            default:
                break;
        }
    }

    public void addnewUser(String name) {
        pfs = getProfillArrayPref();
        pfs.add(new Profill(name, thumbnailID));
        setProfillArrayPref(PROFIILS, pfs);
    }

    private void setProfillArrayPref(String key, ArrayList<Profill> values) {
        Gson gson = new GsonBuilder().create();
        Type listType = new TypeToken<ArrayList<Profill>>() {
        }.getType();
        String json = gson.toJson(values, listType);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(key, json);
        editor.apply();
    }

    private ArrayList<Profill> getProfillArrayPref() {
        ArrayList<Profill> arrayList = new ArrayList<>();
        SharedPreferences Prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        Gson gson = new Gson();
        String json = Prefs.getString(PROFIILS, "");
        if (!json.equals("")) {
            Type type = new TypeToken<ArrayList<Profill>>() {
            }.getType();
            arrayList = gson.fromJson(json, type);
        }
        return arrayList;
    }

}

