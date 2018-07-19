package com.twobro.tvelections.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.squareup.picasso.Picasso;
import com.twobro.tvelections.R;
import com.twobro.tvelections.activity.MainActivity;
import com.twobro.tvelections.databinding.FragmentSpeakersBinding;
import com.twobro.tvelections.models.Speaker;
import com.twobro.tvelections.mvp.SpeakerPresenter;

public class SpeakerFragment extends Fragment {
  private static final String TAG = "SpeakerFragment";
  private static final String SPEAKER_KEY = "speaker";
  private FragmentSpeakersBinding binding;
  private SpeakerPresenter presenter;
  private Speaker speaker = null;

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (getArguments() != null) {
      speaker = (Speaker) getArguments().getSerializable(SPEAKER_KEY);
    }
  }


  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    binding = DataBindingUtil.inflate(inflater, R.layout.fragment_speakers, container, false);
    if (speaker != null)
      binding.titleQuestion.setText(speaker.getFIO());
    else
      binding.titleQuestion.setText(R.string.additionalSpeaker);
    return binding.getRoot();
  }

  @Override
  public void onStart() {
    super.onStart();
    if (speaker != null)
      presenter = new SpeakerPresenter(this, speaker.getId());
    else
      presenter = new SpeakerPresenter(this, -1);
  }

  @Override
  public void onSaveInstanceState(@NonNull Bundle outState) {

  }

  public void setImage(String url) {
    Picasso.get().load(url).into(binding.currentImage);
  }

  public void setImage(@DrawableRes int image) {
    Picasso.get().load(image).into(binding.currentImage);
  }

  public void nextImage(){
    presenter.nextImage();
  }

  public void previousImage(){
    presenter.previousImage();
  }

  public static SpeakerFragment createFragment(Speaker speaker) {
    SpeakerFragment fragment = new SpeakerFragment();
    Bundle args = new Bundle();
    args.putSerializable(SPEAKER_KEY, speaker);
    fragment.setArguments(args);
    return fragment;
  }

  public static SpeakerFragment createFragment() {
    SpeakerFragment fragment = new SpeakerFragment();
    return fragment;
  }

  public void setCountImage(int index, int count){
    binding.numberImage.setText(String.format("%d/%d", index+1, count));
  }

  public void serverError() {
    MainActivity activity = (MainActivity) getActivity();
    activity.serverError();
  }

  public void startLoadScreen() {
    binding.loadConnect.setVisibility(FrameLayout.VISIBLE);
  }

  public void stopLoadScreen() {
    binding.loadConnect.setVisibility(FrameLayout.INVISIBLE);
  }
}
