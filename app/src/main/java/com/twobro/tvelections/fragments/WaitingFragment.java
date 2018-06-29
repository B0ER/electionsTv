package com.twobro.tvelections.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.twobro.tvelections.R;
import com.twobro.tvelections.activity.MainActivity;
import com.twobro.tvelections.mvp.WaitingPresenter;

public class WaitingFragment extends BaseFragment {
  private static String TAG = "WaitingFragment";
  private WaitingPresenter presenter;

  public WaitingFragment() {

  }

  public static WaitingFragment createFragment() {
    WaitingFragment fragment = new WaitingFragment();
    //        Bundle args = new Bundle();
    //        args.putString(ARG_PARAM1, param1);
    //        args.putString(ARG_PARAM2, param2);
    //        fragment.setArguments(args);
    return fragment;
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_waiting, container, false);
    presenter = new WaitingPresenter(this);
    return view;
  }

  public void toStatsFragment() {
    Log.d(TAG, "backToWaitFragment: ");
    MainActivity activity = (MainActivity) getActivity();
    activity.toStatsFragment();
  }
}
