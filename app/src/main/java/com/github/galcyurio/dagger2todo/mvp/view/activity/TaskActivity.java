package com.github.galcyurio.dagger2todo.mvp.view.activity;

import com.github.galcyurio.dagger2todo.R;
import com.github.galcyurio.dagger2todo.app.TodoApplication;
import com.github.galcyurio.dagger2todo.commons.BusProvider;
import com.github.galcyurio.dagger2todo.commons.Events;
import com.github.galcyurio.dagger2todo.commons.FilterType;
import com.github.galcyurio.dagger2todo.data.model.Task;
import com.github.galcyurio.dagger2todo.di.component.DaggerTaskComponent;
import com.github.galcyurio.dagger2todo.di.module.TaskPresenterModule;
import com.github.galcyurio.dagger2todo.mvp.contract.TaskContract;
import com.github.galcyurio.dagger2todo.mvp.presenter.TaskPresenter;

import java.util.List;

import javax.inject.Inject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;

public class TaskActivity extends Activity implements TaskContract.View {

    @Inject TaskPresenter mPresenter;

    @BindView(R.id.ta_btnAddTask) View mBtnAddTask;

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
    public void appendTasks(List<Task> tasks) {
        // TODO
        Toast.makeText(this, "tasks added!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void clearTasks() {
        // TODO
        Toast.makeText(this, "clear!", Toast.LENGTH_SHORT).show();
    }
}
