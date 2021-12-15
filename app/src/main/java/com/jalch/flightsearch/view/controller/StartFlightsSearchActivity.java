package com.jalch.flightsearch.view.controller;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.jalch.flightsearch.R;
import com.jalch.flightsearch.view.FlightsLivePricesFragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class StartFlightsSearchActivity extends AppCompatActivity {

    @BindView(R.id.tap_screen_tv)
    TextView result;
    @BindView(R.id.screen_image)
    ImageView screenImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_flights_search);
        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        this.setTitle(R.string.main_title);
        super.onResume();
    }

    @OnClick(R.id.screen_image)
    void search() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        FlightsLivePricesFragment fragment = new FlightsLivePricesFragment();
        transaction.add(R.id.main_activity, fragment, FlightsLivePricesFragment.TAG);
        transaction.addToBackStack(FlightsLivePricesFragment.TAG);
        transaction.commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            int backStackEntryCount
                    = getSupportFragmentManager().getBackStackEntryCount();
            if (backStackEntryCount > 0) {
                getSupportFragmentManager().popBackStack();
            }
            return true;
        }
        return false;
    }

}
