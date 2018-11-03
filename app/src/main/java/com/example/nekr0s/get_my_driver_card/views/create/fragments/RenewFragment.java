package com.example.nekr0s.get_my_driver_card.views.create.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.button.MaterialButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.nekr0s.get_my_driver_card.R;
import com.example.nekr0s.get_my_driver_card.models.Reason;
import com.example.nekr0s.get_my_driver_card.views.create.adapter.ReasonsAdapter;
import com.example.nekr0s.get_my_driver_card.views.create.attatchments.DocumentsActivity;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
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
    MaterialButton mNextButton;

    private int mPreselectedIndex = -1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_renew, container, false);

        ButterKnife.bind(this, view);

        final ReasonsAdapter adapter = new ReasonsAdapter(getActivity(), Arrays.asList(
                new Reason(false, getString(R.string.duetoexpire)),
                new Reason(false, getString(R.string.card_has_expired)),
                new Reason(false, getString(R.string.card_is_suspended)),
                new Reason(false, getString(R.string.card_is_withdrawn))
        ));
        mRenewCheckboxList.setAdapter(adapter);

        mRenewCheckboxList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                checkBoxLogic(adapter.getItem(position), adapter, position);
            }
        });

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

        mPreselectedIndex = position;
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


        //only for test
//        Intent intent = new Intent(getActivity(), MyCameraActivity.class);
//        startActivity(intent);


        //only for test
        Intent intent = new Intent(getActivity(), DocumentsActivity.class);
        startActivity(intent);

        //only for test
//        Intent intent = new Intent(getActivity(), DeclarationActivity.class);
//        startActivity(intent);

        // only for test
//        Intent intent = new Intent(getActivity(), RequestPreviewActivity.class);
//        startActivity(intent);

//        //the real deal
//        Intent intent = new Intent(getActivity(), PreviousCardInfoActivity.class);
//        startActivity(intent);


    }
}
