package com.example.jetpackdemo.calculation_demo06;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.SavedStateViewModelFactory;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jetpackdemo.R;
import com.example.jetpackdemo.databinding.FragmentQuestionBinding;

public class QuestionFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        MyViewModel viewModel = new ViewModelProvider(requireActivity(),new SavedStateViewModelFactory(requireActivity().getApplication(),requireActivity())).get(MyViewModel.class);
        FragmentQuestionBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_question, container, false);
        viewModel.generator();
        binding.setData(viewModel);
        binding.setLifecycleOwner(requireActivity());
        binding.textView19.setText(viewModel.getCurrentScore().getValue()+"");
        StringBuilder stringBuilder = new StringBuilder();
        View.OnClickListener listener = v -> {
            switch (v.getId()) {
                case R.id.button0:
                    stringBuilder.append(0);
                    break;
                case R.id.button1:
                    stringBuilder.append(1);
                    break;
                case R.id.button2:
                    stringBuilder.append(2);
                    break;
                case R.id.button3:
                    stringBuilder.append(3);
                    break;
                case R.id.button4:
                    stringBuilder.append(4);
                    break;
                case R.id.button5:
                    stringBuilder.append(5);
                    break;
                case R.id.button6:
                    stringBuilder.append(6);
                    break;
                case R.id.button7:
                    stringBuilder.append(7);
                    break;
                case R.id.button8:
                    stringBuilder.append(8);
                    break;
                case R.id.button9:
                    stringBuilder.append(9);
                    break;
                case R.id.buttonClear:
                    stringBuilder .setLength(0);
                    break;
                case R.id.buttonSubmit:
                    break;
            }

            if (stringBuilder.length()==0){
                binding.textView25.setText("Your Answer!");
            }else{
                binding.textView25.setText(stringBuilder.toString());
            }
        };

        binding.button0.setOnClickListener(listener);
        binding.button1.setOnClickListener(listener);
        binding.button2.setOnClickListener(listener);
        binding.button3.setOnClickListener(listener);
        binding.button4.setOnClickListener(listener);
        binding.button5.setOnClickListener(listener);
        binding.button6.setOnClickListener(listener);
        binding.button7.setOnClickListener(listener);
        binding.button8.setOnClickListener(listener);
        binding.button9.setOnClickListener(listener);
        binding.buttonClear.setOnClickListener(listener);

        binding.buttonSubmit.setOnClickListener(v -> {
            if (stringBuilder.toString().isEmpty()){
                stringBuilder.append("0");
            }
            if (Integer.valueOf(stringBuilder.toString()).intValue() == viewModel.getAnswer().getValue()){
                viewModel.answerCorrect();
                stringBuilder.setLength(0);
                binding.textView25.setText("回答正确");
            }else{
                NavController controller = Navigation.findNavController(v);
                if (viewModel.win_flag){
                    controller.navigate(R.id.action_questionFragment_to_winFragment);
                    viewModel.win_flag = false;
                    viewModel.save();
                }else{
                    controller.navigate(R.id.action_questionFragment_to_loseFragment2);
                }
            }
        });

        return binding.getRoot();
    }

}
