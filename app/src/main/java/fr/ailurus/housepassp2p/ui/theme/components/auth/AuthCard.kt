package fr.ailurus.housepassp2p.ui.theme.components.auth

import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import fr.ailurus.housepassp2p.ui.theme.BordersConfig.ShapeMedium

@Composable
fun AuthCard(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
){
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerLow
        ),
        shape = ShapeMedium
    ){
        content()
    }
}