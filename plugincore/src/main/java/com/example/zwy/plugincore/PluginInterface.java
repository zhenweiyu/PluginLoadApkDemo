package com.example.zwy.plugincore;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;

/**
 * Created by Zhen Weiyu on 2017/4/19.
 */

public interface PluginInterface {

    public void attach(Activity proxyActivity);

    public void onCreate(Bundle saveInstanceState);

    public void onStart();

    public void onResume();

    public void onPause();

    public void onStop();

    public void onDestroy();

    public void onSaveInstanceState(Bundle outSate);

    public boolean onTouchEvent(MotionEvent event);

    public void onBackPressed();


}
