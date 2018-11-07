package com.example.nekr0s.get_my_driver_card.views.login;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nekr0s.get_my_driver_card.R;
import com.example.nekr0s.get_my_driver_card.models.User;
import com.example.nekr0s.get_my_driver_card.utils.Constants;
import com.example.nekr0s.get_my_driver_card.utils.enums.ErrorCode;
import com.example.nekr0s.get_my_driver_card.utils.keyboard.KeyboardHider;
import com.example.nekr0s.get_my_driver_card.validator.RegisterValidator;
import com.example.nekr0s.get_my_driver_card.validator.base.ValidatorLogin;
import com.example.nekr0s.get_my_driver_card.views.list.ListActivity;

import java.util.ArrayList;
import java.util.List;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;
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
    CircularProgressButton mCustomLoginButton;

    @BindView(R.id.text_no_account)
    TextView mNoAccount;

    @BindView(R.id.username_edittext)
    EditText mUsernameEditText;

    @BindView(R.id.password_edittext)
    EditText mPasswordEditText;

    SmartUser mCurrentUser;
    SmartLoginConfig mConfig;
    SmartLogin mSmartLogin;
    private LoginContracts.Presenter mPresenter;
    private AlertDialog mAlertDialog;
    private List<ErrorCode> errorCodes = new ArrayList<>();
    private final ValidatorLogin mRegisterValidator = new RegisterValidator();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        mConfig = new SmartLoginConfig(this, this);
        mPresenter = new LoginPresenter(this);
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
    protected void onDestroy() {
        super.onDestroy();
        mCustomLoginButton.dispose();
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
        mPresenter.login(mUsernameEditText.getText().toString(), mPasswordEditText.getText().toString());
        SmartUser user = new SmartUser();
        user.setEmail(mUsernameEditText.getText().toString());
        return user;
    }

    @Override
    // to be inspected (not complete)
    public SmartUser doCustomSignup() {
        SmartUser user = new SmartUser();
        user.setEmail(mUsernameEditText.getText().toString());
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

        final TextInputLayout tilUsernameRegister = view.findViewById(R.id.text_input_email_register);
        final TextInputLayout tilPasswordRegister = view.findViewById(R.id.text_input_password_one);
        final TextInputLayout tilPasswordConfirm = view.findViewById(R.id.text_input_password_two);

//        errorCodes.add(mRegisterValidator.isUsernameValid(Objects.requireNonNull(tilUsernameRegister
//                .getEditText()).getText().toString().trim()));
//        errorCodes.add(mRegisterValidator.isPasswordValid(tilPasswordRegister
//                        .getEditText().getText().toString().trim(),
//                Objects.requireNonNull(tilPasswordConfirm.getEditText())
//                        .getText().toString().trim()));
//        errorCodes.add(mRegisterValidator.isPasswordValid(tilPasswordConfirm.getEditText()
//                .getText().toString().trim(), tilPasswordRegister
//                .getEditText().getText().toString().trim()));

        Button mConfirmButton = view.findViewById(R.id.register_confirm_button);
        mBuilder.setView(view);
        mAlertDialog = mBuilder.create();
        mAlertDialog.show();
        mConfirmButton.setOnClickListener(v -> {

            User user = new User(tilUsernameRegister.getEditText().getText().toString(),
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
        mCustomLoginButton.startAnimation();
    }

    @Override
    public void hideLoading() {
        mCustomLoginButton.doneLoadingAnimation(Color.parseColor("#dc4e41"),
                BitmapFactory.decodeResource(getResources(), R.drawable.ic_done_white_48dp));
    }

    @Override
    public void showError(Throwable throwable) {
        mCustomLoginButton.revertAnimation();
        String errorExplained = throwable.getMessage().trim();
        switch (errorExplained) {
            case "401":
                errorExplained = "Invalid credentials. Check your email and password.";
                break;
        }
        Toast.makeText(this, "Error: " + errorExplained, Toast.LENGTH_LONG)
                .show();
    }

    @Override
    public void navigateToHome(User user) {
        Intent intent = new Intent(this, ListActivity.class);
        intent.putExtra(Constants.USER_OBJ_EXTRA, user);
        startActivity(intent);
        finish();
    }

//    public boolean setErrors(List<ErrorCode> errors, TextInputLayout username,
//                             TextInputLayout password, TextInputLayout confirmPassword) {
//
//        int errorCount = 0;
//
//        for (int i = 0; i < 2; i++) {
//
//            switch (i) {
//                case 0:
//                    if (errors.get(0).equals(ErrorCode.USERNAME_OK))
//                        username.setError(null);
//                    else {
//                        username.setError(errors
//                                .get(0).getLabel(Objects.requireNonNull(getBaseContext())));
//                        errorCount++;
//                    }
//
//                case 1:
//                    if (errors.get(1).equals(ErrorCode.PASSWORD_OK))
//                        password.setError(null);
//                    else {
//                        password.setError(errors.get(1)
//                                .getLabel(Objects.requireNonNull(getBaseContext())));
//                        errorCount++;
//                    }
//                case 2:
//                    if (errors.get(2).equals(ErrorCode.PASSWORD_OK))
//                        confirmPassword.setError(null);
//                    else {
//                        confirmPassword.setError(errors.get(2)
//                                .getLabel(Objects.requireNonNull(getBaseContext())));
//                        errorCount++;
//                    }
//            }
//
//        }
//        errors.clear();
//        return errorCount == 0;
//    }

}
