package com.example.jetpackdemo.vocabulary_demo08;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.example.jetpackdemo.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class WordsFragment extends Fragment {

    Button button1, button3;
    TextView textWords;

    private WordViewModel wordViewModel;
    private RecyclerView recyclerView;
    private MyAdapter myAdapter1, myAdapter2;
    private Switch switch1;
    private FloatingActionButton floatingActionButton2;

    public WordsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_words, container, false);
    }


    @Override
    public void onResume() {
        super.onResume();
        InputMethodManager imm = (InputMethodManager) requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getView().getWindowToken(),0);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        View view = getView();
//        button1 = view.findViewById(R.id.button20);
//        button2 = findViewById(R.id.button21);
//        button3 = view.findViewById(R.id.button22);
//        button4 = findViewById(R.id.button23);
//        textWords = view.findViewById(R.id.textViewNumber);
        floatingActionButton2 = view.findViewById(R.id.floatingActionButton2);
        recyclerView = view.findViewById(R.id.recyclerView);
        wordViewModel = new ViewModelProvider(this).get(WordViewModel.class);

//        switch1 = view.findViewById(R.id.switch1);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
        myAdapter2 = new MyAdapter(true, wordViewModel);
        myAdapter1 = new MyAdapter(false, wordViewModel);
        recyclerView.setAdapter(myAdapter1);
//        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if (isChecked) {
//                    recyclerView.setAdapter(myAdapter2);
//                } else {
//                    recyclerView.setAdapter(myAdapter1);
//                }
//            }
//        });
        floatingActionButton2.setOnClickListener(v-> Navigation.findNavController(v).navigate(R.id.action_wordsFragment_to_addFragment2));

        wordViewModel.getListLiveData().observe(requireActivity(), new Observer<List<Word>>() {
            @Override
            public void onChanged(List<Word> words) {
                int temp = myAdapter1.getItemCount();
                myAdapter1.setAllWords(words);
                myAdapter2.setAllWords(words);
                if (temp != words.size()) {
                    myAdapter1.notifyDataSetChanged();
                    myAdapter2.notifyDataSetChanged();
                }
            }
        });

//        wordViewModel.getListLiveData().observe(this, new Observer<List<Word>>() {
//            @Override
//            public void onChanged(List<Word> words) {
//                StringBuilder i = new StringBuilder();
//                for (Word word : Objects.requireNonNull(words)) {
//                    i.append(word.getId()).append(",").append(word.getWord()).append(",").append(word.getChineseMeaning()).append("\n");
//                }
//                textWords.setText(i.toString());
//            }
//        });


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

//        button3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                wordViewModel.deleteAllWords();
//            }
//        });

//        button2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Word word = new Word("change", "改他");
//                word.setId(171);
//                wordViewModel.updateWords(word);
//            }
//        });
//
//        button4.setOnClickListener(v -> {
//            Word word = new Word("f", "");
//            word.setId(171);
//            wordViewModel.deleteWords(word);
//        });


    }
}
