package com.example.testapp.Model.ReverseGeoCoding;

import com.google.gson.annotations.SerializedName;

public class Region {
    @SerializedName("area1")
    private Area area1;

    @SerializedName("area2")
    private Area area2;

    @SerializedName("area3")
    private Area area3;

    @SerializedName("area4")
    private Area area4;

    public Area getArea1() {
        return area1;
    }

    public Area getArea2() {
        return area2;
    }

    public Area getArea3() {
        return area3;
    }

    public Area getArea4() {
        return area4;
    }
}
