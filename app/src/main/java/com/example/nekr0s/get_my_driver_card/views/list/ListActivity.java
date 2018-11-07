package com.example.nekr0s.get_my_driver_card.views.list;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nekr0s.get_my_driver_card.GetMyDriverCardApplication;
import com.example.nekr0s.get_my_driver_card.R;
import com.example.nekr0s.get_my_driver_card.models.Request;
import com.example.nekr0s.get_my_driver_card.models.User;
import com.example.nekr0s.get_my_driver_card.utils.Constants;
import com.example.nekr0s.get_my_driver_card.views.create.CardCreateActivity;
import com.example.nekr0s.get_my_driver_card.views.list.recycler.RequestsAdapter;
import com.example.nekr0s.get_my_driver_card.views.login.LoginActivity;
import com.example.nekr0s.get_my_driver_card.views.preview.RequestPreviewActivity;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ListActivity extends AppCompatActivity implements ListContracts.View, RequestsAdapter.OnItemClickListener {

    private static final String TOPIC = "GetMyDriverCard";
    @BindView(R.id.userToolbar)
    android.support.v7.widget.Toolbar toolbar;

    @BindView(R.id.floating_action_menu)
    FloatingActionMenu mFloatingActionMenu;

    @BindView(R.id.rv_requests)
    RecyclerView mRecyclerView;

    @BindView(R.id.empty_request_list_textview)
    TextView mEmptyListTextView;

    @BindView(R.id.renew)
    FloatingActionButton mFAB_Renew;

    @BindView(R.id.replace)
    FloatingActionButton mFAB_Replace;

    @BindView(R.id.exchange)
    FloatingActionButton mFAB_Exchange;

    @BindView(R.id.new_card)
    FloatingActionButton mFAB_NewCard;

    private User mUser;
    private ListContracts.Presenter mPresenter;
    private RequestsAdapter mRequestsAdapter;
    private ListContracts.Navigator mNavigator;

    //    Translator translator = new Translator();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        ButterKnife.bind(this);

        // Subscribe for notifications
        FirebaseMessaging.getInstance().subscribeToTopic(TOPIC);

        // Presenter
        mPresenter = new ListPresenter(this);
        mRequestsAdapter = new RequestsAdapter();
        mRequestsAdapter.setOnRequestClickListener(this);
        mRecyclerView.setAdapter(mRequestsAdapter);
        LinearLayoutManager mContactsViewLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mContactsViewLayoutManager);

        // Get intent
        mUser = (User) getIntent().getSerializableExtra(Constants.USER_OBJ_EXTRA);
        toolbar.setTitle(mUser.getUsername());

        // Toolbar
        setSupportActionBar(toolbar);
        ImageView i = toolbar.findViewById(R.id.toolbar_logout);
        i.setOnClickListener(item -> logoutCurrentUser());

    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.subscribe(this);
        if (mUser.getRoles().size() > 1) {
            mFloatingActionMenu.setVisibility(View.GONE);
            mPresenter.loadRequestsAdmin();
        } else mPresenter.loadRequestsUser(mUser.getId());
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPresenter.unsubscribe();
    }

    // One onclick method for all fab buttons
    @OnClick({R.id.new_card, R.id.exchange, R.id.replace, R.id.renew})
    public void openInfoActivityNew(FloatingActionButton fab) {
        Intent intent = new Intent(this, CardCreateActivity.class);
        intent.putExtra(CardCreateActivity.EXTRA_KEY, fab.getLabelText());
        intent.putExtra(CardCreateActivity.CURRENT_USER, mUser);
        startActivity(intent);
    }

    @Override
    public void setPresenter(ListContracts.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showRequests(List<Request> requestList) {
        mRequestsAdapter.clear();
        mRequestsAdapter.addAll(requestList);
        mRequestsAdapter.notifyDataSetChanged();
        mEmptyListTextView.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showRequestPreview(Request request) {
        Intent intent = new Intent(this, RequestPreviewActivity.class);
        intent.putExtra(RequestPreviewActivity.FROM_LIST, request);
        startActivity(intent);
    }

    @Override
    public void showEmptyRequestList() {
        mRecyclerView.setVisibility(View.GONE);
        mEmptyListTextView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showLoading() {
        Toast.makeText(this, "Loading Requests..", Toast.LENGTH_LONG).show();
    }

    @Override
    public void hideLoading() {
        Toast.makeText(this, "Done!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showError(Throwable throwable) {
        Toast.makeText(this, "Error: " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
    }

    private boolean logoutCurrentUser() {
        FirebaseMessaging.getInstance().unsubscribeFromTopic(TOPIC);
        GetMyDriverCardApplication.getCookieJar(this).clear();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
        return true;
    }

    @Override
    public void onClick(Request request) {
        Intent intent = new Intent(this, RequestPreviewActivity.class);
        intent.putExtra(RequestPreviewActivity.FROM_LIST, request);
        startActivity(intent);
    }

}

