package com.example.nekr0s.get_my_driver_card.views.create.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.nekr0s.get_my_driver_card.R;
import com.example.nekr0s.get_my_driver_card.models.Reason;
import com.example.nekr0s.get_my_driver_card.models.User;
import com.example.nekr0s.get_my_driver_card.utils.Constants;
import com.example.nekr0s.get_my_driver_card.utils.enums.RequestReason;
import com.example.nekr0s.get_my_driver_card.views.create.adapter.ReasonsAdapter;
import com.example.nekr0s.get_my_driver_card.views.create.base.UserHolder;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class RenewFragment extends Fragment {


    public RenewFragment() {
        // Required empty public constructor
    }

    public static Fragment newInstance() {
        return new RenewFragment();
    }

    @BindView(R.id.renew_top_text)
    TextView mRenewTopText;

    @BindView(R.id.renew_checkbox_list)
    ListView mRenewCheckboxList;

    @BindView(R.id.renew_next_button)
    Button mNextButton;

    private int mPreselectedIndex = -1;
    private ReasonsAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_renew, container, false);

        ButterKnife.bind(this, view);

        mAdapter = new ReasonsAdapter(getActivity(), Arrays.asList(
                new Reason(false, RequestReason.REASON_DUE_TO_EXPIRE, getString(R.string.duetoexpire)),
                new Reason(false, RequestReason.REASON_EXPIRED, getString(R.string.card_has_expired)),
                new Reason(false, RequestReason.REASON_SUSPENDED, getString(R.string.card_is_suspended)),
                new Reason(false, RequestReason.REASON_WITHDRAWN, getString(R.string.card_is_withdrawn))
        ));
        mRenewCheckboxList.setAdapter(mAdapter);

        mRenewCheckboxList
                .setOnItemClickListener((parent, view1, position, id) ->
                        checkBoxLogic(mAdapter.getItem(position), mAdapter, position));

        return view;
    }

    // Have to rethink that one, its repeating code between 2 fragments
    private void checkBoxLogic(Reason reason, ReasonsAdapter adapter, int position) {
        if (reason.isSelected()) {
            reason.setSelected(false);
            reactivateButton(false);
        } else {
            reason.setSelected(true);
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

    private void reactivateButton(boolean isSelected) {
        if (isSelected) mNextButton.setEnabled(true);
        else mNextButton.setEnabled(false);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        getActivity().finish();
    }

    @OnClick(R.id.renew_next_button)
    void openNextActivity() {
        Intent intent = new Intent(getActivity(), PreviousCardInfoActivity.class);
        User user = ((UserHolder) getActivity()).getCurrentUser();
        intent.putExtra(Constants.RENEWAL_REASON, mAdapter.getItem(mPreselectedIndex).getRequestReason());
        intent.putExtra(Constants.USER_OBJ_EXTRA, user);
        startActivity(intent);
    }
}
