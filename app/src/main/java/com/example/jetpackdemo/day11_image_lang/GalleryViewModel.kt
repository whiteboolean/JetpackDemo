package com.example.jetpackdemo.day11_image_lang

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.google.gson.Gson

class GalleryViewModel(application: Application) : AndroidViewModel(application) {

    private val _photoListLive = MutableLiveData<List<Hit>>()

    val photoList: LiveData<List<Hit>>
        get() = _photoListLive

    fun fetchData() {
        val stringRequest = StringRequest(
                Request.Method.GET,
                getUrl(),
                Response.Listener {
                    _photoListLive.value = Gson().fromJson(it,PixaData::class.java).hits.toList()
                },
                Response.ErrorListener {

                }

        )
        VolleySingleton.getInstance2(getApplication()).request.add(stringRequest)
    }

    private fun getUrl(): String {
        return "https://pixabay.com/api/?key=16772942-b4249147ca8679541f3e67147&q=${keyWords.random()}&per_page=100";
    }

    private val keyWords = arrayOf("dog", "animal", "car", "beauty", "phone", "computer", "animal", "flower");

}
