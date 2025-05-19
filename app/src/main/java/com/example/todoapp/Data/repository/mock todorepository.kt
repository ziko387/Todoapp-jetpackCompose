package com.example.todoapp.Data.repository

import android.net.Uri
import androidx.compose.runtime.mutableStateOf
import com.example.todoapp.Data.model.Todoitem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class MockTodoRepository : TodoRepository{
    private  val todos = mutableListOf(
        Todoitem(105,"ken","buy stocks","eggs",
            null,"musa",2024,false),
        Todoitem(106,"ken","add cars","toyota",null,"musa",
        2025, false),
        Todoitem(106,"ken","add cars","issuzu",null,"musa",
            2025, false)
    )
    private val nextId= 3
    override fun getAllTodos(): Flow<List<Todoitem>> {
        return flowOf(todos.toList())
    }

    override fun fenchtodosfromFirebase(): Flow<List<Todoitem>> {
        return flowOf(todos.toList())
    }
    override suspend fun getTodoById(id: Int): Todoitem? {
        return todos.find{it.id == id}
    }

    override suspend fun insertTodo(todo: Todoitem) {
          todos.add(todo.copy(id = nextId))
    }

    override suspend fun updateTodo(todo: Todoitem) {
        todos.removeIf {it.id == todo.id}
    }

    override suspend fun deleteTodo(todo: Todoitem) {
        val index =todos.indexOfFirst { it.id == todo.id}
        if (index!=1){
           todos[index] = todo
        }
    }

    override suspend fun uploadToFirebase(todo: Todoitem) {
        // upload process
    }

    override suspend fun uploadImageToFirebase(imageUri: Uri): String {
        // mock url
        return "mock_url"
    }

    override suspend fun deleteTodoFirebase(todo: Todoitem) {
        //
    }

    override suspend fun updateTodoFirebase(todo: Todoitem) {
        //
    }


}