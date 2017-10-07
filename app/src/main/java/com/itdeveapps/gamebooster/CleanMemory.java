package com.itdeveapps.gamebooster;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Process;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Omar AlTamimi on 6/3/2017.
 */

public class CleanMemory extends AsyncTask<Void, Void, Void> {
    Context context;
    Callback callback;

    public CleanMemory(Context context, Callback callback) {
        this.callback = callback;
        this.context = context;
    }

    @Override
    protected Void doInBackground(Void... params) {
        final PackageManager pm = context.getPackageManager();
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> pids = am.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo pid : pids) {
            try {
                PackageInfo pkgInfo = pm.getPackageInfo(pid.pkgList[0],
                        PackageManager.GET_ACTIVITIES);
                if (!isSystemPackage(pkgInfo) && !context.getPackageName().equals(pid.processName)) {
                    Toast.makeText(context,""+ pid.processName,Toast.LENGTH_SHORT).show();
                    am.killBackgroundProcesses(pid.processName);
                    Process.killProcess(pid.pid);
                }
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
        }
        //Discard this one
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        callback.onDone();
    }

    public interface Callback {
        void onDone();
    }

    private boolean isSystemPackage(PackageInfo pkgInfo) {
        return (pkgInfo.applicationInfo.flags &
                ApplicationInfo.FLAG_SYSTEM) != 0;
    }
}
