package com.versovivo.app.data.repository

import com.versovivo.app.data.remote.ApiClient
import com.versovivo.app.domain.model.Verse
import com.versovivo.app.domain.repository.VerseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class VerseRepositoryImpl : VerseRepository {
    private val api by lazy { ApiClient.create() }

    override suspend fun getVerseOfDay(): Verse = withContext(Dispatchers.IO) {
        api.getVerseOfDay()
    }
}
