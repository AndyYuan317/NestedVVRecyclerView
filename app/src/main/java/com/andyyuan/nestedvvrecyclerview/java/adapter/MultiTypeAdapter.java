package com.andyyuan.nestedvvrecyclerview.java.adapter;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.andyyuan.nestedvvrecyclerview.R;
import com.andyyuan.nestedvvrecyclerview.java.myview.MyCategoryViewHolder;
import com.andyyuan.nestedvvrecyclerview.java.view.ChildRecyclerView;
import com.andyyuan.nestedvvrecyclerview.java.viewholder.SimpleFristViewHolder;
import com.andyyuan.nestedvvrecyclerview.java.viewholder.SimpleTextViewHolder;

import java.util.ArrayList;

/**
 * author : AndyYuan
 * date   : 2020/12/9 000915:02
 * desc   : 主界面中的ParentAdapter 子item都放在该层中显示，子recyclerView放在最后一个item中
 * version: 2.0
 */
public class MultiTypeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<Object> mDataList;
    private ArrayList<String> mChildDataList;

    public static final int TYPE_TEXT = 0;
    public static final int TYPE_CATEGORY = 1;
    public static final int TYPE_FIRST = 3;

    MyCategoryViewHolder mCategoryViewHolder;
    //主界面传递过来的FragmentManager
    private FragmentManager fragmentManager;

    public MultiTypeAdapter(ArrayList<Object> dataList, ArrayList<String> childData, FragmentManager fragmentManager) {
        mDataList = dataList;
        mChildDataList = childData;
        this.fragmentManager = fragmentManager;
    }

    @Override
    public int getItemViewType(int position) {
        if (mDataList.get(position) instanceof String) {
            if (position == 0) {
                return TYPE_FIRST;
            }
            return TYPE_TEXT;
        } else {
            return TYPE_CATEGORY;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        if (viewType == TYPE_FIRST) {
            return new SimpleFristViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_item_first_view_holer
                    , viewGroup, false));
        } else if (viewType == TYPE_TEXT) {
            return new SimpleTextViewHolder(LayoutInflater.from(
                    viewGroup.getContext()
            ).inflate(R.layout.layout_item_text, viewGroup, false));
        } else {
//            SimpleCategoryViewHolder simpleCategoryViewHolder =
//                    new SimpleCategoryViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(
//                            R.layout.layout_item_category_default,
//                            viewGroup,
//                            false
//                    ));
//            mCategoryViewHolder = simpleCategoryViewHolder;
//            return simpleCategoryViewHolder;
            //自己替换Fragment的ViewPager
            MyCategoryViewHolder simpleCategoryViewHolder =
                    new MyCategoryViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(
                            R.layout.layout_item_category_holder,
                            viewGroup,
                            false
                    ), fragmentManager, mChildDataList);
            mCategoryViewHolder = simpleCategoryViewHolder;
            return simpleCategoryViewHolder;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int pos) {
        if (viewHolder instanceof SimpleTextViewHolder) {
//            Log.d("gaohui","pos " + pos + mDataList.get(pos) + mDataList.get(pos));
            ((SimpleTextViewHolder) viewHolder).mTv.setText((String) mDataList.get(pos));
        } else if (viewHolder instanceof MyCategoryViewHolder) {
            //((SimpleCategoryViewHolder)viewHolder).bindData(mDataList.get(pos));
            ((MyCategoryViewHolder) viewHolder).bindData();
        }
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public ChildRecyclerView getCurrentChildRecyclerView() {
        if (mCategoryViewHolder != null) {
            return mCategoryViewHolder.getCurrentChildRecyclerView();
        }
        return null;
    }

    public void destroy() {
        if (mCategoryViewHolder != null) {
            //mCategoryViewHolder.destroy();
        }

    }
}
