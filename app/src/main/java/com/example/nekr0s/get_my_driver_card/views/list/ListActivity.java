package com.example.nekr0s.get_my_driver_card.views.list;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.nekr0s.get_my_driver_card.R;
import com.example.nekr0s.get_my_driver_card.models.User;
import com.example.nekr0s.get_my_driver_card.utils.Constants;
import com.example.nekr0s.get_my_driver_card.views.create.CardCreateActivity;
import com.github.clans.fab.FloatingActionButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ListActivity extends AppCompatActivity {

    @BindView(R.id.userToolbar)
    android.support.v7.widget.Toolbar toolbar;


    @BindView(R.id.renew)
    FloatingActionButton mFAB_Renew;

    @BindView(R.id.replace)
    FloatingActionButton mFAB_Replace;

    @BindView(R.id.exchange)
    FloatingActionButton mFAB_Exchange;

    @BindView(R.id.new_card)
    FloatingActionButton mFAB_NewCard;

    //    Translator translator = new Translator();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        ButterKnife.bind(this);

        // Get intent
        User user = (User) getIntent().getSerializableExtra(Constants.USER_OBJ_EXTRA);

        // Toolbar
        setSupportActionBar(toolbar);

    }

    // One onclick method for all fab buttons
    @OnClick({R.id.new_card, R.id.exchange, R.id.replace, R.id.renew})
    public void openInfoActivityNew(FloatingActionButton fab) {
        Intent intent = new Intent(this, CardCreateActivity.class);
        intent.putExtra(CardCreateActivity.EXTRA_KEY, fab.getLabelText());
        startActivity(intent);
    }
}

