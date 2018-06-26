package com.twobro.tvelections.time;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ViewClock {

    private AsyncTask clock;
    private TextView tvData, tvTime;
    private boolean status;

    public ViewClock(TextView tvData, TextView tvTime) {
        this.tvData = tvData;
        this.tvTime = tvTime;
    }

    @SuppressLint("StaticFieldLeak")
    public void startDataTimeUpdater() {
        status = true;
        clock = new AsyncTask<Void,Void,Void>(){
            SimpleDateFormat time = new SimpleDateFormat("HH:mm:ss");
            SimpleDateFormat date = new SimpleDateFormat("dd.MM.yyyy");
            String currentTime;
            String currentDate;

            @Override
            protected void onProgressUpdate(Void... values) {
                super.onProgressUpdate(values);
                if(tvData != null)
                    tvData.setText(currentDate);
                if(tvTime != null)
                    tvTime.setText(currentTime);
            }

            @Override
            protected Void doInBackground(Void... params) {
                currentDate = date.format(new Date());
                while (status) {
                    currentTime = time.format(new Date());
                    publishProgress();
                    SystemClock.sleep(1000);
                }
                return null;
            }
        }.execute();
    }

    public void stopClock(){
        status = false;
    }
}