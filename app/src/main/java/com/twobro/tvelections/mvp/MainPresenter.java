package com.twobro.tvelections.mvp;

import android.util.Log;

import com.google.gson.Gson;
import com.twobro.tvelections.activity.MainActivity;
import com.twobro.tvelections.models.Speaker;
import com.twobro.tvelections.network.retrofit.RetrofitConnector;
import com.twobro.tvelections.network.retrofit.ServerApi;
import com.twobro.tvelections.network.socket.SocketSingleton;
import io.socket.client.Socket;

public class MainPresenter {
  public static final String TAG = "MainPresenter";
  private MainActivity activity;
  private Socket socket;
  private ServerApi retrofitApi;

  public MainPresenter(MainActivity activity) {
    this.activity = activity;
    socket = SocketSingleton.getInstance();
    retrofitApi = RetrofitConnector.getInstance();
    initSocket();
  }

  private void initSocket() {
    socket.on("startVoting", (Object... args) -> {
      activity.toStatsFragment();
    });

    socket.on("votingSpeakersTv", (Object... args) -> {
      Log.d(TAG, "initSocket: args="+args[0].toString());
      Speaker speaker = new Gson().fromJson(args[0].toString(), Speaker.class);
      activity.toSpeakerFragment(speaker);
    });

    socket.on("additionalSpeakerToTv", (Object... args) -> {
      activity.toSpeakerFragment();
    });

    socket.on("startSession", (Object... args) -> {
      activity.toSessionFragment();
    });

    socket.connect();
  }
}
