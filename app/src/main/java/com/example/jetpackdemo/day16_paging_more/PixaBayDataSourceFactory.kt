package com.example.jetpackdemo.day16_paging_more

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.jetpackdemo.day11_image_lang.Hit

class PixaBayDataSourceFactory(private var context: Context) : DataSource.Factory<Int, Hit>() {

    val pixaBayDataSource  = MutableLiveData<PixaBayDataSource>()

    override fun create(): DataSource<Int, Hit> {
        return PixaBayDataSource(context =context ).also {
            pixaBayDataSource.postValue(it)
        }
    }

}