package com.example.retrofit.interfaces;

import com.example.retrofit.model.ModelPhoto;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface GetDataServicePhotos {
    @GET("/photos")
    Call<List<ModelPhoto>> getAllPhotos();
}
