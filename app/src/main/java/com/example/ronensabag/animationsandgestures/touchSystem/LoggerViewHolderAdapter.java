package com.example.ronensabag.animationsandgestures.touchSystem;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.ronensabag.animationsandgestures.R;
import java.util.ArrayList;
import java.util.List;

class LoggerViewHolderAdapter extends RecyclerView.Adapter<LoggerViewHolder> implements EventLogger {
  private final List<String> mData = new ArrayList<>();

  void addEvent(String event) {
    mData.add(0, event);
    notifyItemInserted(0);
    notifyDataSetChanged();
  }

  @Override
  public LoggerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    TextView view = (TextView) LayoutInflater.from(parent.getContext())
        .inflate(R.layout.event_item, parent, false);
    return new LoggerViewHolder(view);
  }

  @Override
  public void onBindViewHolder(LoggerViewHolder holder, int position) {
    holder.setText(mData.get(position));
  }

  @Override
  public int getItemCount() {
    return mData.size();
  }

  void deleteAll() {
    mData.clear();
    notifyDataSetChanged();
  }

  @Override
  public void log(String event) {
    addEvent(event);
  }
}
