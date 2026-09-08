package com.versovivo.native.di

import com.versovivo.native.data.repository.VerseRepositoryImpl
import com.versovivo.native.domain.usecase.GetVerseOfDayUseCase
import com.versovivo.native.presentation.viewmodel.VerseViewModelFactory

object AppContainer {
    private val verseRepository by lazy { VerseRepositoryImpl() }
    private val getVerseOfDayUseCase by lazy { GetVerseOfDayUseCase(verseRepository) }

    fun provideVerseViewModelFactory(): VerseViewModelFactory =
        VerseViewModelFactory(getVerseOfDayUseCase)
}
