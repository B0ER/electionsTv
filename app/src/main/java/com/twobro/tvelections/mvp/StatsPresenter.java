package com.twobro.tvelections.mvp;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.google.gson.Gson;
import com.twobro.tvelections.activity.MainActivity;
import com.twobro.tvelections.fragments.StatsFragment;
import com.twobro.tvelections.models.InfoUsers;
import com.twobro.tvelections.models.VotingProgress;
import com.twobro.tvelections.network.NetChecker;
import com.twobro.tvelections.network.retrofit.RetrofitConnector;
import com.twobro.tvelections.network.socket.SocketSingleton;
import com.twobro.tvelections.time.TimerCallback;

import java.util.concurrent.TimeUnit;

import io.socket.client.Socket;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StatsPresenter {
  public static final String TAG = "ProgressPresenter";
  private StatsFragment fragment;
  private Socket socket;
  private TimerCallback timer;
  private Handler hStats;
  private Handler hFinishVoting;
  private VotingProgress currentProgress;

  @SuppressLint("HandlerLeak")
  public StatsPresenter(StatsFragment fragment) {
    currentProgress = new VotingProgress();
    this.fragment = fragment;
    socket = SocketSingleton.getInstance();

    hFinishVoting = new Handler() {
      @Override
      public void handleMessage(Message msg) {
        super.handleMessage(msg);
        fragment.toTheWaitingFragment();
      }
    };

    hStats = new Handler() {
      @Override
      public void handleMessage(Message msg) {
        super.handleMessage(msg);
        if (msg.what != -1) {
          fragment.getVPC().setMissing(msg.what);
          fragment.getVPC().updateUI();
        } else {
          fragment.getVPC().setStats((VotingProgress) msg.obj);
          fragment.getVPC().updateUI();
        }
      }
    };

    getInfoAllUsers();
    socket.on("votingStat", (Object... args) -> {
      currentProgress = new Gson().fromJson(args[0].toString(), VotingProgress.class);
      Message message = hStats.obtainMessage();
      message.what = -1;
      message.obj = currentProgress;
      message.sendToTarget();
      Log.d("SocEvent", "ProgressPresenter: ");
    });

    socket.on("eventCloseVoting", (Object... args) -> {
      //fragment.toTheWaitingFragment();
      //waitingThread.start();
    });
    socket.connect();
  }

  public void waitAndGoToWaitingFragment(){
    new Thread(() -> {
      try {
        TimeUnit.SECONDS.sleep(5);
        hFinishVoting.sendEmptyMessage(0);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }).start();
  }

  public void startTimer(int time) {
    timer = new TimerCallback(time);
    timer.setCallbackTime(times -> fragment.setTimeSec(times));
    timer.startTimer();
  }

  public void getInfoAllUsers() {
    if (NetChecker.checkInternet(fragment.getActivity())) {
      fragment.startLoadScreen();
      RetrofitConnector.getInstance().getInfoUsers().enqueue(new Callback<InfoUsers>() {
        @Override
        public void onResponse(Call<InfoUsers> call, Response<InfoUsers> response) {
          fragment.getVPC().setMaxProgress(response.body().getCountAllUser());
          fragment.stopLoadScreen();
          startTimer(response.body().getQuestionTime());
          hStats.sendEmptyMessage(
              response.body().getCountAllUser() - response.body().getCountAbsentUser());
        }

        @Override
        public void onFailure(Call<InfoUsers> call, Throwable t) {
          fragment.stopLoadScreen();
          fragment.serverError();
          Log.d("Retrofit", "ProgressPresenter onFailure: " + t);
        }
      });
    } else {
      fragment.serverError();
    }
  }

  public VotingProgress getCurrentProgress() {
    return currentProgress;
  }
}