package com.daclink.drew.sp22.plainolnotes.database;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.daclink.drew.sp22.plainolnotes.utilities.SampleData;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * This class determines where our data originate.
 * We use this class to pull from the DB and, in the future, from a cloud based storage solution.
 */
public class AppRepository {
    public static AppRepository ourInstance;

    public LiveData<List<NoteEntity>> mNotes;
    private AppDatabase mDb;
//    use the same executor so all DB operations are queued rather than run on top of each other.
    private Executor executor = Executors.newSingleThreadExecutor();

    public static AppRepository getInstance(Context context){
        if (ourInstance == null) {
            ourInstance = new AppRepository(context);
        }
        return ourInstance;
    }

    private AppRepository(Context context){
        mDb = AppDatabase.getInstance(context);
        mNotes = getAllNotes();
    }

    public void addSampleData() {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                mDb.noteDao().insertAll(SampleData.getNotes());
            }
        });
    }
//    this is where we add a connection to the web.
    private LiveData<List<NoteEntity>> getAllNotes(){

        //we don't need an Executor because room handles background threads when using LiveData
        return mDb.noteDao().getAll();
    }

    public void deleteAllNotes() {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                mDb.noteDao().deleteAll();
            }
        });
    }

    public NoteEntity getNoteById(int noteId) {
        return mDb.noteDao().getNoteById(noteId);
    }

    public void insertNote(NoteEntity note) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                //works on old and new notes. iF it is a new note a new one is created. if it is an old note the value is updated.
                //this is due to the onConflict annotation in the DAO
                mDb.noteDao().insertNote(note);
            }
        });
    }

    public void deleteNote(final NoteEntity note) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                mDb.noteDao().deleteNote(note);
            }
        });
    }
}
