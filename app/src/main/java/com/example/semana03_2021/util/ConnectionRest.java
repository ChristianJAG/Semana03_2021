package com.example.semana03_2021.util;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ConnectionRest {
    public static  Retrofit retrofit;
    public static final String URL="https://jsonplaceholder.typicode.com/";


    public static Retrofit getConnection() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }
}
