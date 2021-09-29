package com.example.testapp.Model.ReverseGeoCoding;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DTO {

    @SerializedName("status")
    private Status status;

    @SerializedName("results")
    private ArrayList<Result> results;

    @RequiresApi(api = Build.VERSION_CODES.N)
    public ArrayList<String> getAddr(){
        ArrayList<String> ret = new ArrayList();
        results.stream().forEach(it->{
            StringBuilder sb = new StringBuilder();
            sb.append(it.getRegion().getArea1().getName()).append(" ");
            sb.append(it.getRegion().getArea2().getName()).append(" ");
            sb.append(it.getRegion().getArea3().getName()).append(" ");
            sb.append(it.getRegion().getArea4().getName());
            ret.add(sb.toString());
        });
        return ret;
    }

    public Status getStatus() {
        return status;
    }

    public ArrayList<Result> getResults() {
        return results;
    }
}
