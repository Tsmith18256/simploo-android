package com.simploo.simplooapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.simploo.simplooapp.ApiClient.LoginInterface;
import com.simploo.simplooapp.DataModel.ServerToken;
import com.simploo.simplooapp.DataModel.TokenRequest;
import com.simploo.simplooapp.DataModel.TokenResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SettingsActivity extends AppCompatActivity {
    private LoginButton loginButton;
    private CallbackManager callbackManager;
    private AccessTokenTracker fbLogoutTracker;
    protected SimplooApplication apiAdapter = new SimplooApplication();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        apiAdapter.init("login");

        FacebookSdk.sdkInitialize(getApplicationContext());

        setContentView(R.layout.activity_settings);

        callbackManager = CallbackManager.Factory.create();
        loginButton = (LoginButton) findViewById(R.id.fbLogin);
        List<String> fbPermissions = new ArrayList<>();
        fbPermissions.add("email");
        loginButton.setReadPermissions(fbPermissions);

        fbLogoutTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
                if(currentAccessToken == null){
                    Log.d("FB", "User Logged Out.");
                    Toast.makeText(SettingsActivity.this, "You've been logged out.", Toast.LENGTH_SHORT).show();
                    ServerToken.serverToken = null;
                    finish();
                }
            }
        };

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                //Pass Facebook access token to server to get a server access token
                String fbToken = loginResult.getAccessToken().getToken();

                final TokenRequest tokenRequest = new TokenRequest();

                tokenRequest.setAccess_token(fbToken);
                tokenRequest.setSocial_network("facebook");

                apiAdapter.getTokenResponse(tokenRequest, new Callback<TokenResponse>() {
                    @Override
                    public void onResponse(Call<TokenResponse> call, Response<TokenResponse> response) {
                        int statusCode = response.code();

                        TokenResponse tokenResponse = response.body();

                        ServerToken.serverToken =  tokenResponse.getAccess_token();
                        finish();
                    }

                    @Override
                    public void onFailure(Call<TokenResponse> call, Throwable t) {
                        Log.e("Token Error: ", t.getMessage());
                    }
                });

            }

            @Override
            public void onCancel() {
                //TODO: Possible indication that user has cancelled
            }

            @Override
            public void onError(FacebookException e) {
                Log.e("Facebook Login Error: ", e.getMessage());
                Toast.makeText(SettingsActivity.this, "Oh no! Something has gone wrong!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
