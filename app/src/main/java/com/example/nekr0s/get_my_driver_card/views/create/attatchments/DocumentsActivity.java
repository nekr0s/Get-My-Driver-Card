package com.example.nekr0s.get_my_driver_card.views.create.attatchments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nekr0s.get_my_driver_card.R;
import com.example.nekr0s.get_my_driver_card.models.Request;
import com.example.nekr0s.get_my_driver_card.views.signature.DeclarationActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DocumentsActivity extends AppCompatActivity implements DocumentsContracts.View {

    public static final String REQUEST_SO_FAR = "REQUEST_SO_FAR";
    private Request mRequestSoFar;

    static final int REQUEST_IMAGE_CAPTURE = 143;

    private String mSelfieByteString;
    private String mPersonalIdByteString;
    private String mDriverLicenseByteString;
    private String mPreviousCardByteString;


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

    private DocumentsContracts.Presenter mPresenter;
    private String mClickedButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_document_upload);


        ButterKnife.bind(this);

        mPresenter = new DocumentsPresenter(this);

        // Gets request so far
        Intent intent = getIntent();
        mRequestSoFar = (Request) intent.getSerializableExtra(REQUEST_SO_FAR);
        if (!mPresenter.deviceHasCamera()) {
            mNextButton.setEnabled(false);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.subscribe(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPresenter.unsubscribe();
    }

    @OnClick(R.id.selfie_button)
    void takePictureFront(Button button) {
        mClickedButton = button.getText().toString().trim();
        Intent intent = mPresenter.capturePhoto(true);
        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
    }

    @OnClick({R.id.add_ID_button, R.id.driver_license_button, R.id.previous_card_button})
    void takePictureBack(Button button) {
        mClickedButton = button.getText().toString().trim();
        Intent intent = mPresenter.capturePhoto(false);
        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
    }

    @OnClick(R.id.documents_next_button)
    void openDeclarationActivity() {
        if (mPersonalIdByteString == null || mSelfieByteString == null || mDriverLicenseByteString == null) {
            Toast.makeText(this, "Please provide all pictures.", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent(this, DeclarationActivity.class);
        intent.putExtra(DeclarationActivity.ALMOST_READY_REQUEST, mRequestSoFar);
        intent.putExtra(DeclarationActivity.SELFIE_BYTESTRING, mSelfieByteString);
        intent.putExtra(DeclarationActivity.PERSONAL_ID_BYTESTRING, mPersonalIdByteString);
        intent.putExtra(DeclarationActivity.DRIVER_LICENSE_BYTESTRING, mPersonalIdByteString);
        if (mPreviousCardByteString != null)
            intent.putExtra(DeclarationActivity.PREVIOUS_CARD_BYTESTRING, mPreviousCardByteString);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_CANCELED) {
            if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK && data != null) {
                fillIcon(mClickedButton);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void setPresenter(DocumentsContracts.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public Bitmap rotateImage(Bitmap img, int degree) {
        Matrix matrix = new Matrix();
        matrix.postRotate(degree);
        Bitmap rotatedImg = Bitmap
                .createBitmap(img, 0, 0, img.getWidth(), img.getHeight(), matrix, true);
        img.recycle();
        return rotatedImg;
    }

    @Override
    public void showError(Throwable throwable) {
        Toast.makeText(this, "Error: ", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void fillIcon(String whichButton) {
        // Get the dimensions of the View
        int targetW = mSelfieIcon.getWidth();
        int targetH = mSelfieIcon.getHeight();

        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        // Determine how much to scale down the image
        int scaleFactor = Math.max(photoW / targetW, photoH / targetH);

        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        // For attachments
        Bitmap bitmap = mPresenter.getBitmap(bmOptions);

        switch (whichButton) {
            case "Capture Photo":
                mSelfieIcon.setImageBitmap(bitmap);
                mSelfieByteString = mPresenter.getByteString(bitmap);
                break;
            case "Add Personal ID":
                mAddIdIcon.setImageBitmap(bitmap);
                mPersonalIdByteString = mPresenter.getByteString(bitmap);
                break;
            case "Add driver license":
                mAddLicense.setImageBitmap(bitmap);
                mDriverLicenseByteString = mPresenter.getByteString(bitmap);
                break;
            case "Add previous card":
                mAddLicense.setImageBitmap(bitmap);
                mPreviousCardByteString = mPresenter.getByteString(bitmap);
                break;
        }
    }


}

