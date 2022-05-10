package com.example.a160419034_todoapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Todo(
    @ColumnInfo(name = "title")
    var title: String,
    @ColumnInfo(name = "notes")
    var note:String,
    @ColumnInfo(name = "is_done")
    var is_done:Int,
    @ColumnInfo(name = "priority")
    var priority: Int
)
    {
    @PrimaryKey(autoGenerate = true)
    var uuid: Int = 0
}