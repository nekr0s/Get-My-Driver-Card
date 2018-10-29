package com.example.nekr0s.get_my_driver_card.views.create.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.button.MaterialButton;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nekr0s.get_my_driver_card.R;
import com.example.nekr0s.get_my_driver_card.utils.enums.ErrorCode;
import com.example.nekr0s.get_my_driver_card.validator.UserCreateValidator;
import com.example.nekr0s.get_my_driver_card.validator.base.CreateValidator;
import com.example.nekr0s.get_my_driver_card.views.signature.DeclarationActivity;

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

    public CreateValidator mValidator;


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
        mValidator = new UserCreateValidator();

        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
//        Objects.requireNonNull(getActivity()).finish();
    }

    @OnClick(R.id.new_card_next_button)
    void openCameraActivity() {

        ErrorCode result;
        result = mValidator
                .validateNcFragment(mTIL_firstName.getEditText().getText().toString(),
                        mTIL_firstName_cyrillic.getEditText().getText().toString()
                        , mTIL_lastName.getEditText().getText().toString(), mTIL_lastName_cyrillic.
                                getEditText().getText().toString()
                        , mTIL_personalNumber.getEditText().getText().toString(), mTIL_dateOfBirth.
                                getEditText().getText().toString(),
                        mTIL_address.getEditText().getText().toString(), mTIL_phoneNumber.getEditText()
                                .getText().toString(),
                        mTIL_email_address.getEditText().getText().toString());
        switch (result) {
            case NAME_NULL:
                mTIL_firstName.setError(ErrorCode.NAME_NULL.getLabel(getContext()));
                break;
            case NAME_NOT_VALID:
                mTIL_firstName.setError(ErrorCode.NAME_NOT_VALID.getLabel(getContext()));
                break;
            case CYR_NAME_NULL:
                mTIL_firstName.setError(null);
                mTIL_firstName_cyrillic.setError(ErrorCode.CYR_NAME_NULL.getLabel(getContext()));
                break;
            case CYR_NAME_NOT_VALID:
                mTIL_firstName.setError(null);
                mTIL_firstName_cyrillic.setError(ErrorCode.CYR_LAST_NAME_NOT_VALID.getLabel(getContext()));
                break;
            case NAME_NOT_IN_CYRILLIC:
                mTIL_firstName.setError(null);
                mTIL_firstName_cyrillic.setError(ErrorCode.NAME_NOT_IN_CYRILLIC.getLabel(getContext()));
                break;
            case LAST_NAME_NULL:
                mTIL_firstName_cyrillic.setError(null);
                mTIL_lastName.setError(ErrorCode.LAST_NAME_NULL.getLabel(getContext()));
                break;
            case LAST_NAME_NOT_VALID:
                mTIL_firstName_cyrillic.setError(null);
                mTIL_lastName.setError(ErrorCode.LAST_NAME_NOT_VALID.getLabel(getContext()));
                break;
            case CYR_LAST_NAME_NULL:
                mTIL_lastName.setError(null);
                mTIL_lastName_cyrillic.setError(ErrorCode.CYR_LAST_NAME_NULL.getLabel(getContext()));
                break;
            case CYR_LAST_NAME_NOT_VALID:
                mTIL_lastName.setError(null);
                mTIL_lastName_cyrillic.setError(ErrorCode.LAST_NAME_NOT_VALID.getLabel(getContext()));
                break;
            case LAST_NAME_NOT_IN_CYRILLIC:
                mTIL_lastName.setError(null);
                mTIL_lastName_cyrillic.setError(ErrorCode.LAST_NAME_NOT_IN_CYRILLIC.getLabel(getContext()));
                break;
            case NOT_DIGIT:
                mTIL_lastName_cyrillic.setError(null);
                mTIL_personalNumber.setError(ErrorCode.NOT_DIGIT.getLabel(getContext()));
                break;
            case ID_INVALID:
                mTIL_lastName_cyrillic.setError(null);
                mTIL_personalNumber.setError(ErrorCode.ID_INVALID.getLabel(getContext()));
                break;

            case EVERYTHING_OK:
                Intent intent = new Intent(getActivity(), DeclarationActivity.class);
                startActivity(intent);


        }
    }

//        Intent intent = new Intent(getActivity(), CameraActivity.class);
//        startActivity(intent);


}


