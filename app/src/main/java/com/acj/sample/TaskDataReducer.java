package com.acj.sample;

import com.acj.sharpreduxlib.Action;
import com.acj.sharpreduxlib.Reducer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sharon on 2017/10/30.
 */

public class TaskDataReducer extends Reducer<TaskData> {

    public TaskDataReducer(String name) {
        super(name);
    }

    public List<Task> todoList(List<Task> tasks, Action action) {

        if (tasks == null) tasks = new ArrayList<>();

        switch (action.getType()) {
            case TaskDataActions.ADD_TODO:
                tasks.add((Task) action.getData());
                break;
            case TaskDataActions.COMPLETE_TODO: {
                int index = (int) action.getData();
                if (tasks.get(index) != null) {
                    tasks.get(index).isCompleted = !tasks.get(index).isCompleted;
                }
            }
            break;
            default:
                break;
        }

        return tasks;
    }


    @Override
    public TaskData reducer(TaskData data, Action action) {
        if (data == null) data = new TaskData();
        data.taskList = todoList(data.taskList, action);
        return data;
    }
}
