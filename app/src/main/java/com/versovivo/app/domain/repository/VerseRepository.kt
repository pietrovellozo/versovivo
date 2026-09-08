package com.versovivo.app.domain.repository

import com.versovivo.app.domain.model.Verse

interface VerseRepository {
    suspend fun getVerseOfDay(): Verse
}
