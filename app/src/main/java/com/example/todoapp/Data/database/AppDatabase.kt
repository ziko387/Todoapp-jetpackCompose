package com.example.todoapp.Data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.todoapp.Data.dao.Todo.TodoDAO
import com.example.todoapp.Data.model.Todoitem


// add annotations  database to mark this class as the database migration
// layer
@Database(
  entities = [Todoitem::class],
    version = 2,
    exportSchema = false
)
abstract  class  AppDatabase : RoomDatabase(){
    // define an abstract function for the database functions
    abstract fun  todoDAO()  : TodoDAO
    //add seed data
}