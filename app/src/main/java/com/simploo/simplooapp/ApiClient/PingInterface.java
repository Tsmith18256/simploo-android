package com.simploo.simplooapp.ApiClient;

import com.simploo.simplooapp.DataModel.Ping;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by macbook on 2016-09-24.
 */

public interface PingInterface {
    @GET("/api")
    Call<Ping> ping();
}
