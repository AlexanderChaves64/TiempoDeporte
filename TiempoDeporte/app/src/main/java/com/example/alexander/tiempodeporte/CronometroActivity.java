package com.example.alexander.tiempodeporte;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

public class CronometroActivity extends AppCompatActivity {

    private TextView mTvTime;
    private EditText mEtLaps;
    private Button mBtnStart;
    private Button mBtnLap;
    private Button mBtnStop;
    private ScrollView mSvLaps;

    private int mLaps = 1;

    private Context mContext;
    private Crono mCronometro;
    private Thread mThreadCrono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cronometro);

        mContext = this;

        mTvTime = (TextView) findViewById(R.id.tv_time);
        mEtLaps = (EditText) findViewById(R.id.et_laps);
        mSvLaps = (ScrollView) findViewById(R.id.sv_laps);
        mBtnStart = (Button) findViewById(R.id.btn_start);
        mBtnLap = (Button) findViewById(R.id.btn_lap);
        mBtnStop = (Button) findViewById(R.id.btn_stop);

        mEtLaps.setEnabled(false);

        mBtnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mCronometro == null)
                {
                    mCronometro = new Crono(mContext);
                    mThreadCrono = new Thread(mCronometro);
                    mThreadCrono.start();
                    mCronometro.start();
                    mLaps = 1;
                    mEtLaps.setText("");
                }

            }
        });

        mBtnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mCronometro != null)
                {
                    mCronometro.stop();
                    mThreadCrono.interrupt();
                    mThreadCrono = null;

                    mCronometro = null;
                }
            }
        });

        mBtnLap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mCronometro == null)
                {
                    return;
                }

                mEtLaps.append("LAP" + String.valueOf(mLaps) + " " + String.valueOf(mTvTime.getText()) + "\n");

                mLaps++;

                mSvLaps.post(new Runnable() {
                    @Override
                    public void run() {
                        mSvLaps.smoothScrollBy(0, mEtLaps.getBottom());
                    }
                });
            }
        });

    }

    public void updateTimerText(final String time)
    {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mTvTime.setText(time);
            }
        });
    }

}
