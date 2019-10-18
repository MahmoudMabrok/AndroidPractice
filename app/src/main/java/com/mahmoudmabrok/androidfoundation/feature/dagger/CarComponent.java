package com.mahmoudmabrok.androidfoundation.feature.dagger;

import dagger.Component;

@Component
public interface CarComponent {

    Car getCar();

    void inject(TestDagger2 testDagger2);
}
