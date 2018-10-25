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
import com.example.nekr0s.get_my_driver_card.views.CameraView.CameraActivity;

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
        Objects.requireNonNull(getActivity()).finish();
    }

    @OnClick(R.id.new_card_next_button)
    void openCameraActivity() {
        Intent intent = new Intent(getActivity(), CameraActivity.class);
        startActivity(intent);

    }

}
