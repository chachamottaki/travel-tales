package com.example.traveltales

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.traveltales.viewmodel.LoginViewModel

@Composable
fun HomePage(viewModel: LoginViewModel) {
    val journals by viewModel.journals.collectAsState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Welcome to the Home Page",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        if (journals.isNotEmpty()) {
            journals.forEach { journal ->
                JournalItem(
                    journalId = journal.journal_id,
                    themeId = journal.theme_id,
                    onClick = {
                        // navigate to journal entries :p
                    }
                )
            }
            Button(
                onClick = { /* Navigate to Create Journal Screen */ },
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text("Create Journal")
            }
        } else {
            Text(
                text = "No journals found",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Button(
                onClick = { /* Navigate to Create Journal Screen */ },
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text("Create Journal")
            }
        }
    }
}
