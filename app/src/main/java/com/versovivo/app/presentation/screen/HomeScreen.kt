package com.versovivo.app.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Event
import androidx.compose.material.icons.filled.PlayCircle
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomeScreen(
    onGoToBible: () -> Unit,
    onGoToMusic: () -> Unit,
    onGoToUser: () -> Unit,
    onGoToSettings: () -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentPadding = PaddingValues(bottom = 24.dp)
    ) {
        item {
            HeaderSection()
        }

        item {
            SectionTitle("Sugestões de Versículos")
            VerseSuggestionsRow()
        }

        item {
            SectionTitle("Eventos e Transmissões")
            EventsList()
        }

        item {
            SectionTitle("Devocionais de Parceiros")
            PartnerDevotionals()
        }
    }
}

@Composable
private fun HeaderSection() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
            .background(
                Brush.verticalGradient(
                    listOf(MaterialTheme.colorScheme.primary, MaterialTheme.colorScheme.primaryContainer)
                )
            )
            .padding(24.dp),
        contentAlignment = Alignment.BottomStart
    ) {
        Column {
            Text(
                text = "Bem-vindo ao Verso Vivo",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onPrimary,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Inspiração para o seu dia",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.8f)
            )
        }
    }
}

@Composable
private fun SectionTitle(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleLarge,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(start = 24.dp, top = 24.dp, bottom = 12.dp)
    )
}

@Composable
private fun VerseSuggestionsRow() {
    LazyRow(
        contentPadding = PaddingValues(horizontal = 24.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(listOf(
            "\"O Senhor é o meu pastor...\" - Salmos 23",
            "\"Tudo posso naquele que me fortalece.\" - Filipenses 4:13",
            "\"Porque Deus amou o mundo...\" - João 3:16"
        )) { verse ->
            Card(
                modifier = Modifier.width(280.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
            ) {
                Text(
                    text = verse,
                    modifier = Modifier.padding(20.dp),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}

@Composable
private fun EventsList() {
    Column(
        modifier = Modifier.padding(horizontal = 24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        EventItem("Transmissão ao Vivo: Culto de Domingo", "19:00h", Icons.Default.PlayCircle)
        EventItem("Estudo Bíblico em Grupo", "Terça-feira, 20:00h", Icons.Default.Event)
    }
}

@Composable
private fun EventItem(title: String, time: String, icon: androidx.compose.ui.graphics.vector.ImageVector) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(icon, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(text = title, fontWeight = FontWeight.Bold)
                Text(text = time, style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}

@Composable
private fun PartnerDevotionals() {
    Column(
        modifier = Modifier.padding(horizontal = 24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        repeat(2) { index ->
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Parceiro da Fé #$index",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = "A importância da oração constante na vida do cristão moderno.",
                        modifier = Modifier.padding(top = 4.dp),
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }
        }
    }
}
