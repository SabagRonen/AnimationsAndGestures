package com.example.ronensabag.animationsandgestures.scrollDetector;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;
import com.example.ronensabag.animationsandgestures.R;

public class ScrollDetectorActivity extends AppCompatActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_scroll_detector);

    setSupportActionBar((Toolbar) findViewById(R.id.scroll_toolbar));
    if (getSupportActionBar() != null) {
      getSupportActionBar().setHomeButtonEnabled(true);
      getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    final float expandViewMinHeight = getResources().getDimension(R.dimen.scroll_detector_expend_view_min_height);
    final float expandViewMaxHeight = getResources().getDimension(R.dimen.scroll_detector_expend_view_max_height);

    initExpendView(expandViewMinHeight, expandViewMaxHeight);
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

  @Override public void onBackPressed() {
    super.onBackPressed();
    overridePendingTransition(R.anim.close_scroll_detector_activity_in,
        R.anim.close_scroll_detector_activity_out);
  }

  @SuppressLint("ClickableViewAccessibility")
  private void initExpendView(final float expandViewMinHeight, float expandViewMaxHeight) {
    final TextView expendableView = findViewById(R.id.expendableView);
    final float maxScroll = expandViewMaxHeight - expandViewMinHeight;
    ScrollDetector scrollDetector = new ScrollDetector(this,
        maxScroll, new ScrollDetector.ScrollPercentageUpdate() {
      @Override public void onScrollPercentageUpdate(float newPercentage) {
        expendableView.getLayoutParams().height = (int)(maxScroll * newPercentage) +
            (int)expandViewMinHeight;
        int percentage = (int)(newPercentage * 100f);
        expendableView.setText(String.format(getString(R.string.expand_view_pattern), percentage));
        expendableView.requestLayout();
      }
    });
    expendableView.setOnTouchListener(scrollDetector);
  }

}
