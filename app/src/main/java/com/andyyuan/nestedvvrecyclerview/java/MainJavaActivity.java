package com.andyyuan.nestedvvrecyclerview.java;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.andyyuan.nestedvvrecyclerview.R;
import com.andyyuan.nestedvvrecyclerview.java.adapter.MultiTypeAdapter;
import com.andyyuan.nestedvvrecyclerview.java.bean.CategoryBean;
import com.andyyuan.nestedvvrecyclerview.java.myview.MyFragment;
import com.andyyuan.nestedvvrecyclerview.java.view.ParentRecyclerView;
import com.andyyuan.nestedvvrecyclerview.java.view.StoreSwipeRefreshLayout;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * author : AndyYuan
 * date   : 2020/12/9 000915:02
 * desc   : 主界面，只有两层的RecyclerView嵌套
 * version: 2.0
 */
public class MainJavaActivity extends AppCompatActivity {

    //父类数据
    ArrayList<Object> mDataList = new ArrayList<Object>();
    //子类数据
    ArrayList<String> mChildDataList = new ArrayList<>();
    //子类Fragment
    ArrayList<MyFragment> mChildFragmentList = new ArrayList<>();

    MultiTypeAdapter adapter = new MultiTypeAdapter(mDataList, mChildDataList, getSupportFragmentManager());

    StoreSwipeRefreshLayout storeSwipeRefreshLayout;
    //SmartRefreshLayout smartRefreshLayout;
    private boolean isRefresh = false;

    ParentRecyclerView javaRecyclerView;

    Long lastBackPressedTime = 0L;

    String[] strArray = new String[]{"推荐", "视频", "直播", "图片", "精华", "热门"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_java);

        javaRecyclerView = findViewById(R.id.javaRecyclerView);
        storeSwipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        //smartRefreshLayout = findViewById(R.id.refreshLayout);
        javaRecyclerView.setAdapter(adapter);
        javaRecyclerView.initLayoutManager(this);

        refresh();

        storeSwipeRefreshLayout.setColorSchemeColors(Color.RED);
        storeSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Toast.makeText(MainJavaActivity.this, "数据已刷新", Toast.LENGTH_SHORT).show();
                refresh();
            }
        });
    }


    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - lastBackPressedTime < 2000) {
            super.onBackPressed();
        } else {
            javaRecyclerView.scrollToPosition(0);
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            lastBackPressedTime = System.currentTimeMillis();
        }
    }

    private void refresh() {
        initParentData();
        initChildData();
        CategoryBean categoryBean = new CategoryBean();
        categoryBean.getTabTitleList().clear();
        categoryBean.getTabTitleList().addAll(Arrays.asList(strArray));
        mDataList.add(categoryBean);
        adapter.notifyDataSetChanged();
        storeSwipeRefreshLayout.setRefreshing(false);
        //第一次滑动出现的卡顿问题
//        javaRecyclerView.smoothScrollToPosition(adapter.getItemCount()-1);
//        javaRecyclerView.smoothScrollToPosition(0);
        //自己新增的,初始化进入会吸顶。
        // 也可以直接在父布局中添加android:focusable="true"
        // android:focusableInTouchMode="true"
        //javaRecyclerView.smoothScrollToPosition(0);
    }

    //初始化父类数据
    private void initParentData() {
        mDataList.clear();
        for (int i = 0; i < 7; i++) {
            mDataList.add("parent item text " + i);
        }
    }

    //初始化子类数据
    private void initChildData() {
        mChildDataList.clear();
        mChildFragmentList.clear();
        mChildDataList.addAll(Arrays.asList(strArray));
        for (int i = 0; i < mChildDataList.size(); i++) {
            //mChildDataList.add("child item " + i );
            mChildFragmentList.add(new MyFragment());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        adapter.destroy();
    }
}
