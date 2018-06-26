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
import com.twobro.tvelections.databinding.FragmentWaitingBinding;

public class WaitingFragment extends Fragment {
    FragmentWaitingBinding mBinding;
    public WaitingFragment(){

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_waiting, container, false);
        return mBinding.getRoot();
    }

    public static WaitingFragment createFragment(){
        return new WaitingFragment();
    }

}
