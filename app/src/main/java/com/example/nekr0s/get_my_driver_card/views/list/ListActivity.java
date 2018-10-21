package com.example.nekr0s.get_my_driver_card.views.list;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.nekr0s.get_my_driver_card.utils.Constants;
import com.example.nekr0s.get_my_driver_card.R;
import com.example.nekr0s.get_my_driver_card.models.User;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import com.example.nekr0s.get_my_driver_card.utils.translator.Translator;
import com.example.nekr0s.get_my_driver_card.views.requestinfo.RequestInfoActivity;
import com.github.clans.fab.FloatingActionButton;

public class ListActivity extends AppCompatActivity {

    @BindView(R.id.userToolbar)
    android.support.v7.widget.Toolbar toolbar;


    @BindView(R.id.menu_item)
    FloatingActionButton mFAB_Renew;

    @BindView(R.id.menu_item1)
    FloatingActionButton mFAB_Replace;

    @BindView(R.id.menu_item2)
    FloatingActionButton mFAB_Exchange;

    @BindView(R.id.menu_item3)
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
        @OnClick(R.id.menu_item3)
        public void openInfoActivity(){
            Intent intent = new Intent(this,RequestInfoActivity.class);
            startActivity(intent);

    }
}
