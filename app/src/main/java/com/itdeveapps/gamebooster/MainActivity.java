package com.itdeveapps.gamebooster;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.itdeveapps.gamebooster.adapter.AppAdapter;
import com.itdeveapps.gamebooster.model.AppInfo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.rvApps)
    RecyclerView appRecyclerView;
    @BindView(R.id.progress)
    ProgressBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


    }

    public void setUpAdapter(List<AppInfo> list) {
        AppAdapter appAdapter = new AppAdapter(list, this, new AppAdapter.ClickListener() {
            @Override
            public void onClick(AppInfo appInfo) {
                new CleanMemory(MainActivity.this, new CleanMemory.Callback() {
                    @Override
                    public void onDone() {
                        Log.d("love","haha");
                    }
                }).execute();
            }
        });
        appRecyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        appRecyclerView.setAdapter(appAdapter);


    }
}
