package com.example.testapp.Model.ReverseGeoCoding;

import com.google.gson.annotations.SerializedName;

public class Result {
    @SerializedName("region")
    Region region;

    public Region getRegion() {
        return region;
    }
}
