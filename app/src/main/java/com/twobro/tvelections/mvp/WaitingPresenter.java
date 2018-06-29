package com.twobro.tvelections.mvp;

import com.google.gson.Gson;
import com.twobro.tvelections.fragments.SpeakerFragment;
import com.twobro.tvelections.fragments.WaitingFragment;
import com.twobro.tvelections.models.Speaker;
import com.twobro.tvelections.network.socket.SocketSingleton;
import io.socket.client.Socket;

public class WaitingPresenter {
  WaitingFragment fragment;
  Socket socket;

  public WaitingPresenter(WaitingFragment fragment) {
    this.fragment = fragment;
    socket = SocketSingleton.getInstance();
    init();
  }

  private void init() {
    socket.on("votingFollowers", (Object... args) -> {
      fragment.toStatsFragment();
    });

    socket.on("votingQuestions", (Object... args) -> {
      fragment.toStatsFragment();
    });

    socket.on("votingSpeakers", (Object... args) -> {
      Speaker speaker = new Gson().fromJson(args[0].toString(), Speaker.class);
      SpeakerFragment.createFragment(speaker.getId());
    });

    socket.connect();
  }
}
