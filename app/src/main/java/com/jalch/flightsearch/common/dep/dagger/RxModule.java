package com.jalch.flightsearch.common.dep.dagger;

import com.jalch.flightsearch.common.rx.AppRxSchedulers;
import com.jalch.flightsearch.common.rx.RxSchedulers;

import dagger.Module;
import dagger.Provides;

@Module
public class RxModule {
    @Provides
    RxSchedulers provideRxSchedulers() {
        return new AppRxSchedulers();
    }
}
