package com.mahmoudmabrok.androidfoundation;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.mahmoudmabrok.androidfoundation.feature.lifecycle.Act1;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btnLifeCycle)
    Button mBtnLifeCycle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnLifeCycle)
    public void onViewClicked() {
        Intent openAcivity = new Intent(MainActivity.this, Act1.class);
        startActivity(openAcivity);
    }
}
