package com.jalch.flightsearch.view.recycler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jalch.flightsearch.R;
import com.jalch.flightsearch.view.viewmodel.ItineraryCardViewModel;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class ItinerariesCardViewAdapter extends RecyclerView.Adapter<CardViewHolder> {

    private List<ItineraryCardViewModel> itineraries;

    public ItinerariesCardViewAdapter(List<ItineraryCardViewModel> cardViewModels) {
        itineraries = cardViewModels;
    }

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View cardItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.itinerary_card_item, parent, false);
        return new CardViewHolder(cardItem);
    }

    @Override
    public void onBindViewHolder(final CardViewHolder card, int position) {
        ItineraryCardViewModel cardViewModel = itineraries.get(position);
        if (cardViewModel.isValid()) {
            card.setContent(cardViewModel);
        } else if (cardViewModel.isEmpty()) {
            card.setEmptyCard();
        }
    }

    @Override
    public int getItemCount() {
        return itineraries.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public void setItems(List<ItineraryCardViewModel> searchResults) {
        this.itineraries = searchResults;
    }
}
