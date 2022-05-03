package com.example.medicalhelp.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "entries")
public class Entry {
    @PrimaryKey(autoGenerate = true)
    public long id;

    @ColumnInfo(name = "title",
    defaultValue = "defaultTitle")
    public String title;

    @ColumnInfo(name = "content")
    public String content;
}
