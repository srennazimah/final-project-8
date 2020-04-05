package com.nazimah.my_dictionary.data;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.nazimah.my_dictionary.data.dao.DictionaryDao;
import com.nazimah.my_dictionary.data.entity.Dictionary;

@Database(version = 1, entities = {Dictionary.class}, exportSchema = false)
abstract public class DictionaryStoreDatabase extends RoomDatabase {

    private static final String DB_NAME = "dictionary_store_db";

    //create abstract method for dao

    public abstract DictionaryDao dictionaryDao();

    public static DictionaryStoreDatabase getInstance(Context context){
        return Room.databaseBuilder(context, DictionaryStoreDatabase.class, DB_NAME)
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
    }
}
