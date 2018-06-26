package com.twobro.tvelections.controller;

import android.content.Context;

import com.twobro.tvelections.R;
import com.twobro.tvelections.databinding.FragmentProgressBinding;
import com.twobro.tvelections.models.ProgressBarClass;
import com.twobro.tvelections.models.VotingProgress;

public class VotingProgressController {

    private Context context;
    private ProgressBarClass mConsonants, mAgainst, mResisted, mMissing;
    private FragmentProgressBinding mBinding;
    private int maxProgress = 100;

    public VotingProgressController(FragmentProgressBinding binding) {
        mBinding = binding;

        mConsonants = new ProgressBarClass();
        mAgainst = new ProgressBarClass();
        mResisted = new ProgressBarClass();
        mMissing = new ProgressBarClass();

        mBinding.pbConsonants.setPbClass(mConsonants);
        mBinding.pbAgainst.setPbClass(mAgainst);
        mBinding.pbResisted.setPbClass(mResisted);
        mBinding.pbMissing.setPbClass(mMissing);

        context = mBinding.getRoot().getContext();

        setMaxProgress(maxProgress);

        init();
        updateUI();
    }

    private void init() {
        mConsonants.setColor(getColor(R.color.colorYellow));
        mAgainst.setColor(getColor(R.color.colorBlue));
        mResisted.setColor(getColor(R.color.colorPrimary));
        mMissing.setColor(getColor(R.color.colorDarkWhite));

        mConsonants.setName(getString(R.string.consonants));
        mAgainst.setName(getString(R.string.against));
        mResisted.setName(getString(R.string.resisted));
        mMissing.setName(getString(R.string.missing));
    }

    private String getString(int string) {
        return context.getString(string);
    }

    private int getColor(int color) {
        return context.getColor(color);
    }

    public void setStats(VotingProgress vp) {
        mConsonants.setProgress(vp.getBehind());
        mAgainst.setProgress(vp.getAgainst());
        mResisted.setProgress(vp.getResisted());

        mConsonants.setCountVoting("" + vp.getBehind());
        mAgainst.setCountVoting("" + vp.getAgainst());
        mResisted.setCountVoting("" + vp.getResisted());
    }

    public void setMissing(int missing) {
        mMissing.setProgress(missing);
        mMissing.setCountVoting("" + missing);
    }

    public void setMaxProgress(int maxProgress) {
        mBinding.pbConsonants.pb.setMax(maxProgress);
        mBinding.pbAgainst.pb.setMax(maxProgress);
        mBinding.pbResisted.pb.setMax(maxProgress);
        mBinding.pbMissing.pb.setMax(maxProgress);
    }

    public void updateUI() {
        mBinding.invalidateAll();
    }
}