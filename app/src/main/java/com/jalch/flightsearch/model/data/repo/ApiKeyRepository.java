package com.jalch.flightsearch.model.data.repo;

import io.reactivex.Single;

public class ApiKeyRepository implements DataRepository<Single<String>> {

    // Get an API key at: https://www.partners.skyscanner.net/affiliates/travel-apis
    static final String SKYSCANNER_API_KEY = "prtl6749387986743898559646983194";

    @Override
    public Single<String> load() {
        return Single.just(SKYSCANNER_API_KEY);
    }
}
