package com.example.jetpackdemo.vocabulary_demo08;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

/**
 * 数据的获取
 */
public class WordRepository  {
    private LiveData<List<Word>>allWordsLive ;
    private WordDataBase wordDataBase ;
    private WordDao wordDao;
    public WordRepository(Context context){
        wordDataBase = WordDataBase.getInstance(context.getApplicationContext());
        wordDao = wordDataBase.getWordDao();
    }


    public LiveData<List<Word>> getListLiveData() {
        return wordDao.getAllWords();
    }

    public WordDataBase getWordDataBase() {
        return wordDataBase;
    }

    public WordDao getWordDao() {
        return wordDao;
    }

    static class InsertAsyncTask extends AsyncTask<Word, Void, Void> {

        WordDao wordDao;

        public InsertAsyncTask(WordDao wordDao) {
            this.wordDao = wordDao;
        }

        @Override
        protected Void doInBackground(Word... words) {
            wordDao.insertWords(words);
            return null;
        }
    }

    public void insertWords(Word...words){
        new InsertAsyncTask(wordDao).execute(words);
    }

    public void deleteWords(Word...words){
        new DeleteAsyncTask(wordDao).execute(words);
    }

    public void updateWords(Word...words){
        new UpdateAsyncTask(wordDao).execute(words);
    }
    public void deleteAllWords(){
        new DeleteAllAsyncTask(wordDao).execute();
    }

    static class DeleteAllAsyncTask extends AsyncTask<Word, Void, Void> {

        WordDao wordDao;

        public DeleteAllAsyncTask(WordDao wordDao) {
            this.wordDao = wordDao;
        }

        @Override
        protected Void doInBackground(Word... words) {
            wordDao.deleteAllWords();
            return null;
        }
    }

    static class DeleteAsyncTask extends AsyncTask<Word, Void, Void> {

        WordDao wordDao;

        public DeleteAsyncTask(WordDao wordDao) {
            this.wordDao = wordDao;
        }

        @Override
        protected Void doInBackground(Word... words) {
            wordDao.deleteWords(words);
            return null;
        }
    }

    static class UpdateAsyncTask extends AsyncTask<Word, Void, Void> {

        WordDao wordDao;

        public UpdateAsyncTask(WordDao wordDao) {
            this.wordDao = wordDao;
        }

        @Override
        protected Void doInBackground(Word... words) {
            wordDao.updateWords(words);
            return null;
        }
    }
}
