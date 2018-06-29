package com.twobro.tvelections.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.squareup.picasso.Picasso;
import com.twobro.tvelections.R;
import com.twobro.tvelections.activity.MainActivity;
import com.twobro.tvelections.databinding.FragmentSpeakersBinding;
import com.twobro.tvelections.mvp.SpeakerPresenter;

public class SpeakerFragment extends Fragment {
  private static final String TAG = "SpeakerFragment";
  private static final String SPEAKER_KEY = "speaker";
  private FragmentSpeakersBinding binding;
  private SpeakerPresenter presenter;
  private int idSpeaker = -1;

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (getArguments() != null) {
      idSpeaker = getArguments().getInt(SPEAKER_KEY);
    }
  }

  @Nullable @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    binding = DataBindingUtil.inflate(inflater, R.layout.fragment_speakers, container, false);
    return binding.getRoot();
  }

  @Override public void onStart() {
    super.onStart();
    presenter = new SpeakerPresenter(this, idSpeaker);
  }

  public void setImage(String url) {
    Log.d(TAG, "setImage: Image is set!!!");
    Picasso.get().load(url).into(binding.currentImage);
  }

  public void setImage(@DrawableRes int image) {
    Log.d(TAG, "setImage: Image is set!!!");
    Picasso.get().load(image).fit().into(binding.currentImage);
  }

  public static SpeakerFragment createFragment(int idSpeaker) {
    SpeakerFragment fragment = new SpeakerFragment();
    Bundle args = new Bundle();
    args.putInt(SPEAKER_KEY, idSpeaker);
    fragment.setArguments(args);
    return fragment;
  }

  public void serverError() {
    MainActivity activity = (MainActivity) getActivity();
    activity.serverError();
  }

  public void toStatsFragment() {
    Log.d(TAG, "toStatsFragment: ");
    MainActivity activity = (MainActivity) getActivity();
    activity.toStatsFragment();
  }

  public void startLoadScreen() {
    binding.loadConnect.setVisibility(FrameLayout.VISIBLE);
  }

  public void stopLoadScreen() {
    binding.loadConnect.setVisibility(FrameLayout.INVISIBLE);
  }
}
