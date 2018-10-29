package com.example.nekr0s.get_my_driver_card.views.login;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nekr0s.get_my_driver_card.R;
import com.example.nekr0s.get_my_driver_card.async.AsyncSchedulerProvider;
import com.example.nekr0s.get_my_driver_card.models.User;
import com.example.nekr0s.get_my_driver_card.services.HttpUsersService;
import com.example.nekr0s.get_my_driver_card.utils.BCrypt;
import com.example.nekr0s.get_my_driver_card.utils.Constants;
import com.example.nekr0s.get_my_driver_card.utils.enums.ErrorCode;
import com.example.nekr0s.get_my_driver_card.views.list.ListActivity;

import org.springframework.http.HttpAuthentication;
import org.springframework.http.HttpBasicAuthentication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
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


    SmartUser mCurrentUser;
    SmartLoginConfig mConfig;
    SmartLogin mSmartLogin;
    private LoginContracts.Presenter mPresenter;
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
        Toast.makeText(this, user.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoginFailure(SmartLoginException e) {
        Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public SmartUser doCustomLogin() {
        new FetchSecuredResourceTask().execute();
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
        User customUser = new User(user.getEmail(), "");
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
        mConfirmButton.setOnClickListener(v -> {
            if (!validateEmail(mTIL_email_register) |
                    !validatePassword(mTIL_password_register, mTIL_password_confirm) |
                    !validatePasswordTwo(mTIL_password_confirm, mTIL_password_register)) {
                return;
            }

            User user = new User(mEmailEditText.getText().toString(),
                    BCrypt.hashpw(mPasswordEditText.getText().toString(), BCrypt.gensalt()));
            mPresenter.register(user);


        });
    }


    @Override
    public void setPresenter(LoginContracts.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showLoading() {
        Toast.makeText(this, "Loading...", Toast.LENGTH_LONG).show();
    }

    @Override
    public void hideLoading() {
        Toast.makeText(this, "Done.", Toast.LENGTH_SHORT).show();
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

    private boolean validatePassword(TextInputLayout password, TextInputLayout passwordtwo) {
        String passwordInput = Objects.requireNonNull(password.getEditText()).getText().toString().trim();
        String passwordInputtwo = Objects.requireNonNull(passwordtwo.getEditText()).getText().toString().trim();

        if (passwordInput.isEmpty()) {
            password.setError(ErrorCode.PASSWORD_NULL.getLabel(getBaseContext()));
            return false;
        } else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()) {
            password.setError(ErrorCode.PASSWORD_TOO_SIMPLE.getLabel(getBaseContext()));
            return false;
        } else if (!passwordInput.equals(passwordInputtwo)) {
            password.setError(ErrorCode.PASSWORDS_DONT_MATCH.getLabel(getBaseContext()));
            passwordtwo.setError(ErrorCode.PASSWORDS_DONT_MATCH.getLabel(getBaseContext()));
            return false;
        } else password.setError(null);
        passwordtwo.setError(null);
        return true;
    }

    private boolean validatePasswordTwo(TextInputLayout passwordtwo, TextInputLayout password) {
        String passwordInput = Objects.requireNonNull(passwordtwo.getEditText()).getText().toString().trim();
        String passwordInputTwo = Objects.requireNonNull(password.getEditText()).getText().toString().trim();

        if (passwordInput.isEmpty()) {
            passwordtwo.setError(ErrorCode.PASSWORD_NULL.getLabel(getBaseContext()));
            return false;
        } else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()) {
            passwordtwo.setError(ErrorCode.PASSWORD_TOO_SIMPLE.getLabel(getBaseContext()));
            return false;
        } else if (!passwordInput.equals(passwordInputTwo)) {
            passwordtwo.setError(ErrorCode.PASSWORDS_DONT_MATCH.getLabel(getBaseContext()));
            password.setError(ErrorCode.PASSWORDS_DONT_MATCH.getLabel(getBaseContext()));
            return false;
        } else passwordtwo.setError(null);
        passwordtwo.setError(null);
        return true;
    }


    // Private class
    private class FetchSecuredResourceTask extends AsyncTask<Void, Void, User> {

        private String email;
        private String password;

        @Override
        protected void onPreExecute() {
            showLoading();
            this.email = mEmailEditText.getText().toString();
            this.password = mPasswordEditText.getText().toString();
        }

        @Override
        protected User doInBackground(Void... voids) {
            final String url = Constants.BASE_SERVER_URL + "/users/me";

            // Populate
            HttpAuthentication authHeader = new HttpBasicAuthentication(email, password);
            HttpHeaders requestHeaders = new HttpHeaders();
            requestHeaders.setAuthorization(authHeader);
            requestHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

            // Create rest template
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

            try {
                // Make the network request
                Log.d("Trying network REQ", url);
                ResponseEntity<User> responseEntity = restTemplate
                        .exchange(url, HttpMethod.GET, new HttpEntity<>(requestHeaders),
                                User.class);
                return responseEntity.getBody();
            } catch (HttpClientErrorException e) {
                Log.e("First catch", e.getLocalizedMessage(), e);
                return null;
            } catch (ResourceAccessException e) {
                Log.e("QWERTY", e.getLocalizedMessage());
                return null;
            }

        }


        @Override
        protected void onPostExecute(User user) {
            if (user == null) {
                Log.d("FAILFAILFAIL", "NOTGOOD");
                Toast.makeText(LoginActivity.this, "Error", Toast.LENGTH_LONG).show();
            } else {
                Log.d("ALLCOOL", "ALLLLGOOOOD");
                Toast.makeText(LoginActivity.this, "All Cool " + user.getEmail(), Toast.LENGTH_SHORT).show();
            }
        }
    }

}
