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
import com.example.nekr0s.get_my_driver_card.validator.base.DateValidator;
import com.example.nekr0s.get_my_driver_card.validator.base.DigitsValidator;
import com.example.nekr0s.get_my_driver_card.validator.base.EmailValidator;
import com.example.nekr0s.get_my_driver_card.validator.base.NameValidator;
import com.example.nekr0s.get_my_driver_card.validator.base.NamesValidator;
import com.example.nekr0s.get_my_driver_card.validator.base.Validator;
import com.example.nekr0s.get_my_driver_card.validator.base.ValidatorDate;
import com.example.nekr0s.get_my_driver_card.validator.base.ValidatorDigits;

import java.util.HashSet;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewCardFragment extends Fragment {
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
    private HashSet<ErrorCode> errorCodes = new HashSet<>();


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


        errorCodes.add(mNameValidator.isFirstNameValid(mTIL_firstName.getEditText().getText().toString().trim()));
        errorCodes.add(mNameValidator.isFirstNameCyrValid(mTIL_firstName_cyrillic.getEditText().getText().toString().trim()));
        errorCodes.add(mNameValidator.isLastNameValid(mTIL_lastName.getEditText().getText().toString().trim()));
        errorCodes.add(mNameValidator.isLastNameCyrValid(mTIL_lastName_cyrillic.getEditText().getText().toString().trim()));
        errorCodes.add(mDigitsValidator.isPersonalNumberValid(mTIL_personalNumber.getEditText().getText().toString().trim()));
        errorCodes.add(mNameValidator.isAddressValid(mTIL_address.getEditText().getText().toString().trim()));
        errorCodes.add(mDigitsValidator.isPhoneNumberValid(mTIL_phoneNumber.getEditText().getText().toString().trim()));
        errorCodes.add(mDateValidator.isDateValid(mTIL_dateOfBirth.getEditText().getText().toString().trim()));
        errorCodes.add(mValidator.isValid(mTIL_email_address.getEditText().getText().toString().trim()));

        setErrors(errorCodes);

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

    public void setErrors(HashSet<ErrorCode> errors) {

        for (ErrorCode e : errors) setErrorCorresponding(e);
        errors.clear();
    }
    private void setErrorCorresponding(ErrorCode e) {
        switch (e) {
            case NAME_NULL:
            case NAME_TOO_LONG:
            case NAME_NOT_VALID:
                mTIL_firstName.setError(e.getLabel(Objects.requireNonNull(getContext())));
            case NAME_OK:
                mTIL_firstName.setError(null);
                break;

            case CYR_NAME_NULL:
            case CYR_NAME_TOO_LONG:
            case CYR_NAME_NOT_IN_CYRILLIC:
                mTIL_firstName_cyrillic.setError(e.getLabel(Objects.requireNonNull(getContext())));
            case CYR_NAME_OK:
                mTIL_firstName_cyrillic.setError(null);
                errorCodes.remove(e);
                break;

            case LAST_NAME_NULL:
            case LAST_NAME_TOO_LONG:
            case LAST_NAME_NOT_VALID:
                mTIL_lastName.setError(e.getLabel(Objects.requireNonNull(getContext())));
            case LAST_NAME_OK:
                mTIL_lastName.setError(null);
                break;

            case CYR_LAST_NAME_NULL:
            case CYR_LAST_NAME_TOO_LONG:
            case CYR_LAST_NAME_NOT_IN_CYRILLIC:
                mTIL_lastName_cyrillic.setError(e.getLabel(Objects.requireNonNull(getContext())));
            case CYR_LAST_NAME_OK:
                mTIL_lastName_cyrillic.setError(null);
                break;

            case ID_NULL:
            case ID_TOO_LONG:
            case ID_INVALID:
                mTIL_personalNumber.setError(e.getLabel(Objects.requireNonNull(getContext())));
            case ID_OK:
                mTIL_personalNumber.setError(null);
                break;

            case ADDRESS_NULL:
            case ADDRESS_TOO_LONG:
                mTIL_address.setError(e.getLabel(Objects.requireNonNull(getContext())));
            case ADDRESS_OK:
                mTIL_address.setError(null);
                break;

            case PHONE_NULL:
            case PHONE_TOO_LONG:
            case PHONE_INVALID:
                mTIL_phoneNumber.setError(e.getLabel(Objects.requireNonNull(getContext())));
            case PHONE_OK:
                mTIL_phoneNumber.setError(null);
                break;

            case DATE_NULL:
            case DATE_INVALID:
                mTIL_dateOfBirth.setError(e.getLabel(Objects.requireNonNull(getContext())));
            case DATE_OK:
                mTIL_dateOfBirth.setError(null);
                break;

            case EMAIL_CARD_NULL:
            case EMAIL_INVALID:
            case EMAIL_TOO_LONG:
                mTIL_email_address.setError(e.getLabel(Objects.requireNonNull(getContext())));
            case EMAIL_OK:
                mTIL_email_address.setError(null);
                break;


        }
    }

}


