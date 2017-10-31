package com.acj.sample;

import com.acj.sharpreduxlib.Action;
import com.acj.sharpreduxlib.Reducer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sharon on 2017/10/30.
 */

public class TaskDataReducer extends Reducer<List<Task>> {

    public TaskDataReducer(String name) {
        super(name);
    }

    @Override
    public List<Task> reducer(List<Task> data, Action<List<Task>> action) {
        //初始化data
        if (data == null) data = new ArrayList<>();

        if (action.getType().equals(TaskDataActions.ADD_TODO)){
            data = action.getData();
        }

        return data;
    }
}
