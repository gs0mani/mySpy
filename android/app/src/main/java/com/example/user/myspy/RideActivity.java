package com.example.user.myspy;

/**
 * Created by user on 03-02-2018.
 */
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.uber.sdk.android.core.UberSdk;
import com.uber.sdk.android.rides.RideParameters;
import com.uber.sdk.core.auth.Scope;
import com.uber.sdk.rides.client.SessionConfiguration;

import java.util.Arrays;
import com.uber.sdk.android.rides.RideRequestButton;
import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import es.situm.sdk.SitumSdk;
import es.situm.sdk.error.Error;
import es.situm.sdk.location.LocationListener;
import es.situm.sdk.location.LocationManager;
import es.situm.sdk.location.LocationRequest;
import es.situm.sdk.location.LocationStatus;
import es.situm.sdk.model.location.Location;

public class RideActivity extends AppCompatActivity implements OnMapReadyCallback {

    private final String TAG = getClass().getSimpleName();
    private final int ACCESS_FINE_LOCATION_REQUEST_CODE = 3096;
    private GoogleMap googleMap;
    private LocationManager locationManager;
    private LocationListener locationListener;
    private Circle circle;
    private ProgressBar progressBar;
    private boolean first_time=true;
    LatLng pre=new LatLng(9999,9999);

    protected void onCreate(Bundle savedInstanceState) {

       // getActionBar().setDisplayHomeAsUpEnabled(true);
        SessionConfiguration config = new SessionConfiguration.Builder()
                // mandatory
                .setClientId("UIZ9ZFdcwCddc2Lom3Tx8FsuuFJCDEGS")
                        // required for enhanced button features
                .setServerToken("AnJLTI_BpCaGzAvxyikikW7AiDzJHXY21yEguYvG")
                        // required for implicit grant authentication
                .setRedirectUri("https://localhost")
                        // required scope for Ride Request Widget features
                .setScopes(Arrays.asList(Scope.RIDE_WIDGETS))
                        // optional: set sandbox as operating environment
                .setEnvironment(SessionConfiguration.Environment.SANDBOX)
                .build();
        UberSdk.initialize(config);


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ride);

        RideRequestButton requestButton = new RideRequestButton(this);
        // get your layout, for instance:
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.uberlayout);
        layout.addView(requestButton);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        setup();

       /* googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.getUiSettings().setZoomGesturesEnabled(true);
        googleMap.getUiSettings().setScrollGesturesEnabled(true);
        googleMap.getUiSettings().setTiltGesturesEnabled(true);*/

        RideParameters rideParams = new RideParameters.Builder()
                // Optional product_id from /v1/products endpoint (e.g. UberX). If not provided, most cost-efficient product will be used
                .setProductId("a1111c8c-c720-46c3-8534-2fcdd730040d")
                        // Required for price estimates; lat (Double), lng (Double), nickname (String), formatted address (String) of dropoff location
                .setDropoffLocation(
                        25.2618931, 82.9943855, "New Lecture Theater Complex", "IIT BHU, Varanasi")
                        // Required for pickup estimates; lat (Double), lng (Double), nickname (String), formatted address (String) of pickup location
                .setPickupLocation(25.2618931, 82.9943855, "New Lecture Theater Complex", "IIT BHU, Varanasi")
                .build();
// set parameters for the RideRequestButton instance
        requestButton.setRideParameters(rideParams);

    }


    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy: ");
        stopLocation();
        super.onDestroy();
    }


    @Override
    public void onBackPressed(){
        finish();
        Intent intent = new Intent(RideActivity.this, HomeActivity.class);
        startActivity(intent);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        this.googleMap.getUiSettings().setAllGesturesEnabled(true);
       /* this.googleMap.getUiSettings().setMapToolbarEnabled(false);
        this.googleMap.getUiSettings().setMyLocationButtonEnabled(true);
        this.googleMap.getUiSettings().setZoomControlsEnabled(true);
        this.googleMap.getUiSettings().setZoomGesturesEnabled(true);
        this.googleMap.getUiSettings().setScrollGesturesEnabled(true);
        this.googleMap.getUiSettings().setTiltGesturesEnabled(true);*/
        checkPermissions();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
//                NavUtils.navigateUpFromSameTask(this);
                finish();
                Intent intent = new Intent(RideActivity.this, HomeActivity.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setup() {
        locationManager = SitumSdk.locationManager();
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
    }


    private void checkPermissions() {
        if (ContextCompat.checkSelfPermission(RideActivity.this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(RideActivity.this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                showPermissionsNeeded();
            } else {
                // No explanation needed, we can request the permission.
                requestPermission();
                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }else{
            startLocation();
        }
    }


    private void requestPermission(){
        ActivityCompat.requestPermissions(RideActivity.this,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                ACCESS_FINE_LOCATION_REQUEST_CODE);
    }


    private void showPermissionsNeeded(){
        Snackbar.make(findViewById(android.R.id.content),
                "Needed location permission to enable service",
                Snackbar.LENGTH_INDEFINITE)
                .setAction("Open", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        requestPermission();
                    }
                }).show();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[],
                                           int[] grantResults) {
        switch (requestCode) {
            case ACCESS_FINE_LOCATION_REQUEST_CODE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    startLocation();
                } else {
                    showPermissionsNeeded();
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
            }
            // other 'case' lines to check for other
            // permissions this app might request
        }
    }


    private void startLocation() {
        if (locationManager.isRunning()) {
            return;
        }
        locationListener = new LocationListener(){

            @Override
            public void onLocationChanged(@NonNull Location location) {
                progressBar.setVisibility(View.GONE);
                LatLng latLng = new LatLng(location.getCoordinate().getLatitude(),
                        location.getCoordinate().getLongitude());
                if (circle == null) {
                    circle = googleMap.addCircle(new CircleOptions()
                            .center(latLng)
                            .radius(1d)
                            .strokeWidth(0f)
                            .fillColor(Color.BLUE));
                }else{
                    circle.setCenter(latLng);
                }
                if(pre==latLng)
                    first_time=true;
                pre=latLng;
                if(first_time) {
                    googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 20));
                    first_time=false;
                }
            }

            @Override
            public void onStatusChanged(@NonNull LocationStatus locationStatus) {

            }

            @Override
            public void onError(@NonNull Error error) {
                Toast.makeText(RideActivity.this, error.getMessage() , Toast.LENGTH_LONG).show();
            }
        };
        LocationRequest locationRequest = new LocationRequest.Builder()
                .useWifi(true)
                .useBle(true)
                .useForegroundService(true)
                .build();
        locationManager.requestLocationUpdates(locationRequest, locationListener);
    }


    private void stopLocation() {
        if (!locationManager.isRunning()) {
            return;
        }
        locationManager.removeUpdates(locationListener);
    }
}

