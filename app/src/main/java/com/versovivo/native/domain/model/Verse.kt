package com.versovivo.native.domain.model

data class Verse(
    val reference: String,
    val text: String,
    val themes: List<String> = emptyList()
)
