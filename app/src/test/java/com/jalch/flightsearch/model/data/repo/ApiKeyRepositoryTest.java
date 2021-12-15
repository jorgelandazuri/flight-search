package com.jalch.flightsearch.model.data.repo;

import org.junit.Test;

import io.reactivex.Single;

import static com.jalch.flightsearch.model.data.repo.ApiKeyRepository.SKYSCANNER_API_KEY;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ApiKeyRepositoryTest {

    DataRepository<Single<String>> underTest;

    private Single<String> loadedApiKey;

    @Test
    public void provides_api_key() throws Exception {
        givenAnApiKeyRepository();

        whenItLoadsTheApiKey();

        thenTheExpectedStaticKeyIsLoaded();
    }

    private void givenAnApiKeyRepository() {
        underTest = new ApiKeyRepository();
    }

    private void whenItLoadsTheApiKey() {
        loadedApiKey = underTest.load();
    }

    private void thenTheExpectedStaticKeyIsLoaded() {
        loadedApiKey.test().assertNoErrors().assertValue(SKYSCANNER_API_KEY);
    }

}