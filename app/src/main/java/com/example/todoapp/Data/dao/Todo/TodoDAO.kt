package com.example.todoapp.Data.dao.Todo

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.todoapp.Data.model.Todoitem
import kotlinx.coroutines.flow.Flow

// annotate the interface as a DAO interface from  the room lib
@Dao
interface TodoDAO {
    // method of our crud operations
    // annotation from android room
    @Query("SELECT * FROM todos")
    fun getAllTodos(): Flow<List<Todoitem>>
    @Query("SELECT * FROM todos WHERE id=:id")
    suspend fun getTodoById(id: Int): Todoitem
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun  insertTodo(todo: Todoitem)

    @Update
    suspend fun updateTodo(todo: Todoitem)
    @Delete
    suspend fun deleteTodo(todo: Todoitem)
}