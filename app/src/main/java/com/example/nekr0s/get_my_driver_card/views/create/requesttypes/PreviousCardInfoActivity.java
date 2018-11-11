package com.example.nekr0s.get_my_driver_card.views.create.requesttypes;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.nekr0s.get_my_driver_card.R;
import com.example.nekr0s.get_my_driver_card.models.Request;
import com.example.nekr0s.get_my_driver_card.models.User;
import com.example.nekr0s.get_my_driver_card.utils.Constants;
import com.example.nekr0s.get_my_driver_card.utils.enums.ErrorCode;
import com.example.nekr0s.get_my_driver_card.utils.enums.RequestReason;
import com.example.nekr0s.get_my_driver_card.utils.enums.RequestStatus;
import com.example.nekr0s.get_my_driver_card.utils.enums.RequestType;
import com.example.nekr0s.get_my_driver_card.views.create.CardCreateContracts;
import com.example.nekr0s.get_my_driver_card.views.create.CardCreatePresenter;
import com.example.nekr0s.get_my_driver_card.views.create.base.UserHolder;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;

public class PreviousCardInfoActivity extends AppCompatActivity implements UserHolder, CardCreateContracts.View {


    @BindView(R.id.fragment_containertwo)
    FrameLayout mLayout;

    @BindView(R.id.previous_header_text)
    TextView mTextView;

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
    private Set<ErrorCode> errorCodes = new HashSet<>();
    private CardCreateContracts.Presenter mPresenter;
    private String mPreviousLostDate;
    private String mPreviousLostPlace;
    private RequestReason mRequestReason;
    private RequestReason mReplaceReason;
    private RequestType mRequestType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.previous_card_info);

        ButterKnife.bind(this);

        mPresenter = new CardCreatePresenter(getBaseContext());

        // Get logged in  user
        Intent intent = getIntent();
        mCurrentUser = (User) intent.getSerializableExtra(Constants.USER_OBJ_EXTRA);

        // Get RequestType
        mRequestType = (RequestType) intent.getSerializableExtra(Constants.REQUEST_TYPE);

        // Get Renew reason, MAY BE NULL
        mRequestReason = (RequestReason) intent.getSerializableExtra(Constants.RENEWAL_REASON);

        // Get Replace Reason, MAY BE NULL
        mReplaceReason = (RequestReason) intent.getSerializableExtra(Constants.REPLACE_REASON);
        if (mReplaceReason == RequestReason.REASON_LOST || mReplaceReason == RequestReason.REASON_STOLEN) {
            mPreviousLostDate = intent.getStringExtra(Constants.REPLACE_DATE_LOST_OR_STOLEN);
            mPreviousLostPlace = intent.getStringExtra(Constants.REPLACE_PLACE_LOST_OR_STOLEN);
        }
    }

    @OnClick(R.id.previous_card_next_button)
    void openNextFragment() {

        errorCodes = mPresenter.checkFieldsPreviousCard(getAllFieldsString());

        setRegisterErrors(getAllTils());

        if (allErrorCodesOk()) {
            Request request = new Request(RequestStatus.REQUEST_NEW, mRequestType, mRequestReason, mCurrentUser);
            request.setPreviousCountryOfIssuing(mTIL_previous_eu_country_of_issuing.getEditText()
                    .getText().toString().trim());
            request.setPreviousDateOfExpiry(mTIL_date_of_expiry.getEditText().getText()
                    .toString().trim());
            request.setPreviousIssuingAuthority(mTIL_issuing_authority.getEditText().getText()
                    .toString().trim());
            request.setPreviousTachCardNum(mTIL_previous_tachograph_card_number.getEditText().getText()
                    .toString().trim());
            if (mRequestType == RequestType.TYPE_REPLACE) {
                request.setPreviousLostDate(mPreviousLostDate);
                request.setPreviousLostPlace(mPreviousLostPlace);
            }
            Bundle bundle = new Bundle();
            bundle.putSerializable(Constants.FROM_PREVIOUS_TO_NEWCARD, request);
            NewCardFragment nextFragment = NewCardFragment.newInstance(bundle);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_containertwo, nextFragment, "newCardFragment")
                    .addToBackStack(null)
                    .commit();
        }

    }

    private boolean allErrorCodesOk() {
        return errorCodes.contains(ErrorCode.COUNTRY_OK) && errorCodes.contains(ErrorCode.TACH_OK) &&
                errorCodes.contains(ErrorCode.ISSUING_AUTHORITY_OK)
                && errorCodes.contains(ErrorCode.DATE_OK);

    }

    @Override
    public void setPresenter(CardCreateContracts.Presenter presenter) {

    }

    @OnLongClick(R.id.previous_header_text)
    boolean setSampleDate() {
        mTIL_previous_eu_country_of_issuing.getEditText().setText("Bulgaria");
        mTIL_previous_tachograph_card_number.getEditText().setText("5169439408");
        mTIL_issuing_authority.getEditText().setText("MVR SOFIA");
        mTIL_date_of_expiry.getEditText().setText("04.08.2017");
        return true;
    }

    @Override
    public void setRegisterErrors(Map<String, TextInputLayout> tils) {
        for (ErrorCode errorCode : errorCodes) {
            switch (errorCode) {
                case COUNTRY_NULL:
                case COUNTRY_INVALID:
                case COUNTRY_TOO_LONG:
                    mTIL_previous_eu_country_of_issuing.setError(errorCode.getLabel(getBaseContext()));
                    break;
                case COUNTRY_OK:
                    mTIL_previous_eu_country_of_issuing.setError(null);
                    break;
                case ISSUING_AUTHORITY_NULL:
                case ISSUING_AUTHORITY_INVALID:
                    mTIL_issuing_authority.setError(errorCode.getLabel(getBaseContext()));
                    break;
                case ISSUING_AUTHORITY_OK:
                    mTIL_issuing_authority.setError(null);
                    break;
                case TACH_NULL:
                case TACH_NOT_VALID:
                    mTIL_previous_tachograph_card_number.setError(errorCode.getLabel(getBaseContext()));
                    break;
                case TACH_OK:
                    mTIL_previous_tachograph_card_number.setError(null);
                    break;
                case DATE_NULL:
                case DATE_INVALID:
                    mTIL_date_of_expiry.setError(errorCode.getLabel(getBaseContext()));
                    break;
                case DATE_OK:
                    mTIL_date_of_expiry.setError(null);
                    break;

            }

        }
    }

    private Map<String, String> getAllFieldsString() {

        Map<String, TextInputLayout> tils = getAllTils();

        Map<String, String> tilsToString = new HashMap<>();

        tilsToString.put("countryOfIssuing", tils.get("countryOfIssuing").getEditText().getText().toString().trim());
        tilsToString.put("authorityIssuer", tils.get("authorityIssuer").getEditText().getText().toString().trim());
        tilsToString.put("tachNumber", tils.get("tachNumber").getEditText().getText().toString().trim());
        tilsToString.put("dateOfExpiry", tils.get("dateOfExpiry").getEditText().getText().toString().trim());

        return tilsToString;
    }

    private Map<String, TextInputLayout> getAllTils() {
        Map<String, TextInputLayout> allTills = new HashMap<>();
        allTills.put("countryOfIssuing", mTIL_previous_eu_country_of_issuing);
        allTills.put("authorityIssuer", mTIL_issuing_authority);
        allTills.put("tachNumber", mTIL_previous_tachograph_card_number);
        allTills.put("dateOfExpiry", mTIL_date_of_expiry);

        return allTills;
    }

    @Override
    public User getCurrentUser() {
        return mCurrentUser;
    }
}


