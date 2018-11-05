package com.example.nekr0s.get_my_driver_card.views.create;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.nekr0s.get_my_driver_card.R;
import com.example.nekr0s.get_my_driver_card.models.User;
import com.example.nekr0s.get_my_driver_card.views.create.base.UserHolder;
import com.example.nekr0s.get_my_driver_card.views.create.fragments.ExchangeFragment;
import com.example.nekr0s.get_my_driver_card.views.create.fragments.NewCardFragment;
import com.example.nekr0s.get_my_driver_card.views.create.fragments.RenewFragment;
import com.example.nekr0s.get_my_driver_card.views.create.fragments.ReplaceFragment;

import butterknife.ButterKnife;

public class CardCreateActivity extends AppCompatActivity implements UserHolder {

    public static final String EXTRA_KEY = "NAVIGATE_EXTRA";
    public static final String CURRENT_USER = "CURRENT_USER";

    private Fragment mFragment;
    private User mLoggedInUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_request);

        ButterKnife.bind(this);

        // Get intent
        Intent intent = getIntent();
        String whichButtonIsClicked = intent.getStringExtra(EXTRA_KEY);
        mLoggedInUser = (User) intent.getSerializableExtra(CURRENT_USER);

        openFragment(whichButtonIsClicked);

    }

    private void openFragment(String fragmentName) {
        whichFragmentToOpen(fragmentName);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right,
                R.anim.enter_from_right, R.anim.exit_to_right);
        transaction.add(R.id.fragment_container, mFragment, "new_fragment").commit();
    }

    public User getLoggedInUser() {
        return mLoggedInUser;
    }

    // Maybe Reflection
    private void whichFragmentToOpen(String fragmentName) {
        switch (fragmentName) {
            case "NEW CARD":
            case "ИСКАМ НОВА КАРТА":
                mFragment = NewCardFragment.newInstance();
                break;
            case "EXCHANGE":
            case "РАЗМЯНА НА КАРТА":
                mFragment = ExchangeFragment.newInstance();
                break;
            case "REPLACEMENT":
            case "ЗАМЯНА НА КАРТА":
                mFragment = ReplaceFragment.newInstance();
                break;
            case "RENEWAL":
            case "ОБНОВЛЕНИЕ":
                mFragment = RenewFragment.newInstance();
                break;
        }
    }

    @Override
    public User getCurrentUser() {
        return mLoggedInUser;
    }
}
