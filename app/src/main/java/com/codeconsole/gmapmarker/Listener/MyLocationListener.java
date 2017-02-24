package com.codeconsole.gmapmarker.Listener;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

/**
 * Created by user on 22-02-2017.
 */

public class MyLocationListener implements LocationListener {

    private static final String TAG = "MyLocationListener";
    private OnLocationUpdateListener listener;
    private Context context;
    private int DURATION = 1000 * 60 * 1;
    private int DISTANCE = 0;

    @SuppressWarnings("MissingPermission")
    public MyLocationListener(Context context, OnLocationUpdateListener listener, boolean isSingle) {
        this.listener = listener;
        this.context = context;

        LocationManager manager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

        if (isSingle) {
            manager.requestSingleUpdate(LocationManager.GPS_PROVIDER, this, null);
        } else {
            manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, DURATION, DISTANCE, this);
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        listener.onUpdate(location, LUP.LOCATION_CHANGED);
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {
    }

    @Override
    public void onProviderEnabled(String s) {
        listener.onUpdate(s, LUP.PROVIDER_ENABLED);
    }

    @Override
    public void onProviderDisabled(String s) {
        listener.onUpdate(s, LUP.PROVIDER_DISABLED);
    }

    public enum LUP {
        LOCATION_CHANGED, PROVIDER_ENABLED, PROVIDER_DISABLED
    }
}
