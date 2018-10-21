package com.example.nekr0s.get_my_driver_card.views.requestinfo;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.example.nekr0s.get_my_driver_card.R;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.view.View.*;

public class RequestInfoActivity extends AppCompatActivity implements NewCardInfoFragment.OnFragmentInteractionListener {


    @BindView(R.id.fragment_container)
    FrameLayout fragmentContainer;

    @BindView(R.id.edittext)
    EditText edittext;

    @BindView(R.id.button)
    Button button;

   // private CardInfoPresenter mPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_info);
        ButterKnife.bind(this);
       // mPresenter = new CardInfoPresenter((MvpView) this);

        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = edittext.getText().toString();
                openFragment(text);
            }

        });

    }

    private void openFragment(String text) {
        NewCardInfoFragment fragment = NewCardInfoFragment.newInstance(text);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_right);
        transaction.addToBackStack(null);
        transaction.add(R.id.fragment_container, fragment, "NewCardInfoFragment").commit();
    }

    @Override
    public void onFragmentInteraction(String sendBackText) {
        edittext.setText(sendBackText);
        onBackPressed();
    }
}
