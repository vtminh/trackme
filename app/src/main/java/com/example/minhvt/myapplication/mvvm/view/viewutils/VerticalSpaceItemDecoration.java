package com.example.minhvt.myapplication.mvvm.view.viewutils;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by vtminh1805 on 4/26/2017.
 */

public class VerticalSpaceItemDecoration extends RecyclerView.ItemDecoration {

    private final int verticalSpaceHeight;
    boolean mPaddingLeftRight;


    public VerticalSpaceItemDecoration(int verticalSpaceHeight, boolean isPaddingLeftRight) {
        this.verticalSpaceHeight = verticalSpaceHeight;
        this.mPaddingLeftRight = isPaddingLeftRight;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                               RecyclerView.State state) {

        outRect.bottom = verticalSpaceHeight;

        if(mPaddingLeftRight) {
            outRect.left = verticalSpaceHeight;
            outRect.right = verticalSpaceHeight;
        }
        else {
            outRect.left = 0;
            outRect.right = 0;

        }
    }
}