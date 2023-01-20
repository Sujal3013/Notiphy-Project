package com.example.mynotes.modelclass

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "Notes")
data class Notes (
    @PrimaryKey(autoGenerate = true)
    var id:Int? =null,
    var title:String,
    var subTitle:String,
    var notes:String,
    var dates:String,
    var priority:String
):Parcelable