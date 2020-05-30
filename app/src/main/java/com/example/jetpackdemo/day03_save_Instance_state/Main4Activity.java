package com.example.jetpackdemo.day03_save_Instance_state;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.SavedStateViewModelFactory;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.example.jetpackdemo.R;
import com.example.jetpackdemo.databinding.ActivityMain4Binding;

public class Main4Activity extends AppCompatActivity {

    ActivityMain4Binding binding;
    MySpViewModel mySpViewModel;
    public final static String KEY_NUMBER = "my_number";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      binding = DataBindingUtil.setContentView(this,R.layout.activity_main4);
//      if (savedInstanceState !=null){
//          mySpViewModel.getNumber().setValue(savedInstanceState.getInt(KEY_NUMBER));
//      }
//      mySpViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(MySpViewModel.class);
      mySpViewModel = new ViewModelProvider(this,new SavedStateViewModelFactory(this.getApplication(),this)).get(MySpViewModel.class);
      binding.setData(mySpViewModel);
      binding.setLifecycleOwner(this);
    }


//    @Override
//    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
//        super.onSaveInstanceState(outState, outPersistentState);
//        outState.putInt(KEY_NUMBER,mySpViewModel.getNumber().getValue());
//    }
}
