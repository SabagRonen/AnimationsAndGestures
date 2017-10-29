package com.example.ronensabag.animationsandgestures.propertyAnimation;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.example.ronensabag.animationsandgestures.R;

class SectionsPagerAdapter extends FragmentPagerAdapter {

  private final Context mContext;

  SectionsPagerAdapter(FragmentManager fragmentManager, Context context) {
    super(fragmentManager);
    mContext = context;
  }

  @Override
  public Fragment getItem(int position) {
    switch (position) {
      case 0:
        return new InterpolatorFragment();
      case 1:
        return new ObjectAnimatorFragment();
      default:
        return new InterpolatorFragment();

    }
  }

  @Override
  public int getCount() {
    return 2;
  }

  @Override public CharSequence getPageTitle(int position) {
    switch (position) {
      case 0:
        return mContext.getString(R.string.interpolator_title);
      case 1:
        return mContext.getString(R.string.object_animator_title);
      default:
        return mContext.getString(R.string.interpolator_title);
    }
  }
}
