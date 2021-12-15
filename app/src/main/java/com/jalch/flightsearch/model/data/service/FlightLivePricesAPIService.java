package com.jalch.flightsearch.model.data.service;

import com.jalch.flightsearch.model.domain.pojo.FlightsLivePrices;
import com.jalch.flightsearch.model.domain.pojo.SessionResponseBody;

import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import io.reactivex.Observable;

public interface FlightLivePricesAPIService {

    @Headers("Accept: application/json")
    @FormUrlEncoded
    @POST("v1.0")
    Single<Response<SessionResponseBody>> createSession(@Field("country") String country,
                                                        @Field("currency") String currency,
                                                        @Field("locale") String locale,
                                                        @Field("originPlace") String originPlace,
                                                        @Field("destinationPlace") String destinationPlace,
                                                        @Field("outboundDate") String outboundDate,
                                                        @Field("inboundDate") String inboundDate,
                                                        @Field("cabinClass") String cabinClass,
                                                        @Field("adults") String adults,
                                                        @Field("locationSchema") String locationSchema,
                                                        @Field("apiKey") String apiKey);

    @Headers("Accept: application/json")
    @GET("{sessionLocation}")
    Observable<FlightsLivePrices> getFlightsLivePrices(@Path("sessionLocation") String session, @Query("apiKey") String apiKey);

}
