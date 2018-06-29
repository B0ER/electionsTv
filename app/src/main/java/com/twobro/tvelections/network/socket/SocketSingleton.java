package com.twobro.tvelections.network.socket;

import android.util.Log;

import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.client.Socket;

public class SocketSingleton {

  private static final String URL = "http://192.168.1.13:2020";

  public static final String TAG = "SocketSingleton";
  private static volatile Socket socket = null;
  private static volatile IO.Options opts = null;

  private SocketSingleton() {
  }

  private static Socket getSocket() {
    synchronized (SocketSingleton.class) {
      opts = new IO.Options();
      opts.forceNew = true;

      try {
        socket = IO.socket(URL, opts);
      } catch (URISyntaxException e) {
        e.printStackTrace();
      }

      socket
          .on(Socket.EVENT_CONNECT, (Object... args) -> Log.d(TAG, "SERVER CONNECT"))
          .on(Socket.EVENT_DISCONNECT, (Object... args) -> Log.d(TAG, "SERVER DISCONNECT"));
      return socket;
    }
  }

  public static Socket getInstance() {
    if (socket == null) {
      synchronized (SocketSingleton.class) {
        getSocket().connect();
      }
    }
    return socket;
  }
}
