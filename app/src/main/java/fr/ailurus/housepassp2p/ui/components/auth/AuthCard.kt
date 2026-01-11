package fr.ailurus.housepassp2p.ui.components.auth

import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import fr.ailurus.housepassp2p.AppDimensions
import fr.ailurus.housepassp2p.BordersConfig.ShapeMedium

@Composable
fun AuthCard(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
){
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(
            defaultElevation = AppDimensions.ElevationMedium
        ),
        shape = ShapeMedium,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerHighest
        )
    ){
        content()
    }
}