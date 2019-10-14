package com.example.MaaKaKhana;

import com.google.android.gms.maps.model.LatLng;

public class Loc {
    private LatLng latLng;
    private String add;

    public Loc() {
    }

    public LatLng getLatLng() {
        return latLng;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }

    public double getLat() {
        return latLng.latitude;
    }

    public double getLong() {
        return latLng.longitude;
    }

    public String getAdd() {
        return add;
    }

    public void setAdd(String add) {
        this.add = add;
    }

}
