package com.versovivo.app.domain.model

data class Verse(
    val reference: String,
    val text: String,
    val themes: List<String> = emptyList()
)
