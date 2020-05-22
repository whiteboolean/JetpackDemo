package com.example.jetpackdemo.save_Instance_state;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

public class MySpViewModel extends ViewModel {
//    private MutableLiveData<Integer> number ;
    private SavedStateHandle savedStateHandle;


    public MySpViewModel(SavedStateHandle savedStateHandle){
        this.savedStateHandle = savedStateHandle;
    }

    public MutableLiveData<Integer> getNumber(){
        if (!savedStateHandle.contains(Main4Activity.KEY_NUMBER)){
            savedStateHandle.set(Main4Activity.KEY_NUMBER,0);
        }
        return savedStateHandle.getLiveData(Main4Activity.KEY_NUMBER);
//        if (number==null){
//            number = new MutableLiveData<>();
//            number.setValue(0);
//        }
//        return number;
    }

    public void setNumber(int n ){
        getNumber().setValue(getNumber().getValue()+n);
    }



}
