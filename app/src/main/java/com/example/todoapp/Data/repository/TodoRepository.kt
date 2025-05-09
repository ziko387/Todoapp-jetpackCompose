package com.example.todoapp.Data.repository
import com.example.todoapp.Data.dao.Todo.TodoDAO
import com.example.todoapp.Data.model.Todoitem
import kotlinx.coroutines.flow.Flow

interface TodoRepository{
    fun getAllTodos(): Flow<List<Todoitem>>
    suspend fun getTodoById(id: Int) : Todoitem?
    suspend fun insertTodo(todo: Todoitem)
    suspend fun updateTodo(todo: Todoitem)
    suspend fun deleteTodo(todo: Todoitem)

}
class TodoRepositoryImpl(private val todoDao: TodoDAO):
        TodoRepository{
    override fun getAllTodos(): Flow<List<Todoitem>> {
        return todoDao.getAllTodos()
    }

    override suspend fun getTodoById(id: Int): Todoitem?{
        return todoDao.getTodoById(id)
    }

    override suspend fun insertTodo(todo: Todoitem) {
        return todoDao.insertTodo(todo)
    }

    override suspend fun updateTodo(todo: Todoitem) {
        return todoDao.updateTodo(todo)
    }

    override suspend fun deleteTodo(todo: Todoitem){
        return todoDao.deleteTodo(todo)
    }

}
