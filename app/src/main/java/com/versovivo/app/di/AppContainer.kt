package com.versovivo.app.di

import com.versovivo.app.data.repository.VerseRepositoryImpl
import com.versovivo.app.domain.usecase.GetVerseOfDayUseCase
import com.versovivo.app.presentation.viewmodel.VerseViewModelFactory

object AppContainer {
    private val verseRepository by lazy { VerseRepositoryImpl() }
    private val getVerseOfDayUseCase by lazy { GetVerseOfDayUseCase(verseRepository) }

    fun provideVerseViewModelFactory(): VerseViewModelFactory =
        VerseViewModelFactory(getVerseOfDayUseCase)
}
