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
  public static String TAG = "SpeakerPresenter";
  private Socket socket;
  private SpeakerFragment fragment;
  private int idSpeaker;
  private List<ImageUrl> urlListImage;
  private int indexImage = 0;

  public SpeakerPresenter(SpeakerFragment speakerFragment, int idSpeaker) {
    fragment = speakerFragment;
    socket = SocketSingleton.getInstance();
    this.idSpeaker = idSpeaker;
    urlListImage = new ArrayList<>();
    getImageList();
  }

  public void nextImage() {
    Log.d(TAG, "nextImage: ");
    if (indexImage + 1 <= urlListImage.size() - 1) {
      fragment.setCountImage(++indexImage, urlListImage.size());
      fragment.setImage(urlListImage.get(indexImage).getUrl());
    }
  }

  public void previousImage() {
    Log.d(TAG, "prevImage: ");
    if (indexImage - 1 >= 0) {
      fragment.setCountImage(--indexImage, urlListImage.size());
      fragment.setImage(urlListImage.get(indexImage).getUrl());
    }
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
              fragment.setImage(urlListImage.get(indexImage).getUrl());
              fragment.setCountImage(indexImage, urlListImage.size());
            }

            @Override
            public void onFailure(Call<List<ImageUrl>> call, Throwable t) {
              fragment.stopLoadScreen();
              fragment.serverError();
              Log.d("Retrofit", "SpeakerPresenter onFailure: " + t);
            }
          });
    } else {
      fragment.serverError();
    }
  }
}
