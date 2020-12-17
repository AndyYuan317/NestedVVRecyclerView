package com.andyyuan.nestedvvrecyclerview.java.myview;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.andyyuan.nestedvvrecyclerview.R;
import com.andyyuan.nestedvvrecyclerview.java.view.ChildRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * author : AndyYuan
 * date   : 2020/12/8 000816:51
 * desc   : 测试使用的Fragment
 * version: 2.0
 */
public class MyFragment extends Fragment {

    private TextView mTextView;
    private ChildRecyclerView childRecyclerView = null;
    private ChildRecyclerAdapter childRecyclerAdapter = null;
    private List<String> mDataList = new ArrayList<>();

    private String x = "0";
    //子类的构造函数
    public static MyFragment newInstanceMyFragment(String y){
        MyFragment myFragment = new MyFragment();
        Bundle bundle = new Bundle();
        bundle.putString("id",y);
        myFragment.setArguments(bundle);
        return myFragment;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        x = bundle.getString("id");
        return inflater.inflate(R.layout.fragment_viewpager, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mTextView = view.findViewById(R.id.fragment_textview);
        mTextView.setText("这是第" +x+ "个Fragment");
        childRecyclerView = view.findViewById(R.id.fragment_recycler);
        childRecyclerAdapter = new ChildRecyclerAdapter(mDataList);
        initData();
    }

    private void initData() {
        for (int i = 0; i < 20; i++) {
            mDataList.add("fragment item " + x);
        }
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        childRecyclerView.setLayoutManager(layoutManager);
        childRecyclerView.setAdapter(childRecyclerAdapter);
        childRecyclerAdapter.notifyDataSetChanged();

    }

    public ChildRecyclerView getChildRecyclerView(){
        return childRecyclerView;
    }

}
