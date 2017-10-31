package com.acj.sharpreduxlib;

/**
 * Created by sharon on 2017/10/27.
 */

public abstract class Reducer<T> {

    private String reducerName;

    public Reducer(String name){
        this.reducerName = name;
    }

    public abstract T reducer(T data, Action<T> action);

    public String getReducerName() {
        return reducerName;
    }
}
