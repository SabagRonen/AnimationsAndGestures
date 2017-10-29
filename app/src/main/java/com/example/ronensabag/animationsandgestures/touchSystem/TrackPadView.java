package com.example.ronensabag.animationsandgestures.touchSystem;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class TrackPadView extends android.support.v7.widget.AppCompatTextView {

  private EventLogger mLogger;

  public TrackPadView(Context context) {
    super(context);
  }

  public TrackPadView(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
  }

  public TrackPadView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  public void setEventLogger(EventLogger logger) {
    mLogger = logger;
  }

  @SuppressLint("ClickableViewAccessibility")
  @Override
  public boolean onTouchEvent(MotionEvent event) {
    if (mLogger != null) {
      mLogger.log("Override Action is: " + MotionEvent.actionToString(event.getAction()));
    }
    return (mLogger != null);
  }
}
