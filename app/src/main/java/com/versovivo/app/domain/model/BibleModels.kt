package com.versovivo.app.domain.model

data class BibleBook(
    val name: String,
    val abbreviation: String,
    val chapters: Int
)

data class BibleVerse(
    val book: String,
    val chapter: Int,
    val number: Int,
    val text: String,
    var isSelected: Boolean = false
)

data class UserDevotional(
    val id: String,
    val title: String,
    val theme: String,
    val date: String,
    val verses: List<BibleVerse>,
    val notes: String = "",
    val reminderTime: String? = null
)
