package com.jalch.flightsearch.common.mock;

import com.jalch.flightsearch.common.util.DateUtils;
import com.jalch.flightsearch.model.data.service.FlightLivePricesAPIService;
import com.jalch.flightsearch.model.domain.pojo.Agent;
import com.jalch.flightsearch.model.domain.pojo.BookingDetailsLink;
import com.jalch.flightsearch.model.domain.pojo.Carrier;
import com.jalch.flightsearch.model.domain.pojo.Currency;
import com.jalch.flightsearch.model.domain.pojo.FlightNumber;
import com.jalch.flightsearch.model.domain.pojo.FlightsLivePrices;
import com.jalch.flightsearch.model.domain.pojo.Itinerary;
import com.jalch.flightsearch.model.domain.pojo.Leg;
import com.jalch.flightsearch.model.domain.pojo.Place;
import com.jalch.flightsearch.model.domain.pojo.PricingOption;
import com.jalch.flightsearch.model.domain.pojo.Query;
import com.jalch.flightsearch.model.domain.pojo.ServiceQuery;
import com.jalch.flightsearch.model.domain.pojo.SessionResponseBody;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import io.reactivex.Observable;
import io.reactivex.Single;
import okhttp3.Headers;
import retrofit2.Response;

import static com.jalch.flightsearch.common.util.DateUtils.formatDate;
import static java.util.Arrays.asList;

public class FlightLivePricesAPIStubService implements FlightLivePricesAPIService {

    private static final String MOCK_SERVICE_QUERY = "http://mock.partners.api.skyscanner.net/apiservices/pricing/";

    @Override
    public Single<Response<SessionResponseBody>> createSession(String country, String currency, String locale, String originPlace, String destinationPlace, String outboundDate, String inboundDate, String cabinClass, String adults, String locationSchema, String apiKey) {
        SessionResponseBody mockSession = new SessionResponseBody();
        ServiceQuery serviceQuery = new ServiceQuery();
        serviceQuery.setDateTime(inboundDate);
        mockSession.setServiceQuery(serviceQuery);
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Location", MOCK_SERVICE_QUERY);
        return Single.just(Response.success(mockSession, Headers.of(headers)));
    }

    @Override
    public Observable<FlightsLivePrices> getFlightsLivePrices(String session, String apiKey) {

        FlightsLivePrices flightsLivePrices = new FlightsLivePrices();
        flightsLivePrices.setSessionKey(session);
        flightsLivePrices.setQuery(this.getQuery());
        flightsLivePrices.setStatus("UpdatesComplete");
        flightsLivePrices.setItineraries(this.getItineraries());
        flightsLivePrices.setLegs(this.getLegs());
        flightsLivePrices.setSegments(new ArrayList<>());
        flightsLivePrices.setCarriers(this.getCarriers());
        flightsLivePrices.setAgents(this.getAgents());
        flightsLivePrices.setPlaces(this.getPlaces());
        flightsLivePrices.setCurrencies(this.getCurrencies());
        flightsLivePrices.setServiceQuery(this.getServiceQuery());

        return Observable.just(flightsLivePrices).delay(2, TimeUnit.SECONDS);
    }

    private Query getQuery(){
        Query query = new Query();
        query.setCountry("GB");
        query.setCurrency("£");
        query.setLocale("en-gb");
        query.setAdults(1);
        query.setChildren(0);
        query.setInfants(0);
        query.setOriginPlace("11111");
        query.setDestinationPlace("22222");
        String dateFormat = "yyyy-MM-dd";
        DateTime nextMonday = DateUtils.getNexMonday();
        query.setOutboundDate(formatDate(nextMonday, dateFormat));
        query.setInboundDate(formatDate(nextMonday.plusDays(7), dateFormat));
        query.setLocationSchema("Default");
        query.setCabinClass("Economy");
        query.setGroupPricing(false);
        return query;
    }

    private List<Itinerary> getItineraries() {
        Itinerary first = getItinerary("1", "2", asList(getPriceOption(400.83, asList(1L)), getPriceOption(410.01, asList(2L))));
        Itinerary second = getItinerary("3", "4", asList(getPriceOption(420.01, asList(3L)), getPriceOption(430.10, asList(2L))));
        Itinerary third = getItinerary("5", "6", asList(getPriceOption(425.30, asList(2L)), getPriceOption(433.88, asList(3L))));
        Itinerary fourth = getItinerary("7", "8", asList(getPriceOption(430.14, asList(1L)), getPriceOption(438.87, asList(4L))));
        Itinerary fifth = getItinerary("9", "10", asList(getPriceOption(433.01, asList(1L)), getPriceOption(448.73, asList(5L))));
        Itinerary sixth = getItinerary("3", "4", asList(getPriceOption(443.99, asList(3L)), getPriceOption(458.70, asList(5L))));
        Itinerary seventh = getItinerary("7", "6", asList(getPriceOption(466.12, asList(4L)), getPriceOption(462.10, asList(2L))));
        Itinerary eight = getItinerary("1", "8", asList(getPriceOption(478.25, asList(2L)), getPriceOption(465.75, asList(1L))));
        Itinerary ninth = getItinerary("5", "10", asList(getPriceOption(480.05, asList(1L)), getPriceOption(476.10, asList(4L))));
        Itinerary tenth = getItinerary("9", "2", asList(getPriceOption(485.10, asList(2L)), getPriceOption(490.12, asList(3L))));

        return asList(first,second,third,fourth,fifth,sixth,seventh,eight,ninth,tenth);
    }

