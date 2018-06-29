package com.twobro.tvelections.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.squareup.picasso.Picasso;
import com.twobro.tvelections.R;
import com.twobro.tvelections.databinding.FragmentSpeakersBinding;
import com.twobro.tvelections.mvp.SpeakerPresenter;

public class SpeakerFragment extends BaseFragment {
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
    presenter = new SpeakerPresenter(this, idSpeaker);
    return binding.getRoot();
  }

  public void setImage(String url){
    Picasso.get().load(url).into(binding.currentImage);
  }

  public void setImage(@DrawableRes int image){
    Picasso.get().load(image).into(binding.currentImage);
  }

  public static SpeakerFragment createFragment(int idSpeaker) {
    SpeakerFragment fragment = new SpeakerFragment();
    Bundle args = new Bundle();
    args.putInt(SPEAKER_KEY, idSpeaker);
    fragment.setArguments(args);
    return fragment;
  }
}
