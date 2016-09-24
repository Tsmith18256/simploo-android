package com.simploo.simplooapp;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.simploo.simplooapp.ApiClient.PingInterface;
import com.simploo.simplooapp.ApiClient.WashroomInterface;
import com.simploo.simplooapp.DataModel.Ping;
import com.simploo.simplooapp.DataModel.Washroom;
import com.simploo.simplooapp.Util.PermissionUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity
        implements
        OnMapReadyCallback,
        GoogleMap.OnMyLocationButtonClickListener,
        ActivityCompat.OnRequestPermissionsResultCallback {

    /**
     * Ping localhost test
     */
    private static final String ENDPOINT_URL = "http://10.10.39.35:5000";
    private TextView washroomRslt;
    private WashroomInterface getWashroom;

    /**
     * Request code for location permission request.
     *
     * @see #onRequestPermissionsResult(int, String[], int[])
     */
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;

    /**
     * Flag indicating whether a requested permission has been denied after returning in
     * {@link #onRequestPermissionsResult(int, String[], int[])}.
     */
    private boolean mPermissionDenied = false;

    private MapFragment mMapFragment;
    private GoogleMap mMap;

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.setOnMyLocationButtonClickListener(this);
        enableMyLocation();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create Retrofit instance to ping localhost
        Retrofit rf = new Retrofit.Builder()
                .baseUrl(ENDPOINT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        getWashroom = rf.create(WashroomInterface.class);

        //Connect button code to UI
        Button washroomBtn = (Button) findViewById(R.id.washroomButton);
        washroomBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadWashrooms();
            }
        });

        //Connect textview code to UI
        washroomRslt = (TextView) findViewById(R.id.washroomResult);

        mMapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mMapFragment.getMapAsync(this);
    }

    private void loadWashrooms() {
        Call<List<Washroom>> call = getWashroom.allWashrooms();
        call.enqueue(new Callback<List<Washroom>>() {
            @Override
            public void onResponse(Call<List<Washroom>> call, Response<List<Washroom>> response) {
                List<Washroom> results = response.body();
                displayWashrooms(results);
            }

            @Override
            public void onFailure(Call<List<Washroom>> call, Throwable t) {
                System.out.println("I fucked up!!: " + t.getMessage());

                Context context = getApplicationContext();
                CharSequence text = "This is a toast to tell you I fucked up!";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        });
    }

    private void displayWashrooms(List<Washroom> response) {
        if(response != null){
            System.out.println("here");
            List<Washroom> washrooms = response;

            String tmp = "";
            for(Washroom washroom: washrooms){
                tmp += washroom.getId() + " | " + washroom.getName();
            }

            washroomRslt.setText(tmp);

        } else {
            washroomRslt.setText("Error getting washrooms");
        }
    }

    @Override
    public boolean onMyLocationButtonClick() {
        // Return false so that we don't consume the event and the default behavior still occurs
        // (the camera animates to the user's current position).
        return false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {

        if (requestCode != LOCATION_PERMISSION_REQUEST_CODE) {
            return;
        }

        if (PermissionUtils.isPermissionGranted(permissions, grantResults,
                Manifest.permission.ACCESS_FINE_LOCATION)) {
            // Enable the my location layer if the permission has been granted.
            enableMyLocation();
        } else {
            // Display the missing permission error dialog when the fragments resume.
            mPermissionDenied = true;
        }
    }

    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();
        if (mPermissionDenied) {
            // Permission was not granted, display error dialog.
            showMissingPermissionError();
            mPermissionDenied = false;
        }
    }

    /**
     * Displays a dialog with error message explaining that the location permission is missing.
     */
    private void showMissingPermissionError() {
        PermissionUtils.PermissionDeniedDialog
                .newInstance(true).show(getSupportFragmentManager(), "dialog");
    }

    /**
     * Enables the My Location layer if the fine location permission has been granted.
     */
    private void enableMyLocation() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission to access the location is missing.
            PermissionUtils.requestPermission(this, LOCATION_PERMISSION_REQUEST_CODE,
                    Manifest.permission.ACCESS_FINE_LOCATION, true);
        } else if (mMap != null) {
            // Access to the location has been granted to the app.
            mMap.setMyLocationEnabled(true);
        }
    }
}
