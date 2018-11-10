package com.example.nekr0s.get_my_driver_card.views.list;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nekr0s.get_my_driver_card.GetMyDriverCardApplication;
import com.example.nekr0s.get_my_driver_card.R;
import com.example.nekr0s.get_my_driver_card.models.Request;
import com.example.nekr0s.get_my_driver_card.models.User;
import com.example.nekr0s.get_my_driver_card.utils.Constants;
import com.example.nekr0s.get_my_driver_card.utils.enums.RequestStatus;
import com.example.nekr0s.get_my_driver_card.utils.notifications.MyFCMClass;
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

public class ListActivity extends AppCompatActivity implements ListContracts.View,
        RequestsAdapter.OnItemClickListener, AdapterView.OnItemSelectedListener {

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

    @BindView(R.id.spinner_id)
    Spinner mDropDownSpinner;

    @BindView(R.id.spinner_layout)
    RelativeLayout mSpinnerLayout;


    private User mUser;
    private ListContracts.Presenter mPresenter;
    private RequestsAdapter mRequestsAdapter;
    private BroadcastReceiver mReceiver;

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

        mFloatingActionMenu.bringToFront();

        // Toolbar
        setSupportActionBar(toolbar);
        ImageView i = toolbar.findViewById(R.id.toolbar_logout);
        i.setOnClickListener(item -> logoutCurrentUser());

        // Spinner adapter
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource
                (this, R.array.sort_by_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mDropDownSpinner.setAdapter(adapter);
        mDropDownSpinner.setOnItemSelectedListener(this);

        // Receiver
        mReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                try {
                    long requestId = intent.getLongExtra("requestId", 0);
                    RequestStatus requestStatus = (RequestStatus) intent.getSerializableExtra("requestStatus");
                    mRequestsAdapter.updateStatus(requestId, requestStatus);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
    }

    @Override
    protected void onStart() {
        super.onStart();
        LocalBroadcastManager.getInstance(this).registerReceiver((mReceiver), new IntentFilter(MyFCMClass.REQUEST_ACCEPT));
    }

    @Override
    protected void onStop() {
        super.onStop();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mReceiver);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.subscribe(this);
        if (mRequestsAdapter.getItemCount() > 0) {
            return;
        }
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
        mSpinnerLayout.setVisibility(View.VISIBLE);
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
        mSpinnerLayout.setVisibility(View.GONE);
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
        if (mUser.getRoles().size() > 1) intent.putExtra(RequestPreviewActivity.IS_ADMIN, true);
        startActivityForResult(intent, RequestPreviewActivity.AWAIT_STATUS_CHANGE);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (mRequestsAdapter.isEmpty()) return;
        String item = parent.getItemAtPosition(position).toString();
        switch (item) {
            case "STATUS":
                mRequestsAdapter.sortByStatus();
                break;
            case "DATE":
                mRequestsAdapter.sortByDate();
                break;
            case "TYPE":
                mRequestsAdapter.sortByType();
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == RequestPreviewActivity.AWAIT_STATUS_CHANGE) {
            if (resultCode == Activity.RESULT_OK) {
                long requestId = data.getLongExtra("requestId", 0);
                RequestStatus requestStatus = (RequestStatus) data.getSerializableExtra("requestStatus");
                mRequestsAdapter.updateStatus(requestId, requestStatus);
            }
        }
    }

}

