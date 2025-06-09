package com.example.dailypulse

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform