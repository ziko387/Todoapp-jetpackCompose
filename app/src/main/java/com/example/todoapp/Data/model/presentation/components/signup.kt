package com.example.todoapp.Data.model.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun SignUpPage(){
    var email by remember{ mutableStateOf("") }
    var username by remember{ mutableStateOf("") }
    var password by remember{ mutableStateOf("") }
    var confirmPassword by remember{ mutableStateOf("") }

    Card(
        modifier = Modifier.padding(24.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 20.dp),
        shape = RoundedCornerShape(25.dp)


    ) {
        Column (
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Card (modifier = Modifier.fillMaxWidth().padding(4.dp)
            , shape = RoundedCornerShape(17.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 22.dp)){

                Text(
                    text = "signup",
                    fontSize = 42.sp

                )
                Spacer(modifier = Modifier.height(6.dp))

                OutlinedTextField(
                    value = email,
                    onValueChange = {email=it},
                    label = {Text("username")},
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(22.dp))

                OutlinedTextField(
                    value = username,
                    onValueChange = {username=it},
                    label = {Text("Username")},
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(22.dp))

                OutlinedTextField(
                    value = password,
                    onValueChange = {password=it},
                    label = {Text("password")},
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(22.dp))

                OutlinedTextField(
                    value = confirmPassword,
                    onValueChange = {confirmPassword=it},
                    label = {Text("confirm password")},
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(22.dp))

                Button(onClick = {}, modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red)) {

                    Text(
                        text = "signup",
                        fontSize = 27.sp
                    )
                }
                Spacer(modifier = Modifier.height(22.dp))

                Text(
                    text = " have an account? login",
                    fontSize = 27.sp
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignUpPagePreview(){
    SignUpPage()
}