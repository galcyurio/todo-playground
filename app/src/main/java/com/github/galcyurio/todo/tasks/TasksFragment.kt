package com.github.galcyurio.todo.tasks

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.github.galcyurio.todo.R
import com.github.galcyurio.todo.databinding.TasksFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TasksFragment : Fragment() {
    private var _binding: TasksFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: TasksViewModel by viewModels()
    private val adapter = TasksAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = TasksFragmentBinding.inflate(inflater, container, false)
        binding.recyclerView.adapter = adapter
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.tasks.observe(viewLifecycleOwner, adapter::submit)
    }

    //region Menu
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.tasks_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.menu_add -> {
            val action = TasksFragmentDirections.actionTasksFragmentToAddEditTaskFragment()
            findNavController().navigate(action)
            true
        }
        else -> false
    }
    //endregion
}
