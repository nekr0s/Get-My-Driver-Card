package com.example.nekr0s.get_my_driver_card.views.create;

import android.content.Context;

import com.example.nekr0s.get_my_driver_card.GetMyDriverCardApplication;
import com.example.nekr0s.get_my_driver_card.async.base.SchedulerProvider;
import com.example.nekr0s.get_my_driver_card.models.Request;
import com.example.nekr0s.get_my_driver_card.services.base.Service;
import com.example.nekr0s.get_my_driver_card.utils.enums.ErrorCode;
import com.example.nekr0s.get_my_driver_card.validator.DateValidator;
import com.example.nekr0s.get_my_driver_card.validator.DigitsValidator;
import com.example.nekr0s.get_my_driver_card.validator.EmailValidator;
import com.example.nekr0s.get_my_driver_card.validator.NamesValidator;
import com.example.nekr0s.get_my_driver_card.validator.base.NameValidator;
import com.example.nekr0s.get_my_driver_card.validator.base.Validator;
import com.example.nekr0s.get_my_driver_card.validator.base.ValidatorDate;
import com.example.nekr0s.get_my_driver_card.validator.base.ValidatorDigits;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CardCreatePresenter implements CardCreateContracts.Presenter {
    private final Service mUsersService;
    private final SchedulerProvider mSchedulerProvider;
    private CardCreateContracts.View mView;
    private NameValidator mNameValidator;
    private ValidatorDigits mDigitsValidator;
    private ValidatorDate mDateValidator;
    private Validator mValidator;

    public CardCreatePresenter(Context context) {
        this.mUsersService = GetMyDriverCardApplication.getUsersService(context);
        this.mSchedulerProvider = GetMyDriverCardApplication.getSchedulerProvider();
        mNameValidator = new NamesValidator();
        mDigitsValidator = new DigitsValidator();
        mDateValidator = new DateValidator();
        mValidator = new EmailValidator();
    }

    @Override
    public void subscribe(CardCreateContracts.View view) {
        mView = view;
    }

    @Override
    public void unsubscribe() {
        mView = null;
    }

    @Override
    public void save(Request request) {

    }

    @Override
    public Set<ErrorCode> checkFields(Map<String, String> allFields) {

        Set<ErrorCode> errorCodes = new HashSet<>();

        errorCodes.add(mNameValidator.isFirstNameValid(allFields.get("firstName")));
        errorCodes.add(mNameValidator.isFirstNameCyrValid(allFields.get("firstNameCyr")));
        errorCodes.add(mNameValidator.isLastNameValid(allFields.get("lastName")));
        errorCodes.add(mNameValidator.isLastNameCyrValid(allFields.get("lastNameCyr")));
        errorCodes.add(mDigitsValidator.isPersonalNumberValid(allFields.get("personalNumber")));
        errorCodes.add(mNameValidator.isAddressValid(allFields.get("address")));
        errorCodes.add(mDigitsValidator.isPhoneNumberValid(allFields.get("phoneNumber")));
        errorCodes.add(mDateValidator.isDateValid(allFields.get("dateOfBirth")));
        errorCodes.add(mValidator.isValid(allFields.get("email")));
        return errorCodes;
    }

    @Override
    public Set<ErrorCode> checkFieldsExchange(Map<String, String> allFields) {

        Set<ErrorCode> errorCodes = new HashSet<>();

        errorCodes.add(mNameValidator.isEuCountryOfIssuingValid(allFields.get("countryOfIssuing")));
        errorCodes.add(mDigitsValidator.isTachNumberValid(allFields.get("tachNumber")));
        errorCodes.add(mNameValidator.isLicenseCountryOfIssuingValid(allFields.get("licenseCountry")));
        errorCodes.add(mDigitsValidator.isLicenseNumberValid(allFields.get("licenseNumber")));
        return errorCodes;
    }

    @Override
    public Set<ErrorCode> checkFieldsPreviousCard(Map<String, String> allFields) {

        Set<ErrorCode> errorCodes = new HashSet<>();

        errorCodes.add(mNameValidator.isEuCountryOfIssuingValid(allFields.get("countryOfIssuing")));
        errorCodes.add(mNameValidator.isIssuerAuthorityValid(allFields.get("authorityIssuer")));
        errorCodes.add(mDigitsValidator.isTachNumberValid(allFields.get("tachNumber")));
        errorCodes.add(mDateValidator.isDateValid(allFields.get("dateOfExpiry")));

        return errorCodes;
    }
}
