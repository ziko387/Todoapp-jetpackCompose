package com.example.todoapp.Data.model.presentation.screen.dashboard

import androidx.compose.runtime.State
import androidx.lifecycle.viewModelScope
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.todoapp.Data.model.Todoitem
import com.example.todoapp.Data.repository.TodoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.WhileSubscribed
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.NonCancellable.isCompleted
import kotlinx.coroutines.internal.OpDescriptor
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(private val repository: TodoRepository
): ViewModel() {

//private val _todos= mutableStateOf(mockToDO)
    //val todos= _todos.asStateFlow()

    val todos : StateFlow<List<Todoitem>> =repository.getAllTodos()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun toogleTodoCompletion(todoId: Int){

        viewModelScope.launch {
            val todo = repository.getTodoById(todoId) ?: return@launch
            val updateTodo=todo.copy(isCompleted=!todo.isCompleted)
            repository.updateTodo(updateTodo)
        }

    }
    // function to add data
    fun addToDo(title: String, description: String,taskers: String){
        viewModelScope.launch {
            val newTodo = Todoitem(
                id=0,
                title=title,
                description=description,
                imageuri = null,
                taskers = taskers,
                isCompleted = false
            )
            repository.insertTodo(newTodo)
        }
    }

}



