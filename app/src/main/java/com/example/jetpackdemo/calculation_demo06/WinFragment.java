package com.example.jetpackdemo.calculation_demo06;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.SavedStateViewModelFactory;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jetpackdemo.R;
import com.example.jetpackdemo.base.BaseFragment;
import com.example.jetpackdemo.databinding.FragmentLoseBinding;
import com.example.jetpackdemo.databinding.FragmentWinBinding;

import java.math.BigInteger;

/**
 * A simple {@link Fragment} subclass.
 */
public class WinFragment extends BaseFragment<FragmentWinBinding,MyViewModel> {

    private FragmentWinBinding binding;
    private MyViewModel viewModel;

    @Override
    public void doSomething() {
        binding = getDataDing();
        viewModel =getViewModel();
        binding.setData(viewModel);
    }

    @Override
    public void secondStep(Bundle saveInstanceStated) {
        binding.button19.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_winFragment_to_titleFragment));
    }

    @Override
    public int layout() {
        return R.layout.fragment_win;
    }

    @Override
    public Class<MyViewModel> getModelClass() {
        return MyViewModel.class;
    }
}
