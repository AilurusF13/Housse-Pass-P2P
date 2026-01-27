package fr.ailurus.housepassp2p.ui.components.group

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun GroupFields(
    modifier: Modifier = Modifier,

    name: String,
    onNameChange: (String) -> Unit,
    description: String,
    onDescriptionChange: (String) -> Unit,
) {
    Column(
        modifier = modifier
    ) {
        GroupFieldUnique(
            name = "Name",
            value = name,
            onValueChange = onNameChange,
            description = "Name your group...",
            lineNumber = 1
        )
        GroupFieldUnique(
            name = "Description",
            value = description,
            onValueChange = onDescriptionChange,
            description = "Describe your groups",
            lineNumber = 5
        )
    }
}