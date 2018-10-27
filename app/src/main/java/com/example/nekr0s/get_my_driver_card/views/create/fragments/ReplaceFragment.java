package com.example.nekr0s.get_my_driver_card.views.create.fragments;


import android.os.Bundle;
import android.support.design.button.MaterialButton;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.example.nekr0s.get_my_driver_card.R;
import com.example.nekr0s.get_my_driver_card.models.Reason;
import com.example.nekr0s.get_my_driver_card.views.create.adapter.ReasonsAdapter;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReplaceFragment extends Fragment {

    @BindView(R.id.reason_list_view)
    ListView listView;

    @BindView(R.id.replace_date)
    TextInputLayout mTIL_Date;

    @BindView(R.id.replace_place)
    TextInputLayout mTIL_Place;

    @BindView(R.id.replace_reason_button)
    MaterialButton mNextButton;

    private int mPreselectedIndex = -1;


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

        final ReasonsAdapter adapter = new ReasonsAdapter(getActivity());
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                checkBoxLogic(adapter.getItem(position), adapter, position);
            }
        });

        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Objects.requireNonNull(getActivity()).finish();
    }

    private void checkBoxLogic(Reason reason, ReasonsAdapter adapter, int position) {
        if (reason.isSelected()) reason.setSelected(false);
        else {
            reason.setSelected(true);
            displayForms(reason.getReasonName());
        }

        adapter.update(position, reason);

        if (mPreselectedIndex > -1) {
            Reason preRecord = adapter.getItem(mPreselectedIndex);
            preRecord.setSelected(false);

            adapter.update(mPreselectedIndex, preRecord);
        }

        mPreselectedIndex = position;
    }

    private boolean isLostOrStolen(String reasonName) {
        return reasonName.contains("lost") || reasonName.contains("stolen") || reasonName.contains("открадната") || reasonName.contains("Загубих");
    }

    private void displayForms(String reasonName) {
        if (isLostOrStolen(reasonName)) {
            mTIL_Date.setVisibility(View.VISIBLE);
            mTIL_Place.setVisibility(View.VISIBLE);
        } else {
            mTIL_Date.setVisibility(View.GONE);
            mTIL_Place.setVisibility(View.GONE);
        }
    }
}
