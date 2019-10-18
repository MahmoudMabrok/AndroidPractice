package com.mahmoudmabrok.androidfoundation;

import android.content.Intent;
import android.os.Bundle;

import com.mahmoudmabrok.androidfoundation.feature.dagger.TestDagger2;
import com.mahmoudmabrok.androidfoundation.feature.lifecycle.Act1;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnLifeCycle)
    public void onLifeCycleClicked() {
        Intent openAcivity = new Intent(MainActivity.this, Act1.class);
        startActivity(openAcivity);
    }

    @OnClick(R.id.btnOpenTestDagger)
    public void onOpenTestDaggerClicked() {
        Intent openAcivity = new Intent(MainActivity.this, TestDagger2.class);
        startActivity(openAcivity);
    }


}
