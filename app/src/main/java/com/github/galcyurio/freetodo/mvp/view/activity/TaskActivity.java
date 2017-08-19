package com.github.galcyurio.freetodo.mvp.view.activity;

import com.github.galcyurio.freetodo.R;
import com.github.galcyurio.freetodo.app.BaseActivity;
import com.github.galcyurio.freetodo.app.TodoApplication;
import com.github.galcyurio.freetodo.commons.BusProvider;
import com.github.galcyurio.freetodo.commons.Events;
import com.github.galcyurio.freetodo.commons.FilterType;
import com.github.galcyurio.freetodo.data.model.Task;
import com.github.galcyurio.freetodo.di.component.DaggerTaskComponent;
import com.github.galcyurio.freetodo.di.module.TaskPresenterModule;
import com.github.galcyurio.freetodo.mvp.adapter.TaskAdapter;
import com.github.galcyurio.freetodo.mvp.contract.TaskContract;
import com.github.galcyurio.freetodo.mvp.presenter.TaskPresenter;
import com.google.common.collect.Lists;

import java.util.List;

import javax.inject.Inject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import butterknife.BindView;
import butterknife.ButterKnife;

public class TaskActivity extends BaseActivity implements TaskContract.View {

    @Inject TaskPresenter mPresenter;
    @Inject TaskAdapter mTaskAdapter;

    @BindView(R.id.ta_btnAddTask) View mBtnAddTask;
    @BindView(R.id.ta_recyclerTasks) RecyclerView mRecyclerTasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
        ButterKnife.bind(this);
        bindEvents();

        DaggerTaskComponent.builder()
                .applicationComponent(TodoApplication.get(this).getApplicationComponent())
                .taskPresenterModule(new TaskPresenterModule(this))
                .build()
                .inject(this);

        mRecyclerTasks.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerTasks.setAdapter(mTaskAdapter);

        mPresenter.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.task, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_filter:
                BusProvider.get().post(new Events.FilterBtnClickEvent());
                break;
            case R.id.menu_refresh:
                BusProvider.get().post(new Events.RefreshMenuClickEvent());
                break;
        }
        return true;
    }

    // ~ MVP =====================================================================

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.registerBus();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mPresenter.unregisterBus();
    }

    @Override
    public void bindEvents() {
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.ta_btnAddTask:
                        BusProvider.get().post(new Events.AddTaskBtnClickEvent());
                        break;
                }
            }
        };

        mBtnAddTask.setOnClickListener(onClickListener);
    }

    @Override
    public void bindEvents(Object view) {
        if(view instanceof PopupMenu) {
            ((PopupMenu) view).setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {
                    switch (menuItem.getItemId()) {
                        case R.id.menu_filter_all:
                            BusProvider.get().post(new Events.FilterPopupClickEvent(FilterType.ALL));
                            break;
                        case R.id.menu_filter_active:
                            BusProvider.get().post(new Events.FilterPopupClickEvent(FilterType.ACTIVE));
                            break;
                        case R.id.menu_filter_completed:
                            BusProvider.get().post(new Events.FilterPopupClickEvent(FilterType.COMPLETED));
                            break;
                    }
                    return false;
                }
            });
        }
    }

    @Override
    public void showAddTaskUi() {
        startActivity(new Intent(this, AddTaskActivity.class));
    }

    @Override
    public void showFilterPopupUi() {
        PopupMenu popupMenu = new PopupMenu(this, findViewById(R.id.menu_filter));
        popupMenu.getMenuInflater().inflate(R.menu.filter_list, popupMenu.getMenu());
        bindEvents(popupMenu);
        popupMenu.show();
    }

    @Override
    public void addTasks(List<Task> tasks) {
        mTaskAdapter.addTasks(tasks);
        mTaskAdapter.notifyDataSetChanged();
    }

    @Override
    public void addTask(Task task) {
        mTaskAdapter.addTasks(Lists.newArrayList(task));
        mTaskAdapter.notifyDataSetChanged();
    }

    @Override
    public void clearTasks() {
        mTaskAdapter.clearTasks();
        mTaskAdapter.notifyDataSetChanged();
    }
}
