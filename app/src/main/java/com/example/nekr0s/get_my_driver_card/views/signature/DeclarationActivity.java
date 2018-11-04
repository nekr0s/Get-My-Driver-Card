package com.example.nekr0s.get_my_driver_card.views.signature;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nekr0s.get_my_driver_card.R;
import com.example.nekr0s.get_my_driver_card.models.Attachment;
import com.example.nekr0s.get_my_driver_card.models.Request;
import com.example.nekr0s.get_my_driver_card.views.preview.RequestPreviewActivity;

import java.io.ByteArrayOutputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DeclarationActivity extends Activity {
    public static final String SELFIE_PATH = "SELFIE_PATH";
    public static final String PERSONAL_ID_PATH = "PID_PATH";
    public static final String DRIVER_LICENSE_PATH = "DL_PATH";
    public static final String PREVIOUS_CARD_PATH = "PREV_PATH";
    private static final int REQUEST_SIGN_HERE = 6;
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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_declaration);

        ButterKnife.bind(this);

        // Get and set all Attachments
        initIntents();

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
                mRequestSoFar.getAttachment().setSignature(getBitmapAsByteArray(bitmap));
            }
        }
    }

    @OnClick(R.id.finish_button)
    void finalizeRequest() {
        if (mRequestSoFar.getAttachment().getSignature() != null && mCheckBox.isChecked()) {
            Intent intent = new Intent(this, RequestPreviewActivity.class);
            startActivity(intent);
        } else if (!mCheckBox.isChecked()) {
            Toast.makeText(this, "Please accept the declaration.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Please Sign.", Toast.LENGTH_SHORT).show();
        }
    }

    private byte[] getBitmapAsByteArray(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 50, stream);
        return stream.toByteArray();
    }

    private Bitmap getBitmap(String path) {
        return BitmapFactory.decodeFile(path);
    }

    private void initIntents() {
        // Get intent
        Intent intent = getIntent();
        mRequestSoFar = (Request) intent.getSerializableExtra(ALMOST_READY_REQUEST);
        String selfiePath = intent.getStringExtra(SELFIE_PATH);
        String personalIdPath = intent.getStringExtra(PERSONAL_ID_PATH);
        String driverLicensePath = intent.getStringExtra(DRIVER_LICENSE_PATH);

        byte[] sBytes = getBitmapAsByteArray(getBitmap(selfiePath));
        byte[] pBytes = getBitmapAsByteArray(getBitmap(personalIdPath));
        byte[] dBytes = getBitmapAsByteArray(getBitmap(driverLicensePath));

        mRequestSoFar.setAttachment(new Attachment());
        mRequestSoFar.getAttachment().setFaceShot(sBytes);
        mRequestSoFar.getAttachment().setDriverLicense(dBytes);
        mRequestSoFar.getAttachment().setIdShot(pBytes);
    }
}
