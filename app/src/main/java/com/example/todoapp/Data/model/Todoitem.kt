package com.example.todoapp.Data.model


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todos")
  data class Todoitem (
    @PrimaryKey(autoGenerate = true) val id: Int=0,
    val title: String,
    val description : String,
    val imageuri: String?,
    val taskers: String,
    val createdAt: Long= System.currentTimeMillis(), // captures time
    val isCompleted: Boolean = false

)
