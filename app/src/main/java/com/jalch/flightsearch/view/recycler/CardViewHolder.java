package com.jalch.flightsearch.view.recycler;


import android.view.View;

import com.jalch.flightsearch.R;
import com.jalch.flightsearch.view.viewmodel.ItineraryCardViewModel;
import com.jalch.flightsearch.view.viewmodel.LegItineraryViewModel;
import com.jalch.flightsearch.view.widget.CardItineraryFooterWidget;
import com.jalch.flightsearch.view.widget.CardLegDetailsWidget;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class CardViewHolder extends RecyclerView.ViewHolder {

    private static final int OUTBOUND_LEG = 0;
    private static final int INBOUND_LEG = 1;

    private final View validCardContainer;
    private CardLegDetailsWidget outBoundLegView;
    private CardLegDetailsWidget inBoundLegView;
    private CardItineraryFooterWidget footerWidget;

    private final View emptyCard;

    public CardViewHolder(View itemView) {
        super(itemView);
        outBoundLegView = itemView.findViewById(R.id.outbound_leg);
        inBoundLegView = itemView.findViewById(R.id.inbound_leg);
        footerWidget = itemView.findViewById(R.id.card_footer);
        emptyCard = itemView.findViewById(R.id.empty_itinerary_card);
        validCardContainer = itemView.findViewById(R.id.card_container);
    }

    public void setContent(ItineraryCardViewModel itineraryCardViewModel) {
        validCardContainer.setVisibility(VISIBLE);
        emptyCard.setVisibility(GONE);
        List<LegItineraryViewModel> legItineraryViewModels = itineraryCardViewModel.getLegItineraryViewModels();
        outBoundLegView.setContent(legItineraryViewModels.get(OUTBOUND_LEG));
        inBoundLegView.setContent(legItineraryViewModels.get(INBOUND_LEG));
        footerWidget.setContent(itineraryCardViewModel.getItineraryPricingViewModel(),
                itineraryCardViewModel.getRatingViewModel());
    }

    public void setEmptyCard(){
        emptyCard.setVisibility(VISIBLE);
        validCardContainer.setVisibility(GONE);
    }
}
