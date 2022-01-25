package com.daclink.drew.sp22.plainolnotes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.daclink.drew.sp22.plainolnotes.fragments.BookSearchFragment;

public class BookSearch extends AppCompatActivity {

    public BookSearch() {
        super(R.layout.activity_book_search);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.fragment_container_view, BookSearchFragment.class, null)
                    .commit();
        }
//        setContentView(R.layout.activity_book_search);

    }
}