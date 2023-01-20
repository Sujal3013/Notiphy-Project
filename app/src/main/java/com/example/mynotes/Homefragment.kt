package com.example.mynotes

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.SearchView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.mynotes.ViewModel.NotesViewModel
import com.example.mynotes.databinding.FragmentHomefragmentBinding
import com.example.mynotes.modelclass.Notes


class Homefragment : Fragment() {

    lateinit var binding: FragmentHomefragmentBinding
    lateinit var viewModel: NotesViewModel
    var oldMyNotes = arrayListOf<Notes>()
    lateinit var Notesadapter: adapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragmentR.

        viewModel = ViewModelProvider(this).get(NotesViewModel::class.java)

        binding = FragmentHomefragmentBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)
       val staggeredGridLayoutManager=StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
        staggeredGridLayoutManager.gapStrategy=StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS
        binding.rvallnotes.layoutManager =staggeredGridLayoutManager

        viewModel.getNotes().observe(viewLifecycleOwner, { notesList ->
            oldMyNotes = notesList as ArrayList<Notes>
            Notesadapter = adapter(requireContext(), notesList)
            binding.rvallnotes.adapter = Notesadapter
        })



        binding.highp.setOnClickListener {
            viewModel.getHighNotes().observe(viewLifecycleOwner, { notesList ->
                apply {
                    binding.highp.elevation = 25F
                    binding.lowp.elevation = 0F
                    binding.medp.elevation = 0F
                    binding.filter.elevation=0F
                }
                val staggeredGridLayoutManager=StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
                staggeredGridLayoutManager.gapStrategy=StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS
                binding.rvallnotes.layoutManager =staggeredGridLayoutManager
                oldMyNotes = notesList as ArrayList<Notes>
                Notesadapter = adapter(requireContext(), notesList)
                binding.rvallnotes.adapter = Notesadapter

            })
        }
        binding.medp.setOnClickListener {
            apply {
                binding.medp.elevation = 25F
                binding.lowp.elevation = 0F
                binding.highp.elevation = 0F
                binding.filter.elevation=0F
            }
            val staggeredGridLayoutManager=StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
            staggeredGridLayoutManager.gapStrategy=StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS
            binding.rvallnotes.layoutManager =staggeredGridLayoutManager
            viewModel.getMedNotes().observe(viewLifecycleOwner, { notesList ->
                oldMyNotes = notesList as ArrayList<Notes>
                Notesadapter = adapter(requireContext(), notesList)
                binding.rvallnotes.adapter = Notesadapter

            })
        }
        binding.lowp.setOnClickListener {
            viewModel.geLowNotes().observe(viewLifecycleOwner, { notesList ->
                apply {
                    binding.lowp.elevation = 25F
                    binding.highp.elevation = 0F
                    binding.medp.elevation = 0F
                    binding.filter.elevation=0F
                }
                val staggeredGridLayoutManager=StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
                staggeredGridLayoutManager.gapStrategy=StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS
                binding.rvallnotes.layoutManager =staggeredGridLayoutManager
                oldMyNotes = notesList as ArrayList<Notes>
                Notesadapter = adapter(requireContext(), notesList)
                binding.rvallnotes.adapter = Notesadapter

            })
        }

        binding.filter.setOnClickListener {
            apply {
                binding.highp.elevation = 0F
                binding.lowp.elevation = 0F
                binding.medp.elevation = 0F
                binding.filter.elevation=25F
            }
            val staggeredGridLayoutManager=StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
            staggeredGridLayoutManager.gapStrategy=StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS
            binding.rvallnotes.layoutManager =staggeredGridLayoutManager
            viewModel.getNotes().observe(viewLifecycleOwner, { notesList ->
                oldMyNotes = notesList as ArrayList<Notes>
                Notesadapter = adapter(requireContext(), notesList)
                binding.rvallnotes.adapter = Notesadapter
            })
        }

        binding.btnaddnotes.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(R.id.action_homefragment_to_createnotesfragment)
        }


        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu, menu)

        val item = menu.findItem(R.id.app_search)

        val searchView = item.actionView as SearchView

        searchView.queryHint = "Enter Notes Here..."
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                NotesFilter(newText)
                return false
            }
        })
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun NotesFilter(newText: String?) {
        val newfilteredList = arrayListOf<Notes>()
        for (i in oldMyNotes) {
            if (i.title.contains(newText!!) || i.subTitle.contains(newText!!)) {
                newfilteredList.add(i)
            }
        }
        Notesadapter?.filtering(newfilteredList)

    }


}