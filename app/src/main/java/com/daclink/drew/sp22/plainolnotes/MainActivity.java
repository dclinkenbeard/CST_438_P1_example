package com.daclink.drew.sp22.plainolnotes;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.daclink.drew.sp22.plainolnotes.ViewModel.MainViewModel;
import com.daclink.drew.sp22.plainolnotes.database.NoteEntity;
import com.daclink.drew.sp22.plainolnotes.databinding.ActivityMainBinding;
import com.daclink.drew.sp22.plainolnotes.ui.NotesAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding mActivityMainBinding;

    //    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    private List<NoteEntity> notesData = new ArrayList<>();
    private NotesAdapter mAdapter;
    private MainViewModel mViewModel;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mActivityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = mActivityMainBinding.getRoot();
        setContentView(view);

        mRecyclerView = mActivityMainBinding.contentMain.recyclerView;

        setSupportActionBar(mActivityMainBinding.toolbar);
//        ButterKnife.bind(this);

        initRecyclerView();
        initViewModel();

        mActivityMainBinding.noteFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), EditorActivity.class);
                startActivity(intent);
            }
        });

        //getting data from the viewmodel rather than getting it directly from the DB
//        notesData.addAll(mViewModel.mNotes);
//        for (NoteEntity note :
//                notesData) {
//            Log.i("PlainOlNotes", note.toString());
//
//        }

    }

    private void initViewModel() {

        final Observer<List<NoteEntity>> notesObserver = new Observer<List<NoteEntity>>() {
            //called automatically when data are updated
            @Override
            public void onChanged(List<NoteEntity> noteEntities) {
                notesData.clear();
                //we do it this way rather than watching noteEntites directly is because it keeps a references
                //to the collection object in the activity for the lifetime of the activity.
                notesData.addAll(noteEntities);

                if (mAdapter == null) {
                    mAdapter = new NotesAdapter(notesData, MainActivity.this);
                    mRecyclerView.setAdapter(mAdapter);
                } else {
                    mAdapter.notifyDataSetChanged();
                }

            }
        };

        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        //this is where we subscribe to the observer
        mViewModel.mNotes.observe(this, notesObserver);
    }

    private void initRecyclerView() {
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        DividerItemDecoration divider = new DividerItemDecoration(
                mRecyclerView.getContext(),
                layoutManager.getOrientation()
        );
        mRecyclerView.addItemDecoration(divider);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add_sample_data) {
            addSampleData();
            return true;
        } else if (id == R.id.action_delete_all) {
            deleteAllNotes();
            return true;
        } else if (id == R.id.action_book_search_page) {
            bookSearch();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void bookSearch() {
        Intent intent = new Intent(getApplicationContext(), BookSearch.class);
        startActivity(intent);
    }

    private void deleteAllNotes() {
        mViewModel.deleteAllNotes();
    }

    private void addSampleData() {
        mViewModel.addSampleData();
    }

//    @Override
//    public boolean onSupportNavigateUp() {
////        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
////        return NavigationUI.navigateUp(navController, appBarConfiguration)
////                || super.onSupportNavigateUp();
//    }
}