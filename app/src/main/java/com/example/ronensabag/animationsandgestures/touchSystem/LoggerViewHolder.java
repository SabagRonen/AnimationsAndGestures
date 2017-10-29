package com.example.ronensabag.animationsandgestures.touchSystem;

import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

class LoggerViewHolder extends RecyclerView.ViewHolder {

  private final TextView mTextView;

  LoggerViewHolder(TextView itemView) {
    super(itemView);
    mTextView = itemView;
  }

  public void setText(String text) {
    mTextView.setText(text);
  }
}
