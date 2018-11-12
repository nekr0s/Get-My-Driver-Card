package com.example.nekr0s.get_my_driver_card.views.preview;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nekr0s.get_my_driver_card.R;
import com.example.nekr0s.get_my_driver_card.models.Attachment;
import com.example.nekr0s.get_my_driver_card.models.Request;
import com.example.nekr0s.get_my_driver_card.utils.Constants;
import com.example.nekr0s.get_my_driver_card.utils.PhotoEncodeHelper;
import com.example.nekr0s.get_my_driver_card.utils.enums.RequestReason;
import com.example.nekr0s.get_my_driver_card.utils.enums.RequestStatus;
import com.example.nekr0s.get_my_driver_card.utils.enums.RequestType;
import com.example.nekr0s.get_my_driver_card.views.list.ListActivity;

import java.io.FileNotFoundException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.nekr0s.get_my_driver_card.views.signature.DeclarationActivity.ALMOST_READY_REQUEST;
import static com.example.nekr0s.get_my_driver_card.views.signature.DeclarationActivity.DRIVER_LICENSE_URISTRING;
import static com.example.nekr0s.get_my_driver_card.views.signature.DeclarationActivity.PERSONAL_ID_URISTRING;
import static com.example.nekr0s.get_my_driver_card.views.signature.DeclarationActivity.PREVIOUS_CARD_URISTRING;
import static com.example.nekr0s.get_my_driver_card.views.signature.DeclarationActivity.SELFIE_URISTRING;
import static com.example.nekr0s.get_my_driver_card.views.signature.DeclarationActivity.SIGNATURE_URISTRING;

public class RequestPreviewActivity extends AppCompatActivity implements RequestPreviewContracts.View, AdapterView.OnItemSelectedListener {

    public static final int AWAIT_STATUS_CHANGE = 1;
    @BindView(R.id.adminToolbar)
    android.support.v7.widget.Toolbar mAdminToolbar;

    @BindView(R.id.admin_panel_change_status_spinner)
    Spinner mAdminSpinner;

    @BindView(R.id.admin_panel_request_status_tv)
    TextView mOnlyAdminTextViewStatus;

    @BindView(R.id.administration_icon)
    ImageView mAdministrationIcon;

    @BindView(R.id.ministry_icon)
    ImageView mMinistryIcon;

    @BindView(R.id.photo_container)
    ImageView mPhotoContainer;

    @BindView(R.id.signature_container)
    ImageView mSignatureContainer;

    @BindView(R.id.id_photo_container)
    ImageView mIdPhotoContainer;

    @BindView(R.id.driverlicense_photo_container)
    ImageView mDriverLicensePhotoContainer;

    @BindView(R.id.preview_header)
    TextView mHeaderMsg;

    @BindView(R.id.request_reason)
    TextView mHeaderMsgTwo;

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
    TextView mAdditionalInfo;

    @BindView(R.id.icon_address)
    ImageView mIconAddress;

    @BindView(R.id.icon_phone_number)
    ImageView mIconPhoneNumber;

    @BindView(R.id.icon_date_of_birth)
    ImageView mIconDateOfBirth;

    @BindView(R.id.icon_email)
    ImageView mIconEmail;

    @BindView(R.id.icon_info)
    ImageView mIconInfo;

    @BindView(R.id.preview_email)
    EditText mEmail;

    @BindView(R.id.request_preview_submit_button)
    Button mSubmitButton;

    private Request mRequest;
    private RequestPreviewContracts.Presenter mPresenter;

