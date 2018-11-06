package com.example.nekr0s.get_my_driver_card.views.create.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.FrameLayout;

import com.example.nekr0s.get_my_driver_card.R;
import com.example.nekr0s.get_my_driver_card.models.User;
import com.example.nekr0s.get_my_driver_card.utils.Constants;
import com.example.nekr0s.get_my_driver_card.utils.enums.ErrorCode;
import com.example.nekr0s.get_my_driver_card.validator.base.DigitsValidator;
import com.example.nekr0s.get_my_driver_card.validator.base.NameValidator;
import com.example.nekr0s.get_my_driver_card.validator.base.NamesValidator;
import com.example.nekr0s.get_my_driver_card.validator.base.ValidatorDigits;
import com.example.nekr0s.get_my_driver_card.views.create.base.UserHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PreviousCardInfoActivity extends AppCompatActivity implements UserHolder {


    @BindView(R.id.fragment_containertwo)
    FrameLayout mLayout;

    @BindView(R.id.previous_eu_country_of_issuing)
    TextInputLayout mTIL_previous_eu_country_of_issuing;

    @BindView(R.id.issuing_authority)
    TextInputLayout mTIL_issuing_authority;

    @BindView(R.id.previous_tachograph_card_number)
    TextInputLayout mTIL_previous_tachograph_card_number;

    @BindView(R.id.date_of_expiry)
    TextInputLayout mTIL_date_of_expiry;

    @BindView(R.id.previous_card_next_button)
    Button mNextButton;

    private User mCurrentUser;
    private List<ErrorCode> errorCodes = new ArrayList<>();
    private final NameValidator mNameValidator = new NamesValidator();
    private final ValidatorDigits mDigitsValidator = new DigitsValidator();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.previous_card_info);

        ButterKnife.bind(this);

        // Get logged in  user
        Intent intent = getIntent();
        mCurrentUser = (User) intent.getSerializableExtra(Constants.USER_OBJ_EXTRA);
    }

    @OnClick(R.id.previous_card_next_button)
    void openNextFragment() {

        NewCardFragment nextFrag = new NewCardFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_containertwo, nextFrag, "newCardFragment")
                .addToBackStack(null)
                .commit();
//        }
    }

    // TODO: 11/6/2018
//    private boolean validateCountryIssuer() {
//        String regexNames = "^[a-zA-Z]*$";
//
//        String countryIssuerInput = Objects.requireNonNull(mTIL_previous_eu_country_of_issuing
//                .getEditText()).getText().toString().trim();
//
//        if (countryIssuerInput.isEmpty()) {
//            mTIL_previous_eu_country_of_issuing.setError(ErrorCode.COUNTRY_NULL
//                    .getLabel(getBaseContext()));
//            return false;
//        } else if (!countryIssuerInput.matches(regexNames)) {
//            mTIL_previous_eu_country_of_issuing.setError(ErrorCode.COUNTRY_TOO_LONG
//                    .getLabel(getBaseContext()));
//            return false;
//        } else mTIL_previous_eu_country_of_issuing.setError(null);
//        return true;
//
//    }
//
//    private boolean validateIssuingAuthority() {
//        String regexNames = "^[a-zA-Z]*$";
//
//        String authorityInput = Objects.requireNonNull(mTIL_issuing_authority
//                .getEditText()).getText().toString().trim();
//
//        if (authorityInput.isEmpty()) {
//            mTIL_issuing_authority.setError(ErrorCode.ISSUING_AUTHORITY_NULL
//                    .getLabel(getBaseContext()));
//            return false;
//        } else if (!authorityInput.matches(regexNames)) {
//            mTIL_issuing_authority.setError(ErrorCode.ISSUING_AUTHORITY_INVALID
//                    .getLabel(getBaseContext()));
//            return false;
//        } else mTIL_issuing_authority.setError(null);
//
//        return true;
//
//    }
//
//    private boolean validateTachCardNumber() {
//        String regexNumbersOnly = "^[0-9]*$";
//
//        String tachNumberInput = Objects.requireNonNull(mTIL_previous_tachograph_card_number.
//                getEditText()).getText().toString().trim();
//
//        if (tachNumberInput.isEmpty()) {
//            mTIL_previous_tachograph_card_number.setError(ErrorCode.TACH_NULL
//                    .getLabel(getBaseContext()));
//            return false;
//        } else if (!tachNumberInput.matches(regexNumbersOnly)) {
//            mTIL_previous_tachograph_card_number.setError(ErrorCode.TACH_NOT_VALID
//                    .getLabel(getBaseContext()));
//            return false;
//        } else mTIL_previous_tachograph_card_number.setError(null);
//
//        return true;
//
//    }
//
//    private boolean validateDateOfExpiry() {
//        String regexDate = getString(R.string.date_exp);
//
//        String dateOfExpiryInput = Objects.requireNonNull(mTIL_date_of_expiry.
//                getEditText()).getText().toString().trim();
//
//        if (dateOfExpiryInput.isEmpty()) {
//            mTIL_date_of_expiry.setError(ErrorCode.DATE_OF_EXPIRY_NULL
//                    .getLabel(getBaseContext()));
//            return false;
//        } else if (!dateOfExpiryInput.matches(regexDate)) {
//            mTIL_date_of_expiry.setError(ErrorCode.DATE_NOT_VALID
//                    .getLabel(getBaseContext()));
//            return false;
//        } else mTIL_date_of_expiry.setError(null);
//        return true;
//    }


    @Override
    public User getCurrentUser() {
        return mCurrentUser;
    }
}


