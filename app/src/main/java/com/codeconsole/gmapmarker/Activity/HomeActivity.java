package com.codeconsole.gmapmarker.Activity;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.codeconsole.gmapmarker.Listener.MyLocationListener;
import com.codeconsole.gmapmarker.Listener.OnLocationUpdateListener;
import com.codeconsole.gmapmarker.MarkerDialog;
import com.codeconsole.gmapmarker.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    NavigationView navigationView;
    FloatingActionButton fab;

    GoogleApiClient googleApiClient;
    GoogleMap googleMap;
    MarkerDialog markerDialog;
    MyLocationListener locationListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initViews();
        initActionListener();

        initMap();
    }

    @SuppressWarnings("deprecation")
    private void initViews() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);

        markerDialog = new MarkerDialog(HomeActivity.this);

    }

    @Override
    protected void onStart() {
        googleApiClient.connect();
        super.onStart();
    }

    @Override
    protected void onStop() {
        if (googleApiClient.isConnected()) {
            googleApiClient.disconnect();
        }
        super.onStop();
    }

    private void initActionListener() {
        navigationView.setNavigationItemSelectedListener(this);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(HomeActivity.this, "Searching for location", Toast.LENGTH_SHORT).show();
                locationListener = new MyLocationListener(HomeActivity.this, onLocationUpdateListener, true);
            }
        });
    }

    private void initMap() {
        googleApiClient = new GoogleApiClient.Builder(HomeActivity.this)
                .addConnectionCallbacks(connectionCallbacks)
                .addOnConnectionFailedListener(onConnectionFailedListener)
                .addApi(LocationServices.API)
                .build();


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.home_map_fragment);

        mapFragment.getMapAsync(onMapReadyCallback);
    }

    OnMapReadyCallback onMapReadyCallback = new OnMapReadyCallback() {
        @Override
        public void onMapReady(GoogleMap map) {
            if (googleMap == null) {
                googleMap = map;
                googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
            }
        }
    };


    GoogleApiClient.ConnectionCallbacks connectionCallbacks = new GoogleApiClient.ConnectionCallbacks() {
        @Override
        public void onConnected(@Nullable Bundle bundle) {

        }

        @Override
        public void onConnectionSuspended(int i) {

        }
    };

    GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener = new
            GoogleApiClient.OnConnectionFailedListener() {
                @Override
                public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

                }
            };

    OnLocationUpdateListener onLocationUpdateListener = new OnLocationUpdateListener() {
        @Override
        public void onUpdate(Object model, MyLocationListener.LUP type) {
            switch (type) {
                case LOCATION_CHANGED:
                    markCurrentLocation((Location) model);
                    break;
            }
        }
    };


    //marker current location
    private void markCurrentLocation(Location location) {
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        if (googleMap != null) {
            //valid google map
            googleMap.addMarker(new MarkerOptions()
                    .position(latLng)
                    .draggable(true));
            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(latLng)
                    .zoom(16f)
                    .tilt(0.0f)
                    .bearing(0.0f)
                    .build();
            googleMap.setOnMarkerClickListener(onMarkerClickListener);

            googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        }
    }

    GoogleMap.OnMarkerDragListener onMarkerDragListener = new GoogleMap.OnMarkerDragListener() {
        @Override
        public void onMarkerDragStart(Marker marker) {

        }

        @Override
        public void onMarkerDrag(Marker marker) {

        }

        @Override
        public void onMarkerDragEnd(Marker marker) {
            markerDialog.setMarker(marker);
            markerDialog.show();
        }
    };

    GoogleMap.OnMarkerClickListener onMarkerClickListener = new GoogleMap.OnMarkerClickListener() {
        @Override
        public boolean onMarkerClick(Marker marker) {
            markerDialog.setMarker(marker);
            markerDialog.show();
            return true;
        }
    };


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add) {
            Toast.makeText(HomeActivity.this, "Searching for location", Toast.LENGTH_SHORT).show();
            locationListener = new MyLocationListener(HomeActivity.this, onLocationUpdateListener, true);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        switch (item.getItemId()) {
            case R.id.nav_my_marker:
                startActivity(new Intent(HomeActivity.this, MyMarkerActivity.class));
                break;
            case R.id.nav_my_label:
                break;
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
