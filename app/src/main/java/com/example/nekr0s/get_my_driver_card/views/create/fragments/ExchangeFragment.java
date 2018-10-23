package com.example.nekr0s.get_my_driver_card.views.create.fragments;


import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nekr0s.get_my_driver_card.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ExchangeFragment extends Fragment {


    public ExchangeFragment() {
        // Required empty public constructor
    }

    @BindView(R.id.eu_country_of_issuing)
    TextInputLayout mTIL_eu_country_of_issuing;

    @BindView(R.id.tachograph_card_number)
    TextInputLayout mTIL_tachograph_card_number;

    @BindView(R.id.driver_licence_country_of_issuing)
    TextInputLayout mTIL_driver_licence_country;

    @BindView(R.id.driving_licence_number)
    TextInputLayout mTIL_driving_licence_number;


    public static Fragment newInstance() {
        return new ExchangeFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_exchange2, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        getActivity().finish();
    }

}
