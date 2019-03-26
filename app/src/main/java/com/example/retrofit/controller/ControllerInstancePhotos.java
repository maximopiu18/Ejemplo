package com.example.retrofit.controller;


import com.example.retrofit.model.constans.Constantes;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class ControllerInstancePhotos {

    private static Retrofit retrofit;
    public static Retrofit getControllerInstancePhotos() {
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(Constantes.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}