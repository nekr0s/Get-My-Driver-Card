package com.example.nekr0s.get_my_driver_card.views.create.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.nekr0s.get_my_driver_card.R;
import com.example.nekr0s.get_my_driver_card.utils.enums.ErrorCode;
import com.example.nekr0s.get_my_driver_card.views.create.CardCreateContracts;
import com.example.nekr0s.get_my_driver_card.views.create.CardCreatePresenter;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class ExchangeFragment extends Fragment implements CardCreateContracts.View {


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

    private Set<ErrorCode> errorCodes = new HashSet<>();
    private CardCreateContracts.Presenter mPresenter;


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

        errorCodes = mPresenter.checkFieldsExchange(getAllFieldsString());

        setRegisterErrors(getAllTils());

        if (allErrorCodesOk()) {
            NewCardFragment nextFrag = new NewCardFragment();
            Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, nextFrag, "newCardFragment")
                    .hide(ExchangeFragment.this)
                    .addToBackStack(ExchangeFragment.class.getName())
                    .commit();
        }

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new CardCreatePresenter(getContext());
    }

    @Override
    public void setPresenter(CardCreateContracts.Presenter presenter) {

    }

    @Override
    public void setRegisterErrors(Map<String, TextInputLayout> tils) {
        for (ErrorCode errorCode : errorCodes) {
            switch (errorCode) {
                case COUNTRY_NULL:
                case COUNTRY_INVALID:
                case COUNTRY_TOO_LONG:
                    mTIL_eu_country_of_issuing.setError(errorCode.getLabel(getContext()));
                    break;
                case COUNTRY_OK:
                    mTIL_eu_country_of_issuing.setError(null);
                    break;
                case TACH_NULL:
                case TACH_NOT_VALID:
                    mTIL_tachograph_card_number.setError(errorCode.getLabel(getContext()));
                    break;
                case TACH_OK:
                    mTIL_tachograph_card_number.setError(null);
                    break;
                case LICENSE_COUNTRY_ISSUER_NULL:
                case LICENSE_COUNTRY_ISSUER_INVALID:
                    mTIL_driver_licence_country.setError(errorCode.getLabel(getContext()));
                    break;
                case LICENSE_COUNTRY_ISSUER_OK:
                    mTIL_driver_licence_country.setError(null);
                    break;

                case LICENSE_NUMBER_NULL:
                case LICENSE_NUMBER_INVALID:
                    mTIL_driving_licence_number.setError(errorCode.getLabel(getContext()));
                    break;
                case LICENSE_NUMBER_OK:
                    mTIL_driving_licence_number.setError(null);
                    break;

            }

        }
    }

    private boolean allErrorCodesOk() {
        return errorCodes.contains(ErrorCode.COUNTRY_OK) && errorCodes.contains(ErrorCode.TACH_OK) &&
                errorCodes.contains(ErrorCode.LICENSE_COUNTRY_ISSUER_OK)
                && errorCodes.contains(ErrorCode.LICENSE_NUMBER_OK);

    }

    private Map<String, String> getAllFieldsString() {

        Map<String, TextInputLayout> tils = getAllTils();

        Map<String, String> tilsToString = new HashMap<>();

        tilsToString.put("countryOfIssuing", tils.get("countryOfIssuing").getEditText().getText().toString().trim());
        tilsToString.put("tachNumber", tils.get("tachNumber").getEditText().getText().toString().trim());
        tilsToString.put("licenseCountry", tils.get("licenseCountry").getEditText().getText().toString().trim());
        tilsToString.put("licenseNumber", tils.get("licenseNumber").getEditText().getText().toString().trim());

        return tilsToString;
    }

    private Map<String, TextInputLayout> getAllTils() {
        Map<String, TextInputLayout> allTills = new HashMap<>();
        allTills.put("countryOfIssuing", mTIL_eu_country_of_issuing);
        allTills.put("tachNumber", mTIL_tachograph_card_number);
        allTills.put("licenseCountry", mTIL_driver_licence_country);
        allTills.put("licenseNumber", mTIL_driving_licence_number);

        return allTills;
    }


}