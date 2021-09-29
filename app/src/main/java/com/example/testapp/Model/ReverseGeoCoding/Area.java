package com.example.testapp.Model.ReverseGeoCoding;

import com.google.gson.annotations.SerializedName;

public class Area {
    @SerializedName("name")
    private String name;

    @SerializedName("coords")
    private Coords coords;

    public String getName() {
        return name;
    }

    public Coords getCoords() {
        return coords;
    }
}
