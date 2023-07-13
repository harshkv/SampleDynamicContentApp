package com.example.sampledynamiccontentapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen(viewModel = viewModel)
        }
    }
}


@Composable
fun MainScreen(viewModel: MainViewModel) {
    val newNameStateContent = viewModel.textFieldState.observeAsState("")

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        GreetingMessage(
            newNameStateContent.value
        ) { newName -> viewModel.onTextChanged(newText = newName) }
    }
}

@Composable
fun GreetingMessage(
    textFieldValue: String,
    textFieldUpdate: (newName: String) -> Unit
) {

    TextField(value = textFieldValue, onValueChange = textFieldUpdate)
    Button(onClick = { }) {
        Text(textFieldValue)
    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier,
        style = MaterialTheme.typography.h5
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
//    MainScreen(viewModel)
}