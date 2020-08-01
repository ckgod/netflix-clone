package com.example.ssfirstweek;

import android.graphics.Bitmap;

public class Profill {
    String name;
    int bm_id;

    public Profill(String name, int bm_id) {
        this.name = name;
        this.bm_id = bm_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBm_id() {
        return bm_id;
    }

    public void setBm_id(int bm_id) {
        this.bm_id = bm_id;
    }
}
