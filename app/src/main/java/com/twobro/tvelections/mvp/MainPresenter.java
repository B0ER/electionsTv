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
    socket.on("votingFollowers", (Object... args) -> {
      activity.toStatsFragment();
    });

    socket.on("votingQuestions", (Object... args) -> {
      activity.toStatsFragment();
    });

    socket.on("votingSpeakers", (Object... args) -> {
      Speaker speaker = new Gson().fromJson(args[0].toString(), Speaker.class);
      activity.toSpeakerFragment(speaker.getId());
    });

    socket.on("additionalSpeaker", (Object... args) -> {
      int id = Integer.parseInt(args[0].toString());
      activity.toSpeakerFragment(id);
    });

    socket.connect();
  }
}
