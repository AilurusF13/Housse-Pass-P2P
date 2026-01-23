package fr.ailurus.housepassp2p.ui.components.vault

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.DeleteOutline
import androidx.compose.material.icons.filled.Groups3
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import fr.ailurus.housepassp2p.ui.viewmodels.EditorStateView

@Composable
fun EditEntryDialog(
    editorStateView: EditorStateView,
    onDismiss: () -> Unit,
) {
    val state = editorStateView.editorState ?: return

    val title = if (editorStateView.editorState == null) "New entry" else "Edit entry"
    var groupExtended by remember { mutableStateOf(false) }

    var showConfirmDelete by remember { mutableStateOf(false) }

    if (showConfirmDelete){
        ConfirmDeleteDialog(
            title = "Delete entry",
            message = "Are you sure you want to delete this entry?",
            onDismiss = {
                showConfirmDelete = false
            },
            onConfirm = {
                editorStateView.onDelete()
                showConfirmDelete = false
            }
        )
    }

    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            Button(onClick = { editorStateView.onSave() }) {
                Icon(Icons.Default.Save, "Save")
            }
        },
        dismissButton = {
            if (editorStateView.editorState != null){
                Button(onClick = { showConfirmDelete = true }) {
                    Icon(Icons.Default.Delete, "Delete")
                }
            }
        },
        title = { Text(title) },
        text = {
            Column {
                EditField(
                    // Check why its required
                    value = state.site,
                    onValueChange = { editorStateView.updateSite(it) },
                    placeholder = "site"
                )
                EditField(
                    value = state.login,
                    onValueChange = { editorStateView.updateLogin(it) },
                    placeholder = "login"
                )
                EditField(
                    value = editorStateView.password,
                    onValueChange = { editorStateView.updatePassword(it) },
                    placeholder = "password",
                    visualTransformation = PasswordVisualTransformation()
                )

                Box (
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ){
                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {
                            groupExtended = !groupExtended
                        }
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween
                        ){
                            Text(text = state.group.name)
                        }
                    }
                    DropdownMenu(
                        expanded = groupExtended,
                        onDismissRequest = { groupExtended = false }
                    ) {
                        for (g in editorStateView.groups) {
                            DropdownMenuItem(
                                text = { Text(text = g.name) },
                                onClick = {
                                    editorStateView.updateGroup(g)
                                    groupExtended = false
                                }
                            )
                        }
                    }
                }
            }
        }
    )
}

@Composable
fun ConfirmDeleteDialog(
    title: String,
    message: String,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
){
    AlertDialog(
        title = { Text(text = title) },
        text = { Text(text = message) },
        confirmButton = {
            Button(onClick = onConfirm) {
                Text(text = "Confirm")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text(text = "Cancel")
            }
        },
        onDismissRequest = onDismiss,
    )
}