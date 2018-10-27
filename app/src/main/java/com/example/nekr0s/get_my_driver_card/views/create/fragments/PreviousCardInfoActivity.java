package com.example.nekr0s.get_my_driver_card.views.create.fragments;

import android.os.Bundle;
import android.support.design.button.MaterialButton;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;

import com.example.nekr0s.get_my_driver_card.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PreviousCardInfoActivity extends AppCompatActivity {


    @BindView(R.id.previous_eu_country_of_issuing)
    TextInputLayout mTIL_previous_eu_country_of_issuing;

    @BindView(R.id.issuing_authority)
    TextInputLayout mTIL_issuing_authority;

    @BindView(R.id.previous_tachograph_card_number)
    TextInputLayout mTIL_previous_tachograph_card_number;

    @BindView(R.id.date_of_expiry)
    TextInputLayout mTIL_date_of_expiry;


    @BindView(R.id.previous_card_next_button)
    MaterialButton mNextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.previous_card_info);
        ButterKnife.bind(this);


    }

    @OnClick(R.id.previous_card_next_button)
    void openNextFragment() {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_containertwo, new NewCardFragment())
                .addToBackStack(null)
                .commit();
    }

}
