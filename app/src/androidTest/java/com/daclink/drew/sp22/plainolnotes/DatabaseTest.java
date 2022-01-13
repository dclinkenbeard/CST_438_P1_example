package com.daclink.drew.sp22.plainolnotes;

import android.content.Context;
import android.util.Log;

import androidx.room.Room;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.daclink.drew.sp22.plainolnotes.database.AppDatabase;
import com.daclink.drew.sp22.plainolnotes.database.NoteDao;
import com.daclink.drew.sp22.plainolnotes.database.NoteEntity;
import com.daclink.drew.sp22.plainolnotes.utilities.SampleData;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class DatabaseTest {
    public static final String TAG = "Junit";
    private AppDatabase mDb;
    private NoteDao mDao;

    @Before
    public void createDb(){
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        //we use inMemoryDatabaseBuilder here so the DB isn't persistent. We are only testing after all.
        mDb = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();

        mDao = mDb.noteDao();

        Log.i(TAG, "created DB");

    }

    @After
    public void closeDb(){
        mDb.close();

        Log.i(TAG, "closeDb: ");
    }

    @Test
    public void createAndRetrieveNotes(){
        mDao.insertAll(SampleData.getNotes());
        int count = mDao.getCount();
        Log.i(TAG, "createAndRetrieveNotes: count=" + count);
        assertEquals(SampleData.getNotes().size(), count);
    }

    @Test
    public void compareStrings(){
        mDao.insertAll(SampleData.getNotes());
        NoteEntity original = SampleData.getNotes().get(0);
        NoteEntity fromDB = mDao.getNoteById(1);

        assertEquals(original.getText(),fromDB.getText());
        assertEquals(1,fromDB.getId());
    }



}
