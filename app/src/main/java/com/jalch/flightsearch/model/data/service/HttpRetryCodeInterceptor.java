package com.jalch.flightsearch.model.data.service;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import static okhttp3.Protocol.HTTP_1_0;

public class HttpRetryCodeInterceptor implements Interceptor {

    static final int RETRY_RESULT_CODE = 204;

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);
        if (response.code() == RETRY_RESULT_CODE) {
            Request newRequest = chain.request().newBuilder().build();
            return chain.proceed(newRequest);
        } else {
            return response;
        }
    }
}