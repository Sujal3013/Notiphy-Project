package com.example.mynotes

import android.os.Bundle
import android.text.format.DateFormat
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation

import com.example.mynotes.ViewModel.NotesViewModel
import com.example.mynotes.databinding.FragmentCreatenotesfragmentBinding
import com.example.mynotes.modelclass.Notes
import java.util.*


class Createnotesfragment : Fragment() {

    lateinit var binding:FragmentCreatenotesfragmentBinding
    var priority:String="1"
    lateinit var viewModel:NotesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val callback=object:OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                 Navigation.findNavController(view!!).navigate(R.id.action_createnotesfragment_to_homefragment)
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)
        // Inflate the layout for this fragment
        viewModel=ViewModelProvider(this).get(NotesViewModel::class.java)

        binding=FragmentCreatenotesfragmentBinding.inflate(layoutInflater,container,false)

        binding.btnSaveNotes.setOnClickListener{
            createNotes(it)
        }

        binding.btnSaveNotes.setImageResource(R.drawable.done)

        binding.edittile.setOnClickListener{
            apply {
                binding.edittile.elevation=10F
                binding.editsubtitle.elevation=0F
                binding.editnote.elevation=0F
            }
        }
        binding.editsubtitle.setOnClickListener{
            apply {
                binding.edittile.elevation=0F
                binding.editsubtitle.elevation=10F
                binding.editnote.elevation=0F
            }
        }
        binding.editnote.setOnClickListener{
            apply {
                binding.edittile.elevation=0F
                binding.editsubtitle.elevation=0F
                binding.editnote.elevation=10F
            }
        }
        binding.greenDot.setImageResource(R.drawable.done)

        binding.greenDot.setOnClickListener{
            priority="1"
            apply {
                binding.greenDot.elevation= 10F
                binding.redDot.elevation=0F
                binding.yellowDot.elevation=0F
            }
            binding.greenDot.setImageResource(R.drawable.done)
            binding.redDot.setImageResource(0)
            binding.yellowDot.setImageResource(0)
        }
        binding.redDot.setOnClickListener{
            priority="2"
            apply {
                binding.redDot.elevation= 10F
                binding.greenDot.elevation=0F
                binding.yellowDot.elevation=0F
            }

            binding.redDot.setImageResource(R.drawable.done)
            binding.yellowDot.setImageResource(0)
            binding.greenDot.setImageResource(0)
        }
        binding.yellowDot.setOnClickListener{
            priority="3"
            apply {
                binding.yellowDot.elevation= 10F
                binding.redDot.elevation=0F
                binding.greenDot.elevation=0F
            }
            binding.yellowDot.setImageResource(R.drawable.done)
            binding.redDot.setImageResource(0)
            binding.greenDot.setImageResource(0)
        }

        return binding.root

    }

    private fun createNotes(it: View?) {
        val title=binding.edittile.text.toString()
        val subtitle=binding.editsubtitle.text.toString()
        var notes=binding.editnote.text.toString()
        val d=Date()
        val notedate:CharSequence= DateFormat.format("MMMM d,yyyy",d.time)

        if(title!="" && subtitle!==""){
            if(notes==""){
                notes="Blank note"
            }
            val data=Notes(null,title,subtitle,notes, notedate.toString(),priority)
            viewModel.addNotes(data)

            Toast.makeText(context,"Notes Created Successfully",Toast.LENGTH_SHORT).show()

            Navigation.findNavController(it!!).navigate(R.id.action_createnotesfragment_to_homefragment)

        }
        else{
            if(title=="" && subtitle==""){
                Toast.makeText(context,"Title and subtitle of notes are mandatory",Toast.LENGTH_SHORT).show()
            }
            else if(title=="" && subtitle!=""){
                Toast.makeText(context,"Title of notes is mandatory. ",Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(context,"SubTitle of notes is mandatory. ",Toast.LENGTH_SHORT).show()

            }

        }

    }
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        when(item.itemId){
//            android.R.id.home->{
//                Navigation.findNavController(requireView()).navigate(R.id.action_createnotesfragment_to_homefragment)
//            }
//        }
//        return super.onOptionsItemSelected(item)
//    }





}