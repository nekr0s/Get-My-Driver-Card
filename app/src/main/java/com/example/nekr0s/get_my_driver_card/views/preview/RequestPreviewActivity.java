package com.example.nekr0s.get_my_driver_card.views.preview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nekr0s.get_my_driver_card.R;
import com.example.nekr0s.get_my_driver_card.models.Request;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RequestPreviewActivity extends AppCompatActivity {


    @BindView(R.id.administration_icon)
    ImageView mAdministrationIcon;

    @BindView(R.id.ministry_icon)
    ImageView mMinistryIcon;

    @BindView(R.id.photo_container)
    ImageView mPhotoContainer;

    @BindView(R.id.signature_container)
    ImageView mSignatureContainer;

    @BindView(R.id.preview_header)
    TextView mHeaderMssg;

    @BindView(R.id.request_reason)
    TextView mHeaderMssgTwo;

    @BindView(R.id.cyr_name)
    EditText mCyrName;

    @BindView(R.id.cyr_surname)
    EditText mCyrSurname;

    @BindView(R.id.preview_name)
    EditText mName;

    @BindView(R.id.preview_surname)
    EditText mLastName;

    @BindView(R.id.preview_address)
    EditText mAddress;

    @BindView(R.id.preview_birth_date)
    EditText mBirthDate;

    @BindView(R.id.preview_number)
    EditText mPhoneNumber;

    @BindView(R.id.preview_additional_info)
    EditText mAdditionalInfo;

    @BindView(R.id.icon_address)
    ImageView mIconAddress;

    @BindView(R.id.icon_phone_number)
    ImageView mIconPhoneNumber;

    @BindView(R.id.icon_date_of_birth)
    EditText mIconDate;

    @BindView(R.id.icon_email)
    EditText mIconEmail;

    @BindView(R.id.request_preview_submit_button)
    Button mSubmitButton;

    private Request mRequest;

    public static final String BUTTON_STATUS = "BUTTON_STATUS";
    public static final String REQUEST_FIN = "REQUEST_FIN";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_preview);

        ButterKnife.bind(this);

        Intent intent = getIntent();
        if (intent.getBooleanExtra(BUTTON_STATUS, false)) {
            mSubmitButton.setVisibility(View.VISIBLE);
        }
        mRequest = (Request) intent.getSerializableExtra(REQUEST_FIN);
    }

    @OnClick(R.id.request_preview_submit_button)
    void onSubmitButtonClicked() {

    }
}
