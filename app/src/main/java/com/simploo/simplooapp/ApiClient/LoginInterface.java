package com.simploo.simplooapp.ApiClient;

import com.simploo.simplooapp.DataModel.TokenRequest;
import com.simploo.simplooapp.DataModel.TokenResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by macbook on 2016-09-24.
 */

public interface LoginInterface {
    @Headers("Content-Type: Application/Json")
    @POST("login/")
//    Call<TokenResponse> getTokenAccess(@Header("Content-Type") String contentType, @Body TokenRequest tokenRequest);
    Call<TokenResponse> getTokenAccess(@Body TokenRequest tokenRequest);

}
