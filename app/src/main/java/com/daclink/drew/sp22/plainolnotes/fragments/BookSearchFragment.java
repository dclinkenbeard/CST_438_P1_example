package com.daclink.drew.sp22.plainolnotes.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.daclink.drew.sp22.plainolnotes.R;
import com.daclink.drew.sp22.plainolnotes.ViewModel.BookSearchViewModel;
import com.daclink.drew.sp22.plainolnotes.model.VolumesResponse;
import com.daclink.drew.sp22.plainolnotes.ui.BookSearchResultsAdapter;
import com.google.android.material.textfield.TextInputEditText;

public class BookSearchFragment extends Fragment {
    private BookSearchViewModel mViewModel;
    private BookSearchResultsAdapter adapter;

    private TextInputEditText keywordEditText;
    private TextInputEditText authorEditText;
    private Button searchButton;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        adapter = new BookSearchResultsAdapter();
        mViewModel = new ViewModelProvider(this).get(BookSearchViewModel.class);
        mViewModel.init();
        mViewModel.getVolumesResponseLiveData().observe(this, new Observer<VolumesResponse>() {
            @Override
            public void onChanged(VolumesResponse volumesResponse) {
                if (volumesResponse != null) {
                    adapter.setResults(volumesResponse.getItems());
                }
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_booksearch, container,false);

        RecyclerView recyclerView = view.findViewById(R.id.fragment_booksearch_searchResultRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        keywordEditText = view.findViewById(R.id.fragment_booksearch_keyword);
        authorEditText = view.findViewById(R.id.fragment_booksearch_author);
        searchButton = view.findViewById(R.id.fragment_booksearch_search);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performSearch();
            }
        });

        return view;
    }

    private void performSearch() {
        String keyword = keywordEditText.getText().toString();
        String author = authorEditText.getText().toString();

        mViewModel.searchVolumes(keyword,author);
    }


}
