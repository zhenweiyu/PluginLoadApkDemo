package com.example.zwy.plugincore;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;

/**
 * Created by Zhen Weiyu on 2017/4/19.
 */

public class PluginManager {

    private static volatile PluginManager pluginManager;

    private Context context;

    private DexClassLoader dexClassLoader;

    private Resources resources;

    private PackageInfo packageInfo;

    private PluginManager(Context context){
        this.context = context;
    }

    public static PluginManager getInstance(Context context){
        if(pluginManager==null){
            synchronized (PluginManager.class){
                if(pluginManager==null){
                    pluginManager = new PluginManager(context);
                }
            }
        }
        return pluginManager;
    }

    public static PluginManager getInstance() throws Exception {
        if(pluginManager==null){
            throw new Exception("必须初始化第一个参数");
        }
        return pluginManager;
    }


    public void loadApk(String path){

        File dexFile = context.getDir("dex",Context.MODE_PRIVATE);
        dexClassLoader = new DexClassLoader(path,dexFile.getAbsolutePath(),null,context.getClassLoader());
        //实例化Resource
        try {
            AssetManager assetManager = AssetManager.class.newInstance();
            Method oldAddAssetPath = AssetManager.class.getMethod("addAssetPath",String.class);
            oldAddAssetPath.invoke(assetManager,path);
            Resources superResouces  = context.getResources();
            resources = new Resources(assetManager,superResouces.getDisplayMetrics(),superResouces.getConfiguration());
            packageInfo = context.getPackageManager().getPackageArchiveInfo(path, PackageManager.GET_ACTIVITIES);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public Resources getResources() {
        return resources;
    }

    public DexClassLoader getDexClassLoader() {
        return dexClassLoader;
    }

    public PackageInfo getPackageInfo() {
        return packageInfo;
    }
}
