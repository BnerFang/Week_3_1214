package com.fsk.week_3_1214.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.fsk.week_3_1214.R;
import com.fsk.week_3_1214.bean.NewsBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者:  方诗康
 * 描述:
 */
public class MyNewsAdapter extends RecyclerView.Adapter<MyNewsAdapter.MyViewHolder> {

    private Context mContext;
    private List<NewsBean.DataBean> mDataBeans = new ArrayList<>();

    public MyNewsAdapter(Context context, List<NewsBean.DataBean> dataBeans) {
        mContext = context;
        mDataBeans = dataBeans;
    }

    /**
     * 删除一条数据
     * @param position
     */
    public void deleData(int position) {
        mDataBeans.remove(mDataBeans.get(position));
        notifyItemRemoved(position);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //加载布局
        View view = LayoutInflater.from(mContext).inflate(R.layout.news_item, viewGroup, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, final int i) {
        //使用Glide加载图片
        Glide.with(mContext).load(mDataBeans.get(i).getThumbnail_pic_s()).into(myViewHolder.mImageViewIcon);
        myViewHolder.mTextViewTitle.setText(mDataBeans.get(i).getTitle());
        myViewHolder.mTextViewDesc.setText(mDataBeans.get(i).getCategory());
        myViewHolder.mTextViewName.setText(mDataBeans.get(i).getAuthor_name());
        myViewHolder.mImageViewSc.setTextDirection(R.drawable.gb);
        //接口回调
        myViewHolder.mImageViewSc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMyListenar.onClicked(myViewHolder.getLayoutPosition(),view);
                Toast.makeText(mContext, "删除了：" + i, Toast.LENGTH_SHORT).show();
            }
        });
    }

    //三目运算符  进行判断
    @Override
    public int getItemCount() {
        return mDataBeans == null ? 0 : mDataBeans.size();
    }

    //自定义viewholder
    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView mImageViewIcon, mImageViewSc;
        TextView mTextViewTitle, mTextViewDesc, mTextViewName;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageViewIcon = itemView.findViewById(R.id.img_icon);
            mTextViewTitle = itemView.findViewById(R.id.txt_title);
            mTextViewDesc = itemView.findViewById(R.id.txt_desc);
            mTextViewName = itemView.findViewById(R.id.txt_author_name);
            mImageViewSc = itemView.findViewById(R.id.img_sc);
        }
    }

    private MyListenar mMyListenar;

    public void setMyListenar(MyListenar myListenar) {
        mMyListenar = myListenar;
    }

    //自定义接口
    public interface MyListenar {
        void onClicked(int position,View view);
    }
}
