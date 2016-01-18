package com.dp.stopme;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.dp.stopme.view.Chronometer;

import java.util.Calendar;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Chronometer chronometer;
    TextView tapToStart, targetTime, timeDiTextView, poinTextView;
    String stoppedTimeStr, targetTimeStr;
    LinearLayout rootView, starComponent;
    RatingBar ratingBar;
    boolean isStarted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        rootView = (LinearLayout) findViewById(R.id.rootView);
        starComponent = (LinearLayout) findViewById(R.id.starComponent);
        chronometer = (Chronometer) findViewById(R.id.chronometer);
//        stoppedTime = (TextView) findViewById(R.id.stoppedTime);
        tapToStart = (TextView) findViewById(R.id.tapToStart);
        targetTime = (TextView) findViewById(R.id.targetTime);
        timeDiTextView = (TextView) findViewById(R.id.timeDiffText);
        poinTextView = (TextView) findViewById(R.id.pointsText);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        rootView.setOnTouchListener(touchListener);
        tapToStart.setOnClickListener(tapToStartClick);
        targetTimeStr = getRandomTime();
        targetTime.setText(targetTimeStr);
        ratingBar.setStepSize(5);
        LayerDrawable stars = (LayerDrawable) ratingBar.getProgressDrawable();
        stars.getDrawable(2).setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_ATOP);
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
            onChronoMeterStop();
        } else {
            onChronoMeterStart();
        }
    }

    private void onChronoMeterStop() {
        isStarted = false;
        chronometer.stop();
        setResults();
        validateResult();
    }

    private void setResults() {
        starComponent.setVisibility(View.VISIBLE);
        timeDiTextView.setText(getTimeDiff());
        poinTextView.setText(getPoints() + "");
        ratingBar.setRating(getRating());
    }

    private float getRating() {
        float rating = (float) 2.5;

        return rating;
    }

    private int getPoints() {
        int points = 0;
        return points;
    }

    private void onChronoMeterStart() {
        starComponent.setVisibility(View.GONE);
        isStarted = true;
        targetTimeStr = getRandomTime();
        targetTime.setText(targetTimeStr);
//            stoppedTime.setVisibility(View.GONE);
//            stoppedTime.setText("");
        chronometer.start();
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
//        stoppedTime.setVisibility(View.VISIBLE);
//        stoppedTime.setText(getTimeDiff());
    }

    private String getTimeDiff() {
        stoppedTimeStr = chronometer.currentTime;
        String[] stoppedTimeArray = stoppedTimeStr.split(":");
        String[] targetTimeArray = targetTimeStr.split(":");
        int seconds = Math.abs(Integer.parseInt(stoppedTimeArray[0]) - Integer.parseInt(targetTimeArray[0]));
        int millSeconds = Math.abs(Integer.parseInt(stoppedTimeArray[1]) - Integer.parseInt(targetTimeArray[1]));
        int timeDiff = (seconds * 60) + millSeconds;
        System.out.println("stoppedTimeStr == " + stoppedTimeStr);
        System.out.println("targetTimeStr == " + targetTimeStr);
        System.out.println("seconds == " + seconds);
        System.out.println("millSeconds == " + millSeconds);
        System.out.println(Integer.parseInt(stoppedTimeArray[0]));
        System.out.println(Integer.parseInt(targetTimeArray[0]));
        System.out.println(Integer.parseInt(stoppedTimeArray[1]));
        System.out.println(Integer.parseInt(targetTimeArray[1]));
        return timeDiff + "";
    }
}
