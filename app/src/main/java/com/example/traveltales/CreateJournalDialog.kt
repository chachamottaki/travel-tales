package com.example.traveltales

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CreateJournalDialog(
    onDismiss: () -> Unit,
    onConfirm: (String) -> Unit
) {
    var journalName by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = "Create Journal") },
        text = {
            Column {
                OutlinedTextField(
                    value = journalName,
                    onValueChange = { journalName = it },
                    label = { Text(text = "Journal Name") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    onConfirm(journalName)
                    onDismiss()
                },
                modifier = Modifier.padding(8.dp)
            ) {
                Text("Create")
            }
        },
        dismissButton = {
            Button(
                onClick = onDismiss,
                modifier = Modifier.padding(8.dp)
            ) {
                Text("Cancel")
            }
        }
    )
}
