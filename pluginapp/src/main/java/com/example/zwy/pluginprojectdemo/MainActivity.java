package com.example.zwy.pluginprojectdemo;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.zwy.plugincore.PluginManager;
import com.example.zwy.plugincore.ProxyActivity;

import java.io.File;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PluginManager.getInstance(this);
        setContentView(R.layout.activity_main);
    }

    public void loadPlugin(View view) {
        File file = new File(Environment.getExternalStorageDirectory(),"plugin.apk");
        if(file.exists()){
            PluginManager.getInstance(this).loadApk(file.getAbsolutePath());
        }else {
            Toast.makeText(this,"插件文件不存在",Toast.LENGTH_SHORT).show();
        }
    }

    public void jumpToPlugin(View view) {
        Intent intent = new Intent(this, ProxyActivity.class);
        intent.putExtra("classname",PluginManager.getInstance(this).getPackageInfo().activities[0].name);
        startActivity(intent);
    }
}
