package com.example.nekr0s.get_my_driver_card.views.create;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.nekr0s.get_my_driver_card.R;
import com.example.nekr0s.get_my_driver_card.views.create.fragments.ExchangeFragment;
import com.example.nekr0s.get_my_driver_card.views.create.fragments.NewCardFragment;
import com.example.nekr0s.get_my_driver_card.views.create.fragments.RenewFragment;
import com.example.nekr0s.get_my_driver_card.views.create.fragments.ReplaceFragment;

import butterknife.ButterKnife;

public class RequestInfoActivity extends AppCompatActivity {

    public static final String EXTRA_KEY = "NAVIGATE_EXTRA";

    private Fragment mFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_info);

        ButterKnife.bind(this);

        Intent intent = getIntent();
        String whichButtonIsClicked = intent.getStringExtra(EXTRA_KEY);

        openFragment(whichButtonIsClicked);

    }

    private void openFragment(String fragmentName) {
        whichFragmentToOpen(fragmentName);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_right);
        transaction.addToBackStack(null);
        transaction.add(R.id.fragment_container, mFragment, "new_fragment").commit();
    }

    private void whichFragmentToOpen(String fragmentName) {
        switch (fragmentName) {
            case "NEW CARD":
                mFragment = NewCardFragment.newInstance();
                break;
            case "EXCHANGE":
                mFragment = ExchangeFragment.newInstance();
                break;
            case "REPLACE":
                mFragment = ReplaceFragment.newInstance();
                break;
            case "RENEW":
                mFragment = RenewFragment.newInstance();
                break;
        }
    }

}
