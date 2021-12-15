package com.jalch.flightsearch.view.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jalch.flightsearch.R;
import com.jalch.flightsearch.view.viewmodel.LegItineraryViewModel;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.ButterKnife;

public class CardLegDetailsWidget extends LinearLayout {

    @BindView(R.id.carrier_logo)
    ImageView carrierLogo;
    @BindView(R.id.flight_times)
    TextView flightTimes;
    @BindView(R.id.flight_route_and_carrier)
    TextView routeAndCarrier;
    @BindView(R.id.stops_number)
    TextView stopsNumber;
    @BindView(R.id.duration)
    TextView duration;

    public CardLegDetailsWidget(Context context) {
        super(context);
        init();
    }

    public CardLegDetailsWidget(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CardLegDetailsWidget(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        View legLayout = LayoutInflater.from(getContext()).inflate(R.layout.leg_item, this, false);
        this.addView(legLayout);
        ButterKnife.bind(this);
    }

    public void setContent(LegItineraryViewModel viewModel) {
//        Picasso.get().setLoggingEnabled();
        Picasso.get().load(viewModel.getCarrierLogo()).into(carrierLogo);
        flightTimes.setText(viewModel.getArrivalAndDepartureTimes());
        routeAndCarrier.setText(viewModel.getRouteAndCarrier());
        stopsNumber.setText(getStopsText(viewModel.getStopsNumber()));
        duration.setText(viewModel.getDuration());
    }

    @NonNull
    private String getStopsText(int stops) {
        if (stops == 0) {
            return getResources().getString(R.string.direct);
        }
        return getResources().getQuantityString(R.plurals.stops, stops, stops);
    }
}
