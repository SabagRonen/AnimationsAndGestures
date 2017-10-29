package com.example.ronensabag.animationsandgestures.finalWork;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import com.example.ronensabag.animationsandgestures.R;
import com.example.ronensabag.animationsandgestures.scrollDetector.ScrollDetector;

public class FinalWorkActivity extends AppCompatActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_final_work);
    final Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    if (getSupportActionBar() != null) {
      getSupportActionBar().setHomeButtonEnabled(true);
      getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    final float expandViewMinHeight = getResources().getDimension(R.dimen.final_work_expend_view_min_height);
    final float expandViewMaxHeight = getResources().getDimension(R.dimen.final_work_expend_view_max_height);
    final float topButtonTranslationY = getResources().getDimension(R.dimen.expend_top_button_translation_y);
    final float bottomButtonTranslationY = getResources().getDimension(R.dimen.expend_bottom_button_translation_y);
    final float middleImageTranslationY = getResources().getDimension(R.dimen.expend_middle_image_translation_y);
    final float maxScroll = expandViewMaxHeight - expandViewMinHeight;


    final View container = findViewById(R.id.constraintLayout);
    final View topButton = findViewById(R.id.topButton);
    final View bottomButton = findViewById(R.id.bottomButton);
    final View middleLeftView = findViewById(R.id.middleLeftView);
    final View middleImage = findViewById(R.id.middleImage);
    final View middleRightView = findViewById(R.id.middleRightView);
    container.setOnTouchListener(new ScrollDetector(this, maxScroll, new ScrollDetector.ScrollPercentageUpdate() {
      @Override public void onScrollPercentageUpdate(float newPercentage) {
        container.getLayoutParams().height = (int)(expandViewMinHeight + (newPercentage * maxScroll));
        container.requestLayout();

        topButton.setTranslationY((int)(newPercentage * -topButtonTranslationY));
        bottomButton.setTranslationY((int)(newPercentage * -bottomButtonTranslationY));
        middleLeftView.setAlpha(newPercentage);
        middleLeftView.setTranslationY((int)(newPercentage * -middleImageTranslationY));
        middleImage.setAlpha(newPercentage);
        middleImage.setTranslationY((int)(newPercentage * -middleImageTranslationY));
        middleRightView.setAlpha(newPercentage);
        middleRightView.setTranslationY((int)(newPercentage * -middleImageTranslationY));
      }
    }));
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        onBackPressed();
        return true;
    }
    return super.onOptionsItemSelected(item);
  }
}
