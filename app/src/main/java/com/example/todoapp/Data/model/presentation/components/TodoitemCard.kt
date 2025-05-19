package com.example.todoapp.Data.model.presentation.components

import android.os.Bundle
//noinspection SuspiciousImport
import android.R
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MovableContent
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.modifier.ModifierLocalConsumer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.draw.clip
import com.example.todoapp.Data.model.Todoitem
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.Image
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow

import java.nio.file.WatchEvent

@Composable
fun TodoItemCard(
    todo : Todoitem,
    onCompleteChange: (Boolean) -> Unit,
    onEditClick:(Todoitem) -> Unit,
    onDeleteClick:(Todoitem) -> Unit
) {
    Card (
        modifier = Modifier.fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable{},
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ){
        Row (
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Checkbox(
                modifier = Modifier.padding(end = 16.dp),
                checked = todo .isCompleted,
                onCheckedChange = onCompleteChange
            )
            Column(modifier = Modifier.weight(1f)){

                Text(
                    text = todo.title,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(8.dp))

                Row(verticalAlignment = Alignment.CenterVertically){
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = "Assignee",
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = todo.taskers,
                        style = MaterialTheme.typography.labelSmall
                    )
                }
            }
            Image(painter = painterResource(R.drawable.ic_menu_send),
                contentDescription = null,
                modifier = Modifier.size(48.dp)
                    .clip(CircleShape),
                    contentScale = ContentScale.Crop


            )

        }
        Row (modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically){
            Spacer(modifier = Modifier.weight(1f))
            Button(onClick = {}) {
                Text(text = "Edit")
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(onClick = {onDeleteClick(todo)}) {
                Text(text = "Delete")
            }
        }
    }




                }









@Preview(showBackground = true)
@Composable
fun TodoItemCardPreview(){
TodoItemCard(
    todo = Todoitem(
        id = 1,
        title = "sample todo",
        description = "sample text",
        imageUri = null,
        taskers = "musa",
        isCompleted = false
    ),
    onCompleteChange = {
        isChecked -> println("checked:$isChecked")
    },
    onEditClick = {todo ->
        println("Edit:${todo.title}")
    },
    onDeleteClick = { todo ->
        println("Delete:${todo.title}")
    }
)

}