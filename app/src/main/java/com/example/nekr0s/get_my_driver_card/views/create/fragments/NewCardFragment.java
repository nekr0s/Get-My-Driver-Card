package com.example.nekr0s.get_my_driver_card.views.create.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.button.MaterialButton;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nekr0s.get_my_driver_card.R;
import com.example.nekr0s.get_my_driver_card.models.Request;
import com.example.nekr0s.get_my_driver_card.models.User;
import com.example.nekr0s.get_my_driver_card.models.UserInfo;
import com.example.nekr0s.get_my_driver_card.utils.enums.ErrorCode;
import com.example.nekr0s.get_my_driver_card.utils.enums.RequestStatus;
import com.example.nekr0s.get_my_driver_card.utils.enums.RequestType;
import com.example.nekr0s.get_my_driver_card.views.create.CardCreateActivity;
import com.example.nekr0s.get_my_driver_card.views.create.attatchments.DocumentsActivity;

import java.util.Objects;
import java.util.regex.Pattern;

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
    MaterialButton mNextButton;

    private String regexNames = "^[a-zA-Z]*$";


    private static final Pattern CYRILLIC_PATTERN = Pattern.compile("[А-яЁё][-А-яЁё]+");

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
        if (validateFirstName() | validateFirstNameCyr() | validateLastName()
                | validateLastNameCyr() | validateID() | validateAddress() | validatePhone()
                | validateDateOfBirth() | validateEmail()) {
            Intent intent = new Intent(getActivity(), DocumentsActivity.class);
            User user = ((CardCreateActivity) getActivity()).getLoggedInUser();
            user.setUserInfo(createUserInfoFromFields());
            Request request = new Request(RequestStatus.REQUEST_NEW, RequestType.TYPE_NEW, user);
            intent.putExtra(DocumentsActivity.REQUEST_SO_FAR, request);
            startActivity(intent);
        }
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

    public boolean validateFirstName() {
        String firstNameInput = Objects.requireNonNull(mTIL_firstName.getEditText()).getText().toString().trim();
        if (firstNameInput.isEmpty()) {
            mTIL_firstName.setError(ErrorCode.NAME_NULL.getLabel(Objects.requireNonNull(getContext())));
            return false;
        } else if (!firstNameInput.matches(regexNames)) {
            mTIL_firstName.setError(ErrorCode.NAME_NOT_VALID.getLabel(Objects.requireNonNull(getContext())));
            return false;
        } else {
            mTIL_firstName.setError(null);
            return true;
        }

    }

    public boolean validateFirstNameCyr() {
        String firstNameCyrInput = Objects.requireNonNull(mTIL_firstName_cyrillic.getEditText()).getText().toString().trim();

        if (firstNameCyrInput.isEmpty()) {
            mTIL_firstName_cyrillic.setError(ErrorCode.CYR_NAME_NULL.getLabel(Objects.requireNonNull(getContext())));
            return false;
        } else if (firstNameCyrInput.length() > 256) {
            mTIL_firstName_cyrillic.setError(ErrorCode.CYR_NAME_NOT_VALID.getLabel(Objects.requireNonNull(getContext())));
            return false;
        } else if (!CYRILLIC_PATTERN.matcher(firstNameCyrInput).matches()) {
            mTIL_firstName_cyrillic.setError(ErrorCode.NAME_NOT_IN_CYRILLIC.getLabel(Objects.requireNonNull(getContext())));
        } else
            mTIL_firstName_cyrillic.setError(null);
        return true;

    }

    public boolean validateLastName() {
        String validateLastName = Objects.requireNonNull(mTIL_lastName.getEditText()).getText().toString().trim();

        if (validateLastName.isEmpty()) {
            mTIL_lastName.setError(ErrorCode.LAST_NAME_NULL.getLabel(Objects.requireNonNull(getContext())));
            return false;
        } else if (!validateLastName.matches(regexNames)) {
            mTIL_lastName.setError(ErrorCode.LAST_NAME_NOT_VALID.getLabel(Objects.requireNonNull(getContext())));
            return false;
        } else mTIL_lastName.setError(null);
        return true;

    }

    public boolean validateLastNameCyr() {
        String lastNameCyr = Objects.requireNonNull(mTIL_lastName_cyrillic.getEditText()).getText().toString().trim();
        if (lastNameCyr.isEmpty()) {
            mTIL_lastName_cyrillic.setError(ErrorCode.CYR_LAST_NAME_NULL.getLabel(Objects.requireNonNull(getContext())));
            return false;
        } else if (lastNameCyr.length() > 256) {
            mTIL_lastName_cyrillic.setError(ErrorCode.CYR_LAST_NAME_NOT_VALID.getLabel(Objects.requireNonNull(getContext())));
            return false;
        } else if (!CYRILLIC_PATTERN.matcher(lastNameCyr).matches()) {
            mTIL_lastName_cyrillic.setError(ErrorCode.LAST_NAME_NOT_IN_CYRILLIC.getLabel(Objects.requireNonNull(getContext())));
            return false;
        } else mTIL_lastName_cyrillic.setError(null);
        return true;

    }

    public boolean validateID() {
        String personalNum = Objects.requireNonNull(mTIL_personalNumber.getEditText()).getText().toString().trim();

        String regexNumbersOnly = "^[0-9]*$";
        if (personalNum.isEmpty()) {
            mTIL_personalNumber.setError(ErrorCode.ID_NULL.getLabel(Objects.requireNonNull(getContext())));
            return false;
        } else if (!(personalNum).matches(regexNumbersOnly)) {
            mTIL_personalNumber.setError(ErrorCode.NOT_DIGIT.getLabel(Objects.requireNonNull(getContext())));
            return false;
        } else if ((personalNum).length() > 10) {
            mTIL_personalNumber.setError(ErrorCode.ID_INVALID.getLabel(Objects.requireNonNull(getContext())));
            return false;
        } else mTIL_personalNumber.setError(null);
        return true;

    }

    public boolean validateAddress() {
        String address = Objects.requireNonNull(mTIL_address.getEditText()).getText().toString().trim();

        if (address.length() > 100) {
            mTIL_address.setError(ErrorCode.ADDRESS_TOO_LONG.getLabel(Objects.requireNonNull(getContext())));
            return false;
        } else if (address.isEmpty()) {
            mTIL_address.setError(ErrorCode.ADDRESS_NULL.getLabel(Objects.requireNonNull(getContext())));
            return false;
        } else mTIL_address.setError(null);
        return true;

    }

    public boolean validatePhone() {
        String phoneNumber = Objects.requireNonNull(mTIL_phoneNumber.getEditText()).getText().toString().trim();

        if (phoneNumber.isEmpty()) {
            mTIL_phoneNumber.setError(ErrorCode.PHONE_NULL.getLabel(Objects.requireNonNull(getContext())));
            return false;
        } else if (!Patterns.PHONE.matcher(phoneNumber).matches()) {
            mTIL_phoneNumber.setError(ErrorCode.PHONE_INVALID.getLabel(Objects.requireNonNull(getContext())));
            return false;
        } else mTIL_phoneNumber.setError(null);
        return true;

    }

    public boolean validateDateOfBirth() {
        String dateOfBirth = Objects.requireNonNull(mTIL_dateOfBirth.getEditText()).getText().toString().trim();

        String regexDate = getString(R.string.date_of_birth_regex);
        if (dateOfBirth.isEmpty()) {
            mTIL_dateOfBirth.setError(ErrorCode.DATE_NULL.getLabel(Objects.requireNonNull(getContext())));
            return false;
        } else if (!dateOfBirth.matches(regexDate)) {
            mTIL_dateOfBirth.setError(ErrorCode.DATE_INVALID.getLabel(Objects.requireNonNull(getContext())));
            return false;
        } else mTIL_dateOfBirth.setError(null);
        return true;

    }

    public boolean validateEmail() {
        String emailInput = Objects.requireNonNull(mTIL_email_address.getEditText()).getText().toString().trim();

        if (emailInput.isEmpty()) {

            mTIL_email_address.setError(ErrorCode.EMAIL_CARD_NULL.getLabel(Objects.requireNonNull(getContext())));
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            mTIL_email_address.setError(ErrorCode.EMAIL_INVALID.getLabel(Objects.requireNonNull(getContext())));
        } else mTIL_email_address.setError(null);
        return true;

    }

}


