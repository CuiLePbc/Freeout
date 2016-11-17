package com.learn.cui19.freeout.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.learn.cui19.freeout.R;
import com.learn.cui19.freeout.model.FreeGoBean;

import java.util.List;

/**
 * Created by cui19 on 2016/11/17.
 */
public class MyMainContentAdapter extends RecyclerView.Adapter<MyMainContentAdapter.ViewHolder> {

    private List<FreeGoBean> mList;

    public MyMainContentAdapter(List<FreeGoBean> mList) {
        this.mList = mList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.item_cardview_content_main, null);

        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
