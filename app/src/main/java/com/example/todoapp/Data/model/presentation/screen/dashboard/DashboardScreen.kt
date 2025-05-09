package com.example.todoapp.Data.model.presentation.screen.dashboard

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todoapp.Data.model.presentation.components.TodoItemCard
import android.app.AlertDialog
import androidx.compose.material3.AlertDialog
import com.example.todoapp.Data.model.presentation.screen.addTodo.AddToDoForm
import androidx.compose.material3.Text
import androidx.hilt.navigation.compose.hiltViewModel


@Composable
fun DashboardScreen(viewModel: DashboardViewModel = hiltViewModel()) {
    // fetch our todos from the viewmodel
    val todos by viewModel.todos.collectAsState()
    // to create a list of composables {listview}
    val showAddingDialog = remember { mutableStateOf(false) }
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showAddingDialog.value = true }) {
                Icon(
                    Icons.Default.Add,
                    contentDescription = "add todo"
                )
            }
        }
    ) { padding ->

        LazyColumn (modifier = Modifier.padding(padding)) {
            items(todos){
                todo -> TodoItemCard(
                 todo=todo,
                    onCompleteChange = {viewModel.toogleTodoCompletion(todo.id)}
            )

            }
        }
  if (showAddingDialog.value){
   //
      //
      AlertDialog(
          onDismissRequest={showAddingDialog.value=false},
          title={Text("add todo")},
          text = {
              AddToDoForm(viewModel = viewModel,
                  onDismiss = {showAddingDialog.value=false})
          },
          confirmButton = {},
          dismissButton = {}
      )

}


    }
}
