package com.versovivo.app.presentation.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.versovivo.app.domain.model.BibleVerse
import com.versovivo.app.domain.model.UserDevotional
import java.util.UUID

class DevotionalViewModel : ViewModel() {
    private val _devotionals = mutableStateListOf<UserDevotional>()
    val devotionals: List<UserDevotional> = _devotionals

    fun addDevotional(
        title: String,
        theme: String,
        date: String,
        verses: List<BibleVerse>,
        notes: String,
        reminderTime: String? = null
    ) {
        val newDevotional = UserDevotional(
            id = UUID.randomUUID().toString(),
            title = title,
            theme = theme,
            date = date,
            verses = verses,
            notes = notes,
            reminderTime = reminderTime
        )
        _devotionals.add(newDevotional)
    }
}
