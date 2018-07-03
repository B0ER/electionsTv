package com.twobro.tvelections.time;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class TimerCallback {

  private final String TAG = "TimerCallback";
  private int startTime;
  private int time;
  private CallbackTime callbackTime;

  @SuppressLint("StaticFieldLeak")
  public TimerCallback(int startTime) {
    this.startTime = startTime;
  }

  @SuppressLint("StaticFieldLeak")
  public void startTimer() {
    if (callbackTime != null) {
      time = startTime;
      new AsyncTask<Void, Void, Void>() {
        @Override
        protected void onProgressUpdate(Void... values) {
          super.onProgressUpdate(values);
          callbackTime.updateTime(time);
        }

        @Override
        protected Void doInBackground(Void... voids) {
          while (time > 0) {
            publishProgress();
            --time;
            try {
              TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
          }
          //publishProgress();
          return null;
        }
      }.execute();
    } else {
      Log.d(TAG, "startTimer:  NOT FOUND CALLBACK");
    }
  }

  public TimerCallback setCallbackTime(CallbackTime callbackTime) {
    this.callbackTime = callbackTime;
    return this;
  }
}