package com.example.jetpackdemo.calculation_demo06;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;

import java.util.Random;
import java.util.logging.Handler;

public class MyViewModel extends AndroidViewModel {
    private SavedStateHandle handle;
    public static final String KEY_HIGH_SCORE = "key_high_score";
    public static final String KEY_LEFT_NUMBER = "key_left_number";
    private static final String KEY_RIGHT_NUMBER = "key_right_number";
    private static final String KEY_OPERATOR = "key_operator";
    private static final String KEY_ANSWER = "key_answer";
    public static final String SAVE_SHP_DATA_NAME = "save_shp_data_name";
    public static final String KEY_CURRENT_NAME = "key_current_name";

    public MyViewModel(@NonNull Application application, SavedStateHandle handle) {
        super(application);
        if (!handle.contains(KEY_HIGH_SCORE)) {
            SharedPreferences shp = getApplication().getSharedPreferences(SAVE_SHP_DATA_NAME, Context.MODE_PRIVATE);
            handle.set(KEY_HIGH_SCORE, shp.getInt(KEY_HIGH_SCORE, 0));
            handle.set(KEY_LEFT_NUMBER, 0);
            handle.set(KEY_RIGHT_NUMBER, 0);
            handle.set(KEY_ANSWER, 0);
            handle.set(KEY_CURRENT_NAME, 0);
        }
        this.handle = handle;
    }


    public MutableLiveData<Integer> getLeftNumber() {
        return handle.getLiveData(KEY_LEFT_NUMBER);
    }

    public MutableLiveData<Integer> getRightNumber() {
        return handle.getLiveData(KEY_RIGHT_NUMBER);
    }

    public MutableLiveData<String> getOperator() {
        return handle.getLiveData(KEY_OPERATOR);
    }

    public MutableLiveData<Integer> getHighScore() {
        return handle.getLiveData(KEY_HIGH_SCORE);
    }

    public MutableLiveData<Integer> getCurrentScore() {
        return handle.getLiveData(KEY_CURRENT_NAME);
    }


    void generator() {
        int LEVEL = 20;

        Random random = new Random();
        int x, y;
        x = random.nextInt(LEVEL) + 1; //0 - 19 --->  +1  --->  1 -- 20
        y = random.nextInt(LEVEL) + 1;
        if (x % 2 == 0) {
            getOperator().setValue("+");


        } else {
            getOperator().setValue("-");


        }
    }


}
