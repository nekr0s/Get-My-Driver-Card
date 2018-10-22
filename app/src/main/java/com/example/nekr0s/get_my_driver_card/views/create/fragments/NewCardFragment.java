package com.example.nekr0s.get_my_driver_card.views.create.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nekr0s.get_my_driver_card.R;

public class NewCardFragment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


    public NewCardFragment() {
        // Required empty public constructor
    }


    public static NewCardFragment newInstance() {
        return new NewCardFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_card_info, container, false);
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
        getActivity().finish();
    }

}
