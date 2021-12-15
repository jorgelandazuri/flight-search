package com.jalch.flightsearch.common.dep.dagger;

import com.jalch.flightsearch.model.data.repo.ApiKeyRepository;
import com.jalch.flightsearch.model.data.repo.liveprices.FlightsLivePricesRepository;
import com.jalch.flightsearch.model.data.repo.liveprices.session.FlightsLivePricesSessionRepository;
import com.jalch.flightsearch.model.data.repo.liveprices.session.FlightsLivePricesSessionResponseProcessor;
import com.jalch.flightsearch.model.data.service.FlightLivePricesAPIService;
import com.jalch.flightsearch.model.domain.usecase.GetFlightsLivePricesUseCase;

import dagger.Module;
import dagger.Provides;

@Module
public class UseCaseModule {

    @Provides
    GetFlightsLivePricesUseCase provideUseCase(FlightLivePricesAPIService service) {
        FlightsLivePricesSessionRepository sessionRepository = new FlightsLivePricesSessionRepository(service, new FlightsLivePricesSessionResponseProcessor());
        return new GetFlightsLivePricesUseCase(new ApiKeyRepository(), sessionRepository, new FlightsLivePricesRepository(service));
    }

}
