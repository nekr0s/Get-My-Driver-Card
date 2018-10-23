package com.example.nekr0s.get_my_driver_card.views.create;

import com.example.nekr0s.get_my_driver_card.models.Request;

public interface CardCreateContracts {

    interface View {
        void setPresenter(Presenter presenter);

    }

    interface Presenter {
        void subscribe(View view);

        void unsubscribe();

        void save(Request request);
    }

    public interface Navigator {
        void navigateWitch();
    }
}
