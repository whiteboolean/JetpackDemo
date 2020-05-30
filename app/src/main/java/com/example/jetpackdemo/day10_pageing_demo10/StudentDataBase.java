package com.example.jetpackdemo.day10_pageing_demo10;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Student.class}, version = 1, exportSchema = false)
public abstract class StudentDataBase extends RoomDatabase {

    private static volatile StudentDataBase instance;

    public static StudentDataBase getInstance(Context context) {
        if (instance == null) {
            synchronized (StudentDataBase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context, StudentDataBase.class, "student_database")
                            .build();
                }
            }
        }
        return instance;
    }

    abstract StudentDao getStudentDao();

}
