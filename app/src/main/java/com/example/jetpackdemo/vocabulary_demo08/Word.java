package com.example.jetpackdemo.vocabulary_demo08;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * entity 实体类 [表]
 */
@Entity
public class Word {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "english_word")
    private String word;
    @ColumnInfo(name = "chinese_meaning")
    private String chineseMeaning;
    @ColumnInfo(name = "isShowChineseMeaning")
    private boolean isShowChineseMeaning;
//    @ColumnInfo(name = "foo_data")
//    private String foo;
//    @ColumnInfo(name = "bar_data")
//    private boolean bar ;
//    @ColumnInfo(name =  "test_data")
//    private boolean test;
//
//    @ColumnInfo(name = "test_data2")
//    private boolean test2;

//    public boolean isTest2() {
//        return test2;
//    }
//
//    public void setTest2(boolean test2) {
//        this.test2 = test2;
//    }
//
//    public boolean isTest() {
//        return test;
//    }
//
//    public void setTest(boolean test) {
//        this.test = test;
//    }
//
//    public void setBar(boolean bar) {
//        this.bar = bar;
//    }
//
//    public boolean isBar() {
//        return bar;
//    }
//
//    public String getFoo() {
//        return foo;
//    }
//
//    public void setFoo(String foo) {
//        this.foo = foo;
//    }


    public boolean isShowChineseMeaning() {
        return isShowChineseMeaning;
    }

    public void setShowChineseMeaning(boolean showChineseMeaning) {
        isShowChineseMeaning = showChineseMeaning;
    }

    public Word(String word, String chineseMeaning) {
        this.word = word;
        this.chineseMeaning = chineseMeaning;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getChineseMeaning() {
        return chineseMeaning;
    }

    public void setChineseMeaning(String chineseMeaning) {
        this.chineseMeaning = chineseMeaning;
    }
}
