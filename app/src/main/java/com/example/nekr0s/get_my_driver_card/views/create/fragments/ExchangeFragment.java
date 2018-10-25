package com.example.nekr0s.get_my_driver_card.views.create.fragments;


import android.os.Bundle;
import android.support.design.button.MaterialButton;
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

    @BindView(R.id.exchange_next_button)
    MaterialButton mNextButton;


    public static Fragment newInstance() {
        return new ExchangeFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_exchange, container, false);

        ButterKnife.bind(this, view);

//
//        mTIL_eu_country_of_issuing.getEditText().addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                changeButtonState(s);
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });
//
//        mTIL_tachograph_card_number.getEditText().addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                changeButtonState(s);
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });
//
//        mTIL_driver_licence_country.getEditText().addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                changeButtonState(s);
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });
//
//        mTIL_driving_licence_number.getEditText().addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                changeButtonState(s);
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });


        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        getActivity().finish();
    }

//    private void changeButtonState(CharSequence s) {
//        if (!s.toString().trim().isEmpty()) {
//            mNextButton.setEnabled(true);
//
//        } else {
//            mNextButton.setEnabled(false);
//        }
//    }
//    private TextWatcher fieldTextWatcher = new TextWatcher() {
//        @Override
//        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//
//        }
//
//        @Override
//        public void onTextChanged(CharSequence s, int start, int before, int count) {
//            String euInput = mTIL_eu_country_of_issuing.getEditText().toString().trim();
//            String tachNumberInput = mTIL_tachograph_card_number.getEditText().toString().trim();
//            String dlIssuerInput = mTIL_driver_licence_country.getEditText().toString().trim();
//            String dlNumberInput = mTIL_driving_licence_number.getEditText().toString().trim();
//
//                mNextButton.setEnabled(!euInput.isEmpty() && !tachNumberInput.isEmpty() &&
//                        !dlIssuerInput.isEmpty() && !dlNumberInput.isEmpty());
//
//        }
//
//        @Override
//        public void afterTextChanged(Editable s) {
//
//        }
//    };

}
