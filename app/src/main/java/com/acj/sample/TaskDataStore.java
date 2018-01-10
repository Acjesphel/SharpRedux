package com.acj.sample;

import com.acj.sharpreduxlib.Store;

/**
 * Created by sharon on 2018/1/10.
 */

public class TaskDataStore {

    private Store<TaskData> store;
    public TaskDataReducer dataReducer;

    private TaskDataStore() {
        dataReducer = new TaskDataReducer("Task");
        store = new Store<>();
        store.createStore(dataReducer, new TaskData(), null);
    }

    private static class SingletonHolder {
        private static TaskDataStore instance = new TaskDataStore();
    }

    public static final TaskDataStore getInstance() {
        return SingletonHolder.instance;
    }

    public Store<TaskData> getStore() {
        return store;
    }
}
