package com.daclink.drew.sp22.plainolnotes;

import static com.daclink.drew.sp22.plainolnotes.utilities.Constants.EDITING_KEY;
import static com.daclink.drew.sp22.plainolnotes.utilities.Constants.NOTE_ID_KEY;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.daclink.drew.sp22.plainolnotes.ViewModel.EditorViewModel;
import com.daclink.drew.sp22.plainolnotes.database.NoteEntity;
import com.daclink.drew.sp22.plainolnotes.databinding.ActivityEditorBinding;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.util.Objects;

public class EditorActivity extends AppCompatActivity {

    private ActivityEditorBinding binding;
    private EditorViewModel mViewModel;

    TextView mTextView;
    private boolean mNewNote, mEditing;
    Toolbar mToolbar;
    CollapsingToolbarLayout toolBarLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityEditorBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mTextView = binding.getRoot().findViewById(R.id.note_text);

        mToolbar = binding.toolbar;
        setSupportActionBar(mToolbar);
        toolBarLayout = binding.toolbarLayout;
//        toolBarLayout.setTitle(getTitle());
        Objects.requireNonNull(getSupportActionBar()).setHomeAsUpIndicator(R.drawable.ic_check);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);

        if (savedInstanceState != null){
            mEditing = savedInstanceState.getBoolean(EDITING_KEY);
        }

        initViewModel();

    }

    private void initViewModel() {
        mViewModel = new ViewModelProvider(this).get(EditorViewModel.class);


        //this is how we get updates from live data
        mViewModel.mLiveNote.observe(this, new Observer<NoteEntity>() {
            @Override
            public void onChanged(NoteEntity noteEntity) {
                if (noteEntity != null && !mEditing){
                    mTextView.setText(noteEntity.getText());
                }
            }
        });

        Bundle extras = getIntent().getExtras();
        if (extras == null) {
//            Objects.requireNonNull(getSupportActionBar()).setTitle(R.string.new_note);
            toolBarLayout.setTitle(getString(R.string.new_note));
            mNewNote = true;
        }else{
//            mToolbar.setTitle(R.string.edit_note);
            toolBarLayout.setTitle(getString(R.string.edit_note));
            int noteId = extras.getInt(NOTE_ID_KEY);
            mViewModel.loadData(noteId);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNewNote){
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.menu_editor,menu);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            saveAndReturn();
            return true;
        }else if(item.getItemId() == R.id.action_delete){
            mViewModel.deleteNote();
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        saveAndReturn();
        super.onBackPressed();
    }

    private void saveAndReturn() {
        mViewModel.saveNote(mTextView.getText().toString());
        finish();
    }

    // this is here to preserve changes through orientation changes
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putBoolean(EDITING_KEY, true);
        super.onSaveInstanceState(outState);
    }
}