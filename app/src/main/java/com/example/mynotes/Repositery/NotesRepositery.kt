package com.example.mynotes.Repositery

import androidx.lifecycle.LiveData
import com.example.mynotes.dao.NotesDao
import com.example.mynotes.modelclass.Notes

class NotesRepositery(val dao:NotesDao) {

    fun getAllNotes():LiveData<List<Notes>>{
        return dao.getNotes()
    }

    fun getLowNotes():LiveData<List<Notes>>{
        return dao.getLowNotes()
    }
    fun getHighNotes():LiveData<List<Notes>>{
        return dao.getHighNotes()
    }
    fun getMedNotes():LiveData<List<Notes>>{
        return dao.getMedNotes()
    }

    fun insertNotes(notes: Notes)
    {
        dao.insertNotes(notes)
    }

    fun deleteNotes(id:Int)
    {
        dao.deleteNotes(id)
    }

    fun updateNotes(notes:Notes){
        dao.updateNotes(notes)
    }

}

