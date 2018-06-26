package com.twobro.tvelections.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.twobro.tvelections.R;
import com.twobro.tvelections.activity.MainActivity;
import com.twobro.tvelections.controller.VotingProgressController;
import com.twobro.tvelections.databinding.FragmentProgressBinding;
import com.twobro.tvelections.models.ProgressBarClass;
import com.twobro.tvelections.mvp.ProgressPresenter;
import com.twobro.tvelections.network.retrofit.RetrofitConnector;

public class StatsFragment extends Fragment {
    private static String TAG = "StatsFragment";
    public FragmentProgressBinding mBinding;
    private VotingProgressController mVPC;
    private ProgressPresenter mPresenter;

    public StatsFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_progress, container, false);

        mVPC = new VotingProgressController(mBinding);
        return mBinding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        mPresenter = new ProgressPresenter(this);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public static StatsFragment createFragment() {
        return new StatsFragment();
    }

    public VotingProgressController getVPC() {
        return this.mVPC;
    }

    public void startLoadScreen() {
        mBinding.loadConnect.setVisibility(FrameLayout.VISIBLE);
    }

    public void stopLoadScreen() {
        mBinding.loadConnect.setVisibility(FrameLayout.INVISIBLE);
    }

    public void serverError() {
        Toast.makeText(getContext(), "Нет соединения сети!", Toast.LENGTH_SHORT).show();
    }

    public void setTimeSec(int timeSec) {
        mBinding.timeEnd.setText(getString(R.string.time_left, timeSec));
        if (timeSec == 0){
            mBinding.timeEnd.setText(getString(R.string.time_lefted));
        }
    }

    public void backToWaitFragment() {
        Fragment fragment = WaitingFragment.createFragment();
        Log.d(TAG, "backToWaitFragment: ");
        ((MainActivity)getActivity()).replaceFragment(fragment);
    }
}