    public static final String BUTTON_VISIBLE = "BUTTON_VISIBLE";
    public static final String IS_ADMIN = "TOOLBAR_VISIBLE";
    public static final String FROM_LIST = "JUST_SHOW_PREVIEW";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_preview);

        ButterKnife.bind(this);

        mPresenter = new RequestPreviewPresenter(this);

        // Get intent
        Intent intent = getIntent();
        // It's either from List or Creation
        if (intent.getSerializableExtra(FROM_LIST) != null) getFromList(intent);
        else {
            try {
                getFromCreation(intent);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        // If admin - display admin panel
        if (intent.getBooleanExtra(IS_ADMIN, false)) {
            mAdminToolbar.setVisibility(View.VISIBLE);
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                    R.array.status_array, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            mAdminSpinner.setAdapter(adapter);
            mAdminSpinner.setSelection(0, false);
            mAdminSpinner.setOnItemSelectedListener(this);
            mOnlyAdminTextViewStatus.append(mRequest.getShortStatusString());

        }
        if (intent.getBooleanExtra(BUTTON_VISIBLE, false))
            mSubmitButton.setVisibility(View.VISIBLE);

        displayRequestPreview();
    }


    private void getFromList(Intent intent) {
        mRequest = (Request) intent.getSerializableExtra(FROM_LIST);
    }

    private void getFromCreation(Intent intent) throws FileNotFoundException {
        mRequest = (Request) intent.getSerializableExtra(ALMOST_READY_REQUEST);
        String mSelfieUriString = intent.getStringExtra(SELFIE_URISTRING);
        String mSelfieEncodedString = PhotoEncodeHelper
                .getByteString(Uri.parse(mSelfieUriString), this);
        String mPersonalIdUriString = intent.getStringExtra(PERSONAL_ID_URISTRING);
        String mPersonalIdEncodedString = PhotoEncodeHelper
                .getByteString(Uri.parse(mPersonalIdUriString), this);
        String mDriverLicenseUriString = intent.getStringExtra(DRIVER_LICENSE_URISTRING);
        String mDriverLicenseEncodedString = PhotoEncodeHelper
                .getByteString(Uri.parse(mDriverLicenseUriString), this);
        String mSignatureUriString = intent.getStringExtra(SIGNATURE_URISTRING);
        String mSignatureEncodedString = PhotoEncodeHelper
                .getByteString(Uri.parse(mSignatureUriString), this);

        mRequest.setAttachment(new Attachment(mSelfieEncodedString, mPersonalIdEncodedString,
                mSignatureEncodedString, mDriverLicenseEncodedString));

        if (mRequest.getRequestTypeString().equals(RequestType.TYPE_EXCHANGE)) {
            String mPreviousCardUriString = intent.getStringExtra(PREVIOUS_CARD_URISTRING);
            mRequest.getAttachment().setPreviousEuCard(PhotoEncodeHelper
                    .getByteString(Uri.parse(mPreviousCardUriString), this));
        }
    }

    private void displayRequestPreview() {
        mPhotoContainer.setImageBitmap(mPresenter.convertStringBytesToBitmap(mRequest
                .getAttachment().getFaceShot()));
        mSignatureContainer.setImageBitmap(mPresenter.convertStringBytesToBitmap(mRequest
                .getAttachment().getSignature()));
        mIdPhotoContainer.setImageBitmap(mPresenter.convertStringBytesToBitmap(mRequest
                .getAttachment().getIdShot()));
        mDriverLicensePhotoContainer.setImageBitmap(mPresenter.convertStringBytesToBitmap(mRequest
                .getAttachment().getDriverLicense()));
        mCyrName.setText(mRequest.getUser().getUserInfo().getFirstNameCyrillic());
        mCyrSurname.setText(mRequest.getUser().getUserInfo().getLastNameCyrillic());
        mName.setText(mRequest.getUser().getUserInfo().getFirstName());
        mLastName.setText(mRequest.getUser().getUserInfo().getLastName());
        mAddress.setText(mRequest.getUser().getUserInfo().getAddress());
        mBirthDate.setText(mRequest.getUser().getUserInfo().getDateOfBirth());
        mPhoneNumber.setText(mRequest.getUser().getUserInfo().getPhoneNumber());
        mEmail.setText(mRequest.getUser().getUserInfo().getEmail());
        if (mRequest.getRequestType() != RequestType.TYPE_NEW)
            displayAdditionalInfo();
        else {
            mAdditionalInfo.setVisibility(View.GONE);
            mIconInfo.setVisibility(View.GONE);
        }
    }

    private void displayAdditionalInfo() {
        StringBuilder sb = new StringBuilder();
        mAdditionalInfo.append("\n");
        sb.append("Request type: ").append(mRequest.getRequestTypeString()).append("\n");
        sb.append("Request reason: ").append(mRequest.getRequestReasonString()).append("\n");
        if (mRequest.getRequestType() == RequestType.TYPE_EXCHANGE) {
            sb.append("Country of issuing: ").append(mRequest.getCurrentCountryOfIssuing()).append("\n");
            sb.append("Driver License Country of Issuing: ").append(mRequest.getCurrentDriverLicenseCountryOfIssuing()).append("\n");
            sb.append("Driver License number: ").append(mRequest.getCurrentDriverLicenseNumber()).append("\n");
            sb.append("Tachograph card number: ").append(mRequest.getCurrentTachCardNum()).append("\n");
        }
        sb.append("Country of Issuing: ").append(mRequest.getPreviousCountryOfIssuing()).append("\n");
        sb.append("Issuing Authority: ").append(mRequest.getPreviousIssuingAuthority()).append("\n");
        sb.append("Date of Expiry: ").append(mRequest.getPreviousDateOfExpiry()).append("\n");
        sb.append("Previous Tachograph card number: ").append(mRequest.getPreviousTachCardNum()).append("\n");
        if (mRequest.getRequestReason() == RequestReason.REASON_LOST ||
                mRequest.getRequestReason() == RequestReason.REASON_STOLEN) {
            sb.append("I've lost my card on: ").append(mRequest.getPreviousLostDate()).append("\n");
            sb.append("Near: ").append(mRequest.getPreviousLostPlace()).append("\n");
        }
        mAdditionalInfo.append(sb);
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

    @OnClick(R.id.request_preview_submit_button)
    void onSubmitButtonClicked() {
        mPresenter.submit(mRequest);
    }

    @Override
    public void setPresenter(RequestPreviewContracts.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void finishHim(Request request) {
        Intent returnIntent = new Intent();
        returnIntent.putExtra("requestId", request.getRequestId());
        returnIntent.putExtra("requestStatus", request.getRequestStatus());
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }

    @Override
    public void showLoading() {
        Toast.makeText(this, "Loading..", Toast.LENGTH_LONG).show();
    }

    @Override
    public void hideLoading() {
        Toast.makeText(this, "Done Loading.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showError(Throwable throwable) {
        Toast.makeText(this, "Error: " + throwable.getMessage(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void navigateToList(Request request) {
        Intent intent = new Intent(this, ListActivity.class);
        intent.putExtra(Constants.USER_OBJ_EXTRA, request.getUser());
        startActivity(intent);
        finish();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String requestStatusString = "Request status: " + parent.getItemAtPosition(position).toString();
        String check = mOnlyAdminTextViewStatus.getText().toString();
        if (check.equals(requestStatusString)) return;
        mOnlyAdminTextViewStatus.setText("Request status: " + parent.getItemAtPosition(position));
        switch (parent.getItemAtPosition(position).toString()) {
            case "NEW":
                mRequest.setRequestStatus(RequestStatus.REQUEST_NEW);
                break;
            case "APPROVED":
                mRequest.setRequestStatus(RequestStatus.REQUEST_APPROVED);
                break;
            case "DISAPPROVED":
                mRequest.setRequestStatus(RequestStatus.REQUEST_DISAPPROVED);
                break;
            case "WAITING":
                mRequest.setRequestStatus(RequestStatus.REQUEST_WAITING);
                break;
        }
        mPresenter.update(mRequest);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
