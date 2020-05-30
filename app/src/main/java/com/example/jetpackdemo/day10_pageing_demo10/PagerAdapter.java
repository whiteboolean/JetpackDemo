package com.example.jetpackdemo.day10_pageing_demo10;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jetpackdemo.R;

public class PagerAdapter extends PagedListAdapter<Student, PagerAdapter.StudentViewHolder> {


    protected PagerAdapter() {
        super(new DiffUtil.ItemCallback<Student>() {
            @Override
            public boolean areItemsTheSame(@NonNull Student oldItem, @NonNull Student newItem) {
                return oldItem.getId() == newItem.getId();
            }

            @Override
            public boolean areContentsTheSame(@NonNull Student oldItem, @NonNull Student newItem) {
                return oldItem.getId() == newItem.getId() &&
                        oldItem.getStudentNumber() == newItem.getStudentNumber();
            }
        });
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflater = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_page,parent,false) ;

        return new PagerAdapter.StudentViewHolder(inflater);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {

        Student student = getItem(position) ;
        if (student==null){
            holder.textView .setText("loading");
        }else{
            holder.textView .setText(String.valueOf(student.getStudentNumber()));
        }
    }

    static class StudentViewHolder extends RecyclerView.ViewHolder{

        private TextView textView ;

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView29) ;
        }
    }
}
