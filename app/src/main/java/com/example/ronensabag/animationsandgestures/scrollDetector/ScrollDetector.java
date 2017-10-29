package com.example.ronensabag.animationsandgestures.scrollDetector;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public class ScrollDetector extends GestureDetector.SimpleOnGestureListener implements View.OnTouchListener {

  private final float mMaxScroll;
  private final GestureDetector gestureDetector;
  private float mLastScroll;
  private final ScrollPercentageUpdate mListener;

  public interface ScrollPercentageUpdate {
    void onScrollPercentageUpdate(float newPercentage);
  }

  public ScrollDetector(Context context, float maxScroll, ScrollPercentageUpdate scrollPercentageUpdate) {
    mMaxScroll = maxScroll;
    gestureDetector = new GestureDetector(context, this);
    mListener = scrollPercentageUpdate;
    mLastScroll = 0f;
  }

  @Override
  public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
    float newScroll = mLastScroll + distanceY;
    newScroll = Math.max(0, newScroll);
    newScroll = Math.min(mMaxScroll, newScroll);
    if (newScroll != mLastScroll) {
      float percentage = newScroll / mMaxScroll;
      if (mListener != null) {
        mListener.onScrollPercentageUpdate(percentage);
      }
      mLastScroll = newScroll;
    }
    return super.onScroll(e1, e2, distanceX, distanceY);
  }

  @Override
  public boolean onDown(MotionEvent e) {
    return true;
  }

  @SuppressLint("ClickableViewAccessibility")
  @Override
  public boolean onTouch(View view, MotionEvent motionEvent) {
    return gestureDetector.onTouchEvent(motionEvent);
  }
}
