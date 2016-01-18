package com.dp.stopme;

import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.dp.stopme.view.Chronometer;

/**
 * Created by pradeepd on 13-01-2016.
 */
public class StopWatch extends Activity {
    Chronometer mChronometer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);

        mChronometer = new Chronometer(this);
        mChronometer.setText("00:00");
        layout.addView(mChronometer);

        Button startButton = new Button(this);
        startButton.setText("Start");
        startButton.setOnClickListener(mStartListener);
        layout.addView(startButton);

        Button stopButton = new Button(this);
        stopButton.setText("Stop");
        stopButton.setOnClickListener(mStopListener);
        layout.addView(stopButton);

        Button resetButton = new Button(this);
        resetButton.setText("Reset");
        resetButton.setOnClickListener(mResetListener);
        layout.addView(resetButton);
        setContentView(layout);
    }

    private void showElapsedTime() {
        long elapsedMillis = SystemClock.elapsedRealtime() - mChronometer.getBase();
        Toast.makeText(StopWatch.this, "Elapsed milliseconds: " + elapsedMillis,
                Toast.LENGTH_SHORT).show();
    }

    View.OnClickListener mStartListener = new View.OnClickListener() {
        public void onClick(View v) {
            mChronometer.start();
            showElapsedTime();
        }
    };

    View.OnClickListener mStopListener = new View.OnClickListener() {
        public void onClick(View v) {
            mChronometer.stop();
            showElapsedTime();
        }
    };

    View.OnClickListener mResetListener = new View.OnClickListener() {
        public void onClick(View v) {
            mChronometer.setBase(SystemClock.elapsedRealtime());
            showElapsedTime();
        }
    };
}


//        extends Activity {
//    Chronometer mChronometer;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.stop_watch);
//        Button button;
//        mChronometer = (Chronometer) findViewById(R.id.chronometer);
//        mChronometer.setFormat("ss:SS");
//        // Watch for button clicks.
//        button = (Button) findViewById(R.id.start);
//        button.setOnClickListener(mStartListener);
//        button = (Button) findViewById(R.id.stop);
//        button.setOnClickListener(mStopListener);
//        button = (Button) findViewById(R.id.reset);
//        button.setOnClickListener(mResetListener);
//
//    }
//
//    View.OnClickListener mStartListener = new View.OnClickListener() {
//        public void onClick(View v) {
//            mChronometer.start();
//        }
//    };
//    View.OnClickListener mStopListener = new View.OnClickListener() {
//        public void onClick(View v) {
//            mChronometer.stop();
//        }
//    };
//    View.OnClickListener mResetListener = new View.OnClickListener() {
//        public void onClick(View v) {
//            mChronometer.setBase(SystemClock.elapsedRealtime());
//        }
//    };
//}
