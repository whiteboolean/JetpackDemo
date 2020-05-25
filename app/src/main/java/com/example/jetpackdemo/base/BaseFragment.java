package com.example.jetpackdemo.base;


import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.SavedStateViewModelFactory;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;


/**
 * fragment基类
 */
public abstract class BaseFragment<T extends ViewDataBinding ,K extends ViewModel> extends Fragment {

    public Activity rAct ;
    private T binding;
    private K viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rAct = requireActivity();
        viewModel = getK();
        binding = getT(inflater, container);
        doSomething();
        binding.setLifecycleOwner(this);
        return binding.getRoot();
    }

    private T getT(LayoutInflater inflater, ViewGroup container){
        return DataBindingUtil.inflate(inflater,layout(),container,false);
    }

    private K getK(){
        return new ViewModelProvider(this,new SavedStateViewModelFactory(rAct.getApplication(),this)).get(getModelClass());
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        secondStep(savedInstanceState);
        super.onActivityCreated(savedInstanceState);
    }

    public T getDataDing(){
        return binding;
    }

    public K getViewModel(){
        return viewModel ;
    }


    public abstract void doSomething();

    public abstract void secondStep(Bundle saveInstanceStated);

    public abstract int layout();

    public abstract Class<K> getModelClass();
}
