package com.learn.cui19.freeout.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.learn.cui19.freeout.R;
import com.learn.cui19.freeout.model.FreeGoBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by cui19 on 2016/11/17.
 */
public class MyMainContentAdapter extends RecyclerView.Adapter<MyMainContentAdapter.ViewHolder> {

    private List<FreeGoBean> mList;

    public void setList(List<FreeGoBean> list) {
        mList = list;
    }

    public MyMainContentAdapter(List<FreeGoBean> mList) {
        this.mList = mList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.item_cardview_content_main, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvInfo.setText(mList.get(position).getInfo());
        holder.tvTitle.setText(mList.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_cardview_title)
        TextView tvTitle;
        @BindView(R.id.tv_cardview_info)
        TextView tvInfo;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
