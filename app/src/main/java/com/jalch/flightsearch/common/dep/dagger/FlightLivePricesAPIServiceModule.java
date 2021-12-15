package com.jalch.flightsearch.common.dep.dagger;

import com.jalch.flightsearch.common.mock.FlightLivePricesAPIStubService;
import com.jalch.flightsearch.model.data.service.FlightLivePricesAPIService;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class FlightLivePricesAPIServiceModule {

    public static final String BASE_URL = "http://partners.api.skyscanner.net/apiservices/pricing/";

    @Provides
    FlightLivePricesAPIService provideApiService(OkHttpClient client) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        return retrofit.create(FlightLivePricesAPIService.class);
        //Uncomment this and comment the lines above to use the a mocked response.
        //return new FlightLivePricesAPIStubService();
    }


}
