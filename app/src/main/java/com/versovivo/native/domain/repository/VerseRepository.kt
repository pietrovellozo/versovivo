package com.versovivo.native.domain.repository

import com.versovivo.native.domain.model.Verse

interface VerseRepository {
    suspend fun getVerseOfDay(): Verse
}
