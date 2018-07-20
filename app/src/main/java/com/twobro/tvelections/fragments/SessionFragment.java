package com.twobro.tvelections.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.twobro.tvelections.R;
import com.twobro.tvelections.databinding.FragmentSessionBinding;

public class SessionFragment extends Fragment {
  FragmentSessionBinding binding;

  public static SessionFragment createFragment(){
    return new SessionFragment();
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    binding = DataBindingUtil.inflate(inflater, R.layout.fragment_session, container, false);
    return binding.getRoot();
  }


}
