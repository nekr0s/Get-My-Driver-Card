package com.example.nekr0s.get_my_driver_card.views.requestinfo;

public class CardInfoPresenter implements CardInfoContract {
    private CardInfoContract.MvpView mView;

    CardInfoPresenter(CardInfoContract.MvpView view){
        mView = view;
    }
}
