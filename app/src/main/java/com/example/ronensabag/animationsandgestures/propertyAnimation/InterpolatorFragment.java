package com.example.ronensabag.animationsandgestures.propertyAnimation;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.OvershootInterpolator;
import com.example.ronensabag.animationsandgestures.R;

public class InterpolatorFragment extends Fragment {

  private static final int DURATION = 1000;
  private float translationWidth;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    float margins = getResources().getDimension(R.dimen.interpolator_margins_for_width_calculation);
     translationWidth = Resources.getSystem().getDisplayMetrics().widthPixels - margins;
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.fragment_interpolator, container, false);
    View startButton = rootView.findViewById(R.id.start);
    final View accelerate = rootView.findViewById(R.id.accelerate);
    final View decelerate = rootView.findViewById(R.id.decelerate);
    final View accelerateDecelerate = rootView.findViewById(R.id.accelerateDecelerate);
    final View overshot = rootView.findViewById(R.id.overshot);
    final View bounce = rootView.findViewById(R.id.bounce);
    startButton.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        setAnimation(accelerate, new AccelerateInterpolator());
        setAnimation(decelerate, new DecelerateInterpolator());
        setAnimation(accelerateDecelerate, new AccelerateDecelerateInterpolator());
        setAnimation(overshot, new OvershootInterpolator());
        setAnimation(bounce, new BounceInterpolator());
      }
    });
    return rootView;
  }

  private void setAnimation(View view, Interpolator interpolator) {
    if (view.getTranslationX() > 0) {
      view.setRotationY(180);
      view.animate().translationX(0).setDuration(DURATION).setInterpolator(interpolator).start();
    } else {
      if (view.getRotationY() != 0) {
        view.setRotationY(0);
      }
      view.animate().translationX(translationWidth).setDuration(DURATION).setInterpolator(interpolator).start();
    }
  }
}
