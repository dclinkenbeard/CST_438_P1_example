package com.daclink.drew.sp22.plainolnotes.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {NoteEntity.class}, version = 1)
@TypeConverters(DateConverter.class)
public abstract class AppDatabase extends RoomDatabase {

    public static final String DATABASE_NAME = "AppDatabase.db";
    //Volatile means it may only be accessed from main memory
    private static volatile AppDatabase instance;
    private static final Object LOCK = new Object();

    //never called directly. Room does all the work with this. THat is why it is abstract.
    //need one of these for each DAO.
    public abstract NoteDao noteDao();

    public static AppDatabase getInstance(Context context) {
        if(instance == null){
            synchronized (LOCK){
                if(instance == null){
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class,
                            DATABASE_NAME).build();
                }
            }
        }
        return instance;
    }


}
