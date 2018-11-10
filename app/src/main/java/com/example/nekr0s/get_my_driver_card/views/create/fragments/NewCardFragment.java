package com.example.nekr0s.get_my_driver_card.views.create.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.nekr0s.get_my_driver_card.R;
import com.example.nekr0s.get_my_driver_card.models.UserInfo;
import com.example.nekr0s.get_my_driver_card.utils.enums.ErrorCode;
import com.example.nekr0s.get_my_driver_card.validator.DateValidator;
import com.example.nekr0s.get_my_driver_card.validator.DigitsValidator;
import com.example.nekr0s.get_my_driver_card.validator.EmailValidator;
import com.example.nekr0s.get_my_driver_card.validator.NamesValidator;
import com.example.nekr0s.get_my_driver_card.validator.base.NameValidator;
import com.example.nekr0s.get_my_driver_card.validator.base.Validator;
import com.example.nekr0s.get_my_driver_card.validator.base.ValidatorDate;
import com.example.nekr0s.get_my_driver_card.validator.base.ValidatorDigits;
import com.example.nekr0s.get_my_driver_card.views.create.CardCreateContracts;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewCardFragment extends Fragment implements CardCreateContracts.View {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


    public NewCardFragment() {
        // Required empty public constructor
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


    private final ValidatorDate mDateValidator = new DateValidator();
    private final Validator mValidator = new EmailValidator();
    private final NameValidator mNameValidator = new NamesValidator();
    private final ValidatorDigits mDigitsValidator = new DigitsValidator();


    public static NewCardFragment newInstance() {
        return new NewCardFragment();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_card_info, container, false);

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

        setRegisterErrors(errorCodes, getAllTils());


        // TODO: 11/10/2018 Check is all fields are okay and continue , fix the nullPointer
//        Intent intent = new Intent(getActivity(), DocumentsActivity.class);
//        User user = ((UserHolder) getActivity()).getCurrentUser();
//        user.setUserInfo(createUserInfoFromFields());
//        Request request = new Request(RequestStatus.REQUEST_NEW, RequestType.TYPE_NEW, null, user);
//        intent.putExtra(DocumentsActivity.REQUEST_SO_FAR, request);
//        startActivity(intent);


    }

    private UserInfo createUserInfoFromFields() {
        return new UserInfo(mTIL_firstName.getEditText().getText().toString(),
                mTIL_firstName_cyrillic.getEditText().getText().toString(),
                mTIL_lastName.getEditText().getText().toString(),
                mTIL_lastName_cyrillic.getEditText().getText().toString(),
                mTIL_personalNumber.getEditText().getText().toString(),
                mTIL_dateOfBirth.getEditText().getText().toString(),
                mTIL_address.getEditText().getText().toString(),
                mTIL_phoneNumber.getEditText().getText().toString(),
                mTIL_email_address.getEditText().getText().toString());
    }

    @Override
    public void setPresenter(CardCreateContracts.Presenter presenter) {

    }

    @Override
    public void setRegisterErrors(Set<ErrorCode> errors, Map<String, TextInputLayout> tils) {

        for (ErrorCode errorCode : errors) {
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
                    tils.get("phone").setError(errorCode.getLabel(getContext()));
                    break;
                case PHONE_OK:
                    tils.get("phone").setError(null);
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
        Map<String, String> allFields = new HashMap<>();
        allFields.put("firstName", mTIL_firstName.getEditText().getText().toString().trim());
        allFields.put("firstNameCyr", mTIL_firstName_cyrillic.getEditText().getText().toString().trim());
        allFields.put("lastName", mTIL_lastName.getEditText().getText().toString().trim());
        allFields.put("lastNameCyr", mTIL_lastName_cyrillic.getEditText().getText().toString().trim());
        allFields.put("personalNumber", mTIL_personalNumber.getEditText().getText().toString().trim());
        allFields.put("address", mTIL_address.getEditText().getText().toString().trim());
        allFields.put("phoneNumber", mTIL_phoneNumber.getEditText().getText().toString().trim());
        allFields.put("dateOfBirth", mTIL_dateOfBirth.getEditText().getText().toString().trim());
        allFields.put("email", mTIL_email_address.getEditText().getText().toString().trim());
        return allFields;
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


