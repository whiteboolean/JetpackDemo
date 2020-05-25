package com.example.jetpackdemo.vocabulary_demo08;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.SavedStateHandle;

import com.example.jetpackdemo.base.BaseViewModel;

import java.util.List;

public class WordViewModel extends BaseViewModel {

    private WordRepository repository;

    public WordViewModel(@NonNull Application application, SavedStateHandle handle) {
        super(application, handle);
        repository = new WordRepository(application);
    }

     LiveData<List<Word>> getListLiveData() {
        return repository.getWordDao().getAllWords();
    }


     void insertWords(Word... words) {
        repository.insertWords(words);
    }

     void deleteWords(Word... words) {
        repository.deleteWords(words);
    }

     void updateWords(Word... words) {
        repository.updateWords(words);
    }

     void deleteAllWords() {
        repository.deleteAllWords();
    }


}
