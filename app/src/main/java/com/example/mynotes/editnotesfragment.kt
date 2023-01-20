package com.example.mynotes


import android.content.Context
import android.os.Bundle
import android.text.format.DateFormat
import android.util.Log
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.ViewModelProvider

import androidx.navigation.Navigation

import com.example.mynotes.ViewModel.NotesViewModel

import com.example.mynotes.databinding.FragmentEditnotesfragmentBinding
import com.example.mynotes.modelclass.Notes
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.util.*


class editnotesfragment : Fragment() {
    private var _binding: FragmentEditnotesfragmentBinding? = null
    private val binding get() = _binding!!
    lateinit var viewModel: NotesViewModel
    lateinit var id: String
    var priority = "1"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val callback=object:OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                Navigation.findNavController(view!!).navigate(R.id.action_editnotesfragment_to_homefragment)
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)

        viewModel = ViewModelProvider(this).get(NotesViewModel::class.java)
        _binding = FragmentEditnotesfragmentBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)
        val bundle = arguments
        if (bundle == null) {
            Log.d("Confirmation", "Fragment End didn't recieve info")
        }
        val args = editnotesfragmentArgs.fromBundle(bundle!!)
        binding.editT.setText(args.title.toString())
        binding.editSt.setText(args.subtitle.toString())
        binding.editNotes.setText(args.notes.toString())
        id = args.id.toString()
        priority = args.priorities.toString()

        binding.btnEditNotes.setImageResource(R.drawable.done)

        when (args.priorities) {
            "1" -> {
                priority = "1"
                apply {
                    binding.greenDot.elevation= 10F
                    binding.redDot.elevation=0F
                    binding.yellowDot.elevation=0F
                }
                binding.greenDot.setImageResource(R.drawable.done)
                binding.redDot.setImageResource(0)
                binding.yellowDot.setImageResource(0)
            }
            "2" -> {
                priority = "2"
                apply {
                    binding.greenDot.elevation= 0F
                    binding.redDot.elevation=0F
                    binding.yellowDot.elevation=10F
                }
                binding.yellowDot.setImageResource(R.drawable.done)
                binding.redDot.setImageResource(0)
                binding.greenDot.setImageResource(0)


            }
            "3" -> {
                priority = "3"
                apply {
                    binding.greenDot.elevation= 0F
                    binding.redDot.elevation=10F
                    binding.yellowDot.elevation=0F
                }
                binding.yellowDot.setImageResource(0)
                binding.redDot.setImageResource(R.drawable.done)
                binding.greenDot.setImageResource(0)
            }
        }

        binding.greenDot.setOnClickListener {
            priority = "1"
            apply {
                binding.greenDot.elevation= 10F
                binding.redDot.elevation=0F
                binding.yellowDot.elevation=0F
            }
            binding.greenDot.setImageResource(R.drawable.done)
            binding.redDot.setImageResource(0)
            binding.yellowDot.setImageResource(0)
        }
        binding.redDot.setOnClickListener {
            priority = "3"
            apply {
                binding.redDot.elevation= 10F
                binding.greenDot.elevation=0F
                binding.yellowDot.elevation=0F
            }
            binding.redDot.setImageResource(R.drawable.done)
            binding.yellowDot.setImageResource(0)
            binding.greenDot.setImageResource(0)
        }
        binding.yellowDot.setOnClickListener {
            priority = "2"
            apply {
                binding.yellowDot.elevation= 10F
                binding.redDot.elevation=0F
                binding.greenDot.elevation=0F
            }
            binding.yellowDot.setImageResource(R.drawable.done)
            binding.redDot.setImageResource(0)
            binding.greenDot.setImageResource(0)
        }

        binding.editT.setOnClickListener{
            apply {
                binding.editT.elevation=10F
                binding.editSt.elevation=0F
                binding.editNotes.elevation=0F
            }
        }
        binding.editSt.setOnClickListener{
            apply {
                binding.editT.elevation=0F
                binding.editSt.elevation=10F
                binding.editNotes.elevation=0F
            }
        }
        binding.editNotes.setOnClickListener{
            apply {
                apply {
                    binding.editT.elevation=0F
                    binding.editSt.elevation=0F
                    binding.editNotes.elevation=10F
                }
            }
        }

        binding.btnEditNotes.setOnClickListener {
            updateNotes(it)
            apply {
                binding.editT.elevation=0F
                binding.editSt.elevation=0F
                binding.editNotes.elevation=0F
            }
        }

        return binding.root

    }


    private fun updateNotes(it: View?) {
        val title = binding.editT.text.toString()
        val subtitle = binding.editSt.text.toString()
        val notes = binding.editNotes.text.toString()
        val d = Date()
        val notedate: CharSequence = DateFormat.format("MMMM d,yyyy", d.time)

        val data = Notes(id.toInt(), title, subtitle, notes, notedate.toString(), priority)
        viewModel.updateNotes(data)

        Toast.makeText(context, "Notes Updated Successfully", Toast.LENGTH_SHORT).show()

        Navigation.findNavController(it!!).navigate(R.id.action_editnotesfragment_to_homefragment)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.menu_dlt-> {
                val bottomsheet: BottomSheetDialog =
                    BottomSheetDialog(requireContext(), R.style.BottomSheetStyle)
                bottomsheet.setContentView(R.layout.dialogue_delete)
                val textViewYes=bottomsheet.findViewById<TextView>(R.id.dialogueyes)
                val textViewNo=bottomsheet.findViewById<TextView>(R.id.dialogueno)

                if (textViewYes != null) {
                    textViewYes.setOnClickListener{
                        viewModel.deleteNotes(id.toInt())
                        Toast.makeText(context, "Notes deleted Successfully", Toast.LENGTH_SHORT).show()
                        binding.editT.setText("")
                        binding.editSt.setText("")
                        binding.editNotes.setText("")
                        bottomsheet.dismiss()
                        Navigation.findNavController(requireView()).navigate(R.id.action_editnotesfragment_to_homefragment)

                    }
                }
                if (textViewNo != null) {
                    textViewNo.setOnClickListener{
                        bottomsheet.dismiss()

                    }
                }
                bottomsheet.show()
            }
            android.R.id.home->{
                Navigation.findNavController(requireView()).navigate(R.id.action_editnotesfragment_to_homefragment)
            }
        }
        return super.onOptionsItemSelected(item)

    }






}