# London to NY flight search Android app


<p align="center">
<img src="https://github.com/jorgelandazuri/flight-search/blob/master/app/flight_search.gif" width="25%" height="25%"/>
</p>


## Implementation details

- Flight search results using Skyscanner 'Live Prices' polling API  (https://skyscanner.github.io/slate/#flights-live-prices)
- You can request an API key at https://www.partners.skyscanner.net/affiliates/travel-apis or alternatevely use 'FlightLivePricesAPIStubService' instead of 'FlightLivePricesAPIService' class in 'FlightLivePricesAPIServiceModule' Dagger module. 
- RxJava2 for polling data from the API.
- Dagger2 for dependency injection & modularizing main objects creation.
- Butterknife for view binding.
- Picasso for image loading.
- Retrofit2 http client & Gson for Json parsing.
- Unit testing with Mockito & JUnit.



