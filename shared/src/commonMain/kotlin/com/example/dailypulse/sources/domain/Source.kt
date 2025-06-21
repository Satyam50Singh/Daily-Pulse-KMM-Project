package com.example.dailypulse.sources.domain

data class Source(
    val id: String,
    val name: String,
    val desc: String,
    val url: String,
    val category: String,
    val lang: String,
    val country: String
)