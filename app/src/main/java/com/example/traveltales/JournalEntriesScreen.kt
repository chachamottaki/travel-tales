package com.example.traveltales

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
fun JournalEntriesScreen(viewModel: LoginViewModel, journalId: Int) {
    val entries by viewModel.entries.collectAsState()
    val journal = viewModel.journals.collectAsState().value.find { it.journal_id == journalId }
    var showDialog by remember { mutableStateOf(false) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        if (journal != null) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            ) {
                Text(
                    text = journal.name,
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Button(
                    onClick = { showDialog = true },
                    modifier = Modifier.padding(top = 8.dp)
                ) {
                    Text("Edit")
                }
            }
        }

        if (entries.isNotEmpty()) {
            entries.forEach { entry ->
                Log.d("JournalEntriesScreen", "Displaying entry: ${entry.entry}")
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Text(
                        text = entry.entry,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        } else {
            Log.d("JournalEntriesScreen", "No entries found")
            Text(
                text = "No entries found",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }
        Button(
            onClick = { /* Navigate to Create Entry Screen */ },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Create Entry")
        }

        if (showDialog) {
            EditJournalDialog(
                journalName = journal?.name ?: "",
                onDismiss = { showDialog = false },
                onConfirm = { newName ->
                    viewModel.editJournalName(journalId, newName)
                    showDialog = false
                }
            )
        }
    }
}
