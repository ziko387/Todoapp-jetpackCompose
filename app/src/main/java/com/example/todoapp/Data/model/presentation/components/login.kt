package com.example.todoapp.Data.model.presentation.components

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
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.platform.LocalContext
import androidx.compose.material3.MaterialTheme
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

@Composable
fun LoginScreen(navController: NavController){
   val  context= LocalContext.current
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var error by remember{ mutableStateOf<String?>(null) }


    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Card(modifier = Modifier.fillMaxWidth().padding(24.dp)
            , shape = RoundedCornerShape(17.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 22.dp)) {

            Text(
                text = "login",
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
                value = password,
                onValueChange = {password=it},
                label = {Text("password")},
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(22.dp))

            error?.let {
                Text(it, color = MaterialTheme.colorScheme.error)
            }

            Button(onClick = {
                if (email.isBlank() || password.isBlank()){
                    error="username and password required"
                }else {

                    Firebase.auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                navController.navigate("dashboard")
                            } else {
                                error = task.exception?.message ?: "Login failed"

                            }
                        }
                }
                }, modifier = Modifier.fillMaxWidth(), colors =
                ButtonDefaults.buttonColors(containerColor = Color.Red)) {

                Text(
                    text = "login",
                    fontSize = 27.sp
                )
            }


            Spacer(modifier = Modifier.height(22.dp))

            TextButton(onClick = {navController.navigate("signup")}){
                Text(
                    text = "new member? register"
                )
            }

        }
    }
}
@Preview(showBackground = true)
@Composable
fun LoginScreenPreview(){
    LoginScreen(rememberNavController())
}