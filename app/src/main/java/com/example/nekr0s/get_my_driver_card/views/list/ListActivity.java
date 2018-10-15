package com.example.nekr0s.get_my_driver_card.views.list;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toolbar;

import com.example.nekr0s.get_my_driver_card.Constants;
import com.example.nekr0s.get_my_driver_card.R;
import com.example.nekr0s.get_my_driver_card.models.User;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListActivity extends AppCompatActivity {

    @BindView(R.id.userToolbar)
    android.support.v7.widget.Toolbar toolbar;

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
}