    private Itinerary getItinerary(String outboundLegId, String inboundLegId, List<PricingOption> pricingOptions) {
        Itinerary itinerary = new Itinerary();
        itinerary.setOutboundLegId(outboundLegId);
        itinerary.setInboundLegId(inboundLegId);
        itinerary.setPricingOptions(pricingOptions);
        itinerary.setBookingDetailsLink(this.getStubBookingDetailsLink());
        return itinerary;
    }

    private PricingOption getPriceOption(double price, List<Long> agents){
        PricingOption pricingOption = new PricingOption();
        pricingOption.setAgents(agents);
        pricingOption.setDeeplinkUrl("https://skyscanner.com");
        pricingOption.setPrice(price);
        return pricingOption;
    }
    
    private BookingDetailsLink getStubBookingDetailsLink(){
        BookingDetailsLink bookingDetailsLink = new BookingDetailsLink();
        bookingDetailsLink.setBody("");
        bookingDetailsLink.setUri("https://skyscanner.com");
        bookingDetailsLink.setMethod("GET");
        return bookingDetailsLink;
    }

    private List<Leg> getLegs() {
        DateTime departureDate = DateUtils.getNexMonday();
        DateTime returnDate = departureDate.plusDays(7);
        //Outbound
        Leg outboundOne = this.getLeg("1", 2L, 1L, departureDate.withHourOfDay(6).withMinuteOfHour(25),
                returnDate.withHourOfDay(9).withMinuteOfHour(40), 375, asList(1L),
                "Outbound", asList(this.getFlightNumber("BA2135", 1L)));
        Leg outboundTwo = this.getLeg("3", 2L, 1L, departureDate.withHourOfDay(7).withMinuteOfHour(40),
                returnDate.withHourOfDay(10).withMinuteOfHour(35), 355, asList(2L),
                "Outbound", asList(this.getFlightNumber("IB1140", 2L)));
        Leg outboundThree = this.getLeg("5", 3L, 1L, departureDate.withHourOfDay(5).withMinuteOfHour(0),
                returnDate.withHourOfDay(8).withMinuteOfHour(35), 395, asList(3L),
                "Outbound", asList(this.getFlightNumber("VG3000", 3L)));
        Leg outboundFourth = this.getLeg("7", 3L, 1L, departureDate.withHourOfDay(6).withMinuteOfHour(0),
                returnDate.withHourOfDay(9).withMinuteOfHour(55), 415, asList(4L),
                "Outbound", asList(this.getFlightNumber("AC9010", 4L)));
        Leg outboundFifth = this.getLeg("9", 2L, 1L, departureDate.withHourOfDay(14).withMinuteOfHour(5),
                returnDate.withHourOfDay(18).withMinuteOfHour(10), 425, asList(5L),
                "Outbound", asList(this.getFlightNumber("FA9010", 5L)));

        //Inbound
        Leg inboundOne = this.getLeg("2", 1L, 2L, departureDate.withHourOfDay(4).withMinuteOfHour(55),
                returnDate.withHourOfDay(8).withMinuteOfHour(5), 420, asList(1L),
                "Inbound", asList(this.getFlightNumber("BA2134", 1L)));
        Leg inboundTwo = this.getLeg("4", 1L, 3L, departureDate.withHourOfDay(4).withMinuteOfHour(55),
                returnDate.withHourOfDay(8).withMinuteOfHour(5), 420, asList(1L),
                "Inbound", asList(this.getFlightNumber("FA3333", 5L)));
        Leg inboundThree = this.getLeg("6", 1L, 2L, departureDate.withHourOfDay(11).withMinuteOfHour(0),
                returnDate.withHourOfDay(15).withMinuteOfHour(5), 425, asList(2L),
                "Inbound", asList(this.getFlightNumber("IB4556", 2L)));
        Leg inboundFour = this.getLeg("8", 1L, 2L, departureDate.withHourOfDay(9).withMinuteOfHour(30),
                returnDate.withHourOfDay(14).withMinuteOfHour(0), 390 , asList(4L),
                "Inbound", asList(this.getFlightNumber("AC9015", 4L)));
        Leg inboundFifth = this.getLeg("10", 1L, 3L, departureDate.withHourOfDay(15).withMinuteOfHour(0),
                returnDate.withHourOfDay(19).withMinuteOfHour(0), 420 , asList(3L),
                "Inbound", asList(this.getFlightNumber("VG3050", 3L)));

        return asList(outboundOne, outboundTwo, outboundThree, outboundFourth, outboundFifth,
                inboundOne, inboundTwo, inboundThree, inboundFour, inboundFifth);
    }

