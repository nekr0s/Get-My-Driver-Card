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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nekr0s.get_my_driver_card.R;
import com.example.nekr0s.get_my_driver_card.async.AsyncSchedulerProvider;
import com.example.nekr0s.get_my_driver_card.models.User;
import com.example.nekr0s.get_my_driver_card.services.HttpUsersService;
import com.example.nekr0s.get_my_driver_card.utils.Constants;
import com.example.nekr0s.get_my_driver_card.utils.enums.ErrorCode;
import com.example.nekr0s.get_my_driver_card.utils.keyboard.KeyboardHider;
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

public class LoginActivity extends AppCompatActivity implements SmartLoginCallbacks,
        LoginContracts.View {
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

    @BindView(R.id.progress_bar_loading)
    ProgressBar mProgressBar;

    SmartUser mCurrentUser;
    SmartLoginConfig mConfig;
    SmartLogin mSmartLogin;
    private LoginContracts.Presenter mPresenter;
    private AlertDialog mAlertDialog;
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
        mPresenter = new LoginPresenter(new HttpUsersService(), AsyncSchedulerProvider.getInstance());
        mConfig.setFacebookAppId(getString(R.string.facebook_app_id));
        mConfig.setFacebookPermissions(null);
        mConfig.setGoogleApiClient(null);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.subscribe(this);
        mCurrentUser = UserSessionManager.getCurrentUser(this);

    }

    @Override
    protected void onPause() {
        super.onPause();
        mPresenter.unsubscribe();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (mSmartLogin != null)
            mSmartLogin.onActivityResult(requestCode, resultCode, data, mConfig);
    }

    @Override
    public void onLoginSuccess(SmartUser user) {
//        Toast.makeText(this, user.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoginFailure(SmartLoginException e) {
//        Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public SmartUser doCustomLogin() {
        mPresenter.login(mEmailEditText.getText().toString(), mPasswordEditText.getText().toString());
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
        final AlertDialog.Builder mBuilder = new AlertDialog.Builder(LoginActivity.this);
        View view = getLayoutInflater().inflate(R.layout.layout_dialog, null);

        final TextInputLayout tilEmailRegister = view.findViewById(R.id.text_input_email_register);
        final TextInputLayout tilPasswordRegister = view.findViewById(R.id.text_input_password_one);
        final TextInputLayout tilPasswordConfirm = view.findViewById(R.id.text_input_password_two);

        Button mConfirmButton = view.findViewById(R.id.register_confirm_button);
        mBuilder.setView(view);
        mAlertDialog = mBuilder.create();
        mAlertDialog.show();
        mConfirmButton.setOnClickListener(v -> {
            if (!validateEmail(tilEmailRegister) |
                    !validatePasswords(tilPasswordRegister, tilPasswordConfirm)) {
                return;
            }

            User user = new User(tilEmailRegister.getEditText().getText().toString(),
                    tilPasswordRegister.getEditText().getText().toString());

            mPresenter.register(user);
        });
    }


    @Override
    public void setPresenter(LoginContracts.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showLoading() {
        if (mAlertDialog != null && mAlertDialog.isShowing()) mAlertDialog.dismiss();
        KeyboardHider.hideKeyboard(this);
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void showError(Throwable throwable) {
        Toast.makeText(this, "Error: " + throwable.getMessage(), Toast.LENGTH_LONG)
                .show();
    }

    @Override
    public void navigateToHome(User user) {
        Intent intent = new Intent(this, ListActivity.class);
        intent.putExtra(Constants.USER_OBJ_EXTRA, user);
        startActivity(intent);
        finish();
    }

    private boolean validateEmail(TextInputLayout email) {
        String emailInput = Objects.requireNonNull(email.getEditText()).getText().toString().trim();

        if (emailInput.isEmpty()) {
            email.setError(ErrorCode.EMAIL_NULL.getLabel(getBaseContext()));
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            email.setError(ErrorCode.EMAIL_NOT_CORRECT.getLabel(getBaseContext()));
            return false;
        } else email.setError(null);
        return true;

    }

    private boolean validatePasswords(TextInputLayout password, TextInputLayout confirmPassword) {
        String passwordInput = Objects.requireNonNull(password.getEditText()).getText().toString().trim();
        String passwordInputTwo = Objects.requireNonNull(confirmPassword.getEditText()).getText().toString().trim();

        if (passwordInput.isEmpty()) {
            password.setError(ErrorCode.PASSWORD_NULL.getLabel(getBaseContext()));
            return false;
        } else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()) {
            password.setError(ErrorCode.PASSWORD_TOO_SIMPLE.getLabel(getBaseContext()));
            return false;
        } else if (!passwordInput.equals(passwordInputTwo)) {
            password.setError(ErrorCode.PASSWORDS_DONT_MATCH.getLabel(getBaseContext()));
            confirmPassword.setError(ErrorCode.PASSWORDS_DONT_MATCH.getLabel(getBaseContext()));
            return false;
        } else password.setError(null);
        confirmPassword.setError(null);
        return true;
    }

}
