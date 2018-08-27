package com.example.minhvt.myapplication.utils;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * Created by LAP11124-local on 1/19/2017.
 */

public class ActivityUtils {

    public static void addFragmentToActivity(@NonNull FragmentManager fragmentManager,
                                             @NonNull Fragment fragment, int frameId) {
        try {
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.add(frameId, fragment);
            transaction.commitAllowingStateLoss();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void replaceFragmentToActivity(@NonNull FragmentManager fragmentManager,
                                                 @NonNull Fragment fragment, int frameId) {
        try {
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(frameId, fragment);
            transaction.commitAllowingStateLoss();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * minhvt: Fragment ở trong 1 fragment khác. Lưu ý gọi getChildFragmentManger
     * Giải thích lý do sử dụng getChildFragmentManager() mà ko phải getFragmentManager()
     * Khi app bị crash, app sẽ restart lại HomeActivity (vì Home đang nằm trong backstack)
     * Nếu sd getFragmentManager() Khi restart lại, sẽ gặp lỗi "No view found for id" dẫn đến crash tiếp.
     */
    public static void replaceFragmentToFragment(@NonNull Fragment parentFragment,
                                                 @NonNull Fragment childFragment, int frameId, boolean saveState) {
        try {
            if (parentFragment.isAdded()) {
                FragmentManager fragmentManager = parentFragment.getChildFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(frameId, childFragment);
                if (!saveState)
                    transaction.addToBackStack(null);
                transaction.commitAllowingStateLoss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static Fragment getChildFragment(@NonNull Fragment parentFragment, int frameId){
        if(!parentFragment.isAdded()){
            return null;
        }

        FragmentManager fm = parentFragment.getChildFragmentManager();
        return fm.findFragmentById(frameId);
    }

}
