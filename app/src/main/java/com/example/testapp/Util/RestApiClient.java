package com.example.testapp.Util;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestApiClient {
    private static final Retrofit retrofit =  new Retrofit.Builder()
            .baseUrl(RestApi.baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static final RestApi service  = retrofit.create(RestApi.class);

}
