package com.example.jetpackdemo.day06_calculation_demo;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.jetpackdemo.R;
import com.example.jetpackdemo.base.BaseFragment;
import com.example.jetpackdemo.databinding.FragmentLoseBinding;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class LoseFragment extends BaseFragment<FragmentLoseBinding,MyViewModel> {


   private FragmentLoseBinding binding;
   private MyViewModel myViewModel ;

    @Override
    public void secondStep(Bundle saveInstanceStated) {
        binding.button19.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_loseFragment_to_titleFragment));
    }

    @Override
    public Class<MyViewModel> getModelClass() {
        return MyViewModel.class;
    }

    @Override
    public int layout() {
        return R.layout.fragment_lose;
    }

    @Override
    public void doSomething() {
        binding = getDataDing();
        myViewModel = getViewModel();
        binding.setData(myViewModel);

    }
}
