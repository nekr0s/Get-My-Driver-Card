package com.example.nekr0s.get_my_driver_card.views.create.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nekr0s.get_my_driver_card.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ExchangeFragment extends Fragment {


    public ExchangeFragment() {
        // Required empty public constructor
    }

    public static Fragment newInstance() {
        return new ExchangeFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_exchange2, container, false);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        getActivity().finish();
    }

}
