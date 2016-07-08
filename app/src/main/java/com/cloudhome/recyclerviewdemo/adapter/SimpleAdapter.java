package com.cloudhome.recyclerviewdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cloudhome.recyclerviewdemo.R;

import java.util.List;

/**
 * Created by xionghu on 2016/7/8.
 * Email：965705418@qq.com
 */

//传统的BaseAdapter会在创建ViewHolder 和对应的View进行赋值
//而在该Adapter中，将这个过程分为了两步，由该两个方法完成。


public class SimpleAdapter extends RecyclerView.Adapter<SimpleAdapter.MyViewHolder> {

    private LayoutInflater mInflater;
    private Context mContext;
    private List<String> mDatas;

    public interface OnItemClickListener
    {
        void onItemClick(View view ,int position);
        void onItemLongClick(View view,int position);
    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener listener)
    {
        this.mOnItemClickListener = listener;
    }

    public SimpleAdapter(Context context, List<String> datas)

    {
        this.mContext = context;
        this.mDatas = datas;
        mInflater = LayoutInflater.from(context);

    }

    /**
     * 创建ViewHolder
     *
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View view = mInflater.inflate(R.layout.item_simple_textview, parent, false);

        MyViewHolder viewHolder = new MyViewHolder(view);


        return viewHolder;
    }


    /**
     * 绑定ViewHolder
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        holder.tv.setText(mDatas.get(position));

        if(mOnItemClickListener!=null)
        {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int pos = holder.getLayoutPosition();
                    mOnItemClickListener.onItemClick(holder.itemView,pos);
                }

        });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {

                    int pos = holder.getLayoutPosition();
                    mOnItemClickListener.onItemLongClick(holder.itemView,pos);
                    return false;
                }
            });
        }
    }


    public void addData(int position)
    {
        mDatas.add(position, "Insert One");
        notifyItemInserted(position);
    }


    public void removeData(int position)
    {
        mDatas.remove(position);
        notifyItemRemoved(position);
    }


    @Override
    public int getItemCount() {
        return mDatas.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv;

        public MyViewHolder(View arg0) {
            super(arg0);

            tv = (TextView) arg0.findViewById(R.id.id_tv);
        }
    }
}