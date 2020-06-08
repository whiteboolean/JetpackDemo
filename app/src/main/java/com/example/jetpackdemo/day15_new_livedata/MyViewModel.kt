package com.example.jetpackdemo.day15_new_livedata

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle

class MyViewModel(private var savedStateHandle: SavedStateHandle,application: Application) : AndroidViewModel(application) {

    private val _text  = MutableLiveData<Int>().also {
        if (!savedStateHandle.contains("number")){
            savedStateHandle.set("number",0)
        }
        it.value = savedStateHandle.get("number")
    }
     val text1 :LiveData<Int> get() = _text

    public fun addOne(){
        _text.value = text1.value?.plus(1)
        savedStateHandle.set("number",_text.value)
    }
}