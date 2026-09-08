package com.versovivo.app.presentation.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.NotificationsActive
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.versovivo.app.domain.model.BibleVerse
import com.versovivo.app.domain.model.UserDevotional
import com.versovivo.app.presentation.viewmodel.DevotionalViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyDevotionalsScreen(
    viewModel: DevotionalViewModel = viewModel(),
    onBack: () -> Unit
) {
    val devotionals = viewModel.devotionals

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Meus Devocionais") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Voltar")
                    }
                }
            )
        }
    ) { padding ->
        if (devotionals.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize().padding(padding), contentAlignment = Alignment.Center) {
                Text("Você ainda não criou nenhum devocional.", color = Color.Gray)
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize().padding(padding),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(devotionals) { devotional ->
                    DevotionalCard(devotional)
                }
            }
        }
    }
}

@Composable
fun DevotionalCard(devotional: UserDevotional) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = devotional.title,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                Row {
                    if (devotional.reminderTime != null) {
                        Icon(
                            Icons.Default.NotificationsActive, 
                            contentDescription = "Lembrete",
                            modifier = Modifier.size(20.dp),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                    IconButton(onClick = { /* Edit */ }) {
                        Icon(Icons.Default.Edit, contentDescription = "Editar", modifier = Modifier.size(20.dp))
                    }
                    IconButton(onClick = { /* Delete */ }) {
                        Icon(Icons.Default.Delete, contentDescription = "Excluir", modifier = Modifier.size(20.dp))
                    }
                }
            }
            
            Text(
                text = "${devotional.theme} • ${devotional.date}",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.primary
            )
            
            if (devotional.reminderTime != null) {
                Text(
                    text = "Lembrete: ${devotional.reminderTime}",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
            
            Spacer(modifier = Modifier.height(8.dp))
            
            devotional.verses.forEach { verse ->
                Text(
                    text = "${verse.book} ${verse.chapter}:${verse.number}",
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}
