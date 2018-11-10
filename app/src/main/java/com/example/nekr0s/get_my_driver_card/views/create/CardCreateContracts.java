package com.example.nekr0s.get_my_driver_card.views.create;

import android.support.design.widget.TextInputLayout;

import com.example.nekr0s.get_my_driver_card.models.Request;
import com.example.nekr0s.get_my_driver_card.utils.enums.ErrorCode;

import java.util.Map;
import java.util.Set;

public interface CardCreateContracts {

    interface View {
        void setPresenter(Presenter presenter);

        void setRegisterErrors(Map<String, TextInputLayout> tils);

    }

    interface Presenter {
        void subscribe(View view);

        void unsubscribe();

        Set<ErrorCode> checkFields(Map<String, String> allFields);

        Set<ErrorCode> checkFieldsExchange(Map<String, String> allFields);


        void save(Request request);
    }

    public interface Navigator {
        void navigateWitch();
    }
}
