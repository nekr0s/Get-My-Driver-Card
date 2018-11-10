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
import com.example.nekr0s.get_my_driver_card.views.create.base.UserHolder;

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
//    private List<ErrorCode> errorCodes = new ArrayList<>();
//    private final NameValidator mNameValidator = new NamesValidator();
//    private final ValidatorDigits mDigitsValidator = new DigitsValidator();
//    private final DateValidator mDateValidator = new DateValidator();

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
//        errorCodes.add(mNameValidator.isEuCountryOfIssuingValid(Objects.requireNonNull(mTIL_previous_eu_country_of_issuing
//                .getEditText()).getText().toString().trim()));
//        errorCodes.add(mNameValidator.isIssuerAuthorityValid(Objects.requireNonNull(mTIL_issuing_authority
//                .getEditText()).getText().toString().trim()));
//        errorCodes.add(mDigitsValidator.isTachNumberValid(Objects.requireNonNull(mTIL_previous_tachograph_card_number
//                .getEditText()).getText().toString().trim()));
//        errorCodes.add(mDateValidator.isDateValid(Objects.requireNonNull(mTIL_date_of_expiry.getEditText())
//                .getText().toString().trim()));

    }


//    public boolean setErrors(List<ErrorCode> errors) {
//
//        int errorCount = 0;
//
//        for (int i = 0; i < 3; i++) {
//
//            switch (i) {
//                case 0:
//                      if (errorCodes.get(0).equals(ErrorCode.COUNTRY_OK))
//                        mTIL_previous_eu_country_of_issuing.setError(null);
//                    else {
//                        mTIL_previous_eu_country_of_issuing.setError(errorCodes
//                                .get(0).getLabel(getBaseContext()));
//                        errorCount++;
//                    }
//                case 1:
//                    if (errorCodes.get(1).equals(ErrorCode.ISSUING_AUTHORITY_OK))
//                        mTIL_issuing_authority.setError(null);
//                    else {
//                        mTIL_issuing_authority.setError(errorCodes.get(1).getLabel(getBaseContext()));
//                        errorCount++;
//                    }
//                case 2:
//                    if (errorCodes.get(2).equals(ErrorCode.TACH_OK))
//                        mTIL_previous_tachograph_card_number.setError(null);
//                    else {
//                        mTIL_previous_tachograph_card_number.setError(errorCodes.get(2).getLabel(getBaseContext()));
//                        errorCount++;
//                    }
//                case 3:
//                    if (errorCodes.get(3).equals(ErrorCode.DATE_OK))
//                        mTIL_date_of_expiry.setError(null);
//                    else {
//                        mTIL_date_of_expiry.setError(errorCodes.get(3).getLabel(getBaseContext()));
//                        errorCount++;
//                    }
//            }
//
//        }
//        errors.clear();
//        return errorCount == 0;
//    }

    @Override
    public User getCurrentUser() {
        return mCurrentUser;
    }
}


