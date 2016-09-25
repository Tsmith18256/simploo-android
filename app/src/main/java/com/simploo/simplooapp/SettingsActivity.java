package com.simploo.simplooapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.simploo.simplooapp.ApiClient.LoginInterface;
import com.simploo.simplooapp.DataModel.TokenRequest;
import com.simploo.simplooapp.DataModel.TokenResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SettingsActivity extends AppCompatActivity {
    private LoginButton loginButton;
    private CallbackManager callbackManager;
    protected SimplooApplication apiAdapter = new SimplooApplication();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        apiAdapter.init("login");

        FacebookSdk.sdkInitialize(getApplicationContext());

        setContentView(R.layout.activity_settings);

        callbackManager = CallbackManager.Factory.create();
        loginButton = (LoginButton) findViewById(R.id.fbLogin);

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code
                String fbToken = loginResult.getAccessToken().getToken();

                TokenRequest tokenRequest = new TokenRequest();

                tokenRequest.setAccess_token(fbToken);
                tokenRequest.setSocial_network("facebook");

                apiAdapter.getTokenResponse(tokenRequest, new Callback<TokenResponse>() {
                    @Override
                    public void onResponse(Call<TokenResponse> call, Response<TokenResponse> response) {
                        int statusCode = response.code();

                        TokenResponse tokenResponse = response.body();

                        System.out.println(statusCode);
                    }

                    @Override
                    public void onFailure(Call<TokenResponse> call, Throwable t) {
                        System.out.println("failed: " + t.getMessage());

                    }
                });

            }

            @Override
            public void onCancel() {
                // App code
            }

            @Override
            public void onError(FacebookException e) {
                // App code
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
