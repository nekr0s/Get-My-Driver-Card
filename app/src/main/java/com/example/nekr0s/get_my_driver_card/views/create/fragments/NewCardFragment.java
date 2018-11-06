package com.example.nekr0s.get_my_driver_card.views.create.fragments;

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
import com.example.nekr0s.get_my_driver_card.utils.enums.ErrorCode;
import com.example.nekr0s.get_my_driver_card.utils.enums.RequestStatus;
import com.example.nekr0s.get_my_driver_card.utils.enums.RequestType;
import com.example.nekr0s.get_my_driver_card.validator.base.DateValidator;
import com.example.nekr0s.get_my_driver_card.validator.base.DigitsValidator;
import com.example.nekr0s.get_my_driver_card.validator.base.EmailValidator;
import com.example.nekr0s.get_my_driver_card.validator.base.NameValidator;
import com.example.nekr0s.get_my_driver_card.validator.base.NamesValidator;
import com.example.nekr0s.get_my_driver_card.validator.base.Validator;
import com.example.nekr0s.get_my_driver_card.validator.base.ValidatorDate;
import com.example.nekr0s.get_my_driver_card.validator.base.ValidatorDigits;
import com.example.nekr0s.get_my_driver_card.views.create.attatchments.DocumentsActivity;
import com.example.nekr0s.get_my_driver_card.views.create.base.UserHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.nekr0s.get_my_driver_card.utils.enums.ErrorCode.ADDRESS_OK;
import static com.example.nekr0s.get_my_driver_card.utils.enums.ErrorCode.CYR_LAST_NAME_OK;
import static com.example.nekr0s.get_my_driver_card.utils.enums.ErrorCode.CYR_NAME_OK;
import static com.example.nekr0s.get_my_driver_card.utils.enums.ErrorCode.DATE_OK;
import static com.example.nekr0s.get_my_driver_card.utils.enums.ErrorCode.EMAIL_OK;
import static com.example.nekr0s.get_my_driver_card.utils.enums.ErrorCode.ID_OK;
import static com.example.nekr0s.get_my_driver_card.utils.enums.ErrorCode.LAST_NAME_OK;
import static com.example.nekr0s.get_my_driver_card.utils.enums.ErrorCode.NAME_OK;
import static com.example.nekr0s.get_my_driver_card.utils.enums.ErrorCode.PHONE_OK;

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
    private List<ErrorCode> errorCodes = new ArrayList<>();


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

        if (setErrors(errorCodes)) {
            Intent intent = new Intent(getActivity(), DocumentsActivity.class);
            User user = ((UserHolder) getActivity()).getCurrentUser();
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

    public boolean setErrors(List<ErrorCode> errors) {

        int errorCount = 0;

        for (int i = 0; i < 8; i++) {

            switch (i) {
                case 0:
                    if (errorCodes.get(0).equals(NAME_OK)) mTIL_firstName.setError(null);
                    else {
                        mTIL_firstName.setError(errorCodes.get(0).getLabel(Objects.requireNonNull(getContext())));
                        errorCount++;
                    }

                case 1:
                    if (errorCodes.get(1).equals(CYR_NAME_OK))
                        mTIL_firstName_cyrillic.setError(null);
                    else {
                        mTIL_firstName_cyrillic.setError(errorCodes.get(1).getLabel(Objects.requireNonNull(getContext())));
                        errorCount++;
                    }

                case 2:
                    if (errorCodes.get(2).equals(LAST_NAME_OK)) mTIL_lastName.setError(null);
                    else {
                        mTIL_lastName.setError(errorCodes.get(2).getLabel(Objects.requireNonNull(getContext())));
                        errorCount++;
                    }

                case 3:
                    if (errorCodes.get(3).equals(CYR_LAST_NAME_OK))
                        mTIL_lastName_cyrillic.setError(null);
                    else {
                        mTIL_lastName_cyrillic.setError(errorCodes.get(3).getLabel(Objects.requireNonNull(getContext())));
                        errorCount++;
                    }
                case 4:
                    if (errorCodes.get(4).equals(ID_OK)) mTIL_personalNumber.setError(null);
                    else {
                        mTIL_personalNumber.setError(errorCodes.get(4).getLabel(Objects.requireNonNull(getContext())));
                        errorCount++;
                    }

                case 5:
                    if (errorCodes.get(5).equals(ADDRESS_OK)) mTIL_address.setError(null);
                    else {
                        mTIL_address.setError(errorCodes.get(5).getLabel(Objects.requireNonNull(getContext())));
                        errorCount++;
                    }

                case 6:
                    if (errorCodes.get(6).equals(PHONE_OK)) mTIL_phoneNumber.setError(null);
                    else {
                        mTIL_phoneNumber.setError(errorCodes.get(6).getLabel(Objects.requireNonNull(getContext())));
                        errorCount++;
                    }

                case 7:
                    if (errorCodes.get(7).equals(DATE_OK)) mTIL_dateOfBirth.setError(null);
                    else {
                        mTIL_dateOfBirth.setError(errorCodes.get(7).getLabel(Objects.requireNonNull(getContext())));
                        errorCount++;
                    }

                case 8:
                    if (errorCodes.get(8).equals(EMAIL_OK)) mTIL_email_address.setError(null);
                    else {
                        mTIL_email_address.setError(errorCodes.get(8).getLabel(Objects.requireNonNull(getContext())));
                        errorCount++;
                    }
            }
            errors.clear();

            return errorCount == 0;
        }
        return true;
    }


}


