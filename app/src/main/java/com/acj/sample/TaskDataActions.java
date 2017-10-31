package com.acj.sample;

import com.acj.sharpreduxlib.Action;

import java.util.List;

/**
 * Created by sharon on 2017/10/31.
 */

public class TaskDataActions {

    public final static String ADD_TODO = "ADD_TODO";
    public final static String COMPLETE_TODO = "COMPLETE_TODO";

    public Action<List<Task>> addTodo(List<Task> list){
        return new Action<>(ADD_TODO, list);
    }

    public Action<Boolean> comleteTodo(boolean isComplete) {
        return new Action<>(COMPLETE_TODO, isComplete);
    }
}
