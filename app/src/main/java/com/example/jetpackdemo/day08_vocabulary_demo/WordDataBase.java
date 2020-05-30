package com.example.jetpackdemo.day08_vocabulary_demo;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Word.class}, version = 7, exportSchema = false)
public abstract class WordDataBase extends RoomDatabase {

    private static volatile WordDataBase instance;

    public abstract WordDao getWordDao();

    public static synchronized WordDataBase getInstance(Context context) {
        if (instance == null) {
            synchronized (WordDataBase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context, WordDataBase.class, "word_database")
                            //数据库迁移 ， 破坏式的
//                            .fallbackToDestructiveMigration()
//                            .addMigrations(MIGRATION6_7)
                            .build();
                }
            }
        }
        return instance;
    }

    private static final Migration MIGRATION4_5 = new Migration(4, 5) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE word ADD COLUMN  test_data2 INTEGER NOT NULL DEFAULT 1");
        }
    };

    private static final Migration MIGRATION6_7 = new Migration(6, 7) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE word ADD COLUMN isShowChineseMeaning INTEGER NOT NULL DEFAULT 1");
        }
    };

    private static final Migration MIGRATION5_6 = new Migration(5, 6) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            //新建表格
            database.execSQL("CREATE TABLE word_temp (id INTEGER PRIMARY KEY NOT NULL ,english_word TEXT , chinese_meaning TEXT )");
            //从原表中提取数据查询数据
            database.execSQL("INSERT INTO word_temp (id,english_word,chinese_meaning) SELECT id,english_word,chinese_meaning FROM word");
            //把原来的表格删掉
            database.execSQL("DROP TABLE word");
            //把新表改回成原数据
            database.execSQL("ALTER TABLE word_temp RENAME TO word");

        }
    };


}
