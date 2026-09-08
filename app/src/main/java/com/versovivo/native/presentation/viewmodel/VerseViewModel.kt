package com.versovivo.native.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.versovivo.native.domain.usecase.GetVerseOfDayUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class VerseUiState(
    val reference: String = "",
    val text: String = "",
    val isLoading: Boolean = false,
    val error: String? = null
)

class VerseViewModel(
    private val getVerseOfDayUseCase: GetVerseOfDayUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(VerseUiState(isLoading = true))
    val uiState: StateFlow<VerseUiState> = _uiState.asStateFlow()

    init {
        refresh()
    }

    fun refresh() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)

            runCatching {
                val verse = getVerseOfDayUseCase()
                _uiState.value = VerseUiState(
                    reference = verse.reference,
                    text = verse.text,
                    isLoading = false
                )
            }.onFailure {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = it.message ?: "Erro ao carregar o versículo do dia."
                )
            }
        }
    }
}

class VerseViewModelFactory(
    private val getVerseOfDayUseCase: GetVerseOfDayUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(VerseViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return VerseViewModel(getVerseOfDayUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
