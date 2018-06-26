package com.twobro.tvelections.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.twobro.tvelections.fragments.StatsFragment;
import com.twobro.tvelections.R;

public class MainActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        mToolbar = findViewById(R.id.custom_toolbar);
        setSupportActionBar(mToolbar);

        FragmentManager cFragmentManager = getSupportFragmentManager();
        Fragment mainFragment = cFragmentManager.findFragmentById(R.id.center_fragment);
        if (mainFragment == null) {
            mainFragment = StatsFragment.createFragment();
            cFragmentManager.beginTransaction()
                    .add(R.id.center_fragment, mainFragment)
                    .commit();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}