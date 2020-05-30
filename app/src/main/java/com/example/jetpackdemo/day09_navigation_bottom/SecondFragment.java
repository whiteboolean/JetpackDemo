package com.example.jetpackdemo.day09_navigation_bottom;

import androidx.lifecycle.ViewModelProvider;

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

public class SecondFragment extends Fragment {

    private SecondViewModel mViewModel;
    private ImageView imageView;

    public static SecondFragment newInstance() {
        return new SecondFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.second_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(requireActivity()).get(SecondViewModel.class);
        imageView = getView().findViewById(R.id.imageView9);
        imageView.setScaleX(mViewModel.scaleFactor);
        imageView.setScaleY(mViewModel.scaleFactor);
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(imageView,"scaleX",0,0);
        ObjectAnimator objectAnimator1 = ObjectAnimator.ofFloat(imageView,"scaleY",0,0);
        objectAnimator.setDuration(600);
        objectAnimator1.setDuration(600);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!objectAnimator.isRunning()){
                    objectAnimator.setFloatValues(imageView.getScaleX(),imageView.getScaleX()+0.1f);
                    objectAnimator1.setFloatValues(imageView.getScaleY(),imageView.getScaleY()+0.1f);
                    mViewModel.scaleFactor += 0.1;
                    objectAnimator.start();
                    objectAnimator1.start();
                }
            }
        });
    }

}
