package com.example.jetpackdemo.base;

import android.app.Application;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.SavedStateHandle;

public class BaseViewModel<T > extends AndroidViewModel {

    private SavedStateHandle handle;

    public BaseViewModel(@NonNull Application application, SavedStateHandle handle) {
        super(application);
        this.handle = handle;
    }



}
