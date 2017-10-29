package com.example.ronensabag.animationsandgestures.touchSystem;

import android.animation.Animator;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;
import com.example.ronensabag.animationsandgestures.R;

public class TouchSystemActivity extends AppCompatActivity implements TouchSystemContract.View {

  private static final String MODE_ID = "MODE_ID";
  private boolean mShouldDoFullLog;
  @IdRes private int mModeId;
  private Switch mEnableLogsSwitch;
  private GestureDetector mGestureDetector;
  private TrackPadView mTrackPad;
  private LoggerViewHolderAdapter mLoggerViewHolderAdapter;
  private TouchSystemContract.UserActions mUserAction;
  private View.OnTouchListener mOnTouchListener;

  @SuppressLint("ClickableViewAccessibility")
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_touch_system);
    Toolbar toolbar = findViewById(R.id.toolbar_touch_system);
    setSupportActionBar(toolbar);

    if (getSupportActionBar() != null) {
      getSupportActionBar().setDisplayHomeAsUpEnabled(true);
      getSupportActionBar().setDisplayShowHomeEnabled(true);
    }


    mModeId = getModeIdOrDefault(savedInstanceState, R.id.navigation_touch_events);
    mUserAction = new TouchSystemPresenter(this);

    RecyclerView eventList = findViewById(R.id.event_list);
    mLoggerViewHolderAdapter = new LoggerViewHolderAdapter();
    eventList.setAdapter(mLoggerViewHolderAdapter);

    initTrackPadView();

    ImageButton deleteLogs = findViewById(R.id.deleteLogs);
    deleteLogs.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        mUserAction.onDeleteLogClick();
      }
    });

    mEnableLogsSwitch = findViewById(R.id.switchLogs);
    mEnableLogsSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
      @Override public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        mUserAction.onSwitchToggle(compoundButton.isChecked(), mModeId);

      }
    });

    BottomNavigationView bottomNavigationView = findViewById(R.id.navigation);
    bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
      @Override public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        mModeId = item.getItemId();
        mUserAction.initMode(mModeId);
        return true;
      }
    });
  }

  @Override protected void onStart() {
    super.onStart();
    mUserAction.initMode(mModeId);
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        mUserAction.onBackPressed();
        return true;
    }
    return super.onOptionsItemSelected(item);
  }

  private int getModeIdOrDefault(Bundle savedInstanceState, int defaultModeId) {
    int mode;
    if (savedInstanceState != null) {
      mode = savedInstanceState.getInt(MODE_ID, defaultModeId);
    } else {
      mode = defaultModeId;
    }
    return mode;
  }

  @Override protected void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    outState.putInt(MODE_ID, mModeId);
  }

  private boolean handleEventInTouchMode(MotionEvent motionEvent) {
    mLoggerViewHolderAdapter.addEvent("Action is: " +
        MotionEvent.actionToString(motionEvent.getAction()));
    return mShouldDoFullLog;
  }

  private boolean handleEventInListenerMode(MotionEvent motionEvent) {
    mLoggerViewHolderAdapter.addEvent("Listener Action is: " +
        MotionEvent.actionToString(motionEvent.getAction()));
    return true;
  }

  private boolean handleEventInGestureMode(MotionEvent motionEvent) {
    if (mGestureDetector == null) {
      mGestureDetector = new GestureDetector(this,
          new TouchSystemSimpleOnGestureListener(mLoggerViewHolderAdapter));
    }
    mGestureDetector.onTouchEvent(motionEvent);
    return true;
  }

  @Override
  public void clearEvents() {
    mLoggerViewHolderAdapter.deleteAll();
  }

  @Override
  public void showSwitchButtonWithText(@StringRes int textResource) {
    if (mEnableLogsSwitch.getVisibility() != View.VISIBLE) {
      mEnableLogsSwitch.setEnabled(true);
      mEnableLogsSwitch.setChecked(false);
      mEnableLogsSwitch.setAlpha(0f);
      mEnableLogsSwitch.setVisibility(View.VISIBLE);
      mEnableLogsSwitch.animate().alpha(1f).start();
    }
    mEnableLogsSwitch.setText(textResource);
  }

  @Override
  public void hideSwitchButton() {
    if (mEnableLogsSwitch.getVisibility() != View.INVISIBLE) {
      mEnableLogsSwitch.setEnabled(false);
      mEnableLogsSwitch.animate().alpha(0f).setListener(new Animator.AnimatorListener() {
        @Override public void onAnimationStart(Animator animator) {

        }

        @Override public void onAnimationEnd(Animator animator) {
          mEnableLogsSwitch.setVisibility(View.INVISIBLE);
          mEnableLogsSwitch.animate().setListener(null);
        }

        @Override public void onAnimationCancel(Animator animator) {
          mEnableLogsSwitch.setVisibility(View.INVISIBLE);
          mEnableLogsSwitch.animate().setListener(null);
        }

        @Override public void onAnimationRepeat(Animator animator) {

        }
      }).start();
    }
  }

  @Override
  public void enableTrackPadInternalTouch(boolean shouldEnabled) {
    mTrackPad.setEventLogger(shouldEnabled ? mLoggerViewHolderAdapter : null);
  }

  @SuppressLint("ClickableViewAccessibility")
  @Override
  public void enableTrackPadListenerTouch(boolean shouldEnabled) {
    mTrackPad.setOnTouchListener(shouldEnabled ? mOnTouchListener : null);
  }

  @Override
  public void enableTrackPadExtendsLogs(boolean shouldEnabled) {
    mShouldDoFullLog = shouldEnabled;
  }

  @Override
  public void uncheckSwtichButton() {
    mEnableLogsSwitch.setChecked(false);
  }

  @Override
  public void backToParent() {
    onBackPressed();
  }

  @Override
  public void updateTitle(@StringRes int titleResource) {
    if (getSupportActionBar() != null) {
      getSupportActionBar().setTitle(titleResource);
    }
  }

  @SuppressLint("ClickableViewAccessibility")
  private void initTrackPadView() {
    mTrackPad = findViewById(R.id.trackPad);
    mTrackPad.setEventLogger(mLoggerViewHolderAdapter);
    mOnTouchListener = new View.OnTouchListener() {
      @SuppressLint("ClickableViewAccessibility")
      @Override public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (mModeId) {
          case R.id.navigation_touch_events:
            return handleEventInTouchMode(motionEvent);
          case R.id.navigation_toggle_listener:
            return handleEventInListenerMode(motionEvent);
          case R.id.navigation_gesture_detector:
            return handleEventInGestureMode(motionEvent);
        }
        return false;
      }
    };
    mTrackPad.setOnTouchListener(mOnTouchListener);
  }
}
