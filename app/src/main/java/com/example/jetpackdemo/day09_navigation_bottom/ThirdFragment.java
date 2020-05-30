package com.example.jetpackdemo.day09_navigation_bottom;

import androidx.lifecycle.ViewModelProviders;

import android.animation.ObjectAnimator;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.jetpackdemo.R;

import java.util.Random;

public class ThirdFragment extends Fragment {

    private ThirdViewModel mViewModel;
    private ImageView imageView;

    public static ThirdFragment newInstance() {
        return new ThirdFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.third_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ThirdViewModel.class);
        imageView = getView().findViewById(R.id.imageView10);
        imageView.setX(mViewModel.dX);
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(imageView,"x",0,0);
        objectAnimator.setDuration(500);
        // TODO: Use the ViewModel
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!objectAnimator.isRunning()){
                    float x = new Random().nextBoolean()?100:-100;
                    objectAnimator.setFloatValues(imageView.getX(),imageView.getX()+x);
                    mViewModel.dX +=x ;
                    objectAnimator.start();
                }
            }
        });
    }

}
