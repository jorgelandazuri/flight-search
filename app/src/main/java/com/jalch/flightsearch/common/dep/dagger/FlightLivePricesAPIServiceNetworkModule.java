package com.jalch.flightsearch.common.dep.dagger;

import android.content.Context;

import com.jalch.flightsearch.model.data.service.HttpRetryCodeInterceptor;

import java.io.File;
import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class FlightLivePricesAPIServiceNetworkModule {

    @Provides
    OkHttpClient provideHttpClient(HttpLoggingInterceptor logger, HttpRetryCodeInterceptor retryInterceptor, Cache cache) {

        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        builder.addInterceptor(logger);
        builder.connectTimeout(15, TimeUnit.SECONDS);
        builder.readTimeout(45, TimeUnit.SECONDS);
        builder.addInterceptor(retryInterceptor);
        builder.cache(cache);
        return builder.build();
    }

    @Provides
    HttpRetryCodeInterceptor provideHttpRetryCodeInterceptor() {
        HttpRetryCodeInterceptor httpLoggingInterceptor = new HttpRetryCodeInterceptor();
        return httpLoggingInterceptor;
    }

    @Provides
    HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return httpLoggingInterceptor;
    }

    @Provides
    Cache provideCache(File file) {
        return new Cache(file, 10 * 10 * 1000);
    }

    @Provides
    File provideCacheFile(Context context) {
        return context.getFilesDir();
    }

}

