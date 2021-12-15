package com.jalch.flightsearch.model.domain.usecase;

import com.jalch.flightsearch.model.ModelVP;
import com.jalch.flightsearch.model.data.repo.DataParametrizedRepository;
import com.jalch.flightsearch.model.data.repo.DataRepository;
import com.jalch.flightsearch.model.data.service.FlightsLivePricesCreateSessionParams;
import com.jalch.flightsearch.model.data.service.FlightsLivePricesPollParams;
import com.jalch.flightsearch.model.domain.pojo.FlightsLivePrices;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.Observable;

public class GetFlightsLivePricesUseCase implements ModelVP<Observable<FlightsLivePrices>, FlightsLivePricesCreateSessionParams> {

    private final DataRepository<Single<String>> apiKeyRepository;
    private final DataParametrizedRepository<Single<String>, FlightsLivePricesCreateSessionParams> sessionRepository;
    private final DataParametrizedRepository<Observable<FlightsLivePrices>, FlightsLivePricesPollParams> pollRepository;

    public GetFlightsLivePricesUseCase(DataRepository<Single<String>> apiKeyRepository,
                                       DataParametrizedRepository<Single<String>, FlightsLivePricesCreateSessionParams> sessionRepository,
                                       DataParametrizedRepository<Observable<FlightsLivePrices>, FlightsLivePricesPollParams> pollRepository) {
        this.apiKeyRepository = apiKeyRepository;
        this.sessionRepository = sessionRepository;
        this.pollRepository = pollRepository;
    }

    @Override
    public Observable<FlightsLivePrices> execute(FlightsLivePricesCreateSessionParams createSessionParams) {
        String apiKey = apiKeyRepository.load().blockingGet();
        createSessionParams.setApiKey(apiKey);
        return  sessionRepository.load(createSessionParams)
                                .subscribeOn(Schedulers.io())
                                .flatMapObservable(sessionLocation -> loadFlightLivePrices(apiKey, sessionLocation));
    }

    private Observable<FlightsLivePrices> loadFlightLivePrices(String apiKey, String sessionLocation) {
        return pollRepository.load(new FlightsLivePricesPollParams(apiKey, sessionLocation));
    }

}
