package com.example.jetpackdemo.day10_pageing_demo10;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.jetpackdemo.R;

public class Main14Activity extends AppCompatActivity {

    private static final String TAG = "Main14Activity";
    private Button button1,button2;
    private StudentDao studentDao ;
    private StudentDataBase studentDataBase ;
    private PagerAdapter pagerAdapter ;
    private RecyclerView recyclerView;
    LiveData<PagedList<Student>> pagedListLiveData ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main14);
        button1 = findViewById(R.id.button23);
        button2 = findViewById(R.id.button24);
        pagerAdapter = new PagerAdapter();
        recyclerView  = findViewById(R.id.recyclerView) ;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(pagerAdapter);
        studentDataBase = StudentDataBase.getInstance(this);
        studentDao = studentDataBase.getStudentDao();
        pagedListLiveData = new LivePagedListBuilder<>(studentDao.getAllStudents(),1).build();
        pagedListLiveData.observe(this, new Observer<PagedList<Student>>() {
            @Override
            public void onChanged(PagedList<Student> students) {
                pagerAdapter.submitList(students);
                students.addWeakCallback(null, new PagedList.Callback() {
                    @Override
                    public void onChanged(int position, int count) {
                        Log.e(TAG,"onChanged:" +students );
                    }

                    @Override
                    public void onInserted(int position, int count) {

                    }

                    @Override
                    public void onRemoved(int position, int count) {

                    }
                });
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Student[]  students = new Student[1000];
                for (int i = 0;i<1000;i++){
                    Student student = new Student();
                    student.setStudentNumber(i);
                    students[i] = student ;
                }
                new AsyncTask1(studentDao).execute(students);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AsyncTask2(studentDao).execute();
            }
        });



    }


    static class AsyncTask1 extends AsyncTask<Student,Void,Void>{

        private StudentDao studentDao ;

        public AsyncTask1(StudentDao studentDao){
            this.studentDao = studentDao ;
        }

        @Override
        protected Void doInBackground(Student... students) {
            studentDao.insert(students);
            return null;
        }
    }

    static class AsyncTask2 extends AsyncTask<Void,Void,Void>{

        private StudentDao studentDao ;

        public AsyncTask2(StudentDao studentDao){
            this.studentDao = studentDao ;
        }


        @Override
        protected Void doInBackground(Void... voids) {
           studentDao.deleteAllStudents();
            return null;
        }
    }
}




