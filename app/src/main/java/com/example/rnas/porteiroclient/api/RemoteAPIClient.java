package com.example.rnas.porteiroclient.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by rnas on 20/10/2017.
 */

public class RemoteAPIClient {

    private static Retrofit retrofit = null;

    public static Retrofit getClient() {

        retrofit = new Retrofit.Builder()
                .baseUrl("http://165.227.10.77")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }

}