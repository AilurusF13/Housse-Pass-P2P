package fr.ailurus.housepassp2p.ui.components.vault

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import fr.ailurus.housepassp2p.ui.theme.HousePassP2PTheme

@Composable
fun EditEntryDialog(
    onDismiss: () -> Unit,
    onSave: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            Button(onClick = onSave) { Text("Sauvegarder") }
        },
        dismissButton = {
            Button(onClick = onDismiss) { Text("Annuler") }
        },
        title = { Text("Nouvelle Entrée") },
        text = {
            Column {
                // Champs du formulaire (vides pour l'instant)
                TextField(value = "", onValueChange = {}, placeholder = { Text("Titre") })
                TextField(value = "", onValueChange = {}, placeholder = { Text("Login") })
                // Password field omis comme demandé
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun EditEntryDialogPreview() {
    HousePassP2PTheme {
        EditEntryDialog(onDismiss = {}, onSave = {})
    }
}
