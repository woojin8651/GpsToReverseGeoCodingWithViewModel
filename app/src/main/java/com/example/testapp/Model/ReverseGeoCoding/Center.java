package com.example.testapp.Model.ReverseGeoCoding;

import com.google.gson.annotations.SerializedName;

public class Center {
    @SerializedName("crs")
    private String crs;

    @SerializedName("x")
    private double x;

    @SerializedName("y")
    private double y;
}
