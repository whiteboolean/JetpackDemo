package com.example.jetpackdemo.day16_paging_more

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.example.jetpackdemo.day11_image_lang.Hit
import com.example.jetpackdemo.day11_image_lang.PixaData
import com.example.jetpackdemo.day11_image_lang.VolleySingleton
import com.google.gson.Gson

enum class NetWorkStatus {
    INITIAL_LOADING,
    LOADING,
    LOADED,
    FAILED,
    COMPLETED
}

open class PixaBayDataSource(var context: Context) : PageKeyedDataSource<Int, Hit>() {

    var retry: (() -> Any)? = null
    val netWorkStatus = MutableLiveData<NetWorkStatus>()
    private val queryKey = arrayOf("dog", "animal", "car", "beauty", "phone", "computer", "animal", "flower").random()
    private fun getUrl(): String {
        return "https://pixabay.com/api/?key=16772942-b4249147ca8679541f3e67147&q=${queryKey.random()}&per_page=${50}&page=1";
    }

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Hit>) {
        //setValue和postValue ---
        //setValue 线程不安全
        //postValue线程会安全一些
        retry = null
        netWorkStatus.postValue(NetWorkStatus.INITIAL_LOADING)
        StringRequest(Request.Method.GET,
                getUrl(),
                Response.Listener {
                    val hits = Gson().fromJson(it, PixaData::class.java).hits.toList()
                    callback.onResult(hits, null, 2)
                    netWorkStatus.postValue(NetWorkStatus.LOADED)
                }, Response.ErrorListener {
            retry = { loadInitial(params, callback) }
            netWorkStatus.postValue(NetWorkStatus.FAILED)
            Log.e("错误", it.toString());
        }).also {
            VolleySingleton.getInstance(context)?.request?.add(it)
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Hit>) {
        retry = null
        var url = "https://pixabay.com/api/?key=16772942-b4249147ca8679541f3e67147&q=${queryKey.random()}&per_page=${50}&page=${params.key}"
        netWorkStatus.postValue(NetWorkStatus.LOADING)
        StringRequest(
                Request.Method.GET,
                url,
                Response.Listener {
                    val hits = Gson().fromJson(it, PixaData::class.java).hits
                    callback.onResult(hits, params.key + 1)
                    netWorkStatus.postValue(NetWorkStatus.LOADED)
                }, Response.ErrorListener {
            if (it.toString() == "com.android.volley.ClientError"){
                netWorkStatus.postValue(NetWorkStatus.COMPLETED)
            }else{
                retry = {loadAfter(params,callback)}
                netWorkStatus.postValue(NetWorkStatus.FAILED)
            }

        })
                .also { VolleySingleton.getInstance(context)?.request?.add(it) }

    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Hit>) {
        TODO("Not yet implemented")
    }
}