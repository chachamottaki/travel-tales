package com.example.traveltales

import android.util.Log
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.traveltales.viewmodel.LoginViewModel

@Composable
fun HomePage(viewModel: LoginViewModel, onJournalClick: (Int) -> Unit) {
    val journals by viewModel.journals.collectAsState()
    var showDialog by remember { mutableStateOf(false) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Create or Edit a Journal",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        if (journals.isNotEmpty()) {
            journals.forEach { journal ->
                JournalItem(
                    name = journal.name,
                    journalId = journal.journal_id,
                    themeId = journal.theme_id,
                    onClick = {
                        Log.d("HomePage", "Journal clicked: ${journal.journal_id}")
                        onJournalClick(journal.journal_id)
                    }
                )
            }
        } else {
            Text(
                text = "No journals found",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }
        Button(
            onClick = { showDialog = true },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Create Journal")
        }
        if (showDialog) {
            CreateJournalDialog(
                onDismiss = { showDialog = false },
                onConfirm = { journalName ->
                    viewModel.createJournal(journalName)
                    viewModel.fetchUserJournals(viewModel.userToken.value ?: "") // Fetch journals after creation
                    showDialog = false
                }
            )
        }
    }
}
