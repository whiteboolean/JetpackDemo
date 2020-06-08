package com.example.jetpackdemo.day13_viewpager2_demo

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.example.jetpackdemo.day11_image_lang.Hit
import com.example.jetpackdemo.day11_image_lang.PixaData
import com.example.jetpackdemo.day11_image_lang.VolleySingleton
import com.google.gson.Gson
import kotlin.math.ceil

const val DATA_STATUS_CAN_LOAD_MORE = 0
const val DATA_STATUS_NO_MORE = 1
const val DATA_STATUS_NETWORK_ERROR = 2

class MyPagerListViewModel(application: Application) : AndroidViewModel(application) {

    private var _photoList = MutableLiveData<List<Hit>>()
    private var _photoStateLive = MutableLiveData<Int>()

    val photoStateLive: LiveData<Int> get() = _photoStateLive
    val photoListData: MutableLiveData<List<Hit>> get() = _photoList

    private var perPage = 100
    private var currentPage = 1
    private var totalPage = 1
    private var isNewRequest = true
    var isLoading = false
    var needScrollToTop = false

    private fun getUrl(): String {
        return "https://pixabay.com/api/?key=16772942-b4249147ca8679541f3e67147&q=${keyWords.random()}&per_page=${perPage}&page=${currentPage}";
    }

    //    private val keyWords = arrayOf("dog", "animal", "car", "beauty", "phone", "computer", "animal", "flower");
    private val keyWords = arrayOf("flower");

    /**
     * 刷新重新加载
     */
    public fun resetFetchData() {
        needScrollToTop = true
        currentPage = 1
        isNewRequest = true
        fetchData()
    }

    /**
     * 上拉加载
     */
    public fun fetchData() {
        if (isLoading) return
        if (currentPage > totalPage) {
            _photoStateLive.value = DATA_STATUS_NO_MORE
            return
        }
        isLoading = true
        val request = StringRequest(
                Request.Method.GET,
                getUrl(),
                Response.Listener {
                    Gson().fromJson(it, PixaData::class.java).apply {
                        //总条数
                        totalPage = ceil(totalHits.toDouble() / perPage).toInt()
                        if (isNewRequest) {
                            _photoList.value = hits.toList()
                        } else {
                            _photoList.value = arrayListOf(_photoList.value!!, hits.toList()).flatten() //将两个数据合并  ， 扁平化操作
                        }
                    }
                    _photoStateLive.value = DATA_STATUS_CAN_LOAD_MORE
                    isNewRequest = false
                    isLoading = false
                    currentPage++
                },
                Response.ErrorListener {
                    Toast.makeText(getApplication(), "请求失败", Toast.LENGTH_SHORT).show()
                    _photoStateLive.value = DATA_STATUS_NETWORK_ERROR
                    isLoading = false
                }
        )
        VolleySingleton.getInstance2(getApplication()).request.add(request)
    }

    fun observer1(){

    }




}