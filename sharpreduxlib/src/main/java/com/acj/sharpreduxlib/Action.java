package com.acj.sharpreduxlib;

/**
 * Created by sharon on 2017/10/27.
 */

public class Action<T> {

    public final static String ACTION_INIT = "INIT";

    private String type;
    private T data;

    public Action(String type, T data){
        this.type = type;
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public T getData() {
        return data;
    }

}
