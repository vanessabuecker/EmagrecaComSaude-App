package com.vbuecker.myapplication12;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
public class MainItem {
    private int id;
    private int drawableId;
    private int color;
    private int textStringId;

    public MainItem(int id, int drawableId, int textStringId, int color) {
        this.id = id;
        this.drawableId = drawableId;
        this.textStringId = textStringId;
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTextStringId() {
        return textStringId;
    }

    public void setTextStringId(int textStringId){
        this.textStringId = textStringId;
    }
    public int getDrawableId() {
        return drawableId;
    }

    public void setDrawableId(int drawableId) {
        this.drawableId = drawableId;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}