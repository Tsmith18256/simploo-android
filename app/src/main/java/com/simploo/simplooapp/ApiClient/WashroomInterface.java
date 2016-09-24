package com.simploo.simplooapp.ApiClient;

import com.simploo.simplooapp.DataModel.Washroom;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by macbook on 2016-09-24.
 */

public interface WashroomInterface {
    @GET("/api/washrooms/")
    Call<List<Washroom>> allWashrooms();

    @GET("/api/{id}/washroom/")
    Call<Washroom> select(@Path("id") long id);
}
