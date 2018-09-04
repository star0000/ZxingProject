package com.moyu.zxingproject;

import android.app.Application;

import com.uuzuche.lib_zxing.activity.ZXingLibrary;

/**
 * Created by 墨羽 on 2018/6/4.
 */

public class ZxingApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        ZXingLibrary.initDisplayOpinion(this);
    }
}
