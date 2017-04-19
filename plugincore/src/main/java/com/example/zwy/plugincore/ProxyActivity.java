package com.example.zwy.plugincore;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by Zhen Weiyu on 2017/4/19.
 */

public class ProxyActivity extends Activity{

    private String className;//插件apk的activity classname
    private PluginInterface pluginInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        className = intent.getStringExtra("classname");
        launchActivity();
    }

    private void launchActivity(){
        try {
            Class <?> classLoad = PluginManager.getInstance(this).getDexClassLoader().loadClass(className);
            Constructor constructor = classLoad.getConstructor(new Class[]{});
            Object instance = constructor.newInstance(new Object[]{});
            pluginInterface = (PluginInterface)instance;
            pluginInterface.attach(this);
            Bundle bundle = new Bundle();
            pluginInterface.onCreate(bundle);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onStart() {
        pluginInterface.onStart();
        super.onStart();
    }

    @Override
    protected void onResume() {
        pluginInterface.onResume();
        super.onResume();
    }

    @Override
    protected void onPause() {
        pluginInterface.onPause();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        pluginInterface.onDestroy();
        super.onDestroy();
    }




    @Override
    public Resources getResources() {
        return PluginManager.getInstance(this).getResources();
    }
}
