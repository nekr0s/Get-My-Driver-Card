package com.example.nekr0s.get_my_driver_card.views.list;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.example.nekr0s.get_my_driver_card.utils.Constants;
import com.example.nekr0s.get_my_driver_card.R;
import com.example.nekr0s.get_my_driver_card.models.User;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import com.example.nekr0s.get_my_driver_card.utils.translator.Translator;
import com.example.nekr0s.get_my_driver_card.views.requestinfo.RequestInfoActivity;

public class ListActivity extends AppCompatActivity {

    @BindView(R.id.userToolbar)
    android.support.v7.widget.Toolbar toolbar;

    @BindView(R.id.test_button)
    Button mTestButton;

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
    @OnClick(R.id.test_button)
    public void openInfoActivity(){
        Intent intent = new Intent(this,RequestInfoActivity.class);
        startActivity(intent);

    }
}
