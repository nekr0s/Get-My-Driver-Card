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
public class PreviousCardInfoFragment extends Fragment {


    public PreviousCardInfoFragment() {
        // Required empty public constructor
    }

    @BindView(R.id.previous_eu_country_of_issuing)
    TextInputLayout mTIL_previous_eu_country_of_issuing;

    @BindView(R.id.issuing_authority)
    TextInputLayout mTIL_issuing_authority;

    @BindView(R.id.previous_tachograph_card_number)
    TextInputLayout mTIL_previous_tachograph_card_number;

    @BindView(R.id.date_of_expiry)
    TextInputLayout mTIL_date_of_expiry;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.previous_card_info, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        getActivity().finish();
    }


}
