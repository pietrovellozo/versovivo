package com.versovivo.app.presentation.screen

import androidx.compose.animation.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.versovivo.app.domain.model.BibleBook
import com.versovivo.app.domain.model.BibleVerse
import com.versovivo.app.presentation.viewmodel.BibleViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BibleScreen(
    viewModel: BibleViewModel = viewModel(),
    onCreateDevotional: (List<BibleVerse>) -> Unit
) {
    val currentBook by viewModel.currentBook.collectAsState()
    val currentChapter by viewModel.currentChapter.collectAsState()
    val verses = viewModel.verses
    val books by viewModel.books.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()

    val snackbarHostState = remember { SnackbarHostState() }

    // Mostrar erro no Snackbar se já houver dados na tela
    LaunchedEffect(error) {
        error?.let {
            if (verses.isNotEmpty()) {
                snackbarHostState.showSnackbar(
                    message = it,
                    duration = SnackbarDuration.Short
                )
                viewModel.clearError()
            }
        }
    }

    var showSelector by remember { mutableStateOf(false) }
    var selectorTab by remember { mutableIntStateOf(0) } // 0: Books, 1: Chapters

    val selectedVerses = verses.filter { it.isSelected }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Surface(
                        onClick = { 
                            if (books.isNotEmpty()) {
                                selectorTab = 0
                                showSelector = true 
                            }
                        },
                        color = MaterialTheme.colorScheme.surfaceVariant,
                        shape = MaterialTheme.shapes.medium,
                        enabled = books.isNotEmpty()
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = currentBook?.let { "${it.name} $currentChapter" } ?: "Bíblia",
                                style = MaterialTheme.typography.titleMedium
                            )
                            Icon(Icons.Default.ArrowDropDown, contentDescription = null)
                        }
                    }
                },
                actions = {
                    IconButton(onClick = { viewModel.refresh() }) {
                        Icon(Icons.Default.Refresh, contentDescription = "Recarregar")
                    }
                }
            )
        },
        floatingActionButton = {
            AnimatedVisibility(
                visible = selectedVerses.isNotEmpty(),
                enter = scaleIn() + fadeIn(),
                exit = scaleOut() + fadeOut()
            ) {
                ExtendedFloatingActionButton(
                    onClick = { onCreateDevotional(selectedVerses.toList()) },
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary,
                    icon = { Icon(Icons.Default.Add, contentDescription = null) },
                    text = { Text("Criar Devocional (${selectedVerses.size})") }
                )
            }
        }
    ) { padding ->
        Box(modifier = Modifier.padding(padding).fillMaxSize()) {
            if (isLoading && verses.isEmpty()) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            } else if (error != null && verses.isEmpty()) {
                Column(
                    modifier = Modifier.align(Alignment.Center).padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = error ?: "Erro ao carregar a Bíblia",
                        color = MaterialTheme.colorScheme.error,
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(onClick = { viewModel.refresh() }) {
                        Text("Tentar Novamente")
                    }
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(start = 16.dp, top = 8.dp, end = 16.dp, bottom = 80.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    currentBook?.let { book ->
                        item {
                            Text(
                                text = "${book.name} Capítulo $currentChapter",
                                style = MaterialTheme.typography.labelMedium,
                                color = MaterialTheme.colorScheme.secondary,
                                modifier = Modifier.padding(bottom = 8.dp)
                            )
                        }
                    }
                    items(verses, key = { "${it.book}_${it.chapter}_${it.number}" }) { verse ->
                        VerseItem(
                            verse = verse,
                            onSelectedChange = { viewModel.toggleVerseSelection(verse) }
                        )
                    }
                }
            }

            if (showSelector && currentBook != null) {
                BibleNavigationDialog(
                    books = books,
                    currentBook = currentBook!!,
                    currentChapter = currentChapter,
                    initialTab = selectorTab,
                    onDismiss = { showSelector = false },
                    onSelectBook = { book ->
                        viewModel.selectBook(book)
                        selectorTab = 1
                    },
                    onSelectChapter = { chapter ->
                        viewModel.selectChapter(chapter)
                        showSelector = false
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BibleNavigationDialog(
    books: List<BibleBook>,
    currentBook: BibleBook,
    currentChapter: Int,
    initialTab: Int,
    onDismiss: () -> Unit,
    onSelectBook: (BibleBook) -> Unit,
    onSelectChapter: (Int) -> Unit
) {
    var selectedTab by remember { mutableIntStateOf(initialTab) }

    BasicAlertDialog(
        onDismissRequest = onDismiss,
        modifier = Modifier.fillMaxWidth().padding(16.dp)
    ) {
        Surface(
            shape = MaterialTheme.shapes.large,
            tonalElevation = 6.dp
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = if (selectedTab == 0) "Selecionar Livro" else "Selecionar Capítulo",
                        style = MaterialTheme.typography.titleLarge
                    )
                    IconButton(onClick = onDismiss) {
                        Icon(Icons.Default.Close, contentDescription = "Fechar")
                    }
                }

                TabRow(selectedTabIndex = selectedTab) {
                    Tab(selected = selectedTab == 0, onClick = { selectedTab = 0 }) {
                        Text(text = "Livros", modifier = Modifier.padding(12.dp))
                    }
                    Tab(selected = selectedTab == 1, onClick = { selectedTab = 1 }) {
                        Text(text = "Capítulos", modifier = Modifier.padding(12.dp))
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Box(modifier = Modifier.height(400.dp)) {
                    if (selectedTab == 0) {
                        LazyColumn {
                            items(books) { book ->
                                ListItem(
                                    headlineContent = { Text(book.name) },
                                    trailingContent = { 
                                        if (book.name == currentBook.name) {
                                            Text("Atual", color = MaterialTheme.colorScheme.primary)
                                        }
                                    },
                                    modifier = Modifier.clickable { onSelectBook(book) }
                                )
                            }
                        }
                    } else {
                        LazyVerticalGrid(
                            columns = GridCells.Adaptive(60.dp),
                            contentPadding = PaddingValues(8.dp),
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            items((1..currentBook.chapters).toList()) { chapter ->
                                OutlinedCard(
                                    onClick = { onSelectChapter(chapter) },
                                    colors = CardDefaults.outlinedCardColors(
                                        containerColor = if (chapter == currentChapter) 
                                            MaterialTheme.colorScheme.primaryContainer 
                                        else MaterialTheme.colorScheme.surface
                                    )
                                ) {
                                    Box(
                                        modifier = Modifier.padding(12.dp).fillMaxSize(),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(text = chapter.toString())
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun VerseItem(
    verse: BibleVerse,
    onSelectedChange: (Boolean) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onSelectedChange(!verse.isSelected) },
        colors = CardDefaults.cardColors(
            containerColor = if (verse.isSelected)
                MaterialTheme.colorScheme.primaryContainer
            else
                MaterialTheme.colorScheme.surface
        ),
        border = if (verse.isSelected)
            androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.primary)
        else null
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.Top
        ) {
            Checkbox(
                checked = verse.isSelected,
                onCheckedChange = onSelectedChange
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(
                    text = "${verse.number}",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.ExtraBold
                )
                Text(
                    text = verse.text,
                    style = MaterialTheme.typography.bodyMedium.copy(lineHeight = 22.sp),
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}
