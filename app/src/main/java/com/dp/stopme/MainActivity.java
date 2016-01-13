package com.dp.stopme;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Chronometer chronometer;
    TextView stoppedTime, tapToStart, targetTime;
    String stoppedTimeStr, targetTimeStr;
    LinearLayout rootView;
    boolean isStarted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        rootView = (LinearLayout) findViewById(R.id.rootView);
        chronometer = (Chronometer) findViewById(R.id.chronometer);
        stoppedTime = (TextView) findViewById(R.id.stoppedTime);
        tapToStart = (TextView) findViewById(R.id.tapToStart);
        targetTime = (TextView) findViewById(R.id.targetTime);
        rootView.setOnTouchListener(touchListener);
        tapToStart.setOnClickListener(tapToStartClick);
        targetTimeStr = getRandomTime();
        targetTime.setText(targetTimeStr);
    }

    View.OnTouchListener touchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            onTapEvent();
            return false;
        }
    };

    View.OnClickListener tapToStartClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            onTapEvent();
        }
    };

    private void onTapEvent() {
        if (isStarted) {
            isStarted = false;
            chronometer.stop();
            validateResult();
        } else {
            isStarted = true;
            targetTimeStr = getRandomTime();
            targetTime.setText(targetTimeStr);
            stoppedTime.setVisibility(View.GONE);
            stoppedTime.setText("");
            chronometer.start();
        }
    }

    private String getRandomTime() {
        final Random random = new Random();
        Calendar time = Calendar.getInstance();
        time.setTimeInMillis(random.nextLong());
        int seconds = time.get(Calendar.SECOND);
        if (seconds > 10) {
            seconds = seconds % 10;
        }
        int milliSeconds = (time.get(Calendar.MILLISECOND)) / 10;
        if (milliSeconds < 9) {
            return seconds + ":0" + milliSeconds;
        } else {
            return seconds + ":" + milliSeconds;
        }
    }

    private void validateResult() {
        stoppedTime.setVisibility(View.VISIBLE);
        stoppedTime.setText(getTimeDiff());
    }

    private String getTimeDiff() {
        stoppedTimeStr = chronometer.currentTime;
        String[] stoppedTimeArray = stoppedTimeStr.split(":");
        String[] targetTimeArray = targetTimeStr.split(":");
        int seconds = Integer.parseInt(stoppedTimeArray[0]) - Integer.parseInt(targetTimeArray[0]);
        int millSeconds = Integer.parseInt(stoppedTimeArray[1]) - Integer.parseInt(targetTimeArray[1]);
        return seconds + ":" + millSeconds;
    }
}
