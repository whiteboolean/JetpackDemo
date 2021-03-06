package com.example.jetpackdemo.day06_calculation_demo;


import android.os.Bundle;

import androidx.navigation.Navigation;

import com.example.jetpackdemo.R;
import com.example.jetpackdemo.base.BaseFragment;
import com.example.jetpackdemo.databinding.FragmentTitleBinding;

/**
 * 标题页面
 */
public class TitleFragment extends BaseFragment<FragmentTitleBinding, MyViewModel> {

    @Override
    public Class<MyViewModel> getModelClass() {
        return MyViewModel.class;
    }

    @Override
    public int layout() {
        return R.layout.fragment_title;
    }

    /**
     * 执行业务逻辑
     */
    @Override
    public void doSomething() {
        binding.setData(viewModel);
    }


    /**
     * 页面初始化出来之后进行操作
     *
     * @param saveInstanceStated
     */
    @Override
    public void secondStep(Bundle saveInstanceStated) {
        binding.button18.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_titleFragment_to_questionFragment));

    }


}
