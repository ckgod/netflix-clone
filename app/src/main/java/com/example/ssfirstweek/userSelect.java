package com.example.ssfirstweek;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.renderscript.ScriptGroup;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Set;
import java.util.prefs.PreferenceChangeEvent;

public class userSelect extends Activity {
    private RelativeLayout ll;
    private GridAdapter adapter;
    private Gson gson;
    int request = 0;
    boolean deleteFlag = false;
    private static final String PROFIILS = "profills";
    private int thumbnailID;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        Intent intent = new Intent(this, SplashActivity.class);
        startActivity(intent);

        final ImageButton plusbutton = findViewById(R.id.plususerbutton);
        final Button deleteButton = findViewById(R.id.btn_user_delete);
        final TextView tv_top = findViewById(R.id.upper);
        final TextView tv_usermanage = findViewById(R.id.logo_usermanage);
        final ImageView logo = findViewById(R.id.logo);
        final Button deleteComplete = findViewById(R.id.btn_deleteComplete);
        thumbnailID = R.drawable.proimg1;

        GridView grid_user = findViewById(R.id.grid_user);

        ArrayList<Profill> pfs = new ArrayList<>();

        adapter = new GridAdapter(
                getApplicationContext(),
                R.layout.griditem,       // GridView 항목의 레이아웃 row.xml
                pfs);    // 데이터

        grid_user.setAdapter(adapter);  // 커스텀 아답타를 GridView 에 적용
        grid_user.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(deleteFlag == false) {
                    redirectMainActivity();
                }
                else
                {
                    openDialog(position);
                }
            }
        });
        grid_user.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                redirectmodifyImg(position);
                return true;
            }
        });

        plusbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectAddProfillActivity();
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(deleteFlag == false) {
                    deleteComplete.setVisibility(View.VISIBLE);
                    logo.setVisibility(View.INVISIBLE);
                    tv_usermanage.setVisibility(View.VISIBLE);
                    tv_top.setVisibility(View.INVISIBLE);
                    plusbutton.setVisibility(View.INVISIBLE);
                    adapter.showGarbage(true);
                    deleteFlag = true;
                    setGridView();
                    deleteButton.setVisibility(View.INVISIBLE);
                }
            }
        });

        deleteComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(deleteFlag == true) {
                    deleteComplete.setVisibility(View.INVISIBLE);
                    logo.setVisibility(View.VISIBLE);
                    tv_usermanage.setVisibility(View.INVISIBLE);
                    tv_top.setVisibility(View.VISIBLE);
                    plusbutton.setVisibility(View.VISIBLE);
                    adapter.showGarbage(false);
                    deleteFlag = false;
                    setGridView();
                    deleteButton.setVisibility(View.VISIBLE);
                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case 1:
                Toast.makeText(this, "image selected", Toast.LENGTH_SHORT).show();
                thumbnailID = data.getIntExtra("thumbnail", 0);
        }
    }

    public void redirectmodifyImg(int pos) {
        Intent intent = new Intent(this, modifyImg.class);
        startActivityForResult(intent, pos);
    }

    public void openDialog(final int pos){
        new AlertDialog.Builder(this)
                .setTitle("프로필 삭제")
                .setMessage("삭제 하시겠습니까?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        adapter.deleteItem(adapter.getItem(pos));
                        setProfillArrayPref(PROFIILS,adapter.getArrayList());
                        setGridView();
                        Toast.makeText(getApplicationContext(),"delete complete!",Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .show();
    }


    public void redirectAddProfillActivity() {
        Intent intent = new Intent(this, AddProfillActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        setGridView();
        super.onResume();
    }

    public void redirectMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    void setGridView() {
        adapter.setArrayList(getStringArrayPref());
        adapter.notifyDataSetChanged();
        // 불러온 객체를 그리으뷰에 뿌려줌
    }

    private ArrayList<Profill> getStringArrayPref() {
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

}

