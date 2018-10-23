package com.example.nekr0s.get_my_driver_card;

import com.example.nekr0s.get_my_driver_card.models.Request;
import com.example.nekr0s.get_my_driver_card.models.User;
import com.example.nekr0s.get_my_driver_card.utils.enums.RequestStatus;
import com.example.nekr0s.get_my_driver_card.utils.enums.RequestType;

import org.junit.Assert;
import org.junit.Test;

public class CurrentDateTests {
    @Test
    public void Date_ShouldBe_CorrectLengthSize() {
        Request request = new Request(1, RequestStatus.REQUEST_APPROVED, RequestType.TYPE_NEW,
                new User("something", "something"));

        Assert.assertEquals(16, request.getRequestDate().length());
    }
}
