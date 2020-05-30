package com.example.jetpackdemo.day12_kotlindemo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel: ViewModel() {

    val number:MutableLiveData<Int> by lazy { MutableLiveData<Int>().also { it.value = 0 } }

    fun modifyNumber(aNumber:Int){
        number.value = number.value?.plus(aNumber)
    }
}