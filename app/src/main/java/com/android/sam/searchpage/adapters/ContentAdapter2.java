package com.android.sam.searchpage.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.sam.searchpage.OnItemClickListener;
import com.android.sam.searchpage.OnItemDeleteListener;
import com.android.sam.searchpage.R;

import java.util.List;

/**
 * @author SamLeung
 * @Emial 729717222@qq.com
 * @date 2018/4/20 0020 18:16
 */
public class ContentAdapter2 extends RecyclerView.Adapter<ContentAdapter2.ViewHolder> {

    private Context context;
    private List<String> contentList;
    private int displayCount; //显示的数量，用于历史记录隐藏/显示所有
    private boolean isShowDelete = false; //是否显示删除按钮
    private OnItemClickListener itemClickListener;
    private OnItemDeleteListener itemDeleteListener;

    public ContentAdapter2(Context context, List<String> contentList){
        this.context = context;
        this.contentList = contentList;

        if (contentList == null) {
            this.displayCount = 0;
        }else{
            this.displayCount = contentList.size();
        }
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_search_item2, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.llDelete.setVisibility(isShowDelete ? View.VISIBLE : View.GONE);
        holder.tvContent.setText(contentList.get(position));

        if (isShowDelete){
            holder.llDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (itemDeleteListener != null){
                        itemDeleteListener.onItemDelete(v, position);
                    }
                }
            });
        }

        //设置监听
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemClickListener != null){
                    itemClickListener.onItemClick(v, position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return displayCount;
    }

    public void setDisplayCount(int displayCount) {
        this.displayCount = displayCount;
    }

    public void setShowDelete(boolean showDelete) {
        isShowDelete = showDelete;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tvContent;
//        private ImageView ivDelete;
        private LinearLayout llDelete;

        public ViewHolder(View itemView) {
            super(itemView);

            tvContent = itemView.findViewById(R.id.tv_content);
            llDelete  = itemView.findViewById(R.id.linear_delete);
//            ivDelete = itemView.findViewById(R.id.iv_delete);
        }
    }


    public void setItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public void setItemDeleteListener(OnItemDeleteListener itemDeleteListener) {
        this.itemDeleteListener = itemDeleteListener;
    }
}
