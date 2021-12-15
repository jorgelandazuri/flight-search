package com.jalch.flightsearch.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.jalch.flightsearch.common.dep.dagger.DaggerAppDependencies;

import com.jalch.flightsearch.R;
import com.jalch.flightsearch.common.dep.dagger.AppContextModule;
import com.jalch.flightsearch.common.dep.dagger.FlightLivePricesAPIServiceModule;
import com.jalch.flightsearch.common.dep.dagger.FlightLivePricesAPIServiceNetworkModule;
import com.jalch.flightsearch.common.dep.dagger.RxModule;
import com.jalch.flightsearch.common.rx.RxSchedulers;
import com.jalch.flightsearch.common.util.DateUtils;
import com.jalch.flightsearch.model.data.service.FlightsLivePricesCreateSessionParams;
import com.jalch.flightsearch.model.domain.usecase.GetFlightsLivePricesUseCase;
import com.jalch.flightsearch.presenter.FlightsLivePricesPresenter;
import com.jalch.flightsearch.presenter.SearchFlightsLivePricesPresenter;
import com.jalch.flightsearch.view.recycler.ItinerariesCardViewAdapter;
import com.jalch.flightsearch.view.viewmodel.ItineraryCardViewModel;
import com.jalch.flightsearch.view.viewmodel.SearchResultsViewModel;

import org.joda.time.DateTime;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import static android.widget.Toast.LENGTH_LONG;
import static com.jalch.flightsearch.common.util.DateUtils.formatDate;
import static java.lang.String.format;

public class FlightsLivePricesFragment extends Fragment implements FlightsLivePricesFragmentView {

    public final static String TAG = FlightsLivePricesFragment.class.getSimpleName();

    @Inject
    GetFlightsLivePricesUseCase useCase;
    @Inject
    RxSchedulers schedulers;

    @BindView(R.id.circle_progress_bar)
    ProgressBar circleProgressBar;
    @BindView(R.id.results)
    TextView results;
    @BindView(R.id.itinerary_list_view)
    RecyclerView cardsList;

    private ItinerariesCardViewAdapter adapter;

    private FlightsLivePricesPresenter presenter;
    private DateTime nexMonday;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDependencies();
        setUpSearchTitle();
        enableToolbarButtons(true);
    }

    private void setUpSearchTitle() {
        String dateFormat = getString(R.string.static_search_date_format_title);
        nexMonday = DateUtils.getNexMonday();
        getActivity().setTitle(format("%s: %s to %s",
                getString(R.string.static_origin_destination_location_title),
                formatDate(nexMonday, dateFormat),
                formatDate(nexMonday.plusDays(7), dateFormat)));
    }

    private void initDependencies() {
        DaggerAppDependencies.builder()
                .appContextModule(new AppContextModule(getContext()))
                .flightLivePricesAPIServiceNetworkModule(new FlightLivePricesAPIServiceNetworkModule())
                .flightLivePricesAPIServiceModule(new FlightLivePricesAPIServiceModule())
                .rxModule(new RxModule())
                .build().inject(this);
    }

    @Override
    public void init(SearchFlightsLivePricesPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.search_itinerary_results_layout, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }

    private void setUpRecyclerView() {
        adapter = new ItinerariesCardViewAdapter(emptyResults());
        cardsList.setItemAnimator(new DefaultItemAnimator());
        cardsList.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        cardsList.setLayoutManager(linearLayoutManager);
        adapter.notifyDataSetChanged();
    }

    private List<ItineraryCardViewModel> emptyResults() {
        return Arrays.asList(ItineraryCardViewModel.empty(),
                ItineraryCardViewModel.empty(),
                ItineraryCardViewModel.empty(),
                ItineraryCardViewModel.empty(),
                ItineraryCardViewModel.empty());
    }

    @Override
    public void onStart() {
        super.onStart();
        init(new SearchFlightsLivePricesPresenter(this, useCase, schedulers));
        setUpRecyclerView();
        searchFlights();
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.onStop();
    }

    @Override
    public void onDestroy() {
        this.getActivity().setTitle(R.string.main_title);
        enableToolbarButtons(false);
        super.onDestroy();
    }

    private void enableToolbarButtons(boolean enable) {
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(enable);
        actionBar.setDisplayShowHomeEnabled(enable);
        this.setHasOptionsMenu(enable);
    }

    private void searchFlights() {
        this.presenter.startSearch(getSearchParams());
    }

    @NonNull
    private FlightsLivePricesCreateSessionParams getSearchParams() {
        String dateFormat = getString(R.string.static_search_date_format);
        nexMonday = DateUtils.getNexMonday();
        String nextMondayOutbound = formatDate(nexMonday, dateFormat);
        String afterMondayOutbound = formatDate(nexMonday.plusDays(7), dateFormat);

        return new FlightsLivePricesCreateSessionParams("",
                getString(R.string.static_search_country),
                getString(R.string.static_search_currency),
                getString(R.string.static_search_locale),
                getString(R.string.static_search_origin),
                getString(R.string.static_search_destination),
                nextMondayOutbound,
                afterMondayOutbound,
                getString(R.string.static_search_cabin_class),
                getString(R.string.static_search_adults),
                getString(R.string.static_search_location_schema));
    }

    @Override
    public void showLivePrices(SearchResultsViewModel searchResults) {
        int numberOfResults = searchResults.getItineraryCardsViewModels().size();
        this.results.setText(getResources().getQuantityString(R.plurals.results, numberOfResults, numberOfResults));
        adapter.setItems(searchResults.getItineraryCardsViewModels());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showLoading() {
        circleProgressBar.setVisibility(View.VISIBLE);
        results.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        circleProgressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showError(String message) {
        String errorMessage = message != null ? format(getString(R.string.error_message_clue), message) : "" ;
        Toast.makeText(getContext(), format(getString(R.string.error_message_format), errorMessage), LENGTH_LONG)
                .show();
        getActivity().onBackPressed();
    }

}
