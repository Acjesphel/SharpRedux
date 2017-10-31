package com.acj.sharpreduxlib;


import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sharon on 2017/10/27.
 */

public class Store<T> {

    private Store(){};

    private static class SingletonHolder{
        private static Store instance = new Store();
    }

    public static final Store getInstance(){
        return SingletonHolder.instance;
    }

    private T currentState;
    private Reducer currentReducer;
    private List<BaseListener> listeners;
    private List<BaseListener> nextListeners;
    private boolean isDispatching = false;


    public T getCurrentState() {
        return currentState;
    }

    public synchronized void setDispatching(boolean dispatching) {
        isDispatching = dispatching;
    }

    public synchronized boolean isDispatching() {
        return isDispatching;
    }

    public void createStore(@NonNull Reducer reducer, @Nullable T preLoadState, @Nullable Enhancer enhancer){

        if (preLoadState != null) {
            currentState = preLoadState;
        }

        //TODO: enhance implementation has not completed yet.
        if (enhancer != null) {
            enhancer.enhancerStore(this);
            return;
        }

        currentReducer = reducer;
        listeners = new ArrayList<>();
        nextListeners = listeners;
        setDispatching(false);
    }

    /**
     *  判断是否能执行下一次监听队列
     *  创建当前的副本给nextListeners，防止队列执行被影响
     */
    public void ensureCanMutateNextListener(){
        if (nextListeners == listeners){
            nextListeners = new ArrayList<>(listeners);
        }
    }


    public void subcribe(BaseListener listener){
        if (listener == null){
            throw new Error("ReduxListener shouldn't be null.");
        }
        ensureCanMutateNextListener();
        nextListeners.add(listener);
        listener.setIsSubcribed(true);
    }

    public void unSubscribe(BaseListener listener){
        if (listener == null){
            throw new Error("ReduxListener shouldn't be null.");
        }

        if (!listener.isSubcribed()) return;
        listener.setIsSubcribed(false);
        ensureCanMutateNextListener();
        nextListeners.remove(listener);
    }

    /**
     * 分发消息
     * @param action 非空
     */
    public void dispatch(@NonNull Action action){
        if (action == null || action.getType()==null || action.getType().isEmpty()){
            throw new Error("The action is illegal.");
        }

        if (isDispatching()){
            Log.w("DISPATCH","Last dispatch hasn't been done yet.");
            return;
        }

        try {
            setDispatching(true);
            currentState = (T) currentReducer.reducer(currentState, action);
        } finally {
            setDispatching(false);
        }

        listeners = nextListeners;
        for (BaseListener listener : listeners) {
            listener.onStateChanged();
        }

        return;
    }

    public void replaceReducer(@NonNull Reducer reducerToReplace){
        if (reducerToReplace == null) {
            throw new Error("The reducer to replace can not be null.");
        }

        currentReducer = reducerToReplace;
        dispatch(new Action(Action.ACTION_INIT, null));
    }




}
