package com.versovivo.app.presentation.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.versovivo.app.domain.model.BibleVerse
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateDevotionalScreen(
    selectedVerses: List<BibleVerse>,
    onSave: (String, String, String, String?) -> Unit,
    onBack: () -> Unit
) {
    var title by remember { mutableStateOf("") }
    var theme by remember { mutableStateOf("") }
    var notes by remember { mutableStateOf("") }
    var hasReminder by remember { mutableStateOf(false) }
    var reminderTime by remember { mutableStateOf("08:00") }
    
    val currentDate = remember { 
        SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date()) 
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Novo Devocional") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Voltar")
                    }
                }
            )
        },
        bottomBar = {
            Surface(shadowElevation = 8.dp) {
                Button(
                    onClick = { 
                        onSave(title, theme, currentDate, if (hasReminder) reminderTime else null) 
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    enabled = title.isNotBlank() && theme.isNotBlank()
                ) {
                    Text("Finalizar Criação")
                }
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentPadding = PaddingValues(24.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            item {
                Text(
                    text = "Informações do Devocional",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
            }

            item {
                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("Nome do Devocional") },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = { Text("Ex: Reflexão sobre a Fé") }
                )
            }

            item {
                OutlinedTextField(
                    value = theme,
                    onValueChange = { theme = it },
                    label = { Text("Tema") },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = { Text("Ex: Esperança, Amor, Paciência") }
                )
            }
            
            item {
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
                    )
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.Notifications, contentDescription = null)
                            Spacer(modifier = Modifier.width(12.dp))
                            Column {
                                Text("Lembrete Diário", fontWeight = FontWeight.Medium)
                                Text("Notificação para leitura", style = MaterialTheme.typography.bodySmall)
                            }
                        }
                        Switch(checked = hasReminder, onCheckedChange = { hasReminder = it })
                    }
                    
                    if (hasReminder) {
                        HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp))
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text("Horário", style = MaterialTheme.typography.bodyMedium)
                            TextButton(onClick = { /* Abrir TimePicker */ }) {
                                Icon(Icons.Default.Timer, contentDescription = null)
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(reminderTime)
                            }
                        }
                    }
                }
            }

            item {
                Text(
                    text = "Versículos Selecionados (${selectedVerses.size})",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }

            items(selectedVerses) { verse ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(
                            text = "${verse.book} ${verse.chapter}:${verse.number}",
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Text(
                            text = verse.text,
                            style = MaterialTheme.typography.bodySmall,
                            lineHeight = 18.sp
                        )
                    }
                }
            }
        }
    }
}
