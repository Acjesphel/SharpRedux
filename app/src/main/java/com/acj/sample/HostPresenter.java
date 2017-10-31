package com.acj.sample;

import com.acj.sharpreduxlib.CombineReducer;
import com.acj.sharpreduxlib.Reducer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sharon on 2017/10/30.
 */

public class HostPresenter implements IContract.IPresenter{


    public IContract.IView view;

    public TaskDataActions actions;
    public TaskDataReducer dataReducer;

    public HostPresenter(IContract.IView v){
        this.view = v;
    }

    public void initData(){
        actions = new TaskDataActions();
        dataReducer = new TaskDataReducer("");
    }


}
