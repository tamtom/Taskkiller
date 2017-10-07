package com.itdeveapps.gamebooster.model;
/**
 * Created by Omar AlTamimi on 6/4/2017.
 */

import android.graphics.drawable.Drawable;

public class AppInfo {
    private String packageName;
    private String name;
    private Drawable icon;

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public Drawable getIcon() {
        return icon;
    }
}
