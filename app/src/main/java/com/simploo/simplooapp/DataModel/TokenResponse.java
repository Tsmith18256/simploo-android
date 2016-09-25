package com.simploo.simplooapp.DataModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by macbook on 2016-09-24.
 */

public class TokenResponse {
    @SerializedName("access_token")
    @Expose
    private String access_token;

    public String getAccess_token() {
        return access_token;
    }
}
