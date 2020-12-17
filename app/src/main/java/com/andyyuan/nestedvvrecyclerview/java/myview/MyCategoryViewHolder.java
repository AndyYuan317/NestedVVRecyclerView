package com.andyyuan.nestedvvrecyclerview.java.myview;

import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.andyyuan.nestedvvrecyclerview.R;
import com.andyyuan.nestedvvrecyclerview.java.view.ChildRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * author : AndyYuan
 * date   : 2020/12/9 000915:02
 * desc   : 子类RecyclerView的容器来盛装子类
 * version: 2.0
 */
public class MyCategoryViewHolder  extends RecyclerView.ViewHolder {

    public ChildRecyclerView childRecyclerView;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private MyPagerAdapter pagerAdapter;
    private ChildRecyclerAdapter adapter;
    private FragmentManager fragmentManager;

    ArrayList<String> mDataList = new ArrayList<>();
    List<MyFragment> myFragments = new ArrayList<>();

    public MyCategoryViewHolder(@NonNull View itemView, FragmentManager fragmentManager,ArrayList<String> childData) {
        super(itemView);
        tabLayout = itemView.findViewById(R.id.tabs);
        viewPager = itemView.findViewById(R.id.viewPager);
        this.fragmentManager = fragmentManager;
        initDataList(childData);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                childRecyclerView = myFragments.get(position).getChildRecyclerView();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    //初始化数据
    private void initDataList(ArrayList<String> childData){
        mDataList.clear();
        mDataList = childData;
        for(int i = 0;i<childData.size();i++) {
            myFragments.add(MyFragment.newInstanceMyFragment(i+""));
        }

    }
    //提供当前显示的子RecyclerView
    public ChildRecyclerView getCurrentChildRecyclerView(){
        //返回当前RecyclerView
        childRecyclerView = myFragments.get(viewPager.getCurrentItem()).getChildRecyclerView();
        return childRecyclerView;
    }

    //绑定Adapter
    public void bindData(){
        if (pagerAdapter == null) {
            pagerAdapter = new MyPagerAdapter(fragmentManager,mDataList,myFragments);
            viewPager.setAdapter(pagerAdapter);
            viewPager.setOffscreenPageLimit(mDataList.size());
            tabLayout.setupWithViewPager(viewPager);
        } else {
            pagerAdapter.notifyDataSetChanged();
        }
    }
}
