package com.example.nekr0s.get_my_driver_card.views.create.attatchments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nekr0s.get_my_driver_card.R;
import com.example.nekr0s.get_my_driver_card.models.Request;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DocumentsActivity extends AppCompatActivity {

    public static final String REQUEST_SO_FAR = "REQUEST_SO_FAR";
    private Request mRequestSoFar;

    @BindView(R.id.documents_header)
    TextView mHeaderMsg;

    @BindView(R.id.selfie_icon)
    ImageView mSelfieIcon;

    @BindView(R.id.add_ID_icon)
    ImageView mAddIdIcon;

    @BindView(R.id.driver_license_photo)
    ImageView mAddLicense;

    @BindView(R.id.previous_card_icon)
    ImageView mAddPreviousCard;

    @BindView(R.id.selfie_button)
    Button mSelfieButton;

    @BindView(R.id.add_ID_button)
    Button mIdButton;

    @BindView(R.id.driver_license_button)
    Button mDriverLicenseButton;

    @BindView(R.id.previous_card_button)
    Button mPreviousCardButton;

    @BindView(R.id.documents_next_button)
    Button mNextButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_document_upload);

        ButterKnife.bind(this);

        // Gets request so far
        Intent intent = getIntent();
        mRequestSoFar = (Request) intent.getSerializableExtra(REQUEST_SO_FAR);
    }
}
