package com.example.jetpackdemo.day10_pageing_demo10;

import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface StudentDao {
    @Insert
    void insert(Student...students) ;

    @Query("DELETE FROM student")
    void deleteAllStudents();

    @Query("SELECT * FROM student ORDER BY id")
    DataSource.Factory<Integer,Student> getAllStudents();
}
