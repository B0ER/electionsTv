package com.twobro.tvelections.mvp;

import com.google.gson.Gson;
import com.twobro.tvelections.activity.MainActivity;
import com.twobro.tvelections.models.Speaker;
import com.twobro.tvelections.network.retrofit.RetrofitConnector;
import com.twobro.tvelections.network.retrofit.ServerApi;
import com.twobro.tvelections.network.socket.SocketSingleton;
import io.socket.client.Socket;

public class MainPresenter {
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
      Integer speaker = Integer.parseInt(args[0].toString());
      activity.toSpeakerFragment(speaker);
    });

    socket.on("additionalSpeakerToTv", (Object... args) -> {
      activity.toSpeakerFragment(-1);
    });

    socket.connect();
  }
}
