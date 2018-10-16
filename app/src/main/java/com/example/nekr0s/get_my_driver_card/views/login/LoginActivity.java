package com.example.nekr0s.get_my_driver_card.views.login;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nekr0s.get_my_driver_card.constants.Constants;
import com.example.nekr0s.get_my_driver_card.R;
import com.example.nekr0s.get_my_driver_card.models.User;
import com.example.nekr0s.get_my_driver_card.validator.LoginValidator;
import com.example.nekr0s.get_my_driver_card.validator.base.Validator;
import com.example.nekr0s.get_my_driver_card.views.list.ListActivity;

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
    private final Validator<User> mValidator = new LoginValidator();

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
        User user = new User(mEmailEditText.getText().toString(), mPasswordEditText.getText().toString());
        if (!mValidator.isValid(user)) {
            Toast.makeText(this, "Invalid form.", Toast.LENGTH_SHORT).show();
            return;
        }
        mSmartLogin.login(mConfig);
    }

    @OnClick(R.id.text_no_account)
    void customCreateAccountClicked() {
        mSmartLogin = SmartLoginFactory.build(LoginType.CustomSignup);
        mSmartLogin.signup(mConfig);
        User user = new User(mEmailEditText.getText().toString(), mPasswordEditText.getText().toString());
        if (!mValidator.isValid(user)) {
            Toast.makeText(this, "Invalid form.", Toast.LENGTH_SHORT).show();
            return;
        }
        mSmartLogin.login(mConfig);
    }
}
