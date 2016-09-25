package com.simploo.simplooapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.simploo.simplooapp.DataModel.Washroom;
import com.simploo.simplooapp.Util.PermissionUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity
        implements
        OnMapReadyCallback,
        GoogleMap.OnMarkerClickListener,
        GoogleMap.OnMyLocationButtonClickListener,
        ActivityCompat.OnRequestPermissionsResultCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {

    protected SimplooApplication apiAdapter = new SimplooApplication();

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

    GoogleApiClient mGoogleApiClient;
    private MapFragment mMapFragment;
    private GoogleMap mMap;

    Location lastLoc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        apiAdapter.init("washroom");

        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }
    }

    private void loadWashrooms() {
        apiAdapter.getAllWashrooms(new Callback<List<Washroom>>() {
            @Override
            public void onResponse(Call<List<Washroom>> call, Response<List<Washroom>> response) {
                List<Washroom> washroomList = response.body();
                for (Washroom washroom : washroomList) {
                    LatLng latLng = new LatLng(washroom.getLatitude(), washroom.getLongitude());
                    mMap.addMarker(new MarkerOptions()
                            .position(latLng)
                            .icon(BitmapDescriptorFactory.fromAsset("icons/Map-Marker.png")));
                }
            }

            @Override
            public void onFailure(Call<List<Washroom>> call, Throwable t) {
                Toast toast = Toast.makeText(getApplicationContext(), "Error fetching data from " +
                        "server.", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }

    protected void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
    }

    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        mMapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mMapFragment.getMapAsync(this);
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnMyLocationButtonClickListener(this);

        MapStyleOptions style = MapStyleOptions.loadRawResourceStyle(this, R.raw.style_map);
        mMap.setMapStyle(style);

        enableMyLocation();
        loadWashrooms();
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
            mMap.getUiSettings().setMyLocationButtonEnabled(false);
            lastLoc = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);

            mMap.animateCamera(CameraUpdateFactory.newLatLng(new LatLng(lastLoc.getLatitude(),
                    lastLoc.getLongitude())));

        }
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        return false;
    }

    /**
     * BUTTON ACTIONS
     */

    public void goToSettings(View view) {
        startActivity(new Intent(MainActivity.this, SettingsActivity.class));
    }

    public void centerCamera(View view) {
        mMap.animateCamera(CameraUpdateFactory.newLatLng(new LatLng(lastLoc.getLatitude(),
                lastLoc.getLongitude())));
    }
}
