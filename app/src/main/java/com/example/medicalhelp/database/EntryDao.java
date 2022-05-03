package com.example.medicalhelp.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface EntryDao {

    @Query("SELECT * FROM entries")
    List<Entry> getAll();

    @Query("SELECT * FROM entries WHERE title LIKE :title")
    Entry getByTitle(String title);

    @Insert
    void insertAll(Entry... entries);

    @Delete
    void delete(Entry entry);

    @Update
    void updateEntry(Entry entry);
}
