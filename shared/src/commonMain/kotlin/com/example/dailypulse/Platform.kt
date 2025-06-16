package com.example.dailypulse


/**
 *  expect declares a common API, and actual provides platform-specific implementations for it.
 * */

expect class Platform {
    val osName: String
    val osVersion: String
    val deviceModel: String
    val density: Int

    fun logSystemInfo()
}
