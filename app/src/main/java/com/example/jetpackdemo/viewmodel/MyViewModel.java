package com.example.jetpackdemo.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyViewModel extends ViewModel {

    private MutableLiveData<Integer> likedNumber;
    public MutableLiveData<Integer> getLikedNumber(){
        if (likedNumber==null){
            likedNumber = new MutableLiveData<>();
            likedNumber.setValue(0);
        }
        return likedNumber;
    }

    public void setLikedNumber(int number){
        likedNumber.setValue(likedNumber.getValue()-number);
    }
}
