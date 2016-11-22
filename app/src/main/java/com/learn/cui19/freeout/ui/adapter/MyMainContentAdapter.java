package com.learn.cui19.freeout.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
        RecyclerView.Adapter<MyMainContentAdapter.ViewHolder> {

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
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cardview_content_main,
                parent, false);
        ViewHolder viewHolder = new ViewHolder(view, mClickListener, mLongClickListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvInfo.setText(mList.get(position).getInfo());
        holder.tvTitle.setText(mList.get(position).getTitle());

        String[] imgUrls = mList.get(position).getImgHref();
        holder.layoutImgContainer.removeAllViews();
        for (int i = 0; i < imgUrls.length; i++) {
            // 最多显示三张图片
            if (i < 3) {
                ImageView iv = new ImageView(context);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        300, 300);
                if (i > 0) {
                    lp.setMarginStart(6);
                }
                iv.setLayoutParams(lp);
                iv.setBaseline(2);

                Glide.with(context)
                        .load(imgUrls[i])
                        .asBitmap()
                        .error(android.R.drawable.sym_def_app_icon)
                        .placeholder(android.R.drawable.sym_def_app_icon)
                        .fitCenter()
                        .into(iv);
                holder.layoutImgContainer.addView(iv);
            }

        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public interface MyItemClickListener {

        void onItemClick(View v, int position);
    }

    public interface MyItemLongClickListener {

        void onItemLongClick(View v, int position);
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,
            View.OnLongClickListener {
        @BindView(R.id.tv_cardview_title)
        TextView tvTitle;
        @BindView(R.id.tv_cardview_info)
        TextView tvInfo;
        @BindView(R.id.layout_cardview_img_container)
        LinearLayout layoutImgContainer;

        private MyItemClickListener mClickListener;
        private MyItemLongClickListener mLongClickListener;

        public ViewHolder(View itemView, MyItemClickListener clickListener,
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
