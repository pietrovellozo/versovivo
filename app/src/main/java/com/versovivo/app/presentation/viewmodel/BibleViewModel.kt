package com.versovivo.app.presentation.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.versovivo.app.data.remote.ApiClient
import com.versovivo.app.data.remote.BibleApiService
import com.versovivo.app.domain.model.BibleBook
import com.versovivo.app.domain.model.BibleVerse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class BibleViewModel : ViewModel() {
    private val bibleApi = ApiClient.createService(BibleApiService::class.java)

    private val _books = MutableStateFlow<List<BibleBook>>(emptyList())
    val books = _books.asStateFlow()

    private val _currentBook = MutableStateFlow<BibleBook?>(null)
    val currentBook = _currentBook.asStateFlow()

    private val _currentChapter = MutableStateFlow(1)
    val currentChapter = _currentChapter.asStateFlow()

    private val _verses = mutableStateListOf<BibleVerse>()
    val verses: List<BibleVerse> = _verses

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error = _error.asStateFlow()

    init {
        loadBooks()
    }

    fun refresh() {
        if (_books.value.isEmpty()) {
            loadBooks()
        } else {
            loadVerses()
        }
    }

    fun clearError() {
        _error.value = null
    }

    private fun loadBooks() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                val response = bibleApi.getBooks()
                val domainBooks = response.map { 
                    BibleBook(it.name, it.abbrev.pt, it.chapters)
                }
                _books.value = domainBooks
                if (domainBooks.isNotEmpty()) {
                    _currentBook.value = domainBooks.first()
                    loadVerses()
                }
            } catch (e: Exception) {
                _error.value = "Erro ao carregar livros: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun selectBook(book: BibleBook) {
        _currentBook.value = book
        _currentChapter.value = 1
        loadVerses()
    }

    fun selectChapter(chapter: Int) {
        _currentChapter.value = chapter
        loadVerses()
    }

    fun toggleVerseSelection(verse: BibleVerse) {
        val index = _verses.indexOfFirst { it.number == verse.number }
        if (index != -1) {
            _verses[index] = _verses[index].copy(isSelected = !verse.isSelected)
        }
    }

    private fun loadVerses() {
        val book = _currentBook.value ?: return
        val chapter = _currentChapter.value

        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                val response = bibleApi.getChapterVerses(book.abbreviation, chapter)
                _verses.clear()
                val domainVerses = response.verses.map { 
                    BibleVerse(book.name, chapter, it.number, it.text)
                }
                _verses.addAll(domainVerses)
            } catch (e: Exception) {
                _error.value = "Erro ao carregar versículos: ${e.message}"
                _verses.clear()
            } finally {
                _isLoading.value = false
            }
        }
    }
}
