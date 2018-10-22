package com.example.nekr0s.get_my_driver_card.views.create;

public interface CardInfoContract {

    interface MvpView{
        void setPresenter(CardInfoPresenter presenter);


    }

    interface Presenter{
       void setView(MvpView view);

    }
}
