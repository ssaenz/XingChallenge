package com.ssaenz.xingchallenge.data.network;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Factory to create endpoints
 *
 */

public class EndpointFactory<T> {

    private static Map<String, Retrofit> retrofits= new HashMap<>();

    public T createEndpoint (Class<T> endpointClass, String baseUrl) {
        Retrofit retrofit = retrofits.get(baseUrl);
        if (retrofit == null) {
            retrofit = createRetrofit(baseUrl);
            retrofits.put(baseUrl, retrofit);
        }
        return retrofit.create(endpointClass);
    }

    private Retrofit createRetrofit (String baseUrl) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }
}
