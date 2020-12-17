package com.andyyuan.nestedvvrecyclerview.java.viewholder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.andyyuan.nestedvvrecyclerview.R;

/**
 * author : AndyYuan
 * date   : 2020/12/14 001417:41
 * desc   : 测试viewHolder
 * version: 2.0
 */
public class SimpleFristViewHolder extends RecyclerView.ViewHolder {

    private ImageView imageView;
    public SimpleFristViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.first_img);
    }
}
