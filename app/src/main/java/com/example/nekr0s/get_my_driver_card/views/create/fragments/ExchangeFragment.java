package com.example.nekr0s.get_my_driver_card.views.create.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.nekr0s.get_my_driver_card.R;
import com.example.nekr0s.get_my_driver_card.utils.enums.ErrorCode;
import com.example.nekr0s.get_my_driver_card.validator.DigitsValidator;
import com.example.nekr0s.get_my_driver_card.validator.NamesValidator;
import com.example.nekr0s.get_my_driver_card.validator.base.NameValidator;
import com.example.nekr0s.get_my_driver_card.validator.base.ValidatorDigits;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


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

    @BindView(R.id.exchange_next_button)
    Button mNextButton;

    private List<ErrorCode> errorCodes = new ArrayList<>();
    private final NameValidator mNameValidator = new NamesValidator();
    private final ValidatorDigits mDigitsValidator = new DigitsValidator();


    public static Fragment newInstance() {
        return new ExchangeFragment();
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_exchange, container, false);

        ButterKnife.bind(this, view);


        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Objects.requireNonNull(getActivity()).finish();
    }


    @OnClick(R.id.exchange_next_button)
    void openNextFragment() {
//
//        errorCodes.add(mNameValidator.isEuCountryOfIssuingValid(Objects.requireNonNull(mTIL_eu_country_of_issuing
//                .getEditText()).getText().toString().trim()));
//        errorCodes.add(mDigitsValidator.isTachNumberValid(Objects.requireNonNull(mTIL_tachograph_card_number
//                .getEditText()).getText().toString().trim()));
//        errorCodes.add(mNameValidator.isLicenseCountryOfIssuingValid(Objects.requireNonNull(mTIL_driver_licence_country
//                .getEditText()).getText().toString().trim()));
//        errorCodes.add(mDigitsValidator.isLicenseNumberValid(Objects.requireNonNull(mTIL_driving_licence_number
//                .getEditText()).getText().toString().trim()));

        NewCardFragment nextFrag = new NewCardFragment();
        Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, nextFrag, "newCardFragment")
                .hide(ExchangeFragment.this)
                .addToBackStack(ExchangeFragment.class.getName())
                .commit();
    }
//
//    public boolean setErrors(List<ErrorCode> errors) {
//
//        int errorCount = 0;
//
//        for (int i = 0; i < 3; i++) {
//
//            switch (i) {
//                case 0:
//                    if (errorCodes.get(0).equals(ErrorCode.COUNTRY_OK))
//                        mTIL_eu_country_of_issuing.setError(null);
//                    else {
//                        mTIL_eu_country_of_issuing.setError(errorCodes
//                                .get(0).getLabel(Objects.requireNonNull(getContext())));
//                        errorCount++;
//                    }
//
//                case 1:
//                    if (errorCodes.get(1).equals(ErrorCode.TACH_OK))
//                        mTIL_tachograph_card_number.setError(null);
//                    else {
//                        mTIL_tachograph_card_number.setError(errorCodes.get(1)
//                                .getLabel(Objects.requireNonNull(getContext())));
//                        errorCount++;
//                    }
//                case 2:
//                    if (errorCodes.get(2).equals(ErrorCode.LICENSE_COUNTRY_ISSUER_OK))
//                        mTIL_driver_licence_country.setError(null);
//                    else {
//                        mTIL_driver_licence_country.setError(errorCodes.get(2)
//                                .getLabel(Objects.requireNonNull(getContext())));
//                        errorCount++;
//                    }
//                case 3:
//                    if (errorCodes.get(3).equals(ErrorCode.LICENSE_NUMBER_OK))
//                        mTIL_driving_licence_number.setError(null);
//                    else {
//                        mTIL_driving_licence_number.setError(errorCodes.get(3)
//                                .getLabel(Objects.requireNonNull(getContext())));
//                        errorCount++;
//
//                    }
//            }
//
//        }
//        errors.clear();
//        return errorCount == 0;
//    }
}