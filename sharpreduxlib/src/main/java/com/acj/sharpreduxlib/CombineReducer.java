package com.acj.sharpreduxlib;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * Created by sharon on 2017/10/30.
 */

public class CombineReducer extends Reducer {


    private HashMap<String, Reducer> reducerList;


    public CombineReducer(List<Reducer> list){
        super("");
        this.reducerList = new HashMap<>();

        for (Reducer reducer : list) {
            if (reducer != null){
                reducerList.put(reducer.getReducerName(), reducer);
            }
        }
    }



    @Override
    public Object reducer(Object data, Action action) {

        //TODO: 需做一次clone
        boolean hasChanged = false;

        if (reducerList == null || reducerList.isEmpty()) return data;

        try {
            Class<?> obj = Class.forName(data.getClass().getName());
            Field[] f = obj.getDeclaredFields();
            for (Field field : f) {
                field.setAccessible(true);
                Object name = field.getName();
                Object value = field.get(data);
                Object result = reducerList.get(name).reducer(value,action);
                if (result == null || result == value) continue;

                field.set(data, result);
                hasChanged = true;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return data;
    }
}
