package com.codeconsole.gmapmarker.Bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by user on 22-02-2017.
 */

public class LocationBean implements Parcelable {
    private int locationId;
    private int markerId;
    private float latitude;
    private float longitude;

    public LocationBean(int locationId, int markerId, float latitude, float longitude) {
        this.locationId = locationId;
        this.markerId = markerId;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public LocationBean() {
    }

    protected LocationBean(Parcel in) {
        locationId = in.readInt();
        markerId = in.readInt();
        latitude = in.readFloat();
        longitude = in.readFloat();
    }

    public static final Creator<LocationBean> CREATOR = new Creator<LocationBean>() {
        @Override
        public LocationBean createFromParcel(Parcel in) {
            return new LocationBean(in);
        }

        @Override
        public LocationBean[] newArray(int size) {
            return new LocationBean[size];
        }
    };

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public int getMarkerId() {
        return markerId;
    }

    public void setMarkerId(int markerId) {
        this.markerId = markerId;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(locationId);
        parcel.writeInt(markerId);
        parcel.writeFloat(latitude);
        parcel.writeFloat(longitude);
    }
}
