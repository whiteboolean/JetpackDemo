package com.example.jetpackdemo.base;


import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
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
    protected T binding;
    protected K viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rAct = requireActivity();
        viewModel = new ViewModelProvider(this,new SavedStateViewModelFactory(rAct.getApplication(),this)).get(getModelClass());
        binding = DataBindingUtil.inflate(inflater,layout(),container,false);
        binding.setLifecycleOwner(this);
        doSomething();
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        secondStep(savedInstanceState);
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public abstract void doSomething();

    public abstract void secondStep(Bundle saveInstanceStated);

    @LayoutRes
    public abstract int layout();

    public abstract Class<K> getModelClass();
}
