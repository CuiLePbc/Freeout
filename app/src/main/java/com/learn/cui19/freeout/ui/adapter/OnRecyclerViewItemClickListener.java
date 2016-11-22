package com.learn.cui19.freeout.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by cui19 on 2016/11/22.
 */
public interface OnRecyclerViewItemClickListener {
    void onItemClick(RecyclerView.Adapter adapter, View view, int position);
}
