package com.versovivo.native.domain.usecase

import com.versovivo.native.domain.model.Verse
import com.versovivo.native.domain.repository.VerseRepository

class GetVerseOfDayUseCase(
    private val repository: VerseRepository
) {
    suspend operator fun invoke(): Verse = repository.getVerseOfDay()
}
