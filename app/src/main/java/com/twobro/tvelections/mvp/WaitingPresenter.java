package com.twobro.tvelections.mvp;

import com.twobro.tvelections.fragments.WaitingFragment;
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
    socket.on("votingFollowers", args -> {
      fragment.toStatsFragment();
    });

    socket.on("votingQuestions", args -> {
      fragment.toStatsFragment();
    });

    socket.on("votingSpeakers", args -> {

    });

    socket.connect();
  }
}
