package com.mahmoudmabrok.androidfoundation.feature.dagger;

import javax.inject.Inject;

public class Car {

    private static final String TAG = "Car";
  /*  @Inject
    Engine engine;
*/

    @Inject
    public Car() {

    }

    public void drive() {
        //         Log.d(TAG, "drive: " + engine.toString());
    }
}
