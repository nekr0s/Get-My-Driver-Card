package com.example.nekr0s.get_my_driver_card.async.base;

import io.reactivex.Scheduler;

public interface SchedulerProvider {
    Scheduler background();

    Scheduler ui();
}
