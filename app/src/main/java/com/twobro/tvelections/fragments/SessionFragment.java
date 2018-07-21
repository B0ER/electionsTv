package com.twobro.tvelections.fragments;

import android.databinding.DataBindingUtil;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.twobro.tvelections.R;
import com.twobro.tvelections.activity.MainActivity;
import com.twobro.tvelections.databinding.FragmentSessionBinding;

public class SessionFragment extends Fragment {
  public static String TAG = "SessionFragment";
  FragmentSessionBinding binding;
  MediaPlayer mediaPlayer;

  public static SessionFragment createFragment(){
    return new SessionFragment();
  }

  @Override
  public void onResume() {
    super.onResume();
    mediaPlayer = MediaPlayer.create(getContext(), R.raw.gimn);
    mediaPlayer.setLooping(false);
    mediaPlayer.setOnCompletionListener(mp -> {
      ((MainActivity)getActivity()).toTheWaitingFragment();
    });
    mediaPlayer.start();
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    binding = DataBindingUtil.inflate(inflater, R.layout.fragment_session, container, false);
    return binding.getRoot();
  }

  @Override
  public void onDetach() {
    super.onDetach();
    mediaPlayer.stop();
    mediaPlayer.reset();
    mediaPlayer.release();
  }
}
