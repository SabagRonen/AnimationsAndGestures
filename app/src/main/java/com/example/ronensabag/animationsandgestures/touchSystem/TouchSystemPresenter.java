package com.example.ronensabag.animationsandgestures.touchSystem;

import com.example.ronensabag.animationsandgestures.R;

class TouchSystemPresenter implements TouchSystemContract.UserActions {

  private final TouchSystemContract.View mView;

  TouchSystemPresenter(TouchSystemContract.View view) {
    mView = view;
  }

  @Override
  public void initMode(int modeId) {
    mView.clearEvents();
    mView.uncheckSwtichButton();
    switch (modeId) {
      case R.id.navigation_touch_events:
        mView.updateTitle(R.string.title_show_touch_events);
        mView.showSwitchButtonWithText(R.string.enable_full_log);
        mView.enableTrackPadInternalTouch(false);
        mView.enableTrackPadListenerTouch(true);
        break;
      case R.id.navigation_toggle_listener:
        mView.updateTitle(R.string.title_toggle_listener);
        mView.showSwitchButtonWithText(R.string.add_touch_listener);
        mView.enableTrackPadInternalTouch(true);
        mView.enableTrackPadListenerTouch(false);
        break;
      case R.id.navigation_gesture_detector:
        mView.updateTitle(R.string.title_gestures_detector);
        mView.hideSwitchButton();
        mView.enableTrackPadInternalTouch(false);
        mView.enableTrackPadListenerTouch(true);
        break;
    }
  }

  @Override
  public void onDeleteLogClick() {
    mView.clearEvents();
  }

  @Override
  public void onSwitchToggle(boolean checked, int modeId) {
    switch (modeId) {
      case R.id.navigation_touch_events:
        mView.enableTrackPadExtendsLogs(checked);
        break;
      case R.id.navigation_toggle_listener:
        mView.enableTrackPadInternalTouch(!checked);
        mView.enableTrackPadListenerTouch(checked);
        break;
      case R.id.navigation_gesture_detector:
        // do nothing
        break;
    }
  }

  @Override
  public void onBackPressed() {
    mView.backToParent();
  }
}
