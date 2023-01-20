package com.example.mynotes

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.mynotes.databinding.ItemNotesBinding
import com.example.mynotes.modelclass.Notes

class adapter( val requireContext: Context,var notesList: List<Notes>) : RecyclerView.Adapter<adapter.notesViewHolder>() {

    fun filtering(newfilteredList: ArrayList<Notes>) {
        notesList=newfilteredList
        notifyDataSetChanged()
    }

    class notesViewHolder(val binding: ItemNotesBinding) : RecyclerView.ViewHolder(binding.root) {

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): notesViewHolder {
        return notesViewHolder(
            ItemNotesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: notesViewHolder, position: Int) {
        val data=notesList[position]
        holder.binding.itemtitle.text=data.title
        holder.binding.itemsubtitle.text=data.subTitle
        holder.binding.itemnote.text=data.notes
        holder.binding.notedate.text=data.dates

        when(data.priority){
            "1"->{
                holder.binding.priority.setBackgroundResource(R.drawable.dot_image)
            }
            "2"->{
                holder.binding.priority.setBackgroundResource(R.drawable.yellow_dot)

            }
            "3"->{
                holder.binding.priority.setBackgroundResource(R.drawable.red_dot)
            }
        }
        holder.binding.root.setOnClickListener{

            val action =HomefragmentDirections.actionHomefragmentToEditnotesfragment(priorities = data.priority,id = data.id!!,title=data.title, subtitle = data.subTitle, notes = data.notes, dates = data.dates)
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount()= notesList.size
}