package com.simploo.simplooapp;

import android.app.Application;

import com.simploo.simplooapp.ApiClient.WashroomInterface;
import com.simploo.simplooapp.DataModel.Washroom;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by user on 16-09-24.
 */
public class SimplooApplication extends Application {

    private static final String BASE_URL = "http://10.10.39.35:5000";
    private Retrofit rf;

    private WashroomInterface washroomAPI;

    @Override
    public void onCreate() {
        super.onCreate();

        // Initialize the singletons so their instances
        // are bound to the application process.
        init();
    }

    protected void init() {
        rf = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        washroomAPI = rf.create(WashroomInterface.class);
    }

    public void getAllWashrooms(Callback<List<Washroom>> cb) {
        Call<List<Washroom>> call = washroomAPI.allWashrooms();
        call.enqueue(cb);
    }
}
