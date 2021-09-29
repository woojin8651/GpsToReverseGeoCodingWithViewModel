package com.example.testapp.Util;

import com.example.testapp.BuildConfig;
import com.example.testapp.Model.ReverseGeoCoding.DTO;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface RestApi {
    String baseUrl = "https://naveropenapi.apigw.ntruss.com/";
    String DEFUALT_REQUEST = "coordsToaddr";
    String DEFAULT_COORDS = "0,0"; //경도, 위도 순, LONG, LATI
    String DEFAULT_SOURCECRS = "epsg:4326";
    String DEFAULT_ORDERS = "addr";
    String DEFAULT_OUPUT = "json";

    //임시로 하드코딩해놈
    @Headers({"X-NCP-APIGW-API-KEY-ID: " + BuildConfig.X_Naver_Client_Id,
            "X-NCP-APIGW-API-KEY: " + BuildConfig.X_Naver_Client_Secret})
    @GET("map-reversegeocode/v2/gc")

    Call<DTO> getReverseGeocoding(@Query(value = "request", encoded = true) String request,
                                  @Query(value = "coords", encoded = true) String coords,
                                  @Query(value = "sourcecrs", encoded = true) String sourcecrs,
                                  @Query(value = "orders", encoded = true) String orders,
                                  @Query(value = "output", encoded = true) String output);


}
