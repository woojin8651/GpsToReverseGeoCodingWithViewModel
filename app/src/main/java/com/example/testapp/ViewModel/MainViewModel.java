package com.example.testapp.ViewModel;

import android.content.Context;
import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.testapp.BuildConfig;
import com.example.testapp.Model.Coordinate;
import com.example.testapp.Model.ReverseGeoCoding.DTO;
import com.example.testapp.Util.GPSTracker;
import com.example.testapp.Util.RestApi;
import com.example.testapp.Util.RestApiClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModel extends ViewModel {

    private static final String TAG = "MainViewModel";

    private MutableLiveData<Coordinate> cord;
    private MutableLiveData<DTO> dto;

    public LiveData<DTO> getLiveDTO(){
        return dto;
    }

    public void init(){
        cord = new MutableLiveData();
        dto = new MutableLiveData();
    }

    public void updateCord(Context context){
        GPSTracker gpsTracker = new GPSTracker(context);
        cord.setValue(new Coordinate(gpsTracker.getLatitude(),gpsTracker.getLongitude()));
        convertCordToDTO();
    }

    private void convertCordToDTO(){
        double la = cord.getValue().getLa();
        double lo = cord.getValue().getLo();
        String coods = lo +","+ la;
        Log.d(TAG,"coods: "+coods);
        Call<DTO> call = RestApiClient.service.getReverseGeocoding(RestApi.DEFUALT_REQUEST,coods,RestApi.DEFAULT_SOURCECRS,RestApi.DEFAULT_ORDERS,RestApi.DEFAULT_OUPUT);
        call.enqueue(new Callback<DTO>() {
            @Override
            public void onResponse(Call<DTO> call, Response<DTO> response) {
                Log.d(TAG,"Reverse Geocoding Successed");
                Log.d(TAG,response.body().toString());
                dto.setValue(response.body());
            }
            @Override
            public void onFailure(Call<DTO> call, Throwable t) {
                Log.d(TAG,"Reverse Geocoding Failed :"+t.getMessage());
            }
        });
    }

}


