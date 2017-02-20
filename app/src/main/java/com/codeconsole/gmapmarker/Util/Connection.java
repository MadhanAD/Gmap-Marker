package com.codeconsole.gmapmarker.Util;

import android.content.Context;
import android.location.LocationManager;

/**
 * Created by madhan on 20/2/17.
 */

public class Connection {

    private Context context;

    public Connection(Context context) {
        this.context = context;
    }

    public boolean isGpsEnabled() {
        LocationManager manager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        return manager.isProviderEnabled(LocationManager.GPS_PROVIDER)
                || manager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }
}
