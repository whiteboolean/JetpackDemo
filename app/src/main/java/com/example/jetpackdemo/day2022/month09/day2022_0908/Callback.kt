package com.example.jetpackdemo.day2022.month09.day2022_0908

interface Callback<T> {
    fun onSuccess(value: T)

    fun onError(t: Throwable)
}
