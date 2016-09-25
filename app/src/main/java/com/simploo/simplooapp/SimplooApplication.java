package com.simploo.simplooapp;

import android.app.Application;

import com.simploo.simplooapp.ApiClient.LoginInterface;
import com.simploo.simplooapp.ApiClient.WashroomInterface;
import com.simploo.simplooapp.DataModel.TokenRequest;
import com.simploo.simplooapp.DataModel.TokenResponse;
import com.simploo.simplooapp.DataModel.Washroom;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.Retrofit;

/**
 * Created by user on 16-09-24.
 */
public class SimplooApplication {

    private static final String BASE_URL = "http://10.10.24.42:5000/api/";
    private Retrofit rf;

    private WashroomInterface washroomAPI;
    private LoginInterface loginAPI;


    protected void init(String retrofitBindInterface) {
        rf = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        switch (retrofitBindInterface){
            case "washroom":
                washroomAPI = rf.create(WashroomInterface.class);
                break;
            case "login":
                loginAPI = rf.create(LoginInterface.class);
                break;
        }
    }

    public void getAllWashrooms(Callback<List<Washroom>> cb) {
        Call<List<Washroom>> call = washroomAPI.allWashrooms();
        call.enqueue(cb);
    }

    public void getTokenResponse(TokenRequest tokenRequest, Callback<TokenResponse> cb) {
//        Call<TokenResponse> call = loginAPI.getTokenAccess("application/json", tokenRequest);
        Call<TokenResponse> call = loginAPI.getTokenAccess(tokenRequest);
        call.enqueue(cb);
    }
}
