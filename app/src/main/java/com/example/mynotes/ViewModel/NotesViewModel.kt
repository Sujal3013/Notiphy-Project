package com.example.mynotes.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.mynotes.Database.NotesDatabase
import com.example.mynotes.Repositery.NotesRepositery
import com.example.mynotes.modelclass.Notes

class NotesViewModel(application: Application) : AndroidViewModel(application) {

    val repositery:NotesRepositery

    init{
        val dao=NotesDatabase.getDatabaseInstance(application).myNotesDao()
        repositery=NotesRepositery(dao)
    }

    fun addNotes(notes: Notes)
    {
        repositery.insertNotes(notes)
    }

    fun getNotes():LiveData<List<Notes>> = repositery.getAllNotes()

    fun geLowNotes():LiveData<List<Notes>>{
        return repositery.getLowNotes()
    }
    fun getHighNotes():LiveData<List<Notes>>{
        return repositery.getHighNotes()
    }
    fun getMedNotes():LiveData<List<Notes>>{
        return repositery.getMedNotes()
    }

    fun deleteNotes(id:Int)
    {
        repositery.deleteNotes(id)
    }

    fun updateNotes(notes:Notes)
    {
        repositery.updateNotes(notes)
    }

}