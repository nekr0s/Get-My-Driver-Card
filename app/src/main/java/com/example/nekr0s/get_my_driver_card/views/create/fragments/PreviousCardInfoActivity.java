package com.example.nekr0s.get_my_driver_card.views.create.fragments;

import android.os.Bundle;
import android.support.design.button.MaterialButton;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;

import com.example.nekr0s.get_my_driver_card.R;
import com.example.nekr0s.get_my_driver_card.utils.enums.ErrorCode;
import com.example.nekr0s.get_my_driver_card.validator.UserCreateValidator;
import com.example.nekr0s.get_my_driver_card.validator.base.CreateValidator;

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

    public CreateValidator mValidator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.previous_card_info);
        ButterKnife.bind(this);
        mValidator = new UserCreateValidator();

    }

    @OnClick(R.id.previous_card_next_button)
    void openNextFragment() {

        ErrorCode result;
        result = mValidator
                .validatePreviousCardfragment(mTIL_previous_eu_country_of_issuing.getEditText()
                                .getText().toString(), mTIL_issuing_authority.getEditText().getText().toString(),
                        mTIL_previous_tachograph_card_number.getEditText().getText().toString(),
                        mTIL_date_of_expiry.getEditText().getText().toString());

        switch (result) {
            case COUNTRY_NULL:
                mTIL_previous_eu_country_of_issuing.setError(ErrorCode.COUNTRY_NULL.getLabel(getBaseContext()));
                break;
            case COUNTRY_INVALID:
                mTIL_previous_eu_country_of_issuing.setError(ErrorCode.COUNTRY_INVALID.getLabel(getBaseContext()));
                break;
            case ISSUING_AUTHORITY_NULL:
                mTIL_previous_eu_country_of_issuing.setError(null);
                mTIL_issuing_authority.setError(ErrorCode.ISSUING_AUTHORITY_NULL.getLabel(getBaseContext()));
                break;
            case ISSUING_AUTHORITY_INVALID:
                mTIL_previous_eu_country_of_issuing.setError(null);
                mTIL_issuing_authority.setError(ErrorCode.ISSUING_AUTHORITY_NULL.getLabel(getBaseContext()));
                break;
            case TACH_NULL:
                mTIL_issuing_authority.setError(null);
                mTIL_previous_tachograph_card_number.setError(ErrorCode.TACH_NULL.getLabel(getBaseContext()));
                break;
            case TACH_NOT_VALID:
                mTIL_issuing_authority.setError(null);
                mTIL_previous_tachograph_card_number.setError(ErrorCode.TACH_NOT_VALID.getLabel(getBaseContext()));
                break;
            case DATE_OF_EXPIRY_NULL:
                mTIL_previous_tachograph_card_number.setError(null);
                mTIL_date_of_expiry.setError(ErrorCode.DATE_OF_EXPIRY_NULL.getLabel(getBaseContext()));
                break;
            case DATE_NOT_VALID:
                mTIL_previous_tachograph_card_number.setError(null);
                mTIL_date_of_expiry.setError(ErrorCode.DATE_NOT_VALID.getLabel(getBaseContext()));
                break;
            case EVERYTHING_OK:
                mTIL_date_of_expiry.setError(null);
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.fragment_containertwo, new NewCardFragment())
                        .addToBackStack(null)
                        .commit();

        }


    }

}
