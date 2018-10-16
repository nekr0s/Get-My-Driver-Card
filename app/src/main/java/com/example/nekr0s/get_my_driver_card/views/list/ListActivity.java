package com.example.nekr0s.get_my_driver_card.views.list;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.nekr0s.get_my_driver_card.constants.Constants;
import com.example.nekr0s.get_my_driver_card.R;
import com.example.nekr0s.get_my_driver_card.models.User;

import butterknife.BindView;
import butterknife.ButterKnife;

import com.example.nekr0s.get_my_driver_card.models.translator.Translator;

public class ListActivity extends AppCompatActivity {

    @BindView(R.id.userToolbar)
    android.support.v7.widget.Toolbar toolbar;

    Translator translator = new Translator();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        ButterKnife.bind(this);

        // Get intent
        User user = (User) getIntent().getSerializableExtra(Constants.USER_OBJ_EXTRA);

        // Toolbar
        setSupportActionBar(toolbar);

        String input = "тестче";
        String result = translator.translate(input);
    }
}
