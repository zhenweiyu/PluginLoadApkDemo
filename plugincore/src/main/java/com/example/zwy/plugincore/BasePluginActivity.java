package com.example.zwy.plugincore;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Zhen Weiyu on 2017/4/19.
 */

public class BasePluginActivity extends Activity implements PluginInterface{


    protected Activity that;

    @Override
    public void attach(Activity proxyActivity) {
        that = proxyActivity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onStart() {

    }


    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void setContentView(View view) {
       that.setContentView(view);
    }

    @Override
    public void setContentView(int layoutResID) {
        that.setContentView(layoutResID);
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        that.setContentView(view, params);
    }


    @Override
    public View findViewById(int id) {
        return that.findViewById(id);
    }

    @Override
    public ClassLoader getClassLoader() {
        return that.getClassLoader();
    }

    @Override
    public Intent getIntent() {
        return that.getIntent();
    }

    @Override
    public Resources getResources() {
        return that.getResources();
    }


}
