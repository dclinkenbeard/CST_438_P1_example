package com.daclink.drew.sp22.plainolnotes.ViewModel;

import static com.daclink.drew.sp22.plainolnotes.utilities.ApiKeys.GOOGLE_API_KEY;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.daclink.drew.sp22.plainolnotes.database.BookRepository;
import com.daclink.drew.sp22.plainolnotes.model.VolumesResponse;

public class BookSearchViewModel extends AndroidViewModel {
    private BookRepository mBookRepository;
    private LiveData<VolumesResponse> mVolumesResponseLiveData;

    public BookSearchViewModel(@NonNull Application application) {
        super(application);
    }

    public void init(){
        mBookRepository = new BookRepository();
        mVolumesResponseLiveData = mBookRepository.getVolumesResponseLiveData();
    }

    public void searchVolumes(String keyword, String author){
        mBookRepository.searchVolumes(keyword,author, GOOGLE_API_KEY);
    }

    public LiveData<VolumesResponse> getVolumesResponseLiveData(){
        return mVolumesResponseLiveData;
    }
}
