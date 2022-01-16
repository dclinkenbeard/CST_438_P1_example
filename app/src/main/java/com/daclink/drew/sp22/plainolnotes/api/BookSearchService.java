package com.daclink.drew.sp22.plainolnotes.api;

import com.daclink.drew.sp22.plainolnotes.model.VolumesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface BookSearchService {

    @GET("/books/v1/volumes")
    Call<VolumesResponse> searchVolumes(
            @Query("q") String query,
            @Query("inauthor") String author,
            @Query("key") String apiKey
    );

}
