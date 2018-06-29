package com.twobro.tvelections.fragments;

import android.databinding.DataBindingUtil;
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
import com.twobro.tvelections.controller.VotingProgressController;
import com.twobro.tvelections.databinding.FragmentProgressBinding;
import com.twobro.tvelections.mvp.StatsPresenter;

public class StatsFragment extends BaseFragment {
  private static String TAG = "StatsFragment";
  public FragmentProgressBinding binding;
  private VotingProgressController vpController;
  private StatsPresenter presenter;

  public StatsFragment() {
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    binding = DataBindingUtil.inflate(inflater, R.layout.fragment_stats, container, false);
    vpController = new VotingProgressController(binding);
    return binding.getRoot();
  }

  @Override
  public void onStart() {
    super.onStart();
    presenter = new StatsPresenter(this);
  }

  @Override
  public void onResume() {
    super.onResume();
  }

  public static StatsFragment createFragment() {
    return new StatsFragment();
  }

  public VotingProgressController getVPC() {
    return this.vpController;
  }

  public void setTimeSec(int timeSec) {
    binding.timeEnd.setText(getString(R.string.time_left, timeSec));
    if (timeSec == 0) {
      binding.timeEnd.setText(getString(R.string.time_lefted));
    }
  }

  public void toTheWaitingFragment() {
    Log.d(TAG, "backToWaitFragment: ");
    MainActivity activity = (MainActivity) getActivity();
    activity.toTheWaitingFragment();
  }
}
