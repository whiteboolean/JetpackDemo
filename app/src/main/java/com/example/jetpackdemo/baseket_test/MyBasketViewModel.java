package com.example.jetpackdemo.baseket_test;

import android.text.TextUtils;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


public class MyBasketViewModel extends ViewModel {

    public MutableLiveData<Integer> numberA;
    public MutableLiveData<Integer> numberB;
    private int lastA ,lastB;

    public MutableLiveData<Integer> getNumberA() {
        if (numberA == null) {
            numberA = new MutableLiveData<>();
            numberA.setValue(0);
        }
        return numberA;
    }

    public MutableLiveData<Integer> getNumberB() {
        if (numberB == null) {
            numberB = new MutableLiveData<>();
            numberB.setValue(0);
        }
        return numberB;
    }

    public void setNumberA(int n) {
        lastA = getNumberA().getValue()==null?0:getNumberA().getValue();
        numberA.setValue(getNumberA().getValue() + n);
    }

    public void setNumberB(int n) {
        lastB = getNumberB().getValue()==null?0:getNumberB().getValue();
        numberB.setValue(getNumberB().getValue() + n);
    }

    public void reset(){
        getNumberA().setValue(0);
        getNumberB().setValue(0);
    }

    public void setLast(){
        getNumberA().setValue(lastA);
        getNumberB().setValue(lastB);
    }
}
