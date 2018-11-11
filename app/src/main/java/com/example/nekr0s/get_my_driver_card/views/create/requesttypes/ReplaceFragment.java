package com.example.nekr0s.get_my_driver_card.views.create.requesttypes;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.nekr0s.get_my_driver_card.R;
import com.example.nekr0s.get_my_driver_card.models.Reason;
import com.example.nekr0s.get_my_driver_card.models.User;
import com.example.nekr0s.get_my_driver_card.utils.Constants;
import com.example.nekr0s.get_my_driver_card.utils.enums.RequestReason;
import com.example.nekr0s.get_my_driver_card.utils.enums.RequestType;
import com.example.nekr0s.get_my_driver_card.views.create.adapter.ReasonsAdapter;
import com.example.nekr0s.get_my_driver_card.views.create.base.UserHolder;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ReplaceFragment extends Fragment {

    @BindView(R.id.reason_list_view)
    ListView listView;

    @BindView(R.id.replace_date)
    EditText mEditTextLostDate;

    @BindView(R.id.replace_place)
    EditText mEditTextLostPlace;

    @BindView(R.id.replace_reason_button)
    Button mNextButton;

    private int mPreselectedIndex = -1;
    final ReasonsAdapter mAdapter = new ReasonsAdapter(getActivity());


    public ReplaceFragment() {
        // Required empty public constructor
    }

    public static Fragment newInstance() {
        return new ReplaceFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_replace, container, false);

        ButterKnife.bind(this, view);

        listView.setAdapter(mAdapter);

        listView
                .setOnItemClickListener((adapterView, view1, position, id)
                        -> checkBoxLogic(mAdapter.getItem(position), mAdapter, position));

        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Objects.requireNonNull(getActivity()).finish();
    }

    private void checkBoxLogic(Reason reason, ReasonsAdapter adapter, int position) {
        if (reason.isSelected()) {
            reason.setSelected(false);
            mEditTextLostDate.setVisibility(View.GONE);
            mEditTextLostPlace.setVisibility(View.GONE);
            reactivateButton(false);
        } else {
            reason.setSelected(true);
            displayForms(reason.getRequestReasonString());
            reactivateButton(true);
        }

        adapter.update(position, reason);

        if (mPreselectedIndex > -1) {
            Reason preRecord = adapter.getItem(mPreselectedIndex);
            preRecord.setSelected(false);

            adapter.update(mPreselectedIndex, preRecord);
        }

        mPreselectedIndex = (position == mPreselectedIndex) ? -1 : position;
    }

    private boolean isLostOrStolen(String reasonName) {
        return reasonName.contains("lost") || reasonName.contains("stolen") ||
                reasonName.contains("открадната") || reasonName.contains("Загубих");
    }

    private void displayForms(String reasonName) {
        if (isLostOrStolen(reasonName)) {
            mEditTextLostDate.setVisibility(View.VISIBLE);
            mEditTextLostPlace.setVisibility(View.VISIBLE);
        } else {
            mEditTextLostDate.setVisibility(View.GONE);
            mEditTextLostPlace.setVisibility(View.GONE);
        }
    }

    // TODO: have to get that intent in PreviousCardInfoActivity;
    @OnClick(R.id.replace_reason_button)
    void openNextActivity() {
        Intent intent = new Intent(getActivity(), PreviousCardInfoActivity.class);
        User user = ((UserHolder) getActivity()).getCurrentUser();
        intent.putExtra(Constants.USER_OBJ_EXTRA, user);
        intent.putExtra(Constants.REQUEST_TYPE, RequestType.TYPE_REPLACE);
        RequestReason requestReason = mAdapter.getItem(mPreselectedIndex).getRequestReason();
        intent.putExtra(Constants.REPLACE_REASON, requestReason);
        if (requestReason == RequestReason.REASON_LOST || requestReason == RequestReason.REASON_STOLEN) {
            intent.putExtra(Constants.REPLACE_DATE_LOST_OR_STOLEN, mEditTextLostDate.getText().toString().trim());
            intent.putExtra(Constants.REPLACE_PLACE_LOST_OR_STOLEN, mEditTextLostPlace.getText().toString().trim());
        }
        startActivity(intent);
    }

    private void reactivateButton(boolean isSelected) {
        if (isSelected) mNextButton.setEnabled(true);
        else mNextButton.setEnabled(false);
    }
}
