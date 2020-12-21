package com.andyyuan.nestedvvrecyclerview.java.adapter;

import com.andyyuan.nestedvvrecyclerview.java.view.ChildRecyclerView;

/**
 * author : AndyYuan
 * e-mail : 01810788@smg.cn
 * date   : 2020/12/21 002115:27
 * desc   : 新增主界面Adapter获取当前加载的子RecyclerView接口
 * version: 2.0
 */
public interface MultiMainTypeAdapter {

    public abstract ChildRecyclerView getCurrentChildRecyclerView();
}
