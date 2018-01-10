package com.acj.sample;

/**
 * Created by sharon on 2017/10/30.
 */

public class IContract {

    public interface IView{
        void onDataChanged(TaskData data);
    }

    public interface IPresenter{
        void addTask();

        void setTaskComplete(int pos);
    }
}
