package com.nazimah.my_dictionary.data.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.nazimah.my_dictionary.data.entity.Dictionary;

import java.util.List;

@Dao
public interface DictionaryDao {

    @Insert
    void save(Dictionary dictionary);

    @Delete
    void delete(Dictionary dictionary);

    @Update
    void update (Dictionary dictionary);

    @Query("SELECT * FROM dictionary ORDER BY id ASC ")
    List<Dictionary> getDictionary();
}
