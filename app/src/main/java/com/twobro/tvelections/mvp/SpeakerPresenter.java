package com.twobro.tvelections.mvp;

import android.util.Log;
import com.twobro.tvelections.R;
import com.twobro.tvelections.fragments.SpeakerFragment;
import com.twobro.tvelections.models.ImageUrl;
import com.twobro.tvelections.network.NetChecker;
import com.twobro.tvelections.network.retrofit.RetrofitConnector;
import com.twobro.tvelections.network.socket.SocketSingleton;
import io.socket.client.Socket;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SpeakerPresenter {
  private Socket socket;
  private SpeakerFragment fragment;
  private int idSpeaker;
  private List<ImageUrl> urlListImage;

  public SpeakerPresenter(SpeakerFragment speakerFragment, int idSpeaker) {
    fragment = speakerFragment;
    socket = SocketSingleton.getInstance();
    this.idSpeaker = idSpeaker;
    urlListImage = new ArrayList<>();
    init();
    getImageList();
  }

  private void init() {
    socket.on("", args -> {

    });

    socket.connect();
  }

  private void getImageList() {
    if (idSpeaker == -1) {
      fragment.setImage(R.drawable.bg_details);
    } else if (NetChecker.checkInternet(fragment.getActivity())) {
      fragment.startLoadScreen();
      RetrofitConnector.getInstance().getUrlImageList(idSpeaker).enqueue(
          new Callback<List<ImageUrl>>() {
            @Override
            public void onResponse(Call<List<ImageUrl>> call, Response<List<ImageUrl>> response) {
              fragment.stopLoadScreen();
              urlListImage.addAll(response.body());
              fragment.setImage(urlListImage.get(0).getUrl());
            }

            @Override
            public void onFailure(Call<List<ImageUrl>> call, Throwable t) {
              fragment.stopLoadScreen();
              fragment.serverError();
              Log.d("Retrofit", "ProgressPresenter onFailure: " + t);
            }
          });
    } else {
      fragment.serverError();
    }
  }
}
