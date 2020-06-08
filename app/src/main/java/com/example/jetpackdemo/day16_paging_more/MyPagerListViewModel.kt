package com.example.jetpackdemo.day16_paging_more

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.Transformations
import androidx.paging.toLiveData

const val DATA_STATUS_CAN_LOAD_MORE = 0
const val DATA_STATUS_NO_MORE = 1
const val DATA_STATUS_NETWORK_ERROR = 2

class MyPagerListViewModel(application: Application) : AndroidViewModel(application) {

    val factory =PixaBayDataSourceFactory(application)
    val netWorkStatus = Transformations.switchMap(factory.pixaBayDataSource){  it.netWorkStatus }

    val pageListLiveData= factory.toLiveData(1)
    fun requestQuery(){
        pageListLiveData.value?.dataSource?.invalidate()
    }

    fun retry() {
        factory.pixaBayDataSource.value?.retry?.invoke()
    }




}