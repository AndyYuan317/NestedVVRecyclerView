package com.andyyuan.nestedvvrecyclerview.java.myview;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;


import com.andyyuan.nestedvvrecyclerview.R;
import com.andyyuan.nestedvvrecyclerview.java.viewholder.SimpleTextViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * author : AndyYuan
 * date   : 2020/12/8 000811:05
 * desc   :
 * version: 2.0
 */
public class ChildRecyclerAdapter extends RecyclerView.Adapter<SimpleTextViewHolder> {

    private List<String> dataList = new ArrayList<>();
    public ChildRecyclerAdapter(List<String> dataList){
        this.dataList = dataList;
    }
    @NonNull
    @Override
    public SimpleTextViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new SimpleTextViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_item_text,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull SimpleTextViewHolder simpleTextViewHolder, int i) {
        simpleTextViewHolder.mTv.setText(dataList.get(i));
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
