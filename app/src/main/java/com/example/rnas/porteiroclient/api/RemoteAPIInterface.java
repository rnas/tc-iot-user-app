package com.example.rnas.porteiroclient.api;

import com.example.rnas.porteiroclient.model.Hash;
import com.example.rnas.porteiroclient.model.SimpleRequest;
import com.example.rnas.porteiroclient.model.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by rnas on 20/10/2017.
 */


public interface RemoteAPIInterface {

    @GET("/API/sync/{hash}")
    Call<User> syncUser(@Path("hash") String hash);

    @GET("/API/token/{token}/hash/{hash}")
    Call<SimpleRequest> syncToken(@Path("token") String token, @Path("hash") String hash);

    @GET("/API/qr/from/{hash}")
    Call<Hash> getCode(@Path("hash") String hash);

}
