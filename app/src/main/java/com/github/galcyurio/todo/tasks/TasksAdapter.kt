package com.github.galcyurio.todo.tasks

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.galcyurio.todo.databinding.ItemTaskBinding
import com.github.galcyurio.todo.domain.TaskEntity
import com.github.galcyurio.todo.tasks.TasksAdapter.DataHolder
import com.github.galcyurio.todo.tasks.TasksAdapter.DataHolder.TaskDataHolder
import com.github.galcyurio.todo.tasks.TasksAdapter.ViewHolder
import com.github.galcyurio.todo.tasks.TasksAdapter.ViewHolder.TaskViewHolder

class TasksAdapter : ListAdapter<DataHolder, ViewHolder>(DiffCallback) {
    //region Adapter implementation
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (ViewType.values()[viewType]) {
            ViewType.Task ->
                TaskViewHolder(ItemTaskBinding.inflate(inflater, parent, false))
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (val dataHolder = getItem(position)) {
            is TaskDataHolder ->
                (holder as TaskViewHolder).bind(dataHolder)
        }.let { /* exhaustive */ }
    }

    override fun getItemViewType(position: Int): Int = when (getItem(position)) {
        is TaskDataHolder -> ViewType.Task
    }.ordinal
    //endregion

    //region Public APIs
    fun submit(tasks: List<TaskEntity>) {
        val dataHolders = tasks.map(::TaskDataHolder)
        submitList(dataHolders)
    }
    //endregion

    //region ViewHolder, DataHolder
    object DiffCallback : DiffUtil.ItemCallback<DataHolder>() {
        override fun areItemsTheSame(oldItem: DataHolder, newItem: DataHolder): Boolean {
            if (oldItem::class != newItem::class) return false
            return when (oldItem) {
                is TaskDataHolder ->
                    oldItem.task.id == (newItem as TaskDataHolder).task.id
            }
        }

        override fun areContentsTheSame(oldItem: DataHolder, newItem: DataHolder): Boolean {
            return when (oldItem) {
                is TaskDataHolder ->
                    oldItem == newItem
            }
        }
    }

    enum class ViewType {
        Task
    }

    sealed class DataHolder {
        data class TaskDataHolder(val task: TaskEntity) : DataHolder()
    }

    sealed class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        class TaskViewHolder(private val binding: ItemTaskBinding) : ViewHolder(binding.root) {
            fun bind(dataHolder: TaskDataHolder) {
                binding.task = dataHolder.task
            }
        }
    }
    //endregion
}