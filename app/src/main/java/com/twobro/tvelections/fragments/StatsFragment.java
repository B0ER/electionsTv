package com.twobro.tvelections.fragments;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.FrameLayout;
import android.widget.TextView;

import com.twobro.tvelections.R;
import com.twobro.tvelections.activity.MainActivity;
import com.twobro.tvelections.controller.VotingProgressController;
import com.twobro.tvelections.databinding.FragmentStatsBinding;
import com.twobro.tvelections.models.VotingProgress;
import com.twobro.tvelections.mvp.StatsPresenter;

public class StatsFragment extends Fragment {
    private static String TAG = "StatsFragment";
    public FragmentStatsBinding binding;
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
    public void onSaveInstanceState(@NonNull Bundle outState) {

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
        try {
            binding.timeEnd.setText(getString(R.string.time_left, timeSec));
            if (timeSec == 0) {
                binding.timeEnd.setText(getString(R.string.time_lefted));
                setEndResult(presenter.getCurrentProgress());
                presenter.waitAndGoToWaitingFragment();
            }
        } catch (IllegalStateException ex) {
            return;
        }
    }

    public void setEndResult(VotingProgress progress) {
        TextView titleBar = binding.customToolbar.findViewById(R.id.titleBar);
        if (progress == null)
            return;

        if (progress.getBehind() > progress.getAgainst())
            titleBar.setText(getColoredSpannableText(getString(R.string.decision_is_made), Color.GREEN));
        else
            titleBar.setText(getColoredSpannableText(getString(R.string.decision_is_not_made), Color.RED));

    }

    private Spannable getColoredSpannableText(String word, int color) {
        Spannable spannable = new SpannableString(word);
        spannable.setSpan(new ForegroundColorSpan(color), 0, word.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannable.setSpan(new StyleSpan(Typeface.BOLD), 0, word.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannable;
    }

    public void serverError() {
        ((MainActivity) getActivity()).serverError();
    }

    public void toTheWaitingFragment() {
        Log.d(TAG, "backToWaitFragment: ");
        ((MainActivity) getActivity()).toTheWaitingFragment();
    }

    public void startLoadScreen() {
        binding.loadConnect.setVisibility(FrameLayout.VISIBLE);
    }

    public void stopLoadScreen() {
        binding.loadConnect.setVisibility(FrameLayout.INVISIBLE);
    }
}
