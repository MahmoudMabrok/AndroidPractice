package com.mahmoudmabrok.androidfoundation.feature.dagger;

import android.os.Bundle;

import com.mahmoudmabrok.androidfoundation.R;

import javax.inject.Inject;

import androidx.appcompat.app.AppCompatActivity;

public class TestDagger2 extends AppCompatActivity {

  
    @Inject
    Car car;
    @Inject
    Engine engine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_dagger2);
        CarComponent carComponent = DaggerCarComponent.create();
        carComponent.inject(this);
        Car c = carComponent.getCar();
        c.drive();
        car.drive();
    }
}
