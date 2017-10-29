package com.example.ronensabag.animationsandgestures.touchSystem;

import android.support.annotation.StringRes;

interface TouchSystemContract {
  interface View {

    void clearEvents();

    void showSwitchButtonWithText(@StringRes int textResource);

    void hideSwitchButton();

    void enableTrackPadInternalTouch(boolean shouldEnabled);

    void enableTrackPadListenerTouch(boolean shouldEnabled);

    void enableTrackPadExtendsLogs(boolean shouldEnabled);

    void uncheckSwtichButton();

    void backToParent();

    void updateTitle(@StringRes int titleResource);
  }

  interface UserActions {

    void initMode(int modeId);

    void onDeleteLogClick();

    void onSwitchToggle(boolean checked, int modeId);

    void onBackPressed();
  }
}
