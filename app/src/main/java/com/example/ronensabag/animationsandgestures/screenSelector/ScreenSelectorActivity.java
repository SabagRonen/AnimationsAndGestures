package com.example.ronensabag.animationsandgestures.screenSelector;

import android.animation.Animator;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.OvershootInterpolator;
import com.example.ronensabag.animationsandgestures.R;
import com.example.ronensabag.animationsandgestures.finalWork.FinalWorkActivity;
import com.example.ronensabag.animationsandgestures.propertyAnimation.PropertyAnimationActivity;
import com.example.ronensabag.animationsandgestures.scrollDetector.ScrollDetectorActivity;
import com.example.ronensabag.animationsandgestures.touchSystem.TouchSystemActivity;

public class ScreenSelectorActivity extends AppCompatActivity {

  private static final int ANIMATION_DURATION = 2000;
  private View mTouchText;
  private View mTouchIcon;
  private View mScrollText;
  private View mScrollIcon;
  private View mAnimationText;
  private View mAnimationIcon;
  private View mScrollAnimationText;
  private View mScrollAnimationIcon;
  private int screenHeightPixels;
  private int screenWidthPixels;
  private boolean mShouldAnimateEnter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_screen_selector);

    mShouldAnimateEnter = true;
    screenHeightPixels = Resources.getSystem().getDisplayMetrics().heightPixels;
    screenWidthPixels = Resources.getSystem().getDisplayMetrics().widthPixels;

    initTouchButton();
    initScrollButton();
    initAnimationButton();
    initScrollAnimationButton();
  }

  @Override
  protected void onStart() {
    super.onStart();
    if (mShouldAnimateEnter) {
      mShouldAnimateEnter = false;
      getTouchPropertyAnimator().start();
      getScrollPropertyAnimator().start();
      getAnimationPropertyAnimator().start();
      getScrollAnimationPropertyAnimator().setListener(new Animator.AnimatorListener() {
        @Override public void onAnimationStart(Animator animator) {

        }

        @Override public void onAnimationEnd(Animator animator) {
          mTouchIcon.animate().alpha(1f).setDuration(ANIMATION_DURATION).start();
          mScrollIcon.animate().alpha(1f).setDuration(ANIMATION_DURATION).start();
          mAnimationIcon.animate().alpha(1f).setDuration(ANIMATION_DURATION).start();
          mScrollAnimationIcon.animate().alpha(1f).setDuration(ANIMATION_DURATION).start();
        }

        @Override public void onAnimationCancel(Animator animator) {

        }

        @Override public void onAnimationRepeat(Animator animator) {

        }
      }).start();
    }
  }

  public void onClick(View view) {
    switch (view.getId()) {
      case R.id.touchScreenText:
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
            this, mTouchIcon, getString(R.string.sharedIconToBottomTabs));
        Intent intent = new Intent(this, TouchSystemActivity.class);
        ActivityCompat.startActivity(this, intent, options.toBundle());
        break;
      case R.id.scrollScreenText:
        startActivity(new Intent(this, ScrollDetectorActivity.class));
        break;
      case R.id.animationScreenText:
        startActivity(new Intent(this, PropertyAnimationActivity.class));
        break;
      case R.id.scrollAnimationScreenText:
        startActivity(new Intent(this, FinalWorkActivity.class));
        break;
    }
  }

  private void initTouchButton() {
    mTouchText = findViewById(R.id.touchScreenText);
    mTouchIcon = findViewById(R.id.touchScreenIcon);
    mTouchText.setScaleX(0f);
    mTouchText.setScaleY(0f);
    mTouchText.setTranslationX(screenWidthPixels/2);
    mTouchText.setTranslationY(screenHeightPixels);
  }

  private void initScrollButton() {
    mScrollText = findViewById(R.id.scrollScreenText);
    mScrollIcon = findViewById(R.id.scrollScreenIcon);
    mScrollText.setScaleX(0f);
    mScrollText.setScaleY(0f);
    mScrollText.setTranslationX(-screenWidthPixels/2);
    mScrollText.setTranslationY(screenHeightPixels);
  }

  private void initAnimationButton() {
    mAnimationText = findViewById(R.id.animationScreenText);
    mAnimationIcon = findViewById(R.id.animationScreenIcon);
    mAnimationText.setScaleX(0f);
    mAnimationText.setScaleY(0f);
    mAnimationText.setTranslationX(screenWidthPixels);
    mAnimationText.setTranslationY(-screenHeightPixels);
  }

  private void initScrollAnimationButton() {
    mScrollAnimationText = findViewById(R.id.scrollAnimationScreenText);
    mScrollAnimationIcon = findViewById(R.id.scrollAnimationScreenIcon);
    mScrollAnimationText.setScaleX(0f);
    mScrollAnimationText.setScaleY(0f);
    mScrollAnimationText.setTranslationX(-screenWidthPixels);
    mScrollAnimationText.setTranslationY(-screenHeightPixels);
  }

  private ViewPropertyAnimator getTouchPropertyAnimator() {
    return mTouchText.animate().scaleX(1f).scaleY(1f).translationX(0).translationY(0).setDuration(
        ANIMATION_DURATION)
        .setInterpolator(new AccelerateDecelerateInterpolator());
  }

  private ViewPropertyAnimator getScrollPropertyAnimator() {
    return mScrollText.animate().scaleX(1f).scaleY(1f).translationX(0).translationY(0).setDuration(
        ANIMATION_DURATION)
        .setInterpolator(new OvershootInterpolator(1.5f));
  }

  private ViewPropertyAnimator getAnimationPropertyAnimator() {
    return mAnimationText.animate().scaleX(1f).scaleY(1f).translationX(0).translationY(0).setDuration(
        ANIMATION_DURATION)
        .setInterpolator(new AccelerateInterpolator());
  }

  private ViewPropertyAnimator getScrollAnimationPropertyAnimator() {
    return mScrollAnimationText.animate().scaleX(1f).scaleY(1f).translationX(0).translationY(0).setDuration(
        ANIMATION_DURATION)
        .setInterpolator(new DecelerateInterpolator());
  }
}
