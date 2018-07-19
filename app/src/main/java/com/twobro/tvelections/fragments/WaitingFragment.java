package com.twobro.tvelections.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.twobro.tvelections.R;

public class WaitingFragment extends Fragment {
  private static String TAG = "WaitingFragment";

  public WaitingFragment() {

  }

  public static WaitingFragment createFragment() {
    WaitingFragment fragment = new WaitingFragment();
    return fragment;
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_waiting, container, false);
    return view;
  }

  @Override
  public void onSaveInstanceState(@NonNull Bundle outState) {

  }
}
