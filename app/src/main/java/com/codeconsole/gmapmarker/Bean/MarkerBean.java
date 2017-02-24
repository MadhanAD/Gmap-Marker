package com.codeconsole.gmapmarker.Bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by user on 22-02-2017.
 */

public class MarkerBean implements Parcelable {
    private int id;
    private int labelId;
    private float alpha;
    private float anchorU;
    private float anchorV;
    private boolean isDraggable;
    private boolean flat;
    private float infoWindowAnchor;
    private float rotation;
    private String snippet;
    private String title;
    private boolean isVisible;
    private float zIndex;
    //private int locationId;
    private String iconPath;
    private String latitude;
    private String longitude;

    public MarkerBean(int id, int labelId, float alpha, float anchorU, float anchorV, boolean isDraggable,
                      boolean flat, float infoWindowAnchor, float rotation, String snippet,
                      String title, boolean isVisible, float zIndex/*, int locationId*/, String iconPath,
                      String latitude, String longitude) {
        this.id = id;
        this.labelId = labelId;
        this.alpha = alpha;
        this.anchorU = anchorU;
        this.anchorV = anchorV;
        this.isDraggable = isDraggable;
        this.flat = flat;
        this.infoWindowAnchor = infoWindowAnchor;
        this.rotation = rotation;
        this.snippet = snippet;
        this.title = title;
        this.isVisible = isVisible;
        this.zIndex = zIndex;
        //this.locationId = locationId;
        this.iconPath = iconPath;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public MarkerBean() {
    }

    protected MarkerBean(Parcel in) {
        id = in.readInt();
        labelId = in.readInt();
        alpha = in.readFloat();
        anchorU = in.readFloat();
        anchorV = in.readFloat();
        isDraggable = in.readByte() != 0;
        flat = in.readByte() != 0;
        infoWindowAnchor = in.readFloat();
        rotation = in.readFloat();
        snippet = in.readString();
        title = in.readString();
        isVisible = in.readByte() != 0;
        zIndex = in.readFloat();
        //locationId = in.readInt();
        iconPath = in.readString();
        latitude = in.readString();
        longitude = in.readString();
    }

    public static final Creator<MarkerBean> CREATOR = new Creator<MarkerBean>() {
        @Override
        public MarkerBean createFromParcel(Parcel in) {
            return new MarkerBean(in);
        }

        @Override
        public MarkerBean[] newArray(int size) {
            return new MarkerBean[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLabelId() {
        return labelId;
    }

    public void setLabelId(int labelId) {
        this.labelId = labelId;
    }

    public float getAlpha() {
        return alpha;
    }

    public void setAlpha(float alpha) {
        this.alpha = alpha;
    }

    public float getAnchorU() {
        return anchorU;
    }

    public void setAnchorU(float anchorU) {
        this.anchorU = anchorU;
    }

    public float getAnchorV() {
        return anchorV;
    }

    public void setAnchorV(float anchorV) {
        this.anchorV = anchorV;
    }

    public boolean isDraggable() {
        return isDraggable;
    }

    public void setDraggable(boolean draggable) {
        isDraggable = draggable;
    }

    public boolean isFlat() {
        return flat;
    }

    public void setFlat(boolean flat) {
        this.flat = flat;
    }

    public float getInfoWindowAnchor() {
        return infoWindowAnchor;
    }

    public void setInfoWindowAnchor(float infoWindowAnchor) {
        this.infoWindowAnchor = infoWindowAnchor;
    }

    public float getRotation() {
        return rotation;
    }

    public void setRotation(float rotation) {
        this.rotation = rotation;
    }

    public String getSnippet() {
        return snippet;
    }

    public void setSnippet(String snippet) {
        this.snippet = snippet;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public float getzIndex() {
        return zIndex;
    }

    public void setzIndex(float zIndex) {
        this.zIndex = zIndex;
    }

//    public int getLocationId() {
//        return locationId;
//    }

//    public void setLocationId(int locationId) {
//        this.locationId = locationId;
//    }

    public String getIconPath() {
        return iconPath;
    }

    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeInt(labelId);
        parcel.writeFloat(alpha);
        parcel.writeFloat(anchorU);
        parcel.writeFloat(anchorV);
        parcel.writeByte((byte) (isDraggable ? 1 : 0));
        parcel.writeByte((byte) (flat ? 1 : 0));
        parcel.writeFloat(infoWindowAnchor);
        parcel.writeFloat(rotation);
        parcel.writeString(snippet);
        parcel.writeString(title);
        parcel.writeByte((byte) (isVisible ? 1 : 0));
        parcel.writeFloat(zIndex);
//        parcel.writeInt(locationId);
        parcel.writeString(iconPath);
        parcel.writeString(latitude);
        parcel.writeString(longitude);
    }
}
