package com.acj.sample;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class HostActivity extends AppCompatActivity implements IContract.IView{

    private ListView taskListView;
    private List<Task> taskList;
    private TaskListAdapter taskListAdapter;

    private IContract.IPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        initView();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Task task = new Task("newTask");

            }
        });

        presenter = new HostPresenter(this);

    }


    private void initView(){
        taskListView = (ListView) findViewById(R.id.task_listview);
        taskList = new ArrayList<>();
        taskListAdapter = new TaskListAdapter(this, taskList);
        taskListView.setAdapter(taskListAdapter);
    }

    @Override
    public void setPresenter(IContract.IPresenter presenter) {
        this.presenter = presenter;
    }


    private class TaskListAdapter extends BaseAdapter{

        private List<Task> tasks;
        private Context context;

        public TaskListAdapter(Context context, List<Task> list){
            this.context = context;
            this.tasks = list;
        }

        public List<Task> getTasks() {
            return tasks;
        }

        @Override
        public int getCount() {
            return tasks.size();
        }

        @Override
        public Object getItem(int position) {
            return tasks.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder viewHolder;
            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.layout_item, parent);
                viewHolder = new ViewHolder();
                viewHolder.taskTxtView = (TextView) convertView.findViewById(R.id.task_name_txt);
                viewHolder.taskCheckBox = (CheckBox) convertView.findViewById(R.id.task_complete_checkbox);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            viewHolder.taskTxtView.setText(tasks.get(position).taskName);
            viewHolder.taskCheckBox.setChecked(tasks.get(position).isCompleted);
            return convertView;
        }

        // 自定义的容器类（相当于一个Item），其中放置着需要我们放置数据的控件的名称
        private class ViewHolder {
            TextView taskTxtView;
            CheckBox taskCheckBox;
        }
    }

}
