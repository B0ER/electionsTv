package com.twobro.tvelections.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.util.Log;
import android.widget.FrameLayout;
import android.widget.Toast;
import com.twobro.tvelections.databinding.MainActivityBinding;
import com.twobro.tvelections.fragments.SpeakerFragment;
import com.twobro.tvelections.fragments.StatsFragment;
import com.twobro.tvelections.R;
import com.twobro.tvelections.fragments.WaitingFragment;

public class MainActivity extends AppCompatActivity {
  private static final String TAG = "MainActivity";
  private Toolbar toolbar;
  private MainActivityBinding binding;

  private FragmentManager fragmentManager;
  private final WaitingFragment waitingFragment = WaitingFragment.createFragment();
  private final StatsFragment statsFragment = StatsFragment.createFragment();

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main_activity);

    toolbar = findViewById(R.id.custom_toolbar);
    setSupportActionBar(toolbar);

    fragmentManager = getSupportFragmentManager();
    Fragment mainFragment = fragmentManager.findFragmentById(R.id.center_fragment);
    if (mainFragment == null) {
      fragmentManager.beginTransaction()
          .add(R.id.center_fragment, waitingFragment)
          .commit();
    }
  }

  public void serverError() {
    Toast.makeText(this, "Нет соединения сети!", Toast.LENGTH_SHORT).show();
  }

  public void toTheWaitingFragment() {
    fragmentManager
        .beginTransaction()
        .replace(R.id.center_fragment, waitingFragment)
        .commit();
  }

  public void toStatsFragment() {
    fragmentManager
        .beginTransaction()
        .replace(R.id.center_fragment, statsFragment)
        .commit();
  }

  public void toSpeakerFragment(int id) {
    Log.d(TAG, "toSpeakersFragment: ");
    SpeakerFragment fragment = SpeakerFragment.createFragment(id);
    fragmentManager
        .beginTransaction()
        .replace(R.id.center_fragment, fragment)
        .commit();
  }
}