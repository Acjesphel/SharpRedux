package com.acj.sample;

import com.acj.sharpreduxlib.Action;
import com.acj.sharpreduxlib.BaseListener;
import com.acj.sharpreduxlib.CombineReducer;
import com.acj.sharpreduxlib.Reducer;
import com.acj.sharpreduxlib.Store;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sharon on 2017/10/30.
 */

public class HostPresenter extends BaseListener implements IContract.IPresenter {


    public IContract.IView view;


    public HostPresenter(IContract.IView v){
        this.view = v;
        initData();
    }

    public void initData(){
        TaskDataStore.getInstance().getStore().subcribe(this);
    }

    public void addTask() {
        Task task = new Task("newTask");
        TaskDataStore.getInstance().getStore().dispatch(new Action(TaskDataActions.ADD_TODO, task));
    }

    @Override
    public void setTaskComplete(int pos) {
        TaskDataStore.getInstance().getStore().dispatch(new Action(TaskDataActions.COMPLETE_TODO, pos));
    }


    @Override
    public void onStateChanged() {
        view.onDataChanged(TaskDataStore.getInstance().getStore().getCurrentState());
    }


}
