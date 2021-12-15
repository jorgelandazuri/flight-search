package com.jalch.flightsearch.common.dep.dagger;

import com.jalch.flightsearch.view.FlightsLivePricesFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppContextModule.class, FlightLivePricesAPIServiceNetworkModule.class, RxModule.class, UseCaseModule.class, FlightLivePricesAPIServiceModule.class})
public interface AppDependencies {
    void inject(FlightsLivePricesFragment livePricesFragment);
}
