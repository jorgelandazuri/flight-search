package com.jalch.flightsearch.common.dep.dagger;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

@Module
public class AppContextModule {

    private final Context context;

    public AppContextModule(Context aContext) {
        this.context = aContext;
    }

    @Provides
    Context provideAppContext() {
        return context;
    }

}
