package com.example.jetpackdemo.vocabulary_demo08;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.example.jetpackdemo.R;

import java.util.List;
import java.util.Objects;

/**
 * Entity
 * <p>
 * Dao
 * <p>
 * <p>
 * DataBase
 */

public class Main10Activity extends AppCompatActivity {

    private NavController navController;


    @Override
    public boolean onSupportNavigateUp() {
        navController.navigateUp();
        return super.onSupportNavigateUp();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main12);
        navController = Navigation.findNavController(findViewById(R.id.fragment3));
        NavigationUI.setupActionBarWithNavController(this, navController);//设置返回按钮
//
//        button1 = findViewById(R.id.button20);
////        button2 = findViewById(R.id.button21);
//        button3 = findViewById(R.id.button22);
////        button4 = findViewById(R.id.button23);
//        textWords = findViewById(R.id.textViewNumber);
//        recyclerView = findViewById(R.id.recyclerView);
//        wordViewModel = new ViewModelProvider(this).get(WordViewModel.class);
//
//        switch1 = findViewById(R.id.switch1);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        myAdapter2 = new MyAdapter(true,wordViewModel);
//        myAdapter1 = new MyAdapter(false,wordViewModel);
//        recyclerView.setAdapter(myAdapter1);
//        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if (isChecked){
//                    recyclerView.setAdapter(myAdapter2);
//                }else{
//                    recyclerView.setAdapter(myAdapter1);
//                }
//            }
//        });
//
//        wordViewModel.getListLiveData().observe(this, new Observer<List<Word>>() {
//            @Override
//            public void onChanged(List<Word> words) {
//                int temp = myAdapter1.getItemCount();
//                myAdapter1.setAllWords(words);
//                myAdapter2.setAllWords(words);
//                if (temp!=words.size()){
//                    myAdapter1.notifyDataSetChanged();
//                    myAdapter2.notifyDataSetChanged();
//                }
//            }
//        });
//
////        wordViewModel.getListLiveData().observe(this, new Observer<List<Word>>() {
////            @Override
////            public void onChanged(List<Word> words) {
////                StringBuilder i = new StringBuilder();
////                for (Word word : Objects.requireNonNull(words)) {
////                    i.append(word.getId()).append(",").append(word.getWord()).append(",").append(word.getChineseMeaning()).append("\n");
////                }
////                textWords.setText(i.toString());
////            }
////        });
//
//
//        button1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Word word = new Word("hello", "你好");
//                Word word1 = new Word("hello1", "你好1");
////                new InsertAsyncTask(wordDao).execute(word1, word);
//                String[] english = {
//                        "Hello",
//                        "World",
//                        "Android",
//                        "Google",
//                        "Studio",
//                        "Project",
//                        "Database",
//                        "Recycler",
//                        "View",
//                        "String",
//                        "Value",
//                        "Integer"
//                };
//                String[] chinese = {
//                        "你好",
//                        "世界",
//                        "安卓系统",
//                        "谷歌公司",
//                        "工作室",
//                        "项目",
//                        "数据库",
//                        "回收站",
//                        "视图",
//                        "字符串",
//                        "价值",
//                        "整数类型"
//                };
//
//                for (int i = 0; i < english.length; i++) {
//                    wordViewModel.insertWords(new Word(english[i], chinese[i]));
//                }
//
////                wordViewModel.insertWords(word, word1);
//            }
//        });
//
//        button3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                wordViewModel.deleteAllWords();
//            }
//        });
//
////        button2.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                Word word = new Word("change", "改他");
////                word.setId(171);
////                wordViewModel.updateWords(word);
////            }
////        });
////
////        button4.setOnClickListener(v -> {
////            Word word = new Word("f", "");
////            word.setId(171);
////            wordViewModel.deleteWords(word);
////        });
//
//    }

    }
}
