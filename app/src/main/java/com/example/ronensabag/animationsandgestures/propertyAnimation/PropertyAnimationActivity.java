package com.example.ronensabag.animationsandgestures.propertyAnimation;

import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.view.ViewPager;
import android.os.Bundle;

import android.view.MenuItem;
import com.example.ronensabag.animationsandgestures.R;

public class PropertyAnimationActivity extends AppCompatActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_property_animation);

    Toolbar toolbar = findViewById(R.id.property_animation_toolbar);
    setSupportActionBar(toolbar);

    if (getSupportActionBar() != null) {
      getSupportActionBar().setDisplayHomeAsUpEnabled(true);
      getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    SectionsPagerAdapter sectionsPagerAdapter =
        new SectionsPagerAdapter(getSupportFragmentManager(), this);

    ViewPager viewPager = findViewById(R.id.propertyAnimationViewPager);
    viewPager.setAdapter(sectionsPagerAdapter);
    TabLayout tabLayout = findViewById(R.id.propertyAnimationTabLayout);
    tabLayout.setupWithViewPager(viewPager);
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
    overridePendingTransition(R.anim.close_animation_activity_in,
        R.anim.close_animation_activity_out);
  }
}
