package ru.sikuda.mobile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.jetbrains.compose.ui.tooling.preview.Preview
import kotlin.random.Random

@Composable
@Preview
fun App() {

    var textClickButton by remember { mutableStateOf( "" ) }
    val randomGenerator = Random
    val viewModel = TimeTextModel()
    val timeText by viewModel.timeText.collectAsState()

    MaterialTheme {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primaryContainer)
                .safeContentPadding()
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Button(onClick = {
                textClickButton = "${randomGenerator.nextDouble()}"
            }) {
                Text(text = "Click-${textClickButton}")
            }
            Box(Modifier.fillMaxHeight(0.2F))
            Text(
                text = timeText,
                //modifier = modifier
            )
            Button(
                onClick = { viewModel.calcAsync() }) {
                Text(text = "Count Async")
            }
            Button(
                onClick = { viewModel.calcSync()  }) {
                Text(text = "Count Sync")
            }
        }
    }
}