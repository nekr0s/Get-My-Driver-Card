package com.example.nekr0s.get_my_driver_card.views.list;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.nekr0s.get_my_driver_card.R;
import com.example.nekr0s.get_my_driver_card.models.User;
import com.example.nekr0s.get_my_driver_card.utils.Constants;
import com.example.nekr0s.get_my_driver_card.views.create.RequestInfoActivity;
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

    @OnClick({R.id.new_card})
    public void openInfoActivityNew() {
        Intent intent = new Intent(this, RequestInfoActivity.class);
        intent.putExtra(RequestInfoActivity.EXTRA_KEY, mFAB_NewCard.getLabelText());
        startActivity(intent);
    }

    @OnClick({R.id.exchange})
    public void openInfoActivityExchange() {
        Intent intent = new Intent(this, RequestInfoActivity.class);
        intent.putExtra(RequestInfoActivity.EXTRA_KEY, mFAB_Exchange.getLabelText());
        startActivity(intent);
    }

    @OnClick({R.id.replace})
    public void openInfoActivityReplace() {
        Intent intent = new Intent(this, RequestInfoActivity.class);
        intent.putExtra(RequestInfoActivity.EXTRA_KEY, mFAB_Replace.getLabelText());
        startActivity(intent);
    }

    @OnClick({R.id.renew})
    public void openInfoActivityRenew() {
        Intent intent = new Intent(this, RequestInfoActivity.class);
        intent.putExtra(RequestInfoActivity.EXTRA_KEY, mFAB_Renew.getLabelText());
        startActivity(intent);
    }


}

