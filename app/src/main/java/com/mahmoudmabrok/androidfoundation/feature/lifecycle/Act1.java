package com.mahmoudmabrok.androidfoundation.feature.lifecycle;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.mahmoudmabrok.androidfoundation.R;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Act1 extends AppCompatActivity {
    private static final String TAG = "Act1";
    @BindView(R.id.btnOpen2)
    Button mBtnOpen2;

    /**
     * which fires when the system first creates the activity
     * create view hierarchy
     * might bind data to lists, associate to viewmodel
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: ");
        onViewClicked();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act1);
        ButterKnife.bind(this);
        Log.d(TAG, "onCreate: ");

        onViewClicked();
    }

    /**
     * the app prepares for the activity to enter the foreground
     * the app initializes the code that maintains the UI
     */
    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
    }

    /**
     * it follow onStart in case of change in states
     *
     * @param savedInstanceState
     */
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d(TAG, "onRestoreInstanceState: ");
    }


    /**
     * This is the state in which the app interacts with the user
     * <p>
     * app stays in this state until (receiving a phone call,
     * the user’s navigating to another activity
     * , or the device screen’s turning off.)
     */
    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    /**
     * first indication that the user is leaving your activity
     * it indicates that the activity is no longer in the foreground
     * (though it may still be visible if the user is in multi-window mode
     * - A7.0  the system pauses all of the other apps.
     * - not in focus (dialogs)
     * <p>
     * you should consider using onStop() instead of onPause() to fully release or
     * adjust UI-related resources and operations to better support multi-window mode.
     * <p>
     * execution is very brief, no save data network call
     * <p>
     * may be not fully invisible so the system keeps the Activity instance resident in memory,
     * so can be reused with onResume
     */
    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }


    /**
     * no longer visible to the user
     * when a newly launched activity covers the entire screen
     * the app should release or adjust resources that are not needed
     * <p>
     * For example, your app might pause animations or switch from fine-grained to coarse-grained location updates
     * <p>
     * use to save information to a database
     * <p>
     * two paths (go on Restart , or onDestroy)
     */
    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
    }

    /**
     * it follow onStop
     *
     * @param outState
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState: ");
    }


    /**
     * app destroyed by (onFinish()  or configuration change)
     * <p>
     * we can use ViewModel  to save data
     * to be used with recreated activity or onCleared() in viewmodel will clean data.
     * <p>
     * distinguish between two scenario by isFinishing()
     * if true then onDestroy will be final state else
     * the system immediately creates a new activity instance
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart: ");
    }


    @OnClick(R.id.btnOpen2)
    public void onViewClicked() {
        Intent openActivity = new Intent(Act1.this, Act2.class);
        startActivity(openActivity);
    }
}
