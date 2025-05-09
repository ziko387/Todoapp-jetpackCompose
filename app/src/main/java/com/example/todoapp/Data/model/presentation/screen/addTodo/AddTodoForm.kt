package com.example.todoapp.Data.model.presentation.screen.addTodo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.room.util.TableInfo
import com.example.todoapp.Data.model.presentation.screen.dashboard.DashboardViewModel
import com.example.todoapp.Data.repository.MockTodoRepository
import com.example.todoapp.Data.repository.TodoRepository
import java.nio.file.WatchEvent

@Composable
fun AddToDoForm(
    viewModel: DashboardViewModel,
    onDismiss:() -> Unit
){
    // variable to save inputs
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var taskers by remember { mutableStateOf("") }

    Column (modifier = Modifier.padding(16.dp).fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally){
        Text(
            text = "Add To Do",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        OutlinedTextField(
            value = title,
            onValueChange = {title=it},
            label = {Text("Title")},
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )
        OutlinedTextField(
            value = description,
            onValueChange = {description=it},
            label = {Text("Description")},
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )
        OutlinedTextField(
            value = taskers,
            onValueChange = {taskers=it},
            label = {Text("Taskers")},
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )
        Row (modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween){
            Button(onClick = onDismiss,
                colors = ButtonDefaults.buttonColors()) {
                Text("cancel")
            }
            Button(onClick = {
                if(title.isNotBlank()){
                viewModel.addToDo(title,description,taskers)
                    onDismiss()
                }
            }, enabled = title.isNotBlank()) {
                Text("Add to do")
            }
        }



    }

}
@Preview(showBackground = true)
@Composable
fun AddToDoFormPreview(){
    val view_Model= DashboardViewModel(MockTodoRepository())
    AddToDoForm(viewModel =view_Model, onDismiss = {})
}
