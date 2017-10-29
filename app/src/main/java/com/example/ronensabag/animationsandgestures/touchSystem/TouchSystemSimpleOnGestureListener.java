package com.example.ronensabag.animationsandgestures.touchSystem;

import android.view.GestureDetector;
import android.view.MotionEvent;

class TouchSystemSimpleOnGestureListener extends GestureDetector.SimpleOnGestureListener {

  private final EventLogger mEventLogger;

  TouchSystemSimpleOnGestureListener(EventLogger eventLogger) {
    mEventLogger = eventLogger;
  }

  @Override public void onLongPress(MotionEvent e) {
    super.onLongPress(e);
    mEventLogger.log("Long Press");
  }

  @Override
  public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
    mEventLogger.log("Scroll");
    return super.onScroll(e1, e2, distanceX, distanceY);
  }

  @Override
  public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
    mEventLogger.log("Fling");
    return super.onFling(e1, e2, velocityX, velocityY);
  }

  @Override public boolean onDoubleTap(MotionEvent e) {
    mEventLogger.log("Double Tap");
    return super.onDoubleTap(e);
  }

  @Override public boolean onSingleTapConfirmed(MotionEvent e) {
    mEventLogger.log("Single Tap");
    return super.onSingleTapConfirmed(e);
  }
}
