package com.simploo.simplooapp.ApiClient;

import com.simploo.simplooapp.DataModel.Todo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by macbook on 2016-09-23.
 */

public interface TodoInterface {
    @GET("/tmp/todos")
    Call<Todo> all();

    @GET("/tmp/{id}/todo")
    Call<Todo> select(@Path("id") int id);
}
