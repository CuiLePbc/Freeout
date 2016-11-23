package com.learn.cui19.freeout.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.learn.cui19.freeout.R;
import com.learn.cui19.freeout.model.FreeGoBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by cui19 on 2016/11/17.
 */
public class MyMainContentAdapter extends
        RecyclerView.Adapter<RecyclerView.ViewHolder> {

    /* footview还是正常item类型标识 */
    private static final int TYPE_ITEM = 0;
    private static final int TYPE_FOOTER = 1;

    /* 数据源 */
    private List<FreeGoBean> mList;
    private Context context;

    /* Item点击事件 */
    private MyItemLongClickListener mLongClickListener = null;
    private MyItemClickListener mClickListener = null;
    public void setOnItemLongClickListener(MyItemLongClickListener longClickListener) {
        this.mLongClickListener = longClickListener;
    }
    public void setOnItemClickListener(MyItemClickListener clickListener) {
        this.mClickListener = clickListener;
    }

    /**
     * 设置数据源，用以刷新列表
     */
    public void setList(List<FreeGoBean> list) {
        mList = list;
        this.notifyDataSetChanged();
    }

    /**
     * 添加一组数据
     * @param list 要添加的新数据
     */
    public void addList(List<FreeGoBean> list) {
        mList.addAll(list);
        this.notifyDataSetChanged();
    }

    /**
     * 获取某项Item绑定的数据
     * @param position 位置
     * @return FreeGoBean对象
     */
    public FreeGoBean getItemData(int position) {
        return mList.get(position);
    }

    public MyMainContentAdapter(List<FreeGoBean> mList, Context context) {
        this.mList = mList;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == TYPE_ITEM) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_cardview_content_main,
                    parent, false);
            RecyclerView.ViewHolder viewHolder = new ItemViewHolder(view, mClickListener, mLongClickListener);
            return viewHolder;
        } else if (viewType == TYPE_FOOTER) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_content_main_footview,
                    parent, false);
            RecyclerView.ViewHolder viewHolder = new FootViewHolder(view);
            return viewHolder;
        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof FootViewHolder) {
            ((FootViewHolder)holder).mProgressBar.setVisibility(View.VISIBLE);
            return;
        }

        if (holder instanceof ItemViewHolder) {
            ((ItemViewHolder)holder).tvInfo.setText(mList.get(position).getInfo());
            ((ItemViewHolder)holder).tvTitle.setText(mList.get(position).getTitle());
            ((ItemViewHolder)holder).tvAuthor.setText(mList.get(position).getAuthor());
            ((ItemViewHolder)holder).tvTips.setText(mList.get(position).getTips());
            Glide.with(context)
                .load(mList.get(position).getImgHref())
                .asBitmap()
                .error(android.R.drawable.sym_def_app_icon)
                .placeholder(android.R.drawable.sym_def_app_icon)
                .fitCenter()
                .into(((ItemViewHolder)holder).ivImg);
        }
    }

    @Override
    public int getItemViewType(int position) {
        //最后一个设置为footview
        return position + 1 == getItemCount() ? TYPE_FOOTER : TYPE_ITEM;
    }

    @Override
    public int getItemCount() {
        return mList.size() + 1;
    }

    /**
     * 清除所有内容
     */
    public void clearDatas() {
        mList.clear();
        this.notifyDataSetChanged();
    }

    public interface MyItemClickListener {

        void onItemClick(View v, int position);
    }

    public interface MyItemLongClickListener {

        void onItemLongClick(View v, int position);
    }

    class FootViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.content_main_foot_progressbar)
        ProgressBar mProgressBar;

        public FootViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,
            View.OnLongClickListener {
        @BindView(R.id.tv_cardview_title)
        TextView tvTitle;
        @BindView(R.id.tv_cardview_author)
        TextView tvAuthor;
        @BindView(R.id.tv_cardview_info)
        TextView tvInfo;
        @BindView(R.id.tv_cardview_tips)
        TextView tvTips;
        @BindView(R.id.iv_cardview)
        ImageView ivImg;

        private MyItemClickListener mClickListener;
        private MyItemLongClickListener mLongClickListener;

        public ItemViewHolder(View itemView, MyItemClickListener clickListener,
                MyItemLongClickListener longClickListener) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            this.mClickListener = clickListener;
            this.mLongClickListener = longClickListener;

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mClickListener != null)
                mClickListener.onItemClick(v, getLayoutPosition());
        }

        @Override
        public boolean onLongClick(View v) {
            if (mLongClickListener != null)
                mLongClickListener.onItemLongClick(v, getLayoutPosition());
            return true;
        }
    }
}