    private FlightNumber getFlightNumber(String flightNumberCode, long carrierId) {
        FlightNumber flightNumber = new FlightNumber();
        flightNumber.setFlightNumber(flightNumberCode);
        flightNumber.setCarrierId(carrierId);
        return flightNumber;
    }

    private String getFormattedDate(DateTime dateTime) {
        return DateUtils.formatDate(dateTime, "yyyy-MM-dd'T'HH:mm:ss");
    }

    private Leg getLeg(String id, long originStation, long destinationStation, DateTime departure, DateTime arrival,
                       int duration, List<Long> carriers ,String directionality, List<FlightNumber> flightNumbers){
        Leg leg = new Leg();
        leg.setId(id);
        leg.setOriginStation(originStation);
        leg.setDestinationStation(destinationStation);
        leg.setDeparture(this.getFormattedDate(departure));
        leg.setArrival(this.getFormattedDate(arrival));
        leg.setDuration(duration);
        leg.setJourneyMode("flight");
        leg.setSegmentIds(new ArrayList<>());
        leg.setStops(new ArrayList<>());
        leg.setCarriers(carriers);
        leg.setOperatingCarriers(carriers);
        leg.setDirectionality(directionality);
        leg.setFlightNumbers(flightNumbers);
        return leg;
    }

    private List<Agent> getAgents(){
        Agent firstAgent = this.getAgent(1L, "Expedia", "https://logos.skyscnr.com/images/websites/xpuk.png");
        Agent secondAgent = this.getAgent(2L, "Travel up", "https://logos.skyscnr.com/images/websites/trup.png");
        Agent thirdAgent = this.getAgent(3L, "lastminute.com", "https://logos.skyscnr.com/images/websites/lmuk.png");
        Agent fourthAgent = this.getAgent(4L, "Budget air", "https://logos.skyscnr.com/images/websites/s1uk.png");
        Agent fifthAgent = this.getAgent(5L, "My trip", "https://s1.apideeplink.com/images/websites/at24.png");
        return asList(firstAgent, secondAgent,thirdAgent,fourthAgent, fifthAgent);
    }

    private Agent getAgent(long id, String name, String logoUrl) {
        Agent agent = new Agent();
        agent.setId(id);
        agent.setName(name);
        agent.setImageUrl(logoUrl);
        return agent;
    }

    private List<Place> getPlaces(){
        Place jfkAirport = this.getPlace(1L, "JFK", "New York JFK", "Airport");
        Place heathrowAirport = this.getPlace(2L, "LHR", "London Heathrow", "Airport");
        Place gatwickAirport = this.getPlace(3L, "LGW", "London Gatwick", "Airport");
        Place london = this.getPlace(4L, "LON", "London Gatwick", "Airport");
        Place newYork = this.getPlace(5L, "NY", "London Gatwick", "Airport");
        return asList(jfkAirport, heathrowAirport, gatwickAirport, london, newYork);
    }

    private Place getPlace(long id, String code, String name, String type){
        Place place = new Place();
        place.setId(id);
        place.setName(name);
        place.setType(type);
        place.setCode(code);
        return place;
    }

    private List<Carrier> getCarriers(){
        Carrier ba = this.getCarrier(1L, "BA", "British Airlines", "https://www.skyscanner.net/images/airlines/small/BA.png" ,"BA");
        Carrier iberia = this.getCarrier(2L, "IB", "Iberia", "https://www.skyscanner.net/images/airlines/small/IB.png" ,"IBE");
        Carrier virgin = this.getCarrier(3L, "VG", "Virgin Airlines", "https://www.skyscanner.net/images/airlines/small/VS.png" ,"VS");
        Carrier airCanada = this.getCarrier(4L, "AC", "Air Canada", "https://www.skyscanner.net/images/airlines/small/AC.png" ,"AC");
        Carrier finnair = this.getCarrier(5L, "FA", "Finnair", "https://www.skyscanner.net/images/airlines/small/AY.png" ,"FA");

        return Arrays.asList(ba, iberia, virgin, airCanada, finnair);

    }

    private Carrier getCarrier(long id, String code, String name, String imageUrl, String displayCode) {
        Carrier carrier = new Carrier();
        carrier.setId(id);
        carrier.setCode(code);
        carrier.setName(name);
        carrier.setImageUrl(imageUrl);
        carrier.setDisplayCode(displayCode);
        return carrier;
    }

    private List<Currency> getCurrencies() {
        Currency pounds = new Currency();
        pounds.setCode("GBP");
        pounds.setSymbol("£");
        pounds.setThousandsSeparator(",");
        pounds.setDecimalSeparator(".");
        pounds.setSymbolOnLeft(true);
        pounds.setSpaceBetweenAmountAndSymbol(false);
        pounds.setRoundingCoefficient(0);
        pounds.setDecimalDigits(2);
        return Arrays.asList(pounds);
    }

    private ServiceQuery getServiceQuery() {
        ServiceQuery serviceQuery = new ServiceQuery();
        serviceQuery.setDateTime(DateTime.now().toString());
        return serviceQuery;
    }
}
