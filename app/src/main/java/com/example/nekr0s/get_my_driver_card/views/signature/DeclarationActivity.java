package com.example.nekr0s.get_my_driver_card.views.signature;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nekr0s.get_my_driver_card.R;
import com.example.nekr0s.get_my_driver_card.models.Request;
import com.example.nekr0s.get_my_driver_card.utils.enums.RequestType;
import com.example.nekr0s.get_my_driver_card.views.preview.RequestPreviewActivity;

import java.io.ByteArrayOutputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DeclarationActivity extends Activity {
    public static final String SELFIE_BYTESTRING = "SELFIE_BYTESTRING";
    public static final String PERSONAL_ID_BYTESTRING = "PID_PATH";
    public static final String DRIVER_LICENSE_BYTESTRING = "DL_PATH";
    public static final String PREVIOUS_CARD_BYTESTRING = "PREV_PATH";
    private static final int REQUEST_SIGN_HERE = 6;
    public static final String SIGNATURE_BYTESTRING = "SIGNATURE";
    @BindView(R.id.header_msg_declaration)
    TextView mHeader;

    @BindView(R.id.sign_here)
    ImageView mSignImage;

    @BindView(R.id.checkbox)
    CheckBox mCheckBox;

    @BindView(R.id.declaration_text)
    TextView mDeclarationText;

    @BindView(R.id.finish_button)
    Button mFinishButton;

    private Request mRequestSoFar;

    public static final String ALMOST_READY_REQUEST = "ALMOST_READY_REQUEST";
    private String mSelfieByteString;
    private String mPersonalIdByteString;
    private String mDriverLicenseByteString;
    private String mPreviousCardByteString;
    private String mSignatureByteString;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_declaration);

        ButterKnife.bind(this);

        // Get intent
        Intent intent = getIntent();
        mRequestSoFar = (Request) intent.getSerializableExtra(ALMOST_READY_REQUEST);
        mSelfieByteString = intent.getStringExtra(SELFIE_BYTESTRING);
        mPersonalIdByteString = intent.getStringExtra(PERSONAL_ID_BYTESTRING);
        mDriverLicenseByteString = intent.getStringExtra(DRIVER_LICENSE_BYTESTRING);
        if (mRequestSoFar.getRequestType().equals(RequestType.TYPE_EXCHANGE))
            mPreviousCardByteString = intent.getStringExtra(PREVIOUS_CARD_BYTESTRING);

        //disable button if checkbox is not checked else enable button
        mCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) mFinishButton.setEnabled(true);
            else mFinishButton.setEnabled(false);
        });
    }

    @OnClick(R.id.sign_here)
    void signHereClick() {
        Intent intent = new Intent(this, SignatureActivity.class);
        startActivityForResult(intent, REQUEST_SIGN_HERE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_CANCELED) {
            if (requestCode == REQUEST_SIGN_HERE && resultCode == RESULT_OK && data != null) {
                Bundle bundle = data.getExtras();
                // dosmth
                String signaturePath = bundle.getString("imagePath");
                Bitmap bitmap = BitmapFactory.decodeFile(signaturePath);
                mSignImage.setBackground(null);
                mSignImage.setImageBitmap(bitmap);
                mSignatureByteString = getByteString(bitmap);
            }
        }
    }

    @OnClick(R.id.finish_button)
    void finalizeRequest() {
        if (mSignatureByteString != null && mCheckBox.isChecked()) {
            Intent intent = new Intent(this, RequestPreviewActivity.class);
            intent.putExtra(ALMOST_READY_REQUEST, mRequestSoFar);
            intent.putExtra(SELFIE_BYTESTRING, mSelfieByteString);
            intent.putExtra(PERSONAL_ID_BYTESTRING, mPersonalIdByteString);
            intent.putExtra(DRIVER_LICENSE_BYTESTRING, mDriverLicenseByteString);
            intent.putExtra(SIGNATURE_BYTESTRING, mSignatureByteString);
            intent.putExtra(RequestPreviewActivity.BUTTON_VISIBLE, true);
            if (mRequestSoFar.getRequestType().equals(RequestType.TYPE_EXCHANGE))
                intent.putExtra(PREVIOUS_CARD_BYTESTRING, mPreviousCardByteString);
            startActivity(intent);
        } else if (!mCheckBox.isChecked()) {
            Toast.makeText(this, "Please accept the declaration.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Please Sign.", Toast.LENGTH_SHORT).show();
        }
    }

    private String getByteString(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 80, byteArrayOutputStream);
        byte[] bytes = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(bytes, Base64.DEFAULT);
    }

}
