package com.example.jetpackdemo.day04_android_view_model;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.SavedStateHandle;

import com.example.jetpackdemo.R;

public class MyViewModel extends AndroidViewModel {

    String shpName = getApplication().getResources().getString(R.string.shp_name);
    String key =getApplication().getResources().getString(R.string.data_key);
    SavedStateHandle handle;

    public MyViewModel(@NonNull Application application,SavedStateHandle handle) {
        super(application);
        this.handle =  handle;

        if (!handle.contains(key)){
            load();
        }
    }

    public LiveData<Integer> getNumber(){
        return handle.getLiveData(key);
    }


    private void load() {
        SharedPreferences shp = getApplication().getSharedPreferences(shpName,Context.MODE_PRIVATE);
        int x = shp.getInt(key,0);
        handle.set(key,x);
    }


    void save(){
        SharedPreferences shp = getApplication().getSharedPreferences(shpName,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = shp.edit() ;
        editor.putInt(key,getNumber().getValue());
        editor.apply();
    }

    void add(int x ){
        handle.set(key,getNumber().getValue() + x);
        save();
    }

}
