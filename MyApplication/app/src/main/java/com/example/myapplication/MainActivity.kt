package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                ProfileScreen()
            }
        }
    }
}

@Composable
fun ProfileScreen(viewModel:ProfileViewModel=viewModel()) {
    Column(
        modifier=Modifier.fillMaxSize(),
        horizontalAlignment=Alignment.CenterHorizontally,
        verticalArrangement=Arrangement.Center
    ){

        OutlinedTextField(
            value=viewModel.username.value,
            onValueChange={ new->
                viewModel.orNameChange(new)
            }
        )
        Spacer(modifier=Modifier.height(8.dp))
        OutlinedTextField(
            value=viewModel.userAge.value,
            onValueChange={new->
                viewModel.orAgeChange(new)
            }
        )
        Spacer(modifier=Modifier.height(8.dp))
        Button(onClick={
viewModel.addUser()

        }){
            Text("Add")
        }
        Text("Hello ${viewModel.username.value},you are ${viewModel.userAge.value} years old")
        LazyColumn(modifier=Modifier.weight(1f)){
            items(viewModel.list){item->
                Card(
                    modifier=Modifier.padding(8.dp).fillMaxWidth(),
                    elevation=CardDefaults.cardElevation(8.dp)
                ){
                    Text(
                        text=item,
                        modifier=Modifier.padding(16.dp))

                }
            }
        }
    }

}
class ProfileViewModel:ViewModel(){
    val username=mutableStateOf("")
    val userAge=mutableStateOf("")
    val list=mutableStateListOf<String>()
    fun addUser(){
        list.add("${username.value} - ${userAge.value}")
    username.value=""
        userAge.value=""
    }
    fun orNameChange(newName:String){
        username.value=newName
    }
    fun orAgeChange(newAge:String){
        userAge.value=newAge
    }

}