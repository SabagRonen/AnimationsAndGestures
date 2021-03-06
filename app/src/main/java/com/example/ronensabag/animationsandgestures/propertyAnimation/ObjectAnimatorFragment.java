package com.example.ronensabag.animationsandgestures.propertyAnimation;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.ronensabag.animationsandgestures.R;

public class ObjectAnimatorFragment extends Fragment {

  private static final int ANIMATION_DURATION = 1500;
  private boolean toggle;
  private View mTopButton;
  private View mBottomButton;
  private View mMiddleImage;
  private View mMiddleLeftView;
  private View mMiddleRightView;
  private float mTopButtonTranslationY;
  private float mBottomButtonTranslationY;
  private float mMiddleImageTranslationY;

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mTopButtonTranslationY = getResources().getDimension(R.dimen.expend_top_button_translation_y);
    mBottomButtonTranslationY =
        getResources().getDimension(R.dimen.expend_bottom_button_translation_y);
    mMiddleImageTranslationY =
        getResources().getDimension(R.dimen.expend_middle_image_translation_y);
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    final View rootView = inflater.inflate(R.layout.fragment_object_animator, container, false);
    View startButton = rootView.findViewById(R.id.start);
    mTopButton = rootView.findViewById(R.id.topButton);
    mBottomButton = rootView.findViewById(R.id.bottomButton);
    mMiddleImage = rootView.findViewById(R.id.middleImage);
    mMiddleLeftView = rootView.findViewById(R.id.middleLeftView);
    mMiddleRightView = rootView.findViewById(R.id.middleRightView);
    toggle = true;
    startButton.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        AnimatorSet animatorSet = new AnimatorSet();
        // TODO play together all object animators using animator set, set duration and start
        toggle = !toggle;
      }
    });
    return rootView;
  }

  private ObjectAnimator getTopButtonObjectAnimator() {
    // TODO return top button object animator
    return null;
  }

  private ObjectAnimator getBottomButtonObjectAnimator() {
    // TODO return bottom button object animator
    return null;
  }

  private ObjectAnimator getMiddleObjectAnimator(View view) {
    // TODO return middle views object animator
    return null;
  }
}
