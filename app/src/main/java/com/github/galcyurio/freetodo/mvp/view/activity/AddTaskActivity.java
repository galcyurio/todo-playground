package com.github.galcyurio.freetodo.mvp.view.activity;

import com.github.galcyurio.freetodo.R;
import com.github.galcyurio.freetodo.app.BaseActivity;
import com.github.galcyurio.freetodo.app.TodoApplication;
import com.github.galcyurio.freetodo.commons.BusProvider;
import com.github.galcyurio.freetodo.commons.Events;
import com.github.galcyurio.freetodo.di.component.DaggerAddTaskComponent;
import com.github.galcyurio.freetodo.di.module.AddTaskPresenterModule;
import com.github.galcyurio.freetodo.mvp.contract.AddTaskContract;

import javax.inject.Inject;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author galcyurio
 */
public class AddTaskActivity extends BaseActivity implements AddTaskContract.View {

    @Inject AddTaskContract.Presenter mPresenter;

    @BindView(R.id.ata_btnWrite) View mBtnWrite;
    @BindView(R.id.ata_eTxtTitle) EditText mETxtTitle;
    @BindView(R.id.ata_eTxtDescription) EditText mETxtDescription;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        ButterKnife.bind(this);
        bindEvents();

        DaggerAddTaskComponent.builder()
                .applicationComponent(TodoApplication.get(this).getApplicationComponent())
                .addTaskPresenterModule(new AddTaskPresenterModule(this))
                .build().inject(this);

        ActionBar actionBar = checkNotNull(getActionBar());
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(R.string.string_new_task);
    }

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
                    case R.id.ata_btnWrite:
                        String title = mETxtTitle.getText().toString();
                        String description = mETxtDescription.getText().toString();
                        BusProvider.get().post(new Events.WriteBtnClickEvent(title, description));
                        break;
                }
            }
        };

        mBtnWrite.setOnClickListener(onClickListener);
    }

    @Override
    public void showNotSavedMessage() {
        Toast.makeText(this, R.string.string_not_saved, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showSuccessMessage() {
        Toast.makeText(this, R.string.string_success_save_task, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showFailedMessage() {
        Toast.makeText(this, R.string.string_fail_save_task, Toast.LENGTH_SHORT).show();
    }
    
    @Override
    public void showTaskListUi() {
        finish();
    }

}
