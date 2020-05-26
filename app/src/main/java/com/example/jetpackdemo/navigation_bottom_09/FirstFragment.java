package com.example.jetpackdemo.navigation_bottom_09;

import androidx.lifecycle.ViewModelProvider;
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

public class FirstFragment extends Fragment {

    private FirstViewModel mViewModel;
    private ImageView  imageView;


    public static FirstFragment newInstance() {
        return new FirstFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.first_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(requireActivity()).get(FirstViewModel.class);
        imageView = getView().findViewById(R.id.imageView8);
        imageView.setRotation(mViewModel.rotationPosition);
        //属性动画， 对所有view的参数做动画
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(imageView,"rotation",0,0);
        objectAnimator.setDuration(500);
        imageView.setOnClickListener(v->{
            if (!objectAnimator.isRunning()){
                objectAnimator.setFloatValues(imageView.getRotation(),imageView.getRotation()+100);
                mViewModel.rotationPosition+=100;
                objectAnimator.start();
            }
        });
    }

}
