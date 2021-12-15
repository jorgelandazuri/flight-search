package com.jalch.flightsearch.presenter.adapter;

import com.jalch.flightsearch.model.domain.pojo.Carrier;
import com.jalch.flightsearch.model.domain.pojo.FlightsLivePrices;
import com.jalch.flightsearch.model.domain.pojo.Leg;
import com.jalch.flightsearch.model.domain.pojo.Place;
import com.jalch.flightsearch.view.viewmodel.LegItineraryViewModel;

import org.joda.time.Duration;
import org.joda.time.Period;
import org.joda.time.format.PeriodFormatterBuilder;

import java.util.List;

import static java.lang.String.format;
import static org.joda.time.DateTime.parse;

public class LegItineraryViewModelAdapter implements ViewModelAdapter<LegItineraryViewModel, String> {

    private final FlightsLivePrices flightsLivePrices;

    public LegItineraryViewModelAdapter(FlightsLivePrices flightsLivePrices) {
        this.flightsLivePrices = flightsLivePrices;
    }

    @Override
    public LegItineraryViewModel adapt(String input) {
        LegItineraryViewModel result = LegItineraryViewModel.invalid();
        if (input != null) {
            for (Leg currentLeg : flightsLivePrices.getLegs()) {
                if (input.equals(currentLeg.getId())) {
                    Carrier legCarrier = getLegLookUpCarrier(currentLeg.getCarriers());
                    result = new LegItineraryViewModel(
                            adaptCarrierLogo(legCarrier),
                            adaptArrivalAndDepartureTimes(currentLeg.getDeparture(), currentLeg.getArrival()),
                            adaptRouteAndCarrier(currentLeg.getOriginStation(), currentLeg.getDestinationStation(), legCarrier),
                            currentLeg.getStops().size(),
                            adaptDuration(currentLeg.getDuration()));

                    break;
                }
            }
            return result;
        }
        return result;
    }

    private String adaptCarrierLogo(Carrier legCarrier) {
        return legCarrier != null ? legCarrier.getImageUrl() : "";
    }

    private String adaptArrivalAndDepartureTimes(String departureDate, String arrivalDate) {
        return format("%s - %s", parse(departureDate).toString("HH:mm"),
                parse(arrivalDate).toString("HH:mm"));
    }

    private String adaptRouteAndCarrier(Long originStation, Long destinationStation, Carrier legCarrier) {
        String route = getRoute(getStationPlaceCode(originStation), getStationPlaceCode(destinationStation));
        String carrierName = legCarrier != null ? legCarrier.getName() : "";
        String separator = route.isEmpty() || carrierName.isEmpty() ? "" :", ";
        return format("%s%s%s", route, separator, carrierName);
    }

    private String getRoute(String originPlaceCode, String destinationPlaceCode) {
        boolean validRoute = !originPlaceCode.isEmpty() && !destinationPlaceCode.isEmpty();
        return validRoute ? format("%s-%s",originPlaceCode,destinationPlaceCode): "";
    }

    private String getStationPlaceCode(Long lookUpPlaceId) {
        String result = "";
        for (Place currentPlace : flightsLivePrices.getPlaces()) {
            if (lookUpPlaceId.equals(currentPlace.getId())) {
                result = currentPlace.getCode();
                break;
            }
        }
        return result;
    }

    private String adaptDuration(int duration) {
        Period period = new Period(Duration.standardMinutes(duration));
        return new PeriodFormatterBuilder()
                .appendHours().appendSuffix("h ")
                .appendMinutes().appendSuffix("m")
                .toFormatter().print(period);
    }

    private Carrier getLegLookUpCarrier(List<Long> legCarriers) {
        Carrier result = null;
        if (!legCarriers.isEmpty()) {
            Long lookupCarrierId = legCarriers.get(0);
            for (Carrier currentCarrier : flightsLivePrices.getCarriers()) {
                if (lookupCarrierId.equals(currentCarrier.getId())) {
                    result = currentCarrier;
                    break;
                }
            }
        }
        return result;
    }
}
