package com.example.todoapp.Data.model.presentation.components

import android.content.Context
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.CheckboxDefaults.colors
import androidx.compose.material3.TextButton
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.auth.ktx.auth

@Composable
fun SignUpPage(navController: NavController){
    //variables to store and reference inputs
    val context= LocalContext.current//declares the current processing Activity
    var email by remember{ mutableStateOf("") }
    var username by remember{ mutableStateOf("") }
    var password by remember{ mutableStateOf("") }
    var confirmPassword by remember{ mutableStateOf("") }
    var error by remember{ mutableStateOf<String?>(null) }

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
                    label = {Text("email")},
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
           // error is populated on the condition that an error is encountered
            // the error variable will be populated within a text composable
            // showing the error
                error?.let {
                    Text(it, color = MaterialTheme.colorScheme.error)
                }
                Spacer(modifier = Modifier.height(22.dp))

                Button(onClick = {
                    if(password!=confirmPassword){
                        error="passwords do not match"
                    }
                    else{
                        registerUser(email,password,context,navController,onError={errorMsg-> error =errorMsg})
                    }
                }, modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red)) {

                    Text(
                        text = "signup",
                        fontSize = 27.sp
                    )
                }
                Spacer(modifier = Modifier.height(22.dp))
                // navigate login // navigate to login
                TextButton(onClick = { navController.navigate("login") }) {
                    Text(
                        text = "already have an account, login"
                    )
                }
            }
        }
    }
}

private fun registerUser(
    email: String,
    password: String,
    context: Context,
    navController: NavController,
    onError: (String) -> Unit
) {
    Firebase.auth.createUserWithEmailAndPassword(email, password)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                // navigate to the dashboard
                navController.navigate("login")
            } else {
            onError(task.exception?.message ?: "Registration failed")
            }

        }
}

@Preview(showBackground = true)
@Composable
fun SignUpPagePreview(){
    SignUpPage(rememberNavController())
}