package com.github.galcyurio.freetodo.mvp.adapter;

import com.github.galcyurio.freetodo.R;
import com.github.galcyurio.freetodo.commons.BusProvider;
import com.github.galcyurio.freetodo.commons.Events;
import com.github.galcyurio.freetodo.data.model.Task;
import com.google.common.collect.Lists;

import java.util.List;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author galcyurio
 */
public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {

    private List<Task> mTasks = Lists.newArrayList();

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Task task = mTasks.get(position);
        holder.mChkCompleted.setChecked(task.isCompleted());
        holder.mTxtTitle.setText(task.getTitle());
        holder.mTxtDescription.setText(task.getDescription());
    }

    @Override
    public int getItemCount() {
        return mTasks.size();
    }

    public void clearTasks() {
        mTasks.clear();
    }

    public void addTasks(List<Task> tasks) {
        mTasks.addAll(tasks);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ti_chkCompleted) CheckBox mChkCompleted;
        @BindView(R.id.ti_txtTitle) TextView mTxtTitle;
        @BindView(R.id.ti_txtDescription) TextView mTxtDescription;

        ViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            mChkCompleted.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    Task task = mTasks.get(getLayoutPosition());
                    BusProvider.get().post(new Events.TaskCheckedChangeEvent(task, isChecked));
                }
            });
        }
    }
}
