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
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalNavigationDrawer
import com.example.todoapp.Data.model.presentation.screen.addTodo.AddToDoForm
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.todoapp.Data.model.presentation.components.DrawerContents
import com.google.firebase.auth.FirebaseAuth
import com.example.todoapp.Data.model.presentation.screen.dashboard.DashboardViewModel
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.setValue
import kotlinx.coroutines.launch
import com.example.todoapp.Data.model.Todoitem


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    navcontroller: NavController, viewModel: DashboardViewModel = hiltViewModel()) {
    // fetch our todos from the viewmodel
    val todos by viewModel.todos.collectAsState()
    // fenches from firebase todos
    val firebasetodos by viewModel.firebasetodos.collectAsState()
    // to create a list of composables {listview}
    val showAddingDialog = remember { mutableStateOf(false) }
    // selected to do
    val showEditDialog = remember { mutableStateOf(false) }
    val todoBeingEdited=remember { mutableStateOf<Todoitem?>(null) }
    //drawer state menu
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    //courintine scope: handle configs on device change
    val corountineScope= rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerContent = {
           DrawerContents(onNavigateToHome = {
               navcontroller.navigate("home")
           },
            onLogout = {
                FirebaseAuth.getInstance().signOut()
                navcontroller.navigate("login"){
                    popUpTo("dashboard"){inclusive=true}
                }
            }   )
        }


    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title={Text("Dashboard")},
                    navigationIcon = {
                       IconButton(onClick = {corountineScope
                           .launch { drawerState.open() }
                       }) {
                           Icon(Icons.Default.Menu,
                               contentDescription = "menu")
                       }
                    } )

            },
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
                // use todos variables to load items from rooms
                // firebasetodos variables to load items from firebase
                items(firebasetodos){
                        todo -> TodoItemCard(
                    todo=todo,
                    onCompleteChange = {
                        viewModel.toogleTodoCompletion(todo.id)
                    },
                     onEditClick={
                         todoBeingEdited.value=it
                         showEditDialog.value=true

                     } ,
                     onDeleteClick={
                         viewModel.deleteTodoFromFirebase(it)
                    }
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
       //edit dialog
            if (showEditDialog.value && todoBeingEdited.value !=null){
                AlertDialog(
                    onDismissRequest = {showEditDialog.value=false},
                    title = {Text("Edit todo")},
                    text = {Text("edit your todo")},
                    confirmButton = {}
                )
            }

        }
    }


}


