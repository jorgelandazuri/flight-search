package com.jalch.flightsearch.view.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jalch.flightsearch.R;
import com.jalch.flightsearch.view.viewmodel.ItineraryPricingViewModel;
import com.jalch.flightsearch.view.viewmodel.LegItineraryViewModel;
import com.jalch.flightsearch.view.viewmodel.RatingViewModel;
import com.squareup.picasso.Picasso;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import butterknife.BindView;
import butterknife.ButterKnife;

public class CardItineraryFooterWidget extends LinearLayout {

    @BindView(R.id.rating_icon)
    ImageView ratingIcon;
    @BindView(R.id.rating)
    TextView rating;
    @BindView(R.id.price)
    TextView price;
    @BindView(R.id.agent)
    TextView agent;

    public CardItineraryFooterWidget(Context context) {
        super(context);
        init();
    }

    public CardItineraryFooterWidget(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CardItineraryFooterWidget(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        View legLayout = LayoutInflater.from(getContext()).inflate(R.layout.card_itinerary_footer, this, false);
        this.addView(legLayout);
        ButterKnife.bind(this);
    }

    public void setContent(ItineraryPricingViewModel pricingViewModel, RatingViewModel ratingViewModel) {
        ratingIcon.setImageDrawable(getDrawable(ratingViewModel));
        rating.setText(ratingViewModel.getRatingScore());
        price.setText(pricingViewModel.getPrice());
        agent.setText(pricingViewModel.getAgentName());
    }

    private Drawable getDrawable(RatingViewModel ratingViewModel) {
        int logoRId = getResources().getIdentifier(ratingViewModel.getIconResourceId() , "drawable", getContext().getPackageName());
        return ContextCompat.getDrawable(getContext(), logoRId);
    }
}
