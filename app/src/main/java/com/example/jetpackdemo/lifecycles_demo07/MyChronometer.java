package com.example.jetpackdemo.lifecycles_demo07;

import android.content.Context;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.widget.Chronometer;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

public class MyChronometer extends Chronometer implements LifecycleObserver {

    private long elapsedTime;
    public MyChronometer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void pauseMeter(){
        elapsedTime = SystemClock.elapsedRealtime() - getBase() ;
        stop();
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void resumeMeter(){
        setBase(SystemClock.elapsedRealtime() - elapsedTime);
        start();
    }


}
