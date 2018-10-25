package com.example.nekr0s.get_my_driver_card.views.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nekr0s.get_my_driver_card.R;
import com.example.nekr0s.get_my_driver_card.models.User;
import com.example.nekr0s.get_my_driver_card.utils.Constants;
import com.example.nekr0s.get_my_driver_card.views.list.ListActivity;

import java.util.Objects;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import studios.codelight.smartloginlibrary.LoginType;
import studios.codelight.smartloginlibrary.SmartLogin;
import studios.codelight.smartloginlibrary.SmartLoginCallbacks;
import studios.codelight.smartloginlibrary.SmartLoginConfig;
import studios.codelight.smartloginlibrary.SmartLoginFactory;
import studios.codelight.smartloginlibrary.UserSessionManager;
import studios.codelight.smartloginlibrary.users.SmartUser;
import studios.codelight.smartloginlibrary.util.SmartLoginException;

public class LoginActivity extends AppCompatActivity implements SmartLoginCallbacks {

    @BindView(R.id.button_login_facebook)
    Button mFacebookLoginButton;

    @BindView(R.id.button_login_google)
    Button mGoogleLoginButton;

    @BindView(R.id.button_login_custom)
    Button mCustomLoginButton;

    @BindView(R.id.text_no_account)
    TextView mNoAccount;

    @BindView(R.id.email_edittext)
    EditText mEmailEditText;

    @BindView(R.id.password_edittext)
    EditText mPasswordEditText;


    SmartUser mCurrentUser;
    SmartLoginConfig mConfig;
    SmartLogin mSmartLogin;

    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[a-zA-Z])" +      //any letter
                    "(?=\\S+$)" +           //no white spaces
                    ".{4,}" +               //at least 4 characters
                    "$");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        mConfig = new SmartLoginConfig(this, this);
        mConfig.setFacebookAppId(getString(R.string.facebook_app_id));
        mConfig.setFacebookPermissions(null);
        mConfig.setGoogleApiClient(null);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mCurrentUser = UserSessionManager.getCurrentUser(this);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (mSmartLogin != null)
            mSmartLogin.onActivityResult(requestCode, resultCode, data, mConfig);
    }

    @Override
    public void onLoginSuccess(SmartUser user) {
        Toast.makeText(this, user.toString(), Toast.LENGTH_SHORT).show();
        navToHome(user);
    }

    @Override
    public void onLoginFailure(SmartLoginException e) {
        Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public SmartUser doCustomLogin() {
        SmartUser user = new SmartUser();
        user.setEmail(mEmailEditText.getText().toString());
        return user;
    }

    @Override
    // to be inspected (not complete)
    public SmartUser doCustomSignup() {
        SmartUser user = new SmartUser();
        user.setEmail(mEmailEditText.getText().toString());
        return user;
    }

    private void navToHome(SmartUser user) {
        Toast.makeText(this, "Navigating to Home", Toast.LENGTH_SHORT).show();
        User customUser = new User(user.getEmail(), mPasswordEditText.getText().toString());
        Intent intent = new Intent(this, ListActivity.class);
        intent.putExtra(Constants.USER_OBJ_EXTRA, customUser);
        startActivity(intent);
        finish();
    }

    @OnClick(R.id.button_login_facebook)
    void facebookLoginClicked() {
        mSmartLogin = SmartLoginFactory.build(LoginType.Facebook);
        mSmartLogin.login(mConfig);
    }

    @OnClick(R.id.button_login_google)
    void googleLoginClicked() {
        mSmartLogin = SmartLoginFactory.build(LoginType.Google);
        mSmartLogin.login(mConfig);
    }

    @OnClick(R.id.button_login_custom)
    void customLoginClicked() {
        mSmartLogin = SmartLoginFactory.build(LoginType.CustomLogin);
        mSmartLogin.login(mConfig);
    }

    @OnClick(R.id.text_no_account)
    void customCreateAccountClicked() {
//        mSmartLogin = SmartLoginFactory.build(LoginType.CustomSignup);
//        mSmartLogin.signup(mConfig);
//        User user = new User(mEmailEditText.getText().toString(),
//                BCrypt.hashpw(mPasswordEditText.getText().toString(), BCrypt.gensalt()));
//        if (!mValidator.isValid(user)) {
//            Toast.makeText(this, "Invalid form.", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        mSmartLogin.login(mConfig);
        final AlertDialog.Builder mBuilder = new AlertDialog.Builder(LoginActivity.this);
        View mView = getLayoutInflater().inflate(R.layout.layout_dialog, null);


        final TextInputLayout mTIL_email_register = mView.findViewById(R.id.text_input_email_register);
        final TextInputLayout mTIL_password_register = mView.findViewById(R.id.text_input_password_one);
        final TextInputLayout mTIL_password_confirm = mView.findViewById(R.id.text_input_password_two);

        Button mConfirmButton = mView.findViewById(R.id.register_confirm_button);
        mBuilder.setView(mView);
        final AlertDialog dialog = mBuilder.create();
        dialog.show();
        mConfirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateEmail(mTIL_email_register);
                validatePassword(mTIL_password_register);
                validatePasswordTwo(mTIL_password_confirm);
                if (mTIL_email_register.getError() == null && mTIL_password_register.getError() == null
                        && mTIL_password_confirm.getError() == null) {
                    dialog.dismiss();
                }
            }
        });
    }

    private void validateEmail(TextInputLayout mTIL_email_register) {
        String emailInput = Objects.requireNonNull(mTIL_email_register.getEditText()).getText().toString().trim();

        if (emailInput.isEmpty()) {
            mTIL_email_register.setError("Field can't be empty");
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            mTIL_email_register.setError("Please enter a valid email address");
        } else {
            mTIL_email_register.setError(null);
        }
    }

    private void validatePassword(TextInputLayout mTIL_password_register) {
        String passwordInput = Objects.requireNonNull(mTIL_password_register.getEditText()).getText().toString().trim();

        if (passwordInput.isEmpty()) {
            mTIL_password_register.setError("Field can't be empty");
        } else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()) {
            mTIL_password_register.setError("Password too weak");
        } else {
            mTIL_password_register.setError(null);
        }
    }

    private void validatePasswordTwo(TextInputLayout mTIL_confirm_password) {
        String passwordInput = Objects.requireNonNull(mTIL_confirm_password.getEditText()).getText().toString().trim();

        if (passwordInput.isEmpty()) {
            mTIL_confirm_password.setError("Field can't be empty");
        } else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()) {
            mTIL_confirm_password.setError("Password too weak");
        } else {
            mTIL_confirm_password.setError(null);
        }
    }

    private void navToHomeRegister() {
        Intent intent = new Intent(this, ListActivity.class);
        startActivity(intent);
        finish();
    }


}
