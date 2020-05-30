package com.example.jetpackdemo.day11_image_lang

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class PixaData (
    val hits: List<Hit>,
    val total: Int,
    val totalHits: Int
)

@Parcelize data class Hit(
    val comments: Int,
    val downloads: Int,
    val favorites: Int,
    @SerializedName("id")
    val photoId: Int,
    val imageHeight: Int,
    val imageSize: Int,
    val imageWidth: Int,
    val largeImageURL: String,
    val likes: Int,
    val pageURL: String,
    val previewHeight: Int,
    val previewURL: String,
    val previewWidth: Int,
    val tags: String,
    val type: String,
    val user: String,
    val userImageURL: String,
    val user_id: Int,
    val views: Int,
    @SerializedName("webformatHeight")
    val webFormat: Int,
    @SerializedName("webformatURL")
    val previewUrl: String,
    @SerializedName("webformatWidth")
    val webformatWidth: Int
):Parcelable