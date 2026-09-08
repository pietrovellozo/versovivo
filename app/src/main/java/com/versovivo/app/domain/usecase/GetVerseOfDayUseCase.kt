package com.versovivo.app.domain.usecase

import com.versovivo.app.domain.model.Verse
import com.versovivo.app.domain.repository.VerseRepository

class GetVerseOfDayUseCase(
    private val repository: VerseRepository
) {
    suspend operator fun invoke(): Verse = repository.getVerseOfDay()
}
