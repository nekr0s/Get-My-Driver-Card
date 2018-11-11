package com.example.nekr0s.get_my_driver_card.views.create.requesttypes;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.nekr0s.get_my_driver_card.R;
import com.example.nekr0s.get_my_driver_card.models.Request;
import com.example.nekr0s.get_my_driver_card.models.User;
import com.example.nekr0s.get_my_driver_card.models.UserInfo;
import com.example.nekr0s.get_my_driver_card.utils.Constants;
import com.example.nekr0s.get_my_driver_card.utils.enums.ErrorCode;
import com.example.nekr0s.get_my_driver_card.utils.enums.RequestStatus;
import com.example.nekr0s.get_my_driver_card.utils.enums.RequestType;
import com.example.nekr0s.get_my_driver_card.views.create.CardCreateContracts;
import com.example.nekr0s.get_my_driver_card.views.create.CardCreatePresenter;
import com.example.nekr0s.get_my_driver_card.views.create.base.UserHolder;
import com.example.nekr0s.get_my_driver_card.views.create.documents.DocumentsActivity;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewCardFragment extends Fragment implements CardCreateContracts.View {

    private Request mRequest;

    public NewCardFragment() {
        // Required empty public constructor
    }

    public static NewCardFragment newInstance(Bundle bundle) {
        NewCardFragment fragment = new NewCardFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @BindView(R.id.first_name)
    TextInputLayout mTIL_firstName;

    @BindView(R.id.last_name)
    TextInputLayout mTIL_lastName;

    @BindView(R.id.first_name_cyrillic)
    TextInputLayout mTIL_firstName_cyrillic;

    @BindView(R.id.last_name_cyryllic)
    TextInputLayout mTIL_lastName_cyrillic;

    @BindView(R.id.personal_number)
    TextInputLayout mTIL_personalNumber;

    @BindView(R.id.address_layout)
    TextInputLayout mTIL_address;

    @BindView(R.id.phone_number)
    TextInputLayout mTIL_phoneNumber;

    @BindView(R.id.date_of_birth)
    TextInputLayout mTIL_dateOfBirth;

    @BindView(R.id.email_new_card)
    TextInputLayout mTIL_email_address;

    @BindView(R.id.new_card_next_button)
    Button mNextButton;
    private CardCreateContracts.Presenter mPresenter;
    private Set<ErrorCode> errorCodes = new HashSet<>();

    public static NewCardFragment newInstance() {
        return new NewCardFragment();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new CardCreatePresenter(getContext());

    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.subscribe(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.unsubscribe();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_card_info, container, false);

        // Try to get bundle from PreviousCardInfoActivity
        mRequest = (Request) getArguments().getSerializable(Constants.FROM_PREVIOUS_TO_NEWCARD);

        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @OnClick(R.id.new_card_next_button)
    void openDocumentsActivity() {


        errorCodes = mPresenter.checkFields(getAllFieldsString());

        setRegisterErrors(getAllTils());

        if (allErrorCodesOk()) {
            Intent intent = new Intent(getActivity(), DocumentsActivity.class);
            UserInfo userInfo = createUserInfoFromFields();
            if (mRequest == null) {
                User user = ((UserHolder) getActivity()).getCurrentUser();
                user.setUserInfo(userInfo);
                mRequest = new Request(RequestStatus.REQUEST_NEW, RequestType.TYPE_NEW, null, user);
            } else {
                mRequest.getUser().setUserInfo(userInfo);
            }
            intent.putExtra(DocumentsActivity.REQUEST_SO_FAR, mRequest);
            startActivity(intent);
        }
    }

    private boolean allErrorCodesOk() {
        return errorCodes.contains(ErrorCode.NAME_OK) && errorCodes.contains(ErrorCode.CYR_NAME_OK) &&
                errorCodes.contains(ErrorCode.LAST_NAME_OK) && errorCodes.contains(ErrorCode.CYR_LAST_NAME_OK) &&
                errorCodes.contains(ErrorCode.DATE_OK) && errorCodes.contains(ErrorCode.PHONE_OK) &&
                errorCodes.contains(ErrorCode.ADDRESS_OK) && errorCodes.contains(ErrorCode.ID_OK) &&
                errorCodes.contains(ErrorCode.EMAIL_OK);
    }

    private UserInfo createUserInfoFromFields() {
        return new UserInfo(Objects.requireNonNull(mTIL_firstName.getEditText()).getText().toString(),
                Objects.requireNonNull(mTIL_firstName_cyrillic.getEditText()).getText().toString(),
                Objects.requireNonNull(mTIL_lastName.getEditText()).getText().toString(),
                Objects.requireNonNull(mTIL_lastName_cyrillic.getEditText()).getText().toString(),
                Objects.requireNonNull(mTIL_personalNumber.getEditText()).getText().toString(),
                Objects.requireNonNull(mTIL_dateOfBirth.getEditText()).getText().toString(),
                Objects.requireNonNull(mTIL_address.getEditText()).getText().toString(),
                Objects.requireNonNull(mTIL_phoneNumber.getEditText()).getText().toString(),
                Objects.requireNonNull(mTIL_email_address.getEditText()).getText().toString());
    }

    @Override
    public void setPresenter(CardCreateContracts.Presenter presenter) {

    }

    @Override
    public void setRegisterErrors(Map<String, TextInputLayout> tils) {
        for (ErrorCode errorCode : errorCodes) {
            switch (errorCode) {
                case NAME_NULL:
                case NAME_NOT_VALID:
                case NAME_TOO_LONG:
                    tils.get("firstName").setError(errorCode.getLabel(getContext()));
                    break;
                case NAME_OK:
                    tils.get("firstName").setError(null);
                    break;
                case CYR_NAME_NULL:
                case CYR_NAME_TOO_LONG:
                case CYR_NAME_NOT_IN_CYRILLIC:
                    tils.get("firstNameCyr").setError(errorCode.getLabel(getContext()));
                    break;
                case CYR_NAME_OK:
                    tils.get("firstNameCyr").setError(null);
                    break;
                case LAST_NAME_NULL:
                case LAST_NAME_NOT_VALID:
                case LAST_NAME_TOO_LONG:
                    tils.get("lastName").setError(errorCode.getLabel(getContext()));
                    break;
                case LAST_NAME_OK:
                    tils.get("lastName").setError(null);
                    break;
                case CYR_LAST_NAME_NULL:
                case CYR_LAST_NAME_TOO_LONG:
                case CYR_LAST_NAME_NOT_IN_CYRILLIC:
                    tils.get("lastNameCyr").setError(errorCode.getLabel(getContext()));
                    break;
                case CYR_LAST_NAME_OK:
                    tils.get("lastNameCyr").setError(null);
                    break;
                case ID_NULL:
                case ID_TOO_LONG:
                case ID_INVALID:
                    tils.get("personalNumber").setError(errorCode.getLabel(getContext()));
                    break;
                case ID_OK:
                    tils.get("personalNumber").setError(null);
                    break;
                case ADDRESS_TOO_LONG:
                case ADDRESS_NULL:
                    tils.get("address").setError(errorCode.getLabel(getContext()));
                    break;
                case ADDRESS_OK:
                    tils.get("address").setError(null);
                    break;
                case PHONE_NULL:
                case PHONE_TOO_LONG:
                case PHONE_INVALID:
                    tils.get("phoneNumber").setError(errorCode.getLabel(getContext()));
                    break;
                case PHONE_OK:
                    tils.get("phoneNumber").setError(null);
                    break;
                case DATE_NULL:
                case DATE_INVALID:
                    tils.get("dateOfBirth").setError(errorCode.getLabel(getContext()));
                    break;
                case DATE_OK:
                    tils.get("dateOfBirth").setError(null);
                    break;
                case EMAIL_CARD_NULL:
                case EMAIL_INVALID:
                case EMAIL_TOO_LONG:
                    tils.get("email").setError(errorCode.getLabel(getContext()));
                    break;
                case EMAIL_OK:
                    tils.get("email").setError(null);
                    break;

            }
        }
    }

    private Map<String, String> getAllFieldsString() {
        Map<String, TextInputLayout> tils = getAllTils();
        Map<String, String> tilsToString = new HashMap<>();
        tilsToString.put("firstName", tils.get("firstName").getEditText().getText().toString().trim());
        tilsToString.put("firstNameCyr", tils.get("firstNameCyr").getEditText().getText().toString().trim());
        tilsToString.put("lastName", tils.get("lastName").getEditText().getText().toString().trim());
        tilsToString.put("lastNameCyr", tils.get("lastNameCyr").getEditText().getText().toString().trim());
        tilsToString.put("personalNumber", tils.get("personalNumber").getEditText().getText().toString().trim());
        tilsToString.put("address", tils.get("address").getEditText().getText().toString().trim());
        tilsToString.put("phoneNumber", tils.get("phoneNumber").getEditText().getText().toString().trim());
        tilsToString.put("dateOfBirth", tils.get("dateOfBirth").getEditText().getText().toString().trim());
        tilsToString.put("email", tils.get("email").getEditText().getText().toString().trim());
        return tilsToString;
    }

    private Map<String, TextInputLayout> getAllTils() {
        Map<String, TextInputLayout> allTills = new HashMap<>();
        allTills.put("firstName", mTIL_firstName);
        allTills.put("firstNameCyr", mTIL_firstName_cyrillic);
        allTills.put("lastName", mTIL_lastName);
        allTills.put("lastNameCyr", mTIL_lastName_cyrillic);
        allTills.put("personalNumber", mTIL_personalNumber);
        allTills.put("address", mTIL_address);
        allTills.put("phoneNumber", mTIL_phoneNumber);
        allTills.put("dateOfBirth", mTIL_dateOfBirth);
        allTills.put("email", mTIL_email_address);
        return allTills;
    }


}


