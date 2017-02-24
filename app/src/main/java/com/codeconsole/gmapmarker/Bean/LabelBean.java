package com.codeconsole.gmapmarker.Bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by user on 22-02-2017.
 */

public class LabelBean implements Parcelable {
    private int labelId;
    private String Name;
    private String date;

    public LabelBean(int labelId, String name, String date) {
        this.labelId = labelId;
        Name = name;
        this.date = date;
    }

    public LabelBean() {
    }

    protected LabelBean(Parcel in) {
        labelId = in.readInt();
        Name = in.readString();
        date = in.readString();
    }

    public static final Creator<LabelBean> CREATOR = new Creator<LabelBean>() {
        @Override
        public LabelBean createFromParcel(Parcel in) {
            return new LabelBean(in);
        }

        @Override
        public LabelBean[] newArray(int size) {
            return new LabelBean[size];
        }
    };

    public int getLabelId() {
        return labelId;
    }

    public void setLabelId(int labelId) {
        this.labelId = labelId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(labelId);
        parcel.writeString(Name);
        parcel.writeString(date);
    }
}
