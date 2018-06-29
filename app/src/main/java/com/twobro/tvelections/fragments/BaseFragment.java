package com.twobro.tvelections.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.twobro.tvelections.activity.MainActivity;

public abstract class BaseFragment extends Fragment {
  public static final String TAG = "BaseFragment";

  public BaseFragment(){

  }

  @Nullable @Override
  public abstract View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState);

  public void startLoadScreen() {
    MainActivity activity = (MainActivity) getActivity();
    activity.startLoadScreen();
  }
  public void stopLoadScreen() {
    MainActivity activity = (MainActivity) getActivity();
    activity.stopLoadScreen();
  }

  public void serverError() {
    MainActivity activity = (MainActivity) getActivity();
    activity.serverError();
  }

}
