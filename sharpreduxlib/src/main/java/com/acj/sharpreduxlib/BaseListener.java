package com.acj.sharpreduxlib;

/**
 * Created by sharon on 2017/10/29.
 */

public abstract class BaseListener {

    private boolean isSubcribed = false;

    public void setIsSubcribed(boolean isSubcribed) {
        isSubcribed = isSubcribed;
    }

    public boolean isSubcribed() {
        return isSubcribed;
    }

    public abstract void onStateChanged();
}
