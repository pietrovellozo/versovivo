package com.versovivo.app.presentation.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EditNote
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun UserScreen(
    onGoToMyDevotionals: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Perfil",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )

        Card(
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Text("Olá, usuário", style = MaterialTheme.typography.titleLarge)
                Text("Versão 1.0 • Membro da comunidade", modifier = Modifier.padding(top = 8.dp))
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = onGoToMyDevotionals,
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.medium
        ) {
            Icon(Icons.Default.EditNote, contentDescription = null)
            Spacer(modifier = Modifier.width(8.dp))
            Text("Meus Devocionais")
        }
    }
}
