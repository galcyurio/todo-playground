package com.github.galcyurio.todo.addedittask

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.github.galcyurio.todo.R
import com.github.galcyurio.todo.databinding.AddEditTaskFragmentBinding
import com.github.galcyurio.todo.domain.SaveTaskUseCase
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddEditTaskFragment : Fragment() {
    private var _binding: AddEditTaskFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: AddEditTaskViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = AddEditTaskFragmentBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    //region Menu
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.add_edit_task_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.menu_save -> {
            viewModel.saveTask()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }
    //endregion

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.saveTaskResult.observe(viewLifecycleOwner, ::onSaveTaskResultChanged)
    }

    private fun onSaveTaskResultChanged(result: SaveTaskUseCase.Result) {
        when (result) {
            SaveTaskUseCase.Result.Success ->
                findNavController().navigateUp()
            SaveTaskUseCase.Result.EmptyTitle ->
                Snackbar.make(requireView(), R.string.type_empty_title, Snackbar.LENGTH_SHORT).show()
        }
    }
}
