package com.example.jetpackdemo.calculation_demo06;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.example.jetpackdemo.R;
import com.example.jetpackdemo.base.BaseFragment;
import com.example.jetpackdemo.databinding.FragmentTitleBinding;

/**
 * 标题页面
 */
public class TitleFragment extends BaseFragment<FragmentTitleBinding, MyViewModel> {

    FragmentTitleBinding binding ;
    MyViewModel myViewModel;
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
        binding = getDataDing();
        myViewModel = getViewModel();
        binding.setData(myViewModel);
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
